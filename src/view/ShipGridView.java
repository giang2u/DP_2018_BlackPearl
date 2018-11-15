package view;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ShipGridController;
import controller.ShotHistoryGridController;

import model.players.Player;

public class ShipGridView extends JPanel implements Observer{
	
	private Player player;
    
    public ShipGridView(Player p) {
    	player = p;
    	
    	JLabel image = new JLabel( new ImageIcon( "img/grille_bataille_navale.png")); 
    	this.add(image); 
    	this.addMouseMotionListener(new ShipGridController(player));

		//this.player.ajouterVue(this);
    }

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
