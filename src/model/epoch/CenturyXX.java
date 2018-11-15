package model.epoch;

import model.ship.Ship;
import model.ship.Ship_centuryXX;

public class CenturyXX extends EpochFactory {

	@Override
	public Ship buildShip(int x, int y, int size, boolean horizontal) {
		// TODO Auto-generated method stub
		return new Ship_centuryXX(x, y, size, horizontal);
	}

	

}
