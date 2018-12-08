package dragdrop;


import javax.swing.*;
import java.awt.*;

public class Case extends JLabel {

    private int ligne,colonne, taille;
    private Niveau niveau;
    public String type;


    public Case(Niveau niveau, int ligne, int colonne, ImageIcon i){
        super();
        super.setIcon(i);
        this.ligne = ligne;
        this.colonne = colonne;
        this.niveau = niveau;
    }

    public int getTaille() {
		return taille;
	}

	public void setTaille(int size) {
		this.taille = size;
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
    	niveau.ajouterShip(ligne,colonne, taille);
    }

    public void ajouterShip(int i){
        niveau.ajouterShip(ligne,colonne, i);
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

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    //public
}
