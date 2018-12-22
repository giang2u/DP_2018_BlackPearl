package main;



import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

import model.players.strategy.ShotCheckerBoard;
import controller.ControllerAiDifficulty;
import controller.ControllerRestart;

import dao.AbstractDAOFactory;

import model.players.strategy.ShotRandom;
import view.ShipGridView;
import view.ShipSettingView;
import view.ShotHistoryGridView;
import view.VueCreaLaby;
import view.VueMenu;
import view.VueObjets;
import model.epoch.CenturyXVI;
import model.epoch.CenturyXX;
import model.epoch.EpochFactory;
import model.players.AI;
import model.players.Human;
import model.players.Niveau;
import model.players.Player;
import model.ship.Ship;


public class Jeu extends JFrame implements Observer{

	private Player p1 ;
	private Player ai ;
	private EpochFactory epoch;
	private Player joueurCourant;
	private JPanel jpHistory,jpGridShip;
	private JMenuBar jpMenu;
	
	private JPanel jpShip;
	
	public static int[] difficulte = {0,1};
	
	public static Jeu instance;
	
	public static Jeu getInstance() {
		instance = new Jeu();
		return instance;
	}

	public Jeu(){
		
		initJeu();
		joueurCourant = p1;
		setPreferredSize(new Dimension(1400, 900));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;

		jpHistory = new ShotHistoryGridView(p1);

		jpGridShip = new VueCreaLaby((Human) p1,10, 10);
		jpShip = new VueObjets((Human) p1, this);
		jpMenu = new VueMenu(this);
		this.setJMenuBar(jpMenu);
		this.add(jpGridShip, BorderLayout.WEST);
		this.add(jpShip,BorderLayout.SOUTH);
		this.add(jpHistory, BorderLayout.EAST);

		this.setTitle("WORLD OF WARSHIP IN SQUARE BECAUSE NOT GRAPHIC ASSET");
		this.pack();
		this.setVisible(true);
		
	}
	
	
	public void restart() {
		this.dispose();
		getInstance();
	}

	public void initJeu(){
		this.p1 = new Human("Nam");
		//this.ai = new AI("Cumputer", new ShotRandom());
		this.ai = new AI("shotcheckerboard", new ShotCheckerBoard());
		setJoueurCourant(p1);
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
	}

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		Jeu j = new Jeu();
		int nbTour = 0;

		boolean fini = false;
		while(!fini) {
			//System.out.println(j.getAi().getListShip()); // A effacer
			//System.out.println(" ");
				int currentNbShot = j.getJoueurCourant() == j.getP1() ? j.getP1().shotNumber() :  j.getAi().shotNumber();



					if (j.getJoueurCourant() == j.getAi() &&j.getP1().isReady()) 	{((AI) j.getAi()).tirer();
				System.out.println("Je suis la ");}
				else {

						//System.out.println(j.getJoueurCourant());
						System.out.println(j.getP1().isReady());
					}




				if (currentNbShot != j.getJoueurCourant().shotNumber()) {

					Player p = (j.getJoueurCourant() == j.getP1()) ? j.getAi() : j.getP1();

					// check if p has lost
					if (p.isLose()) { 
						System.out.println(" OUA T TRO FORT LE JOUEUR " + p.toString() + " A PERDU"); 
						fini = true;
					}

					// Switch turn
					j.setJoueurCourant(p);
					nbTour++;
				}

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
		this.getP1().update();
	}


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
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
