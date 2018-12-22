package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import model.players.Player;
import view.Case;
import view.CaseLabel;
import view.VueCreaLaby;

public class ListenerPoser implements MouseListener {

	private Player p;
	private VueCreaLaby vue;
	public ListenerPoser(Player p, VueCreaLaby vue){
		this.p = p;
		this.vue = vue;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {


		int x = arg0.getX()/Case.size;
		int y = arg0.getY()/Case.size;

		boolean checkX = x - 1 >= 0 && x-1 < p.getCheckShip().length;
		boolean checkY = y - 1 >= 0 && y-1 < p.getCheckShip()[0].length;

		if (checkX && checkY) {

			if(p.getCheckShip()[x-1][y-1] != null){
				if(p.getCheckShip()[x-1][y-1].isHorizontal()){
					p.getCheckShip()[x-1][y-1].setHorizontal(false);
					p.setxClick(x);
					p.setyClick(y);
					p.setVerticalShip();
				}
				else{
					p.getCheckShip()[x-1][y-1].setHorizontal(true);
					p.setxClick(x);
					p.setyClick(y);
					p.setVerticalShip();
				}
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
