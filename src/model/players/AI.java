package model.players;

import main.Jeu;
import model.players.strategy.ShotCheckerBoard;
import model.players.strategy.ShotRandom;
import model.players.strategy.StrategyShot;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Random;


public class AI extends Player {

	private StrategyShot strategyShot;
	private int difficulte;

	public AI(String name, StrategyShot strategyShot) {
		super(name);
		this.strategyShot = strategyShot;
		difficulte = 0;
	}
	
	public void putShip() {
		Random random = new Random();
		for (int i = 2; i < 6; i++) {
			boolean put = false;
			int xDebut = random.nextInt(SIZE);
			int yDebut = random.nextInt(SIZE);
			
			while (!put) {
				xDebut = random.nextInt(SIZE);
				yDebut = random.nextInt(SIZE);
				put = ajouterShip(xDebut, yDebut, i);
				if (i == 3) {
					if (shipCount[i-1] < 2)
						put = false;
				}
			}
		}
		
	}

	public void tirer(){
		if (strategyShot != null){
			strategyShot.shot(this);
		}else{
			System.out.println("oups il y a un problème");
		}
	}
	
	public void setDifficulte(int i) {
		if (i >= 0 && i < Jeu.difficulte.length) {
			difficulte = i;
		}

		if (difficulte == 0) strategyShot = new ShotRandom();

		if (difficulte == 1) strategyShot = new ShotCheckerBoard();
	}

	/*private void getLastShot(){
		int lastX = strategyShot.getXattack();
		int lastY = strategyShot.getYattack();
		System.out.println(lastX + "YOUHOU" + lastY);
		//System.exit(0);
	}*/


	/*public void randomShot(){
		
		if (nbTireMiss+nbTireToucher < SIZE*SIZE){
			Random random = new Random();
			int xDebut = random.nextInt(SIZE);
			int yDebut = random.nextInt(SIZE);
			
			if (enemy.getShip(xDebut, yDebut) != null) enemy.setShipPart(xDebut,yDebut);
			else enemy.setShipGrill(xDebut, yDebut);
			nbTireMiss++;
		}
		//cibleToucher(xDebut, yDebut);

	}*/

}
