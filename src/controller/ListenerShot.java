package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import view.Case;

import model.players.Player;

public class ListenerShot implements MouseListener {
	private Player p;
	private Case[][] lcase;
	public ListenerShot(Player p){
		this.p = p; 
	}
	
	public ListenerShot(Player p,Case[][] lcase){
		this.p = p; 
		this.lcase = lcase;
	}
	
	


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		if (lcase != null) {
			if(p.cibleToucher(e.getX()/50, e.getY()/50)){
				lcase[e.getX()/50][ e.getY()/50].setColor(Color.red);
				p.toucher();
			}
			else{
				p.rater();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
