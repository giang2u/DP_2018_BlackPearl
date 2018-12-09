package view;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import controller.ListenerPoser;
import controller.MyTransferHandler;
import model.players.Human;
import model.players.Niveau;
import model.players.Player;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

public class VueCreaLaby extends JPanel implements Observer {

    private int nbLigne,nbColonne;
    private JLabel[][] tabLab;
    private Human player;
    private  JPanel jp;
    private JScrollPane js;

    public VueCreaLaby(Human niv, int nbLigne, int nbColonne){

        this.player = niv;
        niv.addObserver(this);

        this.nbLigne = nbLigne;
        this.nbColonne = nbColonne;

        this.setLayout(new BorderLayout());

        jp = new JPanel();
        jp.setLayout(null);


        tabLab = new JLabel[nbLigne][nbColonne];
        jp.setPreferredSize(new Dimension(nbLigne*50,nbColonne*50));

        js = new JScrollPane(jp);
        js.createHorizontalScrollBar();
        js.createVerticalScrollBar();
        this.add(js);
        jp.addMouseListener(new ListenerPoser(player));
        init();
    }
    
    public void init() {
    	tabLab = new JLabel[nbLigne][nbColonne];
    	jp.setPreferredSize(new Dimension(nbLigne*50,nbColonne*50));
    	 for(int i =0;i<nbLigne;i++){
             for(int j =0;j<nbColonne;j++){

            	 tabLab[i][j] = new CaseLabel(player, i, j, new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/case.png")));
                     
                 tabLab[i][j].setTransferHandler(new MyTransferHandler());
                 //tabLab[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                 tabLab[i][j].setBounds(i*50,j*50,50,50);
                 jp.add(tabLab[i][j]);
             }
    	 }
    }

    public Dimension getTailleLaby(){
        return new Dimension(50*nbLigne,50*nbColonne);
    }

    private void modifLab(int h, int l){

        for (int i = 0; i < nbLigne; i++) {

            for (int j = 0; j < nbColonne; j++) {
                jp.remove(tabLab[i][j]);
            }
        }
 

        nbLigne=h;
        nbColonne=l;
        tabLab = new CaseLabel[nbLigne][nbColonne];
        jp.setPreferredSize(new Dimension(nbLigne*50,nbColonne*50));

        for(int i =0;i<nbLigne;i++){
            for(int j =0;j<nbColonne;j++){
	            	if (player.getShip(i, j) != null) {
	            		int x = player.getShip(i, j).getPosX();
	            		//System.out.println(x + "     " + player.getShip(i, j).getShipPart() );
	            		if (player.getShip(i, j).getShipPart()[i-x] == 1)
	            			tabLab[i][j] = new CaseLabel(player, i, j, new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/ship_1_shot.png")));
	            		else tabLab[i][j] = new CaseLabel(player, i, j, new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/ship_1.png")));
	                    
	                }
                    else {
                    	 tabLab[i][j] = new CaseLabel(player, i, j, new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/case.png")));
                    }
                tabLab[i][j].setTransferHandler(new MyTransferHandler());
                //tabLab[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                tabLab[i][j].setBounds(i*50,j*50,50,50);
                jp.add(tabLab[i][j]);
            }
        }
        js.setViewportView(jp);
        this.add(js);
    }


    @Override
    public void update(Observable o, Object arg) {
        this.modifLab(Player.SIZE,Player.SIZE);
        this.repaint();
    }
}
