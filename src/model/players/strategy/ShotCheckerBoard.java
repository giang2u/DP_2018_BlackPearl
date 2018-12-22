package model.players.strategy;

import model.players.Player;

public class ShotCheckerBoard implements StrategyShot {

    private int xDebut = (int) (Math.random() * Player.SIZE);
    private int yDebut = (int) (Math.random() * Player.SIZE);
    private int xAttack;
    private int yAttack;
    private int[][] positions;

    public ShotCheckerBoard() {
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
        Player enemy = p.getEnemy();
        int x = enemy.getShip(xAttack, yAttack).getPosX();
        if (enemy.getShip(xAttack, yAttack).getShipPart()[xAttack-x] == 1){//Partie où l'on a touché le bateau. Faire en sorte que l'on puisse attaquer les 4 cases autour.
            //int croix[][] = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        }else{
        xAttack = (int) (Math.random() * Player.SIZE);
        yAttack = (int) (Math.random() * Player.SIZE);

        System.out.println("Positions d'attaques: " + xAttack + " : " + yAttack);
        System.out.println("Valeur de la position: " + positions[xAttack][yAttack]);

            if (((xDebut == 0 || xDebut % 2 == 0)) && ((yDebut == 0 || yDebut % 2 == 0))) {// La première case sur laquelle on tire est alors une noire sur un échequier
                // On va alors faire en sorte de tirer uniquement sur des noires.
                /* COMPARONS LA CASE CHOISIE AU HASARD SI SA VALEUR DANS LE TABLEAU POSITIONS EST A 1*/
                if (positions[xAttack][yAttack] == 1 && enemy.getShip(xAttack, yAttack).getShipPart()[xAttack-x] == 0) {
                    attaquer(p, xAttack,yAttack);
                }
            }else{
                if (positions[xAttack][yAttack] == 0 && enemy.getShip(xAttack, yAttack).getShipPart()[xAttack-x] == 0) {
                    attaquer(p, xAttack, yAttack);
                }
            }
        }
    }

    private static void attaquer(Player p, int xAttack, int yAttack){


        Player enemy = p.getEnemy();
        if (enemy.getShip(xAttack, yAttack) != null) {
            enemy.setShipPart(xAttack, yAttack);
            p.toucher();

        } else {
            enemy.setShipGrill(xAttack, yAttack);
            p.rater();
        }
    }

    public int getXattack() {
        return xAttack;
    }

    public int getYattack(){
        return yAttack;
    }
}
/*for (int i = 0; i < 4; i++) {
        xAttack = croix[i][0];
        yAttack = croix[0][i];
        }*/
