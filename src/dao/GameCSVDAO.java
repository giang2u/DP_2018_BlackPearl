package dao;


import java.io.*;
import java.util.ArrayList;

import main.Jeu;


public class GameCSVDAO implements GameDAO {

    private static GameDAO instance = null;

    public static GameDAO getInstance(){
        if(instance == null){
            instance = new GameCSVDAO();
        }
        return instance;
    }

    @Override
    public void save(Jeu j) {
        try{
            ObjectOutputStream objectOutputStream = new ObjectOutputStream((new FileOutputStream("BlackPearl.csv")));
            //objectOutputStream.writeObject(j.getJeu); récupérer le jeu en cours..
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fichier non trouvé");
        } catch (IOException e) {
            System.out.println("Erreur IO");
        }
    }

    @Override
    public Jeu load(Jeu j) {

        try {
            Jeu jeu_charge = null;
            ArrayList<Jeu> jeuArrayList = new ArrayList<Jeu>();
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("BlackPearl.csv"));
            //Création du nouveau jeu à partir des fichiers
            try {
                jeuArrayList = ((ArrayList<Jeu>) objectInputStream.readObject());
                System.out.println("LOADING...");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //j.initNewGame(jeuArrayList);  //Creation d'un nouveau jeu à partir de la sauvegarde dans jeuArrayList*/
        } catch (FileNotFoundException e) {
            System.out.println("Fichier non trouvé");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Erreur IO");
            e.printStackTrace();
        }
        return new Jeu();
    }

}
