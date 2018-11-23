package model.players;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

import model.ship.Ship;

public abstract class Player extends Observable{
	
	public final static int SIZE = 10;

	protected int[][] shipGrill = new int[SIZE][SIZE];
	protected int[][] historyGrill = new int[SIZE][SIZE];
	protected String playerName = "";
	protected int nbTireToucher = 0;
	protected int nbTireMiss = 0;
	protected ArrayList<Ship> shipList;
	protected int xClick, yClick;

	public Player(String name) {
		this.playerName = name;
		initGrill();
		shipList = new ArrayList<>(5);
	}
	
	public boolean cibleToucher(int xTirer, int yTirer){
		boolean toucher = false;
		setCoorDonne(xTirer, yTirer);
		for(Ship ship : shipList){
			if(ship.estToucher(xClick, yClick)){
				toucher = true;;
			}
		}
		return toucher;
	}

	public void toucher() {
		this.nbTireToucher++;
	}

	public void rater() {
		this.nbTireMiss++;
	}

	protected void initGrill() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j > SIZE; j++) {
				shipGrill[i][j] = 0;
				historyGrill[i][j] = 0;
			}
		}
	}

	public void addShip(Ship ship) {
		this.shipList.add(ship);
	}

	public void positionShip() {
		int num = 1;
		for (Ship ship : shipList) {
			boolean valide = false;
			while (!valide) {
				Scanner sc = new Scanner(System.in);
				System.out.println("Veuillez saisir la position x du bateau :" + num);
				int x = sc.nextInt();

				System.out.println("Veuillez saisir la position y du bateau :" + num);
				int y = sc.nextInt();

				if (this.shipGrill[x][y] == 0) {
					this.shipGrill[x][y] = 1;
					ship.setPosX(x);
					ship.setPosY(y);
					System.out.println("Veuillez saisir la orientation du bateau : " + num + " \n1 pour vertiacle \n2 pour horizontale");

					int o = sc.nextInt();
					boolean orienation = false;
					while (!orienation) {
						switch (o) {
							case 1:
								ship.setHorizontal(false);
								orienation = true;
								break;

							case 2:
								ship.setHorizontal(true);
								orienation = true;
								break;

							default:
								System.out.println("Erreur tapez 1 ou 2");
								orienation = false;
								o = sc.nextInt();
								break;
						}
					}
						valide = true;
				}

			}
			num++;
		}
	}

	public int[][] getShipGrill(){
		return this.shipGrill;
	}
	
	public int[][] getHistoryGrill(){
		return this.historyGrill;
	}

	public ArrayList<Ship> getListShip(){
		return this.shipList;
	}

	public void setCoorDonne(int x , int y ){
		setxClick(x);
		setyClick(y);
		setChanged();
		notifyObservers();
	}
	
	public int getxClick() {
		return xClick;
	}

	public void setxClick(int xClick) {
		this.xClick = xClick;
	}

	public int getyClick() {
		return yClick;
	}

	public void setyClick(int yClick) {
		this.yClick = yClick;
	}
	
	
}

