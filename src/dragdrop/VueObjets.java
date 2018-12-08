package dragdrop;


import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class VueObjets extends JPanel{
	
	private ImageIcon ship;
	private ImageIcon ship2;
	private ImageIcon ship3;
	private ImageIcon ship4;
	private ImageIcon ship5;
    private ImageIcon mur;
    private ImageIcon dragon;
    private ImageIcon soldat;
    private ImageIcon heros;
    private ImageIcon herbe;
    private ImageIcon tresor;
    private ImageIcon epee;
    private ImageIcon piege;

    private static int NBOBJET = 2;

    private Case labelShip;
    private Case labelShip2;
    private Case labelShip3;
    private Case labelShip4;
    private Case labelShip5;
    private Case labelMur;
    private Case labelSoldat;
    private Case labelDragon;
    private Case labelHeros;
    private Case labelHerbe;
    private Case labelTresor;
    private Case labelEpee;
    private Case labelPiege;

    private JTextField lon;
    private JTextField haut;

    private Niveau niv;


    public VueObjets(final Niveau niv) {

        this.niv = niv;

        this.setLayout(new BorderLayout());

        JPanel objet = new JPanel();
        
        objet.setLayout(new BoxLayout(objet, BoxLayout.Y_AXIS));

        Border borderShip=BorderFactory.createTitledBorder("Ship list");
        objet.setBorder(borderShip);
        
        ship = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/ship_1.png"));
        ship2 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/ship_2.png"));
        ship3 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/ship_3.png"));
        ship4 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/ship_4.png"));
        ship5 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/ship_5.png"));

        objet.setLayout(new GridLayout(5,1));

        labelShip = new Case(niv, -1, -1, ship);
        labelShip2 = new Case(niv, -1, -1, ship2);
        labelShip3 = new Case(niv, -1, -1, ship3);
        labelShip4 = new Case(niv, -1, -1, ship4);
        labelShip5 = new Case(niv, -1, -1, ship5);

        labelShip.setType("ship");
        labelShip2.setType("ship2");
        labelShip3.setType("ship3");
        labelShip4.setType("ship4");
        labelShip5.setType("ship5");

        labelShip.setName("noChange");
        labelShip2.setName("noChange");
        labelShip3.setName("noChange");
        labelShip4.setName("noChange");
        labelShip5.setName("noChange");



        this.dragAndDrop();

        objet.add(labelShip);
        objet.add(labelShip2);
        objet.add(labelShip3);
        objet.add(labelShip4);
        objet.add(labelShip5);
        this.add(objet, BorderLayout.CENTER);

        this.setVisible(true);
    }

    private void dragAndDrop(){
    	
    	labelShip.setTransferHandler(new MyTransferHandler());
    	labelShip.addMouseListener(new MyMouseAdapter(this));
    	
    	labelShip2.setTransferHandler(new MyTransferHandler());
    	labelShip2.addMouseListener(new MyMouseAdapter(this));
    	
    	labelShip3.setTransferHandler(new MyTransferHandler());
    	labelShip3.addMouseListener(new MyMouseAdapter(this));
    	
    	labelShip4.setTransferHandler(new MyTransferHandler());
    	labelShip4.addMouseListener(new MyMouseAdapter(this));
    	
    	labelShip5.setTransferHandler(new MyTransferHandler());
    	labelShip5.addMouseListener(new MyMouseAdapter(this));
    	
    }
}
