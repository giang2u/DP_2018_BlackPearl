package view;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import controller.ControllerStartGame;
import controller.ListenerPoser;
import controller.MyTransferHandler;
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
    private JLabel[][] tabLab;
    private Human player;
    protected JButton start;
    private  JPanel jp;
    private Graphics2D g2;

    public VueCreaLaby(Human niv, int nbLigne, int nbColonne){

        this.player = niv;
        niv.addObserver(this);
        
        this.nbLigne = nbLigne;
        this.nbColonne = nbColonne;

        this.setLayout(new BorderLayout());
        
        jp = new JPanel();
        jp.setLayout(null);


        tabLab = new JLabel[nbLigne][nbColonne];
        jp.setPreferredSize(new Dimension((nbLigne+5)*Player.SIZE,(nbColonne)*Player.SIZE));


        jp.addMouseListener(new ListenerPoser(player));
     	this.add(jp,BorderLayout.CENTER);
     	init();
        
        this.start = new JButton("Start");
     	this.start.setEnabled(false);
     	this.start.addActionListener(new ControllerStartGame(player));
     	this.add(start, BorderLayout.SOUTH);
     	
     	this.setPreferredSize(new Dimension(550,550));
    }
    
    public void init() {
    	tabLab = new JLabel[nbLigne][nbColonne];
    	jp.setPreferredSize(new Dimension((nbLigne)*50,(nbColonne)*50));
    	 for(int i =0;i<nbLigne;i++){
             for(int j =0;j<nbColonne;j++){
            	 tabLab[i][j] = new CaseLabel(player, i, j, new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/case.png")));
                 tabLab[i][j].setTransferHandler(new MyTransferHandler());
                 tabLab[i][j].setBounds((i+1)*50,(j+1)*50,50,50);
                 jp.add(tabLab[i][j]);
             }
    	 }
    }
    
    
    public Dimension getTailleLaby(){
        return new Dimension(50*(nbLigne),50*(nbColonne+2));
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
    	for (int i = 1; i < 10 ; i++ )  {
    		int x = 0;
    		int y = i+1*50;
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
        		int x = j*50;
        		int y = i*50;
        			g2.setColor(Color.white);
                	g2.fillRect(x,y, CaseLabel.size, CaseLabel.size);
            		g2.setColor(Color.black);
            		g2.drawRect(x, y, CaseLabel.size, CaseLabel.size);
        	}
        }
    	
    }

    private void modifLab(int h, int l){
        nbLigne=h;
        nbColonne=l;
        tabLab = new CaseLabel[nbLigne][nbColonne];
        jp.setPreferredSize(new Dimension((nbLigne)*50,(nbColonne)*50));

        for(int i =0;i<nbLigne;i++){
            for(int j =0;j<nbColonne;j++){
	            	if (player.getShip(i, j) != null) {
	            		int x = player.getShip(i, j).getPosX();
	            		if (player.getShip(i, j).getShipPart()[i-x] == 1)
	            			((JLabel) jp.getComponent(i*10+j)).setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/ship_1_shot.png")));

	            		else ((JLabel) jp.getComponent(i*10+j)).setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/ship_1.png")));   
	                }
                    else {
                    	if (player.getShipGrill(i,j) == 1) ((JLabel) jp.getComponent(i*10+j)).setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/case_shot.png")));	
                      	else ((JLabel) jp.getComponent(i*10+j)).setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/case.png")));	
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
        if(this.player.getListShip().size() >= 5 && !this.player.isReady()){
        	this.start.setEnabled(true);
        }

        this.modifLab(Player.SIZE,Player.SIZE);
        
    }
}
