package model.epoch;

import model.ship.Ship;
import model.ship.Ship_centuryXX;

public class CenturyXX extends EpochFactory {

	public CenturyXX(){
		this.epochName = "20eme";
	}
	
	@Override
	public Ship buildShip(int x, int y, int size, boolean horizontal) {
		return new Ship_centuryXX(x, y, size, horizontal);
	}

	@Override
	public String nameEpoch() {
		// TODO Auto-generated method stub
		return this.epochName;
	}

	

}
