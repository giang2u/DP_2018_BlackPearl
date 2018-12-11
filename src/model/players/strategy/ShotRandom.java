package model.players.strategy;

import model.players.Player;

import java.util.Random;

public class ShotRandom implements StrategyShot{

    @Override
    public void shot(Player enemy, int nbTireMiss, int nbTireToucher, int SIZE) {
        if (nbTireMiss+nbTireToucher < SIZE*SIZE){
            Random random = new Random();
            int xDebut = random.nextInt(SIZE);
            int yDebut = random.nextInt(SIZE);
            System.out.println(xDebut +"  "+ yDebut);
            if (enemy.getShip(xDebut, yDebut) != null) enemy.setShipPart(xDebut,yDebut);
            else enemy.setShipGrill(xDebut, yDebut);
            nbTireMiss++;
        }
        System.out.println("SAHUT");
    }
}
