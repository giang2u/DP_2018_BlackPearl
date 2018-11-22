package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ListenerPoser;
import controller.ShipGridController;
import controller.ShotHistoryGridController;

import model.Case;
import model.players.Human;
import model.players.Player;

public class ShipGridView extends JPanel implements Observer{
	
	private Player player;
    protected JButton[][] grid  = new JButton[10][10];
    protected JPanel[] panels = new JPanel[10];
    private Graphics2D g;
    private Case[][] lcase;
    private int taille = 100;
    
    public ShipGridView(Player p) {
    	this.player = p;
    	lcase = new Case[11][11];
    	
    	 for (int i = 0; i < 11 ; i++ )  {
         	for (int j = 0; j < 11 ; j++ ) {
         		lcase[i][j] = new Case(i*Case.size, j*Case.size);
         	}
         }
    	 
    	/*JPanel contentPane = new JPanel();
    	for (int i = 0; i < panels.length; i++) {
            panels[i] = new JPanel();
        }
    	
    	// initialize button 100 casse 
        for(int i = 0; i < Player.SIZE; i++) {
        	for(int j = 0; j < Player.SIZE; j++){
                grid[i][j] = new JButton(" ");
                grid[i][j].addActionListener(null);
                panels[i].setLayout(new FlowLayout(FlowLayout.LEFT));
                panels[i].add(grid[i][j]);
        	}
        }
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        for (JPanel jPanel : panels) {
            contentPane.add(jPanel);
        }
        
        this.add(contentPane);*/
    	
    	
		this.player.addObserver(this);
    }
    
 
    public void paintComponent(Graphics g)
    {
        // dessin du fond
       /* g.setColor(...);
        g.fillRect(0,0,backbufferSizeX, backbufferSizeY);*/
    	
        // dessin des lignes de la grille
    	Graphics2D g2 = (Graphics2D) g;
    	super.paintComponent(g2);
       /* g2.drawLine(0,0, 200, 200);
	    g2.drawLine(0, 200,200, 0);*/
    	
    	
    	
    	
    	g2.fillRect(0,0,11 * Case.size, 11 * Case.size);
     
    	
        for (int i = 0; i < 11 ; i++ )  {
        	for (int j = 0; j < 11 ; j++ ) {
        		int x = lcase[i][j].getX();
        		int y = lcase[i][j].getY();
        		Color c = lcase[i][j].getColor();
        		g2.setColor(Color.blue);
        		g2.drawRect(x, y, Case.size*1000000, Case.size*1000000);
        	}
        }
    }

	@Override
	public void update(Observable o, Object arg) {
		   for(int i = 0; i < Player.SIZE; i++) {
	        	for(int j = 0; j < Player.SIZE; j++){
	        		if(this.player.getShipGrill()[i][j] == 0){
		                grid[i][j] = new JButton(" ");
	        		}
	        		else{

		                grid[i][j] = new JButton("B");
	        		}
	        	}
	        }
		
	}
	


}
