package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import model.players.Player;
import view.Case;
import view.CaseLabel;

public class ListenerPoser implements MouseListener {

	private Player p;
	
	public ListenerPoser(Player p){
		this.p = p;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

		System.out.println(" JE TIRE ICI");
		// TODO Auto-generated method stub
		int x = arg0.getX()/Case.size;
		int y = arg0.getY()/Case.size;
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(" JE TIRE ICI");
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
