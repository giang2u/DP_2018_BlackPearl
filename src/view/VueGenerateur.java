package view;


import javax.swing.*;

import main.Jeu;
import model.players.Human;
import model.players.Niveau;
import model.players.Player;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class VueGenerateur extends JPanel {

    private Human player;
    private VueObjets vo;
    private VueCreaLaby vc;
    private final static int HAUTEUR = 1000;
    private final static int LARGEUR = HAUTEUR+130;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    public VueGenerateur(Human p,Jeu j) {

        this.player = p;
        System.out.println("hello gene");
        //setTitle("Generateur de Monde");
        setSize(LARGEUR, HAUTEUR);
        setLocation((screen.width-this.getSize().width)/2,(screen.height-this.getSize().height)/2);

        vo = new VueObjets(player, j);
        vc = new VueCreaLaby(player,10, 10, j);


        JSplitPane js = new JSplitPane(SwingConstants.VERTICAL, vo, vc);
        this.add(js);
        js.setEnabled(false);

    }

    public VueCreaLaby getCreaLaby(){
        return vc;
    }
    
    public static void main(String[] args) {
    	new VueGenerateur(new Human("oui"), new Jeu());
    }
    
}
