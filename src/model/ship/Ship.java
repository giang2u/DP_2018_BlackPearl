package model.ship;

import java.io.Serializable;
import java.util.Observable;

public abstract class Ship extends Observable implements Serializable{
	
	protected int posX, posY, size, hp;
	protected boolean horizontal, dead;
	protected int[] shipPart;
	
	public Ship(int x, int y, int size, boolean horizontal) {
		this.posX = x;
		this.posY = y;
		this.size = size;
		this.hp = size;
		this.horizontal = horizontal;
		dead = false;
		shipPart = new int[size];
	}



	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public int getSize() {
		return size;
	}

	public int getHp() {
		return hp;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public boolean isHorizontal() {
		return horizontal;
	}

	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
		setChanged();
		notifyObservers();
	}
	
	public boolean isDead() {
		dead = true;
		for (int i = 0; i < shipPart.length; i++) {
			if (shipPart[i] == 0) dead = false;
		}
		return dead;
	}

	public boolean estToucher(int a, int b){
		if(!horizontal){
			if(posX <= a  && a < posX + 1 && posY <=b  && b < posY + size){
				shipPart[a - posX] = 1;
				return true;
			}
			else{
				return false;
			}
		}
		else{ 
			if(posX <= a  && a < posX + size && posY <=b  && b < posY + 1){
				return lostHp(a,b);
			}
			else{
				return false;
			}
		}
	}

	public int[] getShipPart() {
		return shipPart;
	}



	public void setShipPart(int x, int y) {
		if (horizontal && x >= posX && x < posX + size) {
			this.shipPart[x - posX] = 1;
		}
	}
	
	
	public boolean lostHp(int x, int y) {
		boolean touche = false;
		
		if (horizontal) {
			if (shipPart[x - posX] == 0) {
				diminuer();
				shipPart[x - posX] = 1;
				touche = true;
			}
		}
		
		return touche;
	}
	
	public void diminuer() {
		if (this.hp > 0) this.hp--;
	}

}
