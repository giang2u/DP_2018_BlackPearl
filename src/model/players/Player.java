package model.players;

import java.util.ArrayList;

import model.ship.Ship;

public abstract class Player {
	
	private final static int SIZE = 10;
	
	protected int [][] shipGrill = new int[SIZE][SIZE];
	protected int [][] historyGrill = new int[SIZE][SIZE];
	protected String playerName ="";
	protected int nbTireToucher = 0;
	protected int nbTireMiss = 0;
	protected ArrayList<Ship> listeBateaux;
	
	public Player(String name){
		this.playerName = name;
		initGrill();
		listeBateaux = new ArrayList<>(5);
	}
	
	public void toucher(){
		this.nbTireToucher++;
	}
	
	public void rater(){
		this.nbTireMiss++;
	}
	
	protected void initGrill(){
		for(int i = 0 ; i < SIZE; i++){
			for(int j = 0; j > SIZE;j++){
				shipGrill[i][j] = 0;
				historyGrill[i][j] = 0;
			}
		}
	}
	
	public void addShip(Ship ship){
		this.listeBateaux.add(ship);
	}
	
	public void positionShip(){
		for(Ship ship : listeBateaux){
			boolean valide = false;
			while(!valide){
				int x = 0;//lire entrer du clavier
				int y = 0; //lire entre du clavier
				if(this.shipGrill[x][y] == 0){
					this.shipGrill[x][y] = 1;
					ship.setPosX(x);
					ship.setPosY(y);
					ship.setHorizontal(true); // lecture entre
				}
				
			}
		}
	}
	
}
