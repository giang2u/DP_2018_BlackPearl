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

	/**
	 * check if the ship have been destroyed
	 * @return
	 */
	public boolean isDead() {
		/*
		 * check if all part of the ship have been shot

		dead = true;
		for (int i = 0; i < shipPart.length; i++) {
			if (shipPart[i] == 0) dead = false;
		}
		return dead
		 */
		return hp <= 0;
	}

	/**
	 * Check if this part of the ship have been already shot
	 *  true if yes
	 *  false if not shot
	 * @param a
	 * @param b
	 * @return
	 */
	public boolean estDejaToucher(int a, int b){
		boolean touche = false;
		if(collision(a,b))  {
			int c = a;
			int current = posX;
			if (!horizontal) {
				c = b;
				current = posY;
			}
			if (shipPart[c - current] == 1) {
				touche = true;
			}
		}
		return touche;
	}

	public int[] getShipPart() {
		return shipPart;
	}


	/**
	 * set a part of the ship to 1 (shot)
	 * @param x
	 * @param y
	 */
	public void setShipPart(int x, int y) {
		if (collision(x,y)) this.shipPart[x - posX] = 1;
	}


	/**
	 * Check if there is a part of the ship in x,y 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean collision(int x, int y) {
		boolean check = false;
		if (horizontal ) {
			if ( x >= posX && x < posX + size && y == posY) check = true;
		} else {
			if ( y <= posY && y > posY - size && x == posX) check = true;
		}
		return check;
	}

	/**
	 * If the part of the ship in (x,y) have not been shotted already
	 * we return true decrease hp and set part of the ship to 1 (shot)
	 * else we return false
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean lostHp(int x, int y) {
		boolean touche = false;
		if (!estDejaToucher(x, y)) {
			diminuer();
			shipPart[x - posX] = 1;
			touche = true;
		}
		return touche;
	}

	/**
	 * decrease life of the ship
	 */
	public void diminuer() {
		if (this.hp > 0) this.hp--;
	}

}
