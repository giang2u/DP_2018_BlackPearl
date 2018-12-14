package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import main.Jeu;


public class ControllerEpochChoice implements ActionListener {

	/**
	 * @param args
	 */
	public ControllerEpochChoice(Jeu j) {
		
                
            
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String[] sexe = {"facile", "HELL", "random"};
	    JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	    String nom = (String)jop.showInputDialog(null, 
	      "Veuillez choisir le mode de difficulté !",
	      "Gendarmerie nationale !",
	      JOptionPane.QUESTION_MESSAGE,
	      null,
	      sexe,
	      sexe[2]);
	    jop2.showMessageDialog(null, "Votre difficulté est " + nom, "Apple pen", JOptionPane.INFORMATION_MESSAGE);
	}

	
}
