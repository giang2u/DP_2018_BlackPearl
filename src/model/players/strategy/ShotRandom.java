package model.players.strategy;

import model.players.Player;

import java.util.Random;

public class ShotRandom implements StrategyShot{

    public void shot(Player p) {
    		Player enemy = p.getEnemy();
            Random random = new Random();
            int xDebut = random.nextInt(Player.SIZE);
            int yDebut = random.nextInt(Player.SIZE);
            System.out.println(xDebut +"  "+ yDebut);
            if (enemy.getShip(xDebut, yDebut) != null) {
            	enemy.shotShipPart(xDebut,yDebut); 
            	p.toucher();
            }
            else {
            	enemy.setShipGrill(xDebut, yDebut); 
            	p.rater();
            }
    }
}
