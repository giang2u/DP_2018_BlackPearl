package model.players;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

import view.Vue;

import model.ship.Ship;
import model.ship.Ship_centuryXVI;

public abstract class Player extends Observable{

	public final static int SIZE = 10;

	protected int[][] shipGrill = new int[SIZE][SIZE];
	protected int[][] historyGrill = new int[SIZE][SIZE];
	protected String playerName = "";
	protected int nbTireToucher = 0;
	protected int nbTireMiss = 0;
	protected ArrayList<Ship> shipList;
	protected ArrayList<Vue> listView;
	protected boolean isReady = false;

	// last click of user
	protected int xClick, yClick;

	protected Ship[][] checkShip;
	protected int[] shipCount;
	protected Player enemy;

	public Player(String name) {
		this.playerName = name;
		initGrill();
		shipList = new ArrayList<>(5);
		shipCount = new int[5];
		checkShip = new Ship[Player.SIZE][Player.SIZE];
		listView = new ArrayList<>();
	}


	public boolean cibleToucher(int xTirer, int yTirer){

		boolean toucher = false;
		setCoorDonne(xTirer, yTirer);

		for(Ship ship : enemy.getListShip()){
			//System.out.println(ship.getPosX() + "   " + ship.getPosY());

			System.out.println("tir " + xTirer + "  " +  yTirer);
			if(ship.estToucher(xClick, yClick)){
				toucher = true;
			}
		}
		return toucher;
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

	public void setShipPart(int i, int j) {
		checkShip[i][j].setShipPart(i, j);
		setChanged();
		notifyObservers();
	}

	public int getShipGrill(int i,int j){
		return shipGrill[i][j];
	}

	public void setShipGrill(int i, int j) {
		shipGrill[i][j] = 1;
		setChanged();
		notifyObservers();
    }
    
    
	//  ------------------ PLACEMENT DRAG AND DROP DES BATEAUX -------------------------- //

	public void setVerticalShip(){
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
					//Ship s2 = new Ship_centuryXVI(s.getPosX(),s.getPosY(),s.getSize(),false);
					s.setHorizontal(false);
					for (int i = 0; i < s.getSize(); i++) {
						checkShip[s.getPosX()][s.getPosY()-i] = s;
						if(i >= 1){
							checkShip[s.getPosX()+i][s.getPosY()] = null;
						}
					}
				}
			}

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
				s = new Ship_centuryXVI(x,y,taille,true);
				checkShip[x+i][y] = s;
				if (i == 0) addShip(s);
				put = true;
			}
			shipCount[taille-1]++;
		}
		setChanged();
		notifyObservers();
		return put;
	}


	public Player getEnemy() {
		return enemy;
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



	public void setEnemy(Player p) {
		enemy = p;
	}

	//  ------------------ FIN PLACEMENT DRAG AND DROP DES BATEAUX -------------------------- //

	public boolean isLose() {
		boolean lose = true;

		for (Ship s : shipList) {
			// si un bateau nest pas coule alors le joueur na pas perdu
			if (!s.isDead()) {
				lose = false;
			}
		}

		return lose;
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


}

