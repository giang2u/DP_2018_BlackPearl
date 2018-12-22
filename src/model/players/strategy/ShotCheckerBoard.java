package model.players.strategy;

import model.players.Player;
import model.ship.Ship;

import java.lang.management.PlatformLoggingMXBean;

public class ShotCheckerBoard implements StrategyShot {

    private int xDebut = (int) (Math.random() * Player.SIZE);
    private int yDebut = (int) (Math.random() * Player.SIZE);
    private int xAttack;
    private int yAttack;
    private int[][] positions;
    private int[] direction;

    private int gauche = 0, droite = 1, haut = 2, bas = 3, directionActu = gauche;
    private int xActu,yActu;

    public ShotCheckerBoard() {
        direction = new int[4];
        positions = new int[Player.SIZE][Player.SIZE];
        for (int i = 0; i < Player.SIZE; i++) {
            for (int j = 0; j < Player.SIZE; j++) {
                if (((i == 0 || i % 2 == 0) && (j == 0 || j % 2 == 0)) || ((i % 2 == 1) && (j % 2 == 1))) {
                    positions[i][j] = 1; //La case est noire.
                } else {
                    positions[i][j] = 0; //La case est blanche
                }
            }
        }
    }

    public void shot(Player p) {
        Player e = p.getEnemy();

        if (p.getHistoryGrill(xAttack, yAttack) == 1 && e.aTouche(xAttack, yAttack) && direction[3] == 0) {//Partie où l'on a touché le bateau. Faire en sorte que l'on puisse attaquer les 4 cases autour.

            if ( p.getHistoryGrill(xDebut, yDebut) ==  1 && e.aTouche(xDebut, yDebut)) {
                attaquerDirection(xDebut, yDebut, directionActu, p);
            } else {
                int i = 0;
                boolean tirer = false;

                while (!tirer && i < direction.length) {

                    if (i == 0 && xAttack > 0) {
                        xActu -= 1;
                        directionActu = gauche;
                        tirer = true;
                    }

                    if (i == 1 && xAttack < Player.SIZE - 1) {
                        xActu += 1;
                        directionActu = droite;
                        tirer = true;
                    }

                    if (i == 2 && yAttack > 0) {
                        yActu -= 1;
                        directionActu = haut;
                        tirer = true;
                    }

                    if (i == 3 && yAttack < Player.SIZE - 1) {
                        yActu += 1;
                        directionActu = bas;
                        tirer = true;
                    }

                    if (tirer) {
                        if (p.getHistoryGrill(xActu, yActu) == 0) {
                            attaquer(p, xActu, yActu);
                            direction[i] = 1;
                            p.setJoue(true);
                            xDebut = xActu;
                            yDebut = yActu;
                        } else tirer = false;
                    }
                    xActu = xAttack;
                    yActu = yAttack;
                    i++;
                }

                if (!tirer) {
                    direction[3] = 1;
                }

          }

        }else{
            direction = new int[4];
        xAttack = (int) (Math.random() * Player.SIZE);
        yAttack = (int) (Math.random() * Player.SIZE);

            xActu = xAttack;
            yActu = yAttack;

            if (((xDebut == 0 || xDebut % 2 == 0)) && ((yDebut == 0 || yDebut % 2 == 0))) {// La première case sur laquelle on tire est alors une noire sur un échequier
                // On va alors faire en sorte de tirer uniquement sur des noires.
                /* COMPARONS LA CASE CHOISIE AU HASARD SI SA VALEUR DANS LE TABLEAU POSITIONS EST A 1*/
                if (positions[xAttack][yAttack] == 1 && p.getHistoryGrill(xAttack, yAttack) == 0) {
                    attaquer(p, xAttack,yAttack);
                    p.setJoue(true);
                }
            }else{
                if (positions[xAttack][yAttack] == 0 && p.getHistoryGrill(xAttack, yAttack)  == 0) {
                    attaquer(p, xAttack, yAttack);
                    p.setJoue(true);
                }
            }
        }
    }

    private static void attaquer(Player p, int xAttack, int yAttack){
            Player enemy = p.getEnemy();
            if (enemy.getShip(xAttack, yAttack) != null) {
                enemy.shotShipPart(xAttack, yAttack);
                p.toucher();
            } else {
                enemy.setShipGrill(xAttack, yAttack);
                p.rater();

            }
            p.setHistoryGrill(xAttack, yAttack);
    }

    public void attaquerDirection(int x, int y, int direction, Player p){


        if (direction == 0 && x > 0) {
            x -= 1;
            directionActu = gauche;
        }

        if (direction == 1 && x < Player.SIZE - 1) {
            x += 1;
            directionActu = droite;
        }

        if (direction == 2 && y > 0) {
            y -= 1;
            directionActu = haut;
        }

        if (direction == 3 && y < Player.SIZE - 1) {
            y += 1;
            directionActu = bas;
        }


            if (p.getHistoryGrill(x, y) == 0) {
                attaquer(p, x, y);
                xDebut = x;
                yDebut = y;
                p.setJoue(true);
            } else {
                directionActu += 1;
                directionActu = directionActu%4;
                shot(p);
            }

    }

    public int getXattack() {
        return xAttack;
    }

    public int getYattack(){
        return yAttack;
    }
}
