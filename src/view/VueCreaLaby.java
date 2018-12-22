package view;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import controller.ControllerStartGame;
import controller.ListenerPoser;
import controller.MyTransferHandler;
import main.Jeu;
import model.players.Human;
import model.players.Niveau;
import model.players.Player;
import model.ship.Ship;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

public class VueCreaLaby extends JPanel implements Observer {

    private int nbLigne,nbColonne;
    private Human player;
    protected JButton start;
    private Graphics2D g2;
    protected Jeu jeu;

    public VueCreaLaby(Human niv, int nbLigne, int nbColonne,Jeu jeu){

        this.player = niv;
        niv.addObserver(this);
        this.jeu = jeu;
        this.nbLigne = nbLigne;
        this.nbColonne = nbColonne;

        this.setLayout(new BorderLayout());
        
     	init();
        
        this.start = new JButton("Start");
     	this.start.setEnabled(false);
     	this.start.addActionListener(new ControllerStartGame(player, jeu));
     	this.addMouseListener(new ListenerPoser(player, this));
     	JPanel bouton = new JPanel();
     	bouton.add(start);
     	bouton.setPreferredSize(new Dimension(80,40));
     	this.add(bouton, BorderLayout.EAST);
     	this.setPreferredSize(new Dimension(700,550));
    	this.getComponent(9*10+9).setBounds(500, 500, 50, 50);
    }
    
    public void init() {
    	JLabel[][] tabLab = new JLabel[nbLigne][nbColonne];
    	
    	 for(int i =0;i<nbLigne;i++){
             for(int j =0;j<nbColonne;j++){
            	
	            	 tabLab[i][j] = new CaseLabel(player, i, j, new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/case.png")));
	                 tabLab[i][j].setTransferHandler(new MyTransferHandler());
	                 tabLab[i][j].setBounds(50+((i)*50),(50+(j)*50),50,50);
	                this.add(tabLab[i][j]);
	               
             }
    	 }
    }
    
    
    public Dimension getTailleLaby(){
        return new Dimension(50*(nbLigne),50*(nbColonne));
    }
    
    public void setJoueur(Human p) {
    	player = p;
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
    		int y = (i)*50;
			g2.setColor(Color.white);
        	g2.fillRect(x,y, CaseLabel.size, CaseLabel.size);
    		g2.setColor(Color.gray);
    		g2.drawRect(x, y, CaseLabel.size, CaseLabel.size);
    		g2.drawString(Character.toString((char) lettre),(float) (x+20), (float)(y+50-20));
    		lettre++;
    	}
    	
    }

    private void modifLab(int h, int l){
    	this.getComponent(9*10+9).setBounds(500, 500, 50, 50);
        nbLigne=h;
        nbColonne=l;
        for(int i =0;i<nbLigne;i++){
            for(int j =0;j<nbColonne;j++){
	            	if (player.getShip(i, j) != null) {
	            		int x = player.getShip(i, j).getPosX();

	            		if (player.getShip(i, j).getShipPart()[i-x] == 1)
	            			((JLabel) this.getComponent(i*10+j)).setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/ship_1_shot.png")));

	            		else ((JLabel) this.getComponent(i*10+j)).setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/ship_1.png")));   
	                }
                    else {
                    	if (player.getShipGrill(i,j) == 1) ((JLabel) this.getComponent(i*10+j)).setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/case_shot.png")));	
                      	else ((JLabel) this.getComponent(i*10+j)).setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/case.png")));	
                    }
	            	
	            	
            }
        }
    }
  



    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
        if(this.player.isReady()){
        	this.start.setEnabled(false);
        }
        if(this.player.getListShip().size() == 5 && !this.player.isReady()){
        	this.start.setEnabled(true);
        }
        this.modifLab(Player.SIZE,Player.SIZE);		
    }
}
