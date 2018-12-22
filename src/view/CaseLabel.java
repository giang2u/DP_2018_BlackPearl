package view;

import javax.swing.*;

import model.players.Human;
import model.players.Niveau;

import java.awt.*;

public class CaseLabel extends JLabel {

    private int ligne,colonne, taille;
    private Human player;
    public String type;
    public static int size = 50;
    public boolean touche = false;


    public CaseLabel(Human niveau, int ligne, int colonne, ImageIcon i){
        super();
        super.setIcon(i);
        this.ligne = ligne;
        this.colonne = colonne;
        this.player = niveau;
    }

    public int getTaille() {
		return taille;
	}

	public void setTaille(int t) {
		this.taille = t;
	}

	public ImageIcon getIcon() {
        return (ImageIcon) super.getIcon();
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    
    public void ajouterHerbe() {
    	player.ajouterShip(ligne,colonne, taille);
    }

    public void ajouterShip(int i){
    	player.ajouterShip(ligne,colonne, i);
    	setTaille(i);
    	setLigne(ligne);
    	setColonne(colonne);
    }
    
    public void verticalShip() {
    	
    }

    public void HorizontalShip() {
    	
    }
    
    public void ajouterElement(){
        switch(type) {
            case "ship":
                this.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/ship_1.png")));
                ajouterShip(1);
                break;
            case "ship2":
                this.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/ship_1.png")));
                ajouterShip(2);
                break;
            case "ship3":
                this.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/ship_1.png")));
                ajouterShip(3);
                break;
            case "ship4":
                this.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/ship_1.png")));
                ajouterShip(4);
                break;
            case "ship5":
                this.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/ship_1.png")));
                ajouterShip(5);
                break;
                
        }

    }
    
    public void EnemyShot() {
    	if (touche) this.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/ship_1_shot.png")));
    	else this.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./img/case_shot.png")));
    }
    
    public Icon getIconLabel() {
    	return this.getIcon();
    }
    
    public boolean isTouche() {
		return touche;
	}

	public void setTouche(boolean touche) {
		this.touche = touche;
	}

	public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

	public void setLigne(int ligne) {
		this.ligne = ligne;
	}

	public void setColonne(int colonne) {
		this.colonne = colonne;
	}

}