
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Scanner;

import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

import view.ShipGridView;
import view.ShipSettingView;
import view.ShotHistoryGridView;
import view.VueCreaLaby;
import view.VueObjets;
import model.epoch.CenturyXVI;
import model.epoch.CenturyXX;
import model.epoch.EpochFactory;
import model.players.AI;
import model.players.Human;
import model.players.Niveau;
import model.players.Player;
import model.ship.Ship;


public class Jeu extends JFrame {

	private Player p1 ;
	private Player ai ;
	private EpochFactory epoch;
	private Player joueurCourant;
	private JPanel jpShip;
	
	public Jeu(){
		initJeu();
		joueurCourant = p1;
		setPreferredSize(new Dimension(1400, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		
		//JPanel jp = new ShipGridView(p1);
		JPanel jpHistory = new ShotHistoryGridView(p1);
		
		//JPanel panelSettingShip = new ShipSettingView(p1);
		JPanel jpGridShip = new VueCreaLaby((Human) p1,10, 10);
		jpShip = new VueObjets((Human) p1);
		
		this.add(jpGridShip, BorderLayout.WEST);
		this.add(jpShip,BorderLayout.CENTER);
		this.add(jpHistory, BorderLayout.EAST);
		
		this.setTitle("WORLD OF WARSHIP IN SQUARE BECAUSE NOT GRAPHIC ASSET");
		this.pack();
		this.setVisible(true);
	}
	
	public void initJeu(){
		this.p1 = new Human("Nam");
		this.ai = new AI("Cumputer");
		initShip();			
		//this.p1.positionShip();
		
		
	}
	
	/**
	 * init create 5 bateau et donne au joueur
	 */
	public void initShip(){

		int epoque = 1;
		if(epoque == 1){
			this.epoch = new CenturyXVI();
		}
		if(epoque == 2){
			this.epoch = new CenturyXX();
		}
		((AI) this.ai).putShip();
		
		p1.setEnemy(ai);
		ai.setEnemy(p1);
		
		
		
		
		for (Ship s : ai.getListShip()) System.out.println(s.getPosX() + "     " + s.getPosY() + "  "+ s.getSize());
		/*
		//creer 5 
		Ship playerShip2 =  this.epoch.buildShip(0,0,2, false);//
		Ship playerShip3=  this.epoch.buildShip(1,1,3, false);
		Ship playerShip3B =  this.epoch.buildShip(2,2,3, true);//
		Ship playerShip4 =  this.epoch.buildShip(2,4,4, true);
		Ship playerShip5=  this.epoch.buildShip(1,5,5, false);//
		
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
		*/
	}
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		Jeu j = new Jeu();
		
		
		while(true) {
			
			
			System.out.println(j.getP1().getListShip().size());
			if (j.getP1().getListShip().size() == 5) {
				((AI) j.getAi()).randomShot();
				//if (j.getP1().isLose()) System.out.println(" OUA T TRO FORT"); System.exit(0);
			}
		
			
			// changement de tour
			Player p = (j.getJoueurCourant() == j.getP1()) ? j.getAi() : j.getP1();
			j.setJoueurCourant(p);
		}
	}

	public JPanel getJpShip() {
		return jpShip;
	}

	public void setJpShip(JPanel jpShip) {
		this.jpShip = jpShip;
	}

	public Player getP1() {
		return p1;
	}

	public void setP1(Player p1) {
		this.p1 = p1;
	}

	public Player getAi() {
		return ai;
	}

	public void setAi(Player ai) {
		this.ai = ai;
	}

	public EpochFactory getEpoch() {
		return epoch;
	}

	public void setEpoch(EpochFactory epoch) {
		this.epoch = epoch;
	}

	public Player getJoueurCourant() {
		return joueurCourant;
	}

	public void setJoueurCourant(Player joueurCourant) {
		this.joueurCourant = joueurCourant;
	}

	public void loadGame(){
		AbstractDAOFactory.getAbstractDAOFactory(1).getGameDAO().load(this);
	}

	public void saveGame(){
		AbstractDAOFactory.getAbstractDAOFactory(1).getGameDAO().save(this);
	}
	
	/*
	private void createLayout(JComponent... arg) {
		
		ShipSettingView jp = (ShipSettingView) arg[0];

        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);

        gl.setHorizontalGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(gl.createSequentialGroup()
                        .addComponent(jp.getJl().get(0))
                        .addGap(30)
                        .addComponent(jp.getJl().get(1))
                        .addGap(30)
                        .addComponent(jp.getJl().get(2))
                )
                .addComponent(arg[1], groupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE, Integer.MAX_VALUE)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup()
                        .addComponent(jp.getJl().get(0))
                        .addComponent(jp.getJl().get(1))
                        .addComponent(jp.getJl().get(2)))
                .addGap(30)
                .addComponent(arg[1])
        );

        pack();
    }*/
	
	
}
