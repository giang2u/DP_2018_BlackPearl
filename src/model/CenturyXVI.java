package model;

import model.ship.Ship;
import model.ship.Ship_centuryXVI;

public class CenturyXVI extends EpochFactory{

	@Override
	public Ship buildShip(int x, int y, int size, boolean horizontal) {
		// TODO Auto-generated method stub
		return new Ship_centuryXVI(x, y, size, horizontal);
	}


}
