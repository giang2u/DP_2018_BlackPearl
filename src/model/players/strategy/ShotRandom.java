package model.players.strategy;

import model.players.Player;

import java.util.Random;

public class ShotRandom implements StrategyShot{
    private int xDebut;
    private int yDebut;

    public void shot(Player p) {
            Random random = new Random();
            xDebut = random.nextInt(Player.SIZE);
            yDebut = random.nextInt(Player.SIZE);
        Player enemy = p.getEnemy();
        boolean tirer = false;

        while (!p.isJoue()) {
            // if case non shot
            if (p.getHistoryGrill(xDebut, yDebut) == 0) {
                // shot in case x,y
                if (enemy.getShip(xDebut, yDebut) != null) {
                    enemy.shotShipPart(xDebut,yDebut);
                    p.toucher();
                }
                else {
                    enemy.setShipGrill(xDebut, yDebut);
                    p.rater();
                }
                p.setHistoryGrill(xDebut, yDebut);
                p.setJoue(true);
            }
            xDebut = random.nextInt(Player.SIZE);
            yDebut = random.nextInt(Player.SIZE);
        }
    }

    public int getXattack() {
        return xDebut;
    }

    public int getYattack() {
        return yDebut;

    }
}
