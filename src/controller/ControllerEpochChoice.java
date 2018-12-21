package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import main.Jeu;
import model.players.AI;
import model.players.Player;


public class ControllerEpochChoice implements ActionListener {
	
	protected Jeu j ;
	protected String epoch;
	/**
	 * @param args
	 */
	public ControllerEpochChoice(Jeu j, String nameEpoch) {
		this.j = j;
		this.epoch = nameEpoch;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.j.setEpoch(epoch);
	    JOptionPane jop2 = new JOptionPane();
	    jop2.showMessageDialog(null, "Vous avez selectionnee  " + this.epoch , "", JOptionPane.INFORMATION_MESSAGE);
   
	}

	
}
