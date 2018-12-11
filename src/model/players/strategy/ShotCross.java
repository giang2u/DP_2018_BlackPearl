package model.players.strategy;

import model.players.AI;
import model.players.Player;

public class ShotCross implements StrategyShot{

    private int xDebut = (int) (Math.random()* AI.SIZE);
    private int yDebut = (int) (Math.random()* AI.SIZE);

    @Override
    public void shot(Player enemy, int nbTireMiss, int nbTireToucher, int SIZE) {
        if (nbTireMiss+nbTireToucher < SIZE*SIZE){
            if (enemy.getShip(xDebut, yDebut) != null){
                enemy.setShipPart(xDebut,yDebut);
            }
            System.out.println( xDebut+"x" + yDebut+"y");
        }
    }
}
