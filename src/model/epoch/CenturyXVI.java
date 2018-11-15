package model.epoch;

import model.ship.Ship;
import model.ship.Ship_centuryXVI;

public class CenturyXVI extends EpochFactory{

	public CenturyXVI(){
		this.epochName = "18eme";
	}
	@Override
	public Ship buildShip(int x, int y, int size, boolean horizontal) {
		return new Ship_centuryXVI(x, y, size, horizontal);
	}


}
