
import com.sun.org.apache.xalan.internal.xsltc.dom.AbsoluteIterator;

import model.epoch.CenturyXX;
import model.epoch.EpochFactory;
import model.players.AI;
import model.players.Human;
import model.players.Player;
import model.ship.Ship;


public class Jeu {

	private Player p1 ;
	private Player ai ;
	private EpochFactory epoch;
	
	public Jeu(){
		initJeu();
	}
	
	public void initJeu(){
		this.p1 = new Human("Nam");
		this.ai = new AI("Cumputer");
		//player selection epoque 
			
		
		
	}
	
	/**
	 * init create 5 bateau et donne au joueur
	 */
	public void initShip(){

		//le joeur selectionner epoque au clavier
		int epoque = 0;
		
		if(epoque == 16){
			//this.epoch = new CenturyXVI();
		}
		if(epoque == 18){
			//this.epoch = new CenturyXX();
		}
		
		//creer 5 
		Ship playerShip2 =  this.epoch.buildShip(0,0,2, true);
		Ship playerShip3=  this.epoch.buildShip(0,0,3, true);
		Ship playerShip3B =  this.epoch.buildShip(0,0,3, true);
		Ship playerShip4 =  this.epoch.buildShip(0,0,4, true);
		Ship playerShip5=  this.epoch.buildShip(0,0,5, true);
		
		//donne 5 bateaux au joueur
		this.p1.addShip(playerShip2);
		this.p1.addShip(playerShip3);
		this.p1.addShip(playerShip3B);
		this.p1.addShip(playerShip4);
		this.p1.addShip(playerShip5);
		
		//donne 5 bateau au IA
		this.ai.addShip(playerShip2);
		this.ai.addShip(playerShip3);
		this.ai.addShip(playerShip3B);
		this.ai.addShip(playerShip4);
		this.ai.addShip(playerShip5);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void loadGame(){
		AbstractDAOFactory.getAbstractDAOFactory(1).getGameDAO().load(this);
	}

	public void saveGame(){
		AbstractDAOFactory.getAbstractDAOFactory(1).getGameDAO().save(this);
	}
}
