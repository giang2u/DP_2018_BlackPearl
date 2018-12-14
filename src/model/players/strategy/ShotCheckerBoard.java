package model.players.strategy;

import model.players.Player;

import java.util.Arrays;

public class ShotCheckerBoard implements StrategyShot{

    private int xDebut = (int) (Math.random()* Player.SIZE);
    private int yDebut = (int) (Math.random()* Player.SIZE);
    private int[][] positions;

    public ShotCheckerBoard() {
        positions = new int[Player.SIZE][Player.SIZE];
        for (int i = 0; i < Player.SIZE; i++) {
            for (int j = 0; j < Player.SIZE; j++) {
                if (((i == 0 || i % 2 == 0) && (j == 0 || j % 2 == 0)) || (( i % 2 == 1) && (j % 2 == 1))) {
                    positions[i][j] = 1; //La case est noire.
                } else {
                    positions[i][j] = 0; //La case est blanche
                }
            }
        }
        System.out.println("Valeurs des positions "+Arrays.deepToString(positions));
    }

    public void shot(Player p) {
        Player enemy = p.getEnemy();

        int xAttack = (int) (Math.random()* Player.SIZE);
        int yAttack = (int) (Math.random()* Player.SIZE);
        System.out.println("Positions d'attaques: "+ xAttack + " : "+yAttack);
        System.out.println("Valeur de la position: "+ positions[xAttack][yAttack]);

        if (((xDebut == 0 || xDebut % 2 == 0)) && ((yDebut == 0 || yDebut % 2 == 0))){// La première case sur laquelle on tire est alors une noire sur un échequier
            // On va alors faire en sorte de tirer uniquement sur des noires.
/* COMPARONS LA CASE CHOISIE AU HASARD SI SA VALEUR DANS LE TABLEAU POSITIONS EST A 1*/
            if (positions[xAttack][yAttack] == 1) {
                if (enemy.getShip(xAttack, yAttack) != null) {
                    enemy.setShipPart(xAttack, yAttack);
                    p.toucher();
                } else {
                    enemy.setShipGrill(xDebut, yDebut);
                    p.rater();
                }
            }
        }
                    /*Player enemy = p.getEnemy();
                    if (p.shotNumber() < Player.SIZE * Player.SIZE) {
                        if (enemy.getShip(xDebut, yDebut) != null) {
                            enemy.setShipPart(xDebut, yDebut);
                            p.toucher();
                        } else {
                            enemy.setShipGrill(xDebut, yDebut);
                            p.rater();
                        }
                    }*/
    }
}

        /*if (((xDebut == 0 || xDebut % 2 == 0)) && ((yDebut == 0 || yDebut % 2 == 0))){ // La première case est alors une noire sur un échequier
            // On va alors faire en sorte de tirer uniquement sur des noires.
        }else{ //La première case est dans ce cas alors obligatoirement une blanche
            //On va alors faire en sorte de tirer uniquement sur des blanches.

            }
        }
*/
