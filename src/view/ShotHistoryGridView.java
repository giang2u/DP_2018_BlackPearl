package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.players.Player;
import controller.ListenerPoser;
import controller.ListenerShot;
import controller.ShipGridController;
import controller.ShotHistoryGridController;

public class ShotHistoryGridView extends JPanel implements Observer{
	
	protected Player player;
    protected JButton[][] grid  = new JButton[11][11];
    protected JPanel[] panels = new JPanel[11];
    private Graphics2D g2;
    private Case[][] lcase;
    
    public ShotHistoryGridView(Player p) {
    	
    	this.player = p;
    	lcase = new Case[11][11];

     	addMouseListener(new ListenerShot(p));
    	 for (int i = 0; i < 11 ; i++ )  {
         	for (int j = 0; j < 11 ; j++ ) {
         		lcase[i][j] = new Case(i*Case.size, j*Case.size,player);
         	}
         }
    	 

     	this.setPreferredSize(new Dimension(500, 500));
		this.player.addObserver(this);
    }
    
 
    public void paintComponent(Graphics g)
    {
    	
        // dessin des lignes de la grille
    	g2 = (Graphics2D) g;
    	super.paintComponent(g2);

    	g2.fillRect(0,0,11 * Case.size, 11 * Case.size);
     
    	
        for (int i = 0; i < 11 ; i++ )  {
        	for (int j = 0; j < 11 ; j++ ) {
        		int x = lcase[i][j].getX();
        		int y = lcase[i][j].getY();
        		g2.setColor(Color.blue);
        		g2.drawRect(x, y, Case.size, Case.size);
        	}
        }
    }

	@Override
	public void update(Observable o, Object arg) {
		
	}
	


}
