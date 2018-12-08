package model.players;

import model.ship.Ship_centuryXVI;

public class Human extends Player {
	

	public Human(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	
	
	//  ------------------ PLACEMENT DRAG AND DROP DES BATEAUX -------------------------- //

    // try to placed ship where we dropped 
    public void ajouterShip(int x,int y,int taille){
    	
    	
		// if there we can place it
		if (checkCount(taille) && checkPlacedShip(x,y,taille, true) ) {
			// place the ship
	    	for (int i = 0; i < taille; i++) {
	    		checkShip[x + i][y] = new Ship_centuryXVI(x,y,taille,true);
	    	}
			// increment ship register
			shipCount[taille-1]++;
		}
        setChanged();
        notifyObservers();
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
    
    
    public void verticalShip(int x,int y, int taille) {
    	if (checkShip[x][y] != null) {
    		
    	}
    }
    
    //  ------------------ FIN PLACEMENT DRAG AND DROP DES BATEAUX -------------------------- //
    
    

}
