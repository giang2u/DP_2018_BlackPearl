package model.players;



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
    private int hauteur =0,largeur=0;
    private int[] shipCount;

    public Niveau(String nom, int nbLigne, int nbColonne) {
        this.nom = nom;
        setDimensionInit(nbLigne, nbColonne);
        shipCount = new int[5];
    }

    public int[][] getLesMurs() {
        return lesMurs;
    }

    public ArrayList<Ship> getLesMonstres() {
        return shipList;
    }


    

    public int getHauteur() {
        return hauteur;
    }

    public void setDimensionInit(int hauteur, int largeur) {
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




  /*

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
    }*/
}
