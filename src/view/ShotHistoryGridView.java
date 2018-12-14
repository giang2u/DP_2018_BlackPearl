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
    private Graphics2D g2;
    private Case[][] lcase;
    
    public ShotHistoryGridView(Player p) {
    	
    	this.player = p;
    	lcase = new Case[10][10];

     	
    	 for (int i = 0; i < 10 ; i++ )  {
         	for (int j = 0; j < 10 ; j++ ) {
         		lcase[i][j] = new Case(i*CaseLabel.size, j*CaseLabel.size,player);
         	}
         }
    	 addMouseListener(new ListenerShot(p,lcase));

     	this.setPreferredSize(new Dimension(550, 550));
		this.player.addObserver(this);
    }
    
 
    public void paintComponent(Graphics g)
    {
   	 super.paintComponent(g);
        // dessin des lignes de la grille
    	g2 = (Graphics2D) g;
    	
    	// ligne
    	for (int i = 0; i < 11 ; i++ )  {
    	
    		int x = i*50;
    		int y = 0;
    		
			g2.setColor(Color.white);
        	g2.fillRect(x,y, CaseLabel.size, CaseLabel.size);
    		g2.setColor(Color.gray);
    		g2.drawRect(x, y, CaseLabel.size, CaseLabel.size);
		
    		if (i > 0) g2.drawString(String.valueOf(x/50),(float) (x+20), (float)(y+50-20));
    	
    	}
    	// colonne
		int lettre = 65;
    	for (int i = 1; i < 11 ; i++ )  {
    		int x = 0;
    		int y = i*50;
    		
			g2.setColor(Color.white);
        	g2.fillRect(x,y, CaseLabel.size, CaseLabel.size);
    		g2.setColor(Color.gray);
    		g2.drawRect(x, y, CaseLabel.size, CaseLabel.size);
    		g2.drawString(Character.toString((char) lettre),(float) (x+20), (float)(y+50-20));
    		lettre++;
    	
    	}
    	// tableau
        for (int i = 0; i < 10 ; i++ )  {
        	for (int j = 0; j < 10 ; j++ ) {
        		int x = lcase[i][j].getX()+50;
        		int y = lcase[i][j].getY()+50;
        			g2.setColor(lcase[i][j].getColor());
                	g2.fillRect(x,y, CaseLabel.size, CaseLabel.size);
            		g2.setColor(Color.black);
            		g2.drawRect(x, y, CaseLabel.size, CaseLabel.size);
        	}
        }

    }

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
	


}
