package view;



import javax.swing.*;
import javax.swing.border.Border;

import controller.ControllerAiDifficulty;
import controller.MyMouseAdapter;
import controller.MyTransferHandler;
import main.Jeu;
import model.players.Human;
import model.players.Niveau;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class VueObjets extends JPanel implements Observer{
	
	private ImageIcon ship2;
	private ImageIcon ship3;
	private ImageIcon ship4;
	private ImageIcon ship5;

    private static int NBOBJET = 2;

    private CaseLabel labelShip2;
    private CaseLabel labelShip3;    
    private CaseLabel labelShip3_2;
    private CaseLabel labelShip4;
    private CaseLabel labelShip5;


    private Human player;


    public VueObjets(Human player, Jeu j) {
        this.player = player;

        this.setLayout(new BorderLayout());

        JPanel objet = new JPanel();
        
        objet.setLayout(new BoxLayout(objet, BoxLayout.Y_AXIS));

        Border borderShip=BorderFactory.createTitledBorder("Ship list");
        objet.setBorder(borderShip);
        
        ship2 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/ship_2.png"));
        ship3 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/ship_3.png"));
        ship4 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/ship_4.png"));
        ship5 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/ship_5.png"));

        objet.setLayout(new GridLayout(5,1));

        labelShip2 = new CaseLabel(player, -1, -1, ship2);
        labelShip3 = new CaseLabel(player, -1, -1, ship3);
        labelShip3_2 = new CaseLabel(player, -1, -1, ship3);
        labelShip4 = new CaseLabel(player, -1, -1, ship4);
        labelShip5 = new CaseLabel(player, -1, -1, ship5);

        labelShip2.setType("ship2");
        labelShip3.setType("ship3");
        labelShip3_2.setType("ship3");
        labelShip4.setType("ship4");
        labelShip5.setType("ship5");

        labelShip2.setName("ship2");
        labelShip3.setName("ship3");
        labelShip3_2.setName("ship3_2");
        labelShip4.setName("ship4");
        labelShip5.setName("ship5");


        this.dragAndDrop();

        

        labelShip2.setTaille(2);
        labelShip3.setTaille(3);
        labelShip3_2.setTaille(3);
        labelShip4.setTaille(4);
        labelShip5.setTaille(5);


        objet.add(labelShip2);
        objet.add(labelShip3);
        objet.add(labelShip3_2);
        objet.add(labelShip4);
        objet.add(labelShip5);

        this.add(objet, BorderLayout.CENTER);
   


        this.setVisible(true);
    }

    private void dragAndDrop(){
    	
    	labelShip2.setTransferHandler(new MyTransferHandler(player));
    	labelShip2.addMouseListener(new MyMouseAdapter());
    	
    	labelShip3.setTransferHandler(new MyTransferHandler(player));
    	labelShip3.addMouseListener(new MyMouseAdapter());
    	
    	labelShip3_2.setTransferHandler(new MyTransferHandler(player));
    	labelShip3_2.addMouseListener(new MyMouseAdapter());
    
    	labelShip4.setTransferHandler(new MyTransferHandler(player));
    	labelShip4.addMouseListener(new MyMouseAdapter());
    	
    	labelShip5.setTransferHandler(new MyTransferHandler(player));
    	labelShip5.addMouseListener(new MyMouseAdapter());
    	
    }
    
    @Override
	public void update(Observable o, Object arg) {

	}
}
