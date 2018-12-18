package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Jeu;

public class ControllerLoad implements ActionListener{
	
	Jeu jeu;
	
	public ControllerLoad(Jeu j) {
		jeu = j;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		jeu.loadGame();
	}

}
