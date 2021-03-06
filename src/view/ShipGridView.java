package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

import model.players.Human;
import model.players.Player;

public class ShipGridView extends JPanel implements Observer{
	
	private Player player;
    protected JPanel[] panels = new JPanel[10];
    private Graphics2D g2;
    private Case[][] lcase;
    private Case[] lcases;
    

    public ShipGridView(Player p) {
    	
    	this.player = p;
    	lcase = new Case[11][11];
    	 for (int i = 0; i < 11 ; i++ )  {
         	for (int j = 0; j < 11 ; j++ ) {
         		lcase[i][j] = new Case(i*CaseLabel.size, j*CaseLabel.size, player);
         	}
         }
    	
    	 
    	this.setPreferredSize(new Dimension(500, 500));
		this.player.addObserver(this);
		
		
	   	for(int i =0;i<10;i++){
    		lcases[i] = new Case(i*CaseLabel.size, 1*CaseLabel.size, player);
    	}
    }
    
 
    public void paintComponent(Graphics g)
    {
        // dessin des lignes de la grille
    	this.g2 = (Graphics2D) g;
    	super.paintComponent(g2);
    	
    	//g2.fillRect(0,0,10 * Player.SIZE, 1 * Player.SIZE);
        
    	
    	//g2.fillRect(0,0,11 * CaseLabel.size, 11 * CaseLabel.size);
    	
        for (int i = 0; i < 11 ; i++ )  {
        	for (int j = 0; j < 11 ; j++ ) {
        		int x = lcase[i][j].getX();
        		int y = lcase[i][j].getY();
        	
        			g2.setColor(Color.blue);
        			g2.drawRect(x, y, CaseLabel.size, CaseLabel.size);
        			
        		
        	}
        }
        
        
        
        
        
        
    }

	@Override
	public void update(Observable o, Object arg) {
		   /*for(int i = 0; i < Player.SIZE; i++) {
	        	for(int j = 0; j < Player.SIZE; j++){
	        		if(this.player.getShipGrill()[i][j] == 0){
		                grid[i][j] = new JButton(" ");
	        		}
	        		else{

		                grid[i][j] = new JButton("B");
	        		}
	        	}
	        }*/
		
	}
	


}
