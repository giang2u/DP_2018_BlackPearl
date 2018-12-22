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
            System.out.println(xDebut +"  "+ yDebut);
            attaquer(p, xDebut, yDebut);
    }

    private void attaquer(Player player, int xAttack, int yAttack) {

        Player enemy = player.getEnemy();
        if (enemy.getShip(xAttack, yAttack) != null) {
            enemy.setShipPart(xAttack, yAttack);
            player.toucher();
        } else {
            enemy.setShipGrill(xAttack, yAttack);
            player.rater();
        }
    }

    public int getXattack() {
        return xDebut;
    }

    public int getYattack() {
        return yDebut;
    }
}
