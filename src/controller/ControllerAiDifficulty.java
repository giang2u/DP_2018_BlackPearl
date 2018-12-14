package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import main.Jeu;
import model.players.AI;

public class ControllerAiDifficulty implements ActionListener{
	
	Jeu jeu;

	public ControllerAiDifficulty(Jeu j) {
		this.jeu = j;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String[] sexe = {"facile", "HELL", "random"};
	    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String nom = (String)jop.showInputDialog(null, 
	      "Veuillez choisir le mode de difficulté !",
	      "Brigade anti pédophilie!",
	      JOptionPane.QUESTION_MESSAGE,
	      null,
	      sexe,
	      sexe[2]);
	    jop2.showMessageDialog(null, "Votre difficulté est " + nom, "Apple pen", JOptionPane.INFORMATION_MESSAGE);
	    
	    switch(nom) {
	    case "facile":
	    	((AI) jeu.getAi()).setDifficulte(0);
	    	break;
	    case "difficile":
	    	((AI) jeu.getAi()).setDifficulte(1);
	    	break;
	    case "random":
	    	int i = (int)(Math.random() * 1) + 0 ;
	    	((AI) jeu.getAi()).setDifficulte(i);
	    	break;
	    }

	}

}
