package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.players.Player;

public class ControllerStartGame implements ActionListener{

	protected Player p ;

	public ControllerStartGame(Player p){
		this.p = p;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.p.setReady(true);
	}

}
