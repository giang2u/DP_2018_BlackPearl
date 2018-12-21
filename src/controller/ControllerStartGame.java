package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Jeu;
import model.players.Player;

public class ControllerStartGame implements ActionListener{

	protected Player p ;
	protected Jeu jeu;

	public ControllerStartGame(Player p, Jeu j){
		this.p = p;
		this.jeu = j;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.p.setReady(true);
		jeu.selectAiDifficulty();
	}

}
