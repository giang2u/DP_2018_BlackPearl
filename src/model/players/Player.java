package model.players;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

import view.Vue;

import model.ship.Ship;
import model.ship.Ship_centuryXVI;
import model.ship.Ship_centuryXX;

public abstract class Player extends Observable implements Serializable{

	public final static int SIZE = 10;

	protected int[][] shipGrill = new int[SIZE][SIZE];
	protected int[][] historyGrill = new int[SIZE][SIZE];
	protected String playerName = "";
	protected int nbTireToucher = 0;
	protected int nbTireMiss = 0;
	protected ArrayList<Ship> shipList;
	protected boolean isReady = false;
	protected String epoch;

	// last click of user
	protected int xClick, yClick;
	protected Ship[] tabShip;
	protected Ship[][] checkShip;
	protected int[] shipCount;
	protected Player enemy;

	public Player(String name) {
		this.playerName = name;
		initGrill();
		shipList = new ArrayList<>(5);
		tabShip = new Ship[5];
		shipCount = new int[5];
		checkShip = new Ship[Player.SIZE][Player.SIZE];
	}
	

	public void copiePlayer(Player h) {
		this.playerName = h.getPlayerName();
		this.shipList = h.getListShip();
		this.shipCount = h.getShipCount();
		this.checkShip = h.getCheckShip();
		this.shipGrill = h.getShipGrill();
		this.historyGrill = h.getHistoryGrill();
		setChanged();
		notifyObservers();
	}
	
	public Ship[] getTabShip() {
		return tabShip;
	}


	public void setTabShip(Ship[] tabShip) {
		this.tabShip = tabShip;
	}

	// add ship and return his number
	public void addTabShip(Ship s) {
		int i = 0;
		while (tabShip[i] != null && i < (tabShip.length) ) {
			i++;
		}
		
		if (i < this.tabShip.length && i >= 0) {
			tabShip[i] = s;
		}
	}
	
	// return the number of ship in array
	public int getNumberShip() {
		int k = 0;
		for (int i = 0; i < tabShip.length; i++) {
			if  (tabShip[i] != null ) k++;
		}
		return k;
	}



	// check if shot is successful then set the part shot to 1 in shipPart
	public boolean cibleToucher(int xTirer, int yTirer){

		boolean toucher = false;
		setCoorDonne(xTirer, yTirer);

		for(Ship ship : enemy.getListShip()){

			System.out.println("tir " + xTirer + "  " +  yTirer);
			if(ship.collision(xClick, yClick)){
				ship.lostHp(xClick, yClick);
				toucher = true;
			}
		}
		return toucher;
	}

	

