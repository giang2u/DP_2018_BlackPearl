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
		jeu.selectAiDifficulty();
	}

}
