package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Jeu;


public class ControllerRestart implements ActionListener{

	
	Jeu jeu;
	
	public ControllerRestart(Jeu j) {
		jeu = j;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//jeu.restart();
	}

}