	protected void initGrill() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j > SIZE; j++) {
				shipGrill[i][j] = 0;
				historyGrill[i][j] = 0;
			}
		}
	}



	
	/**
	 * check if there is a ship,  if yes decrease his hp, set his part shotted to 1
	 * @param i
	 * @param j
	 */
	public void shotShipPart(int i, int j) {
		checkShip[i][j].setShipPart(i, j);
		
		boolean trouve = false;
		int k = 0;
		
		while (!trouve && k < shipList.size() ) {
			if (shipList.get(k).collision(i, j)) {
				shipList.get(k).lostHp(i, j); 
				trouve = true;
			}
			k++;
		}
		
		setChanged();
		notifyObservers();
	}


    
    
	//  ------------------ PLACEMENT DRAG AND DROP DES BATEAUX -------------------------- //

	public void setVerticalShip(){
		int nShip = 5;
		for(Ship s : this.shipList){
			if(!s.isHorizontal() && s.getPosY() - s.getSize() +1  >= 0){
				boolean notturner = false;
				int j = 1;
				while(!notturner && j < s.getSize()){
					if(checkShip[s.getPosX()][s.getPosY()-j] != null){
						notturner = true;

					}
					j++;
				}
				if(!notturner){
					s.setHorizontal(false);
					for (int i = 0; i < s.getSize(); i++) {
						checkShip[s.getPosX()][s.getPosY()-i] = s;
						shipGrill[s.getPosX()][s.getPosY()-i] = nShip;
						if(i >= 1){
							checkShip[s.getPosX()+i][s.getPosY()] = null;
							shipGrill[s.getPosX()+i][s.getPosY()] = 0;
						}
					}
				}
			}
			nShip++;
			setChanged();
			notifyObservers();
		}
	}

	//  ------------------ PLACEMENT DRAG AND DROP DES BATEAUX -------------------------- //

	// try to placed ship where we dropped 
	public boolean ajouterShip(int x,int y,int taille){
		boolean put = false;
		Ship s;
		// if there we can place it
		if (checkCount(taille) && checkPlacedShip(x,y,taille, true)) {
			for (int i = 0; i < taille; i++) {
				if(this.epoch.equals("16eme")){
					s = new Ship_centuryXVI(x,y,taille,true);
					checkShip[x+i][y] = s;
					if (i == 0) {
						addShip(s); 
						addTabShip(s);
					}
				}
				if(this.epoch =="20eme"){
					s = new Ship_centuryXX(x,y,taille,true);
					checkShip[x+i][y] = s;
					if (i == 0) {
						addShip(s);
						addTabShip(s);
					}
				}
				shipGrill[x+i][y] = getNumberShip()+tabShip.length;
				put = true;
			}
			shipCount[taille-1]++;
		}
		setChanged();
		notifyObservers();
		return put;
	}




	// search if there is already a ship placed 
	// true if no ship
	public boolean checkPlacedShip(int x,int y,int taille, boolean horizontal) {
		// if the ship does not exceed the map size
		boolean noShip = horizontal==true ? (x+taille < Player.SIZE +1) : (y-taille >= 0);
		if (noShip) {
			int i = 0;
			while(i < taille && noShip) {
				noShip = horizontal==true ? (checkShip[x + i][y] == null) : (checkShip[x][y + i] == null);
				i++;
			}
		}
		return noShip;
	}

	// rule allow one ship 2,4,5 and two ship 3
	public boolean checkCount(int size) {
		boolean check = false;
		if (size == 2 || size == 4 || size == 5) check = shipCount[size-1] < 1;
		if (size == 3) check = shipCount[size-1] < 2;
		return check;
	}



	//  ------------------ FIN PLACEMENT DRAG AND DROP DES BATEAUX -------------------------- //

	public boolean isLose() {
		boolean lose = true;

		for (Ship s : shipList) {
			// si un bateau n est pas coule alors le joueur n a pas perdu
			if (!s.isDead()) {
				lose = false;
			}
		}

		return lose;
	}

	
	
	public void update() {
		setChanged();
		notifyObservers();
	}
	
	public void setEpoch(String name){
		this.epoch = name;
		setChanged();
		notifyObservers();
	}
	
	// ----------------------------------- GETTER SETTER ----------------------------------------------------------------------- //
	
	
	public void addShip(Ship ship) {
		this.shipList.add(ship);
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

	public Ship getShip(int i,int j){
		return checkShip[i][j];
	}
	
	public int getShipGrill(int i,int j){
		return shipGrill[i][j];
	}

	public void setShipGrill(int i, int j) {
		shipGrill[i][j] = 1;
		setChanged();
		notifyObservers();
    }
	
	
	public int getNbTireToucher() {
		return nbTireToucher;
	}

	public void setNbTireToucher(int nbTireToucher) {
		this.nbTireToucher = nbTireToucher;
	}

	public int getNbTireMiss() {
		return nbTireMiss;
	}

	public void setNbTireMiss(int nbTireMiss) {
		this.nbTireMiss = nbTireMiss;
	}

	public void toucher() {
		this.nbTireToucher++;
	}

	public void rater() {
		this.nbTireMiss++;
	}
	
	
	public int shotNumber() {
		return nbTireMiss + nbTireToucher;
	}


	public boolean isReady() {
		return isReady;
	}


	public void setReady(boolean isReady) {
		this.isReady = isReady;

		setChanged();
		notifyObservers();
	}


	public Ship[][] getCheckShip() {
		return checkShip;
	}


	public void setCheckShip(Ship[][] checkShip) {
		this.checkShip = checkShip;
	}
	

	public Player getEnemy() {
		return enemy;
	}

	public void setEnemy(Player p) {
		enemy = p;
	}
	

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}


	public void setShipList(ArrayList<Ship> shipList) {
		this.shipList = shipList;
	}

	public int[] getShipCount() {
		return shipCount;
	}

	public void setShipCount(int[] shipCount) {
		this.shipCount = shipCount;
	}

	public void setShipGrill(int[][] shipGrill) {
		this.shipGrill = shipGrill;
	}

	public void setHistoryGrill(int[][] historyGrill) {
		this.historyGrill = historyGrill;
	}


}

