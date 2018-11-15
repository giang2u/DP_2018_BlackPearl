package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.players.Player;

public class ListenerShot implements ActionListener {
	private Player p;
	
	public ListenerShot(Player p){
		this.p = p;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
