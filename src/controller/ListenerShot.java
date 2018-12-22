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
		
		if (p.isReady()) {
			int x = (e.getX()- 50) /50 ;
			int y = (e.getY()- 50) /50 ;
			boolean checkX = x >= 0 && x < Player.SIZE;
			boolean checkY = y >= 0 && y < Player.SIZE;
			if (checkX && checkY && p.getHistoryGrill(x, y) == 0) {
				if(p.cibleToucher(x, y)){
					lcase[x][y].setColor(Color.RED);
					p.toucher();
				} else{
					lcase[x][y].setColor(Color.GRAY);
					p.rater();
				}
				p.setHistoryGrill(x, y);
				p.setJoue(true);
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
