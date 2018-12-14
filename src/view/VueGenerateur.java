package view;


import javax.swing.*;

import main.Jeu;
import model.players.Human;
import model.players.Niveau;
import model.players.Player;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class VueGenerateur extends JFrame {

    private Human player;
    private VueObjets vo;
    private VueCreaLaby vc;
    private final static int HAUTEUR = 1000;
    private final static int LARGEUR = HAUTEUR+130;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    public VueGenerateur(Human p,Jeu j) {

        this.player = p;

        setTitle("Generateur de Monde");
        setSize(LARGEUR, HAUTEUR);
        setLocation((screen.width-this.getSize().width)/2,(screen.height-this.getSize().height)/2);

        vo = new VueObjets(player, j);
        vc = new VueCreaLaby(player,10, 10);

        //this.setLayout(new BoxLayout());
        //this.add(vo,BorderLayout.LINE_START);

        // BoxLayout boxlayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        //this.setLayout(boxlayout);

        JSplitPane js = new JSplitPane(SwingConstants.VERTICAL, vo, vc);
        this.add(js);
        js.setEnabled(false);

        // this.setSize(vc.getTailleLaby().width+130,vc.getTailleLaby().height+45);
        // this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
    }

    public VueCreaLaby getCreaLaby(){
        return vc;
    }
    
    public static void main(String[] args) {
    	new VueGenerateur(new Human("oui"), new Jeu());
    }
    
}
