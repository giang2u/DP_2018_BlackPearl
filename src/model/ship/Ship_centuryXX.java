package model.ship;

public class Ship_centuryXX extends Ship{

	public Ship_centuryXX(int x, int y, int size, boolean horizontal) {
		super(x, y, size, horizontal);
		this.hp = size;
		// TODO Auto-generated constructor stub
	}

	public String toString(){
		return "bateau 20eme";
	}

}
