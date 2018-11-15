package view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.players.Player;
import controller.ShipGridController;
import controller.ShotHistoryGridController;

public class ShotHistoryGridView extends JPanel implements Observer{
	
	private Player player;
	protected ArrayList<JButton> lb;
    private JButton[][] grid;
    
    public ShotHistoryGridView(Player p) {
    	
    	player = p;
    	
    	JLabel image = new JLabel( new ImageIcon( "img/grille_bataille_navale.png")); 
    	this.add(image); 
    	this.addMouseListener(new ShotHistoryGridController(player));
    	
		//this.player.ajouterVue(this);
    }

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
