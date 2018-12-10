package model.players;

import java.util.Random;

public class AI extends Player {

	public AI(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public void putShip() {
		Random random = new Random();
		for (int i = 2; i < 6; i++) {
			boolean put = false;
			int xDebut = random.nextInt(SIZE-1);
			int yDebut = random.nextInt(SIZE-1);
			
			while (!put) {
				xDebut = random.nextInt(SIZE-1);
				yDebut = random.nextInt(SIZE-1);
				put = ajouterShip(xDebut, yDebut, i);
				if (i == 3) {
					if (shipCount[i-1] < 2)
						put = false;
				}
			}
		}
		
	}

}
