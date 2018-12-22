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
import javax.swing.JOptionPane;
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


public class Jeu extends JFrame{

	private Player p1 ;
	private Player ai ;
	private EpochFactory epoch;
	private Player joueurCourant;
	private JPanel jpHistory,jpGridShip;
	private VueMenu jpMenu;
	
	private JPanel jpShip;
	
	public static int[] difficulte = {0,1};
	
	public static Jeu instance;
	
	public static Jeu getInstance() {
		if (instance == null) {
			instance = new Jeu();
		}
		return instance;
	}

	public Jeu(){
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		selectEpoch();
		this.setTitle("WORLD OF WARSHIP IN SQUARE BECAUSE NOT GRAPHIC ASSET");
		jpMenu = new VueMenu(this);
		this.setJMenuBar(jpMenu);
		initJeu(this.epoch.nameEpoch());
		joueurCourant = p1;
		epoch = new CenturyXVI();
		setPreferredSize(new Dimension(1400, 900));
		jpHistory = new ShotHistoryGridView(p1);
		jpGridShip = new VueCreaLaby((Human) p1,10, 10, this);
		jpShip = new VueObjets((Human) p1, this);
		this.add(jpGridShip, BorderLayout.WEST);
		this.add(jpShip,BorderLayout.SOUTH);
		this.add(jpHistory, BorderLayout.EAST);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		this.pack();
		this.setVisible(true);
	}
	
	
	public void restart() {
		this.dispose();
		instance = new Jeu();
	}

	public void initJeu(String name){
		this.p1 = new Human("Nam");
		//this.ai = new AI("Cumputer", new ShotRandom());
		this.ai = new AI("shotcheckerboard", new ShotCheckerBoard());
		setJoueurCourant(p1);
		initShip(name);
	}


	/**
	 * init create 5 bateau et donne au joueur
	 */
	public void initShip(String name){
		if(name == "16eme"){
			this.p1.setEpoch("16eme");
			this.ai.setEpoch("16eme");

		}
		else{

			this.p1.setEpoch("20eme");
			this.ai.setEpoch("20eme");
		}
		((AI)this.ai).putShip();
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

			//System.out.print("");
			if(j.getP1().isReady()){

				System.out.println(j.getP1().getListShip().get(0).getHp());
				int currentNbShot = j.getJoueurCourant() == j.getP1() ? j.getP1().shotNumber() :  j.getAi().shotNumber();

				if (j.getJoueurCourant() == j.getP1()) {
					//System.out.println("PLAYER TURN   " + nbTour);
					// while player have not shot we stay here
				}

				else   {
					if (j.getP1().getListShip().size() == 5) {
						//System.out.println("AI TURN   " + nbTour);
						((AI) j.getAi()).tirer();
					}
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
				
			} else {
				System.out.print("");
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

	public void setEpoch(String name) {
		if(name == "16eme"){
			this.epoch = new CenturyXVI();
		}
		else{
			this.epoch = new CenturyXX();
		}

	}

	public Player getJoueurCourant() {
		return joueurCourant;
	}

	public void setJoueurCourant(Player joueurCourant) {
		this.joueurCourant = joueurCourant;
	}

	public void loadGame(){
		AbstractDAOFactory.getAbstractDAOFactory(1).getGameDAO().load(this);

		this.getP1().update();
	}

	public void saveGame(){
		AbstractDAOFactory.getAbstractDAOFactory(1).getGameDAO().save(this);
		this.getP1().update();
	}

	public void selectEpoch(){
		String[] ep = {"16eme", "20eme"};
	    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String nom = (String)jop.showInputDialog(null, "Veuillez choisir epoque !","",JOptionPane.QUESTION_MESSAGE,null,ep,ep[1]);
	    String camembert = "les vikings au siecle 0 et vous aurez un malus de 10points de plus j aime les POMMES!";
	    String affiche = nom==null?camembert:nom;
	    
	    jop2.showMessageDialog(null, "Votre epoque est " +  affiche, "", JOptionPane.INFORMATION_MESSAGE);
	    if (nom == null)  {
	    	nom = "16eme";
	    }
	    switch(nom) {
	    case "16eme":
			this.setEpoch("16eme");
	    	break;
	    case "20eme":
			this.setEpoch("20eme");
	    	break;
	    }
	}
	
	public void selectAiDifficulty() {
		String[] sexe = {"facile", "HELL", "random"};
	    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String nom = (String)jop.showInputDialog(null, 
	      "Veuillez choisir le mode de difficulte !",
	      "Coucou!",
	      JOptionPane.QUESTION_MESSAGE,
	      null,
	      sexe,
	      sexe[2]);
	    
	    String camembert = "very hard mode";
	    String affiche = nom==null?camembert:nom;
	    
	    jop2.showMessageDialog(null, "Votre difficulte est " +  affiche, " Apple pen", JOptionPane.INFORMATION_MESSAGE);
	    
	    if (nom == null)  {
	    	nom = "difficile";
	    }
	    
	    switch(nom) {
	    case "facile":
	    	((AI) this.getAi()).setDifficulte(0);
	    	break;
	    case "difficile":
	    	((AI) this.getAi()).setDifficulte(1);
	    	break;
	    case "random":
	    	int i = (int)(Math.random() * 2) + 0 ;
	    	((AI) this.getAi()).setDifficulte(i);
	    	break;
	    }
	}
	

}
