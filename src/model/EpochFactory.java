package model;

import model.ship.Ship;

public abstract class EpochFactory {
	
	
	public abstract Ship buildShip(int x, int y, int size, boolean horizontal);

}
