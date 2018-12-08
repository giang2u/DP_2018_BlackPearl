package dragdrop;



import javax.swing.*;

import model.ship.Ship;
import model.ship.Ship_centuryXVI;

import java.io.*;
import java.util.ArrayList;
import java.util.Observable;

public class Niveau extends Observable implements Serializable {

    private int[][] lesMurs;
    private Ship[][] checkShip;
    private ArrayList<Ship> shipList;
    private String nom;
    private int xFin,yFin,xDebut,yDebut;
    private int hauteur =0,largeur=0;
    private int[] shipCount;

    public Niveau(String nom, int nbLigne, int nbColonne) {
        this.nom = nom;
        this.xDebut = -1;
        this.yDebut = -1;
        this.xFin = -1;
        this.yFin = -1;
        setDimensionInit(nbLigne, nbColonne);
        shipCount = new int[5];
    }

    public int[][] getLesMurs() {
        return lesMurs;
    }

    public ArrayList<Ship> getLesMonstres() {
        return shipList;
    }


    public int getxDebut() {
        return xDebut;
    }

    public int getyDebut() {
        return yDebut;
    }
    
    // try to placed ship where we dropped 
    public void ajouterShip(int x,int y,int taille){
    	
    	
		// if there we can place it
		if (checkCount(taille) && checkPlacedShip(x,y,taille, true) ) {
			// place the ship
	    	for (int i = 0; i < taille; i++) {
	    		checkShip[x + i][y] = new Ship_centuryXVI(x,y,taille,true);
	    	}
			// increment ship register
			shipCount[taille-1]++;
		}
        setChanged();
        notifyObservers();
    }
    
    
    // search if there is already a ship placed 
    // true if no ship
    public boolean checkPlacedShip(int x,int y,int taille, boolean horizontal) {
    	
		// if the ship does not exceed the map size
		boolean noShip = horizontal==true ? (x+taille < largeur +1) : (y-taille >= 0);
		if (noShip) {
    		int i = 0;
    		while(i < taille && noShip) {
    			noShip = horizontal==true ? (checkShip[x + i][y] == null) : (checkShip[x][y + i] == null);
    			i++;
    		}
		}
    	return noShip;
    }
    
    // rule allow one ship 2,4,5 and two ship 3
    public boolean checkCount(int size) {
    	boolean check = false;
    	if (size == 2 || size == 4 || size == 5) check = shipCount[size-1] < 1;
    	if (size == 3) check = shipCount[size-1] < 2;
    	return check;
    }
    
    
    public void verticalShip(int x,int y, int taille) {
    	if (checkShip[x][y] != null) {
    		
    	}
    }
    

    public int getHauteur() {
        return hauteur;
    }

    public void setDimensionInit(int hauteur, int largeur) {
        xDebut = -1;
        yDebut = -1;
        this.hauteur = hauteur;
        this.largeur = largeur;
        lesMurs = new int[hauteur][largeur];
        checkShip = new Ship[hauteur][largeur];
        shipList = new ArrayList<Ship>();
        setChanged();
        notifyObservers();
    }

    public void setHauteurLargeur(int hauteur, int largeur){
        this.hauteur = hauteur;
        this.largeur = largeur;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getMur(int i,int j){
        return lesMurs[i][j];
    }

    public Ship getShip(int i,int j){
        return checkShip[i][j];
    }


    public int getXHeros(){
        return xDebut;
    }
    public int getYHeros(){
        return  yDebut;
    }

    public void suppCase(int x, int y) {

        checkShip[x][y] = null;
        lesMurs[x][y] = (Integer) null;
        shipList.remove(checkShip[x][y]);

        if(this.xDebut == x && this.yDebut == y){
            this.xDebut = -1;
            this.yDebut = -1;
        }

        if(this.xFin == x && this.yFin == y){
            this.xFin = -1;
            this.yFin = -1;
        }
    }

    public void serialize() throws IOException {

        JFileChooser dialogue = new JFileChooser(new File("."));
        PrintWriter sortie;
        File fichier = null;

        if (dialogue.showOpenDialog(null)==
                JFileChooser.APPROVE_OPTION) {
            fichier = dialogue.getSelectedFile();
            sortie = new PrintWriter
                    (new FileWriter(fichier.getPath(), true));
            sortie.close();
        }


        if (fichier !=null) {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier));
            // sérialization de l'objet
            oos.writeObject(this);
        }
    }

    public void deSerilize() throws IOException, ClassNotFoundException {


        JFileChooser dialogue = new JFileChooser(new File("."));
        PrintWriter sortie;
        File fichier = null;

        if (dialogue.showOpenDialog(null)==
                JFileChooser.APPROVE_OPTION) {
            fichier = dialogue.getSelectedFile();
            sortie = new PrintWriter
                    (new FileWriter(fichier.getPath(), true));
            sortie.close();
        }

        if (fichier !=null) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier));

            // désérialization de l'objet
            Niveau n = (Niveau) ois.readObject();
            Charger(n);
        }

        // fermeture du flux dans le bloc finally
    }

    private void Charger(Niveau n) {
        setHauteurLargeur(n.hauteur,n.largeur);
        this.lesMurs = n.lesMurs;
        this.checkShip = n.checkShip;
        this.shipList = n.shipList;
        this.nom = n.nom;
        this.xFin = n.xFin;
        this.yFin = n.yFin;
        this.xDebut = n.xDebut;
        this.yDebut = n.yDebut;
        setChanged();
        notifyObservers();
    }
}
