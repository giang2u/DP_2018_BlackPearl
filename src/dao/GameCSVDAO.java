package dao;


import java.io.*;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


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
			JFileChooser choix = new JFileChooser();
			choix.showOpenDialog(null);

			final File f = choix.getSelectedFile();
			
			if (f != null) {
				String nomF = f.getAbsolutePath();
				ObjectOutputStream objectOutputStream = new ObjectOutputStream((new FileOutputStream( nomF +"BlackPearl.csv")));
				//objectOutputStream.writeObject(j.getJeu); récupérer le jeu en cours..
				objectOutputStream.close();

			

			JOptionPane.showMessageDialog(null,
					"fichier enregistre", "Youpi",
					JOptionPane.INFORMATION_MESSAGE) ;
			}

		} catch (FileNotFoundException e) {
			System.out.println("Fichier non trouvé");
		} catch (IOException e) {
			System.out.println("Erreur IO");
		}
	}


	@Override
	public Jeu load(Jeu j) {
		Jeu jeu_charge = null;
		ArrayList<Jeu> jeuArrayList = new ArrayList<Jeu>();
		JFileChooser jf = new JFileChooser() ;
		jf.setCurrentDirectory(new File("/")); 
		jf.changeToParentDirectory(); 
		int reponse = jf.showOpenDialog(null) ;
		// parent : composant dont d�epend jf
		if (reponse == JFileChooser.APPROVE_OPTION) {
			File fichier = jf.getSelectedFile() ;
			String[] parts = fichier.getName().split(("\\."));

			if (parts[1].equals("csv")) {

				// String nomF = fichier.getName();

				String nomF = fichier.getAbsolutePath();

				try {
					System.out.println(fichier.getName());
					ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fichier.getName()));
					//Création du nouveau jeu à partir des fichiers

					jeuArrayList = ((ArrayList<Jeu>) objectInputStream.readObject());
					System.out.println("LOADING...");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				//j.initNewGame(jeuArrayList);  //Creation d'un nouveau jeu à partir de la sauvegarde dans jeuArrayList*/
				catch (FileNotFoundException e) {
					System.out.println("Fichier non trouvé");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("Erreur IO");
					e.printStackTrace();
				}


				// if
			} else{
				JOptionPane.showMessageDialog(null,
						"Mauvais fichier", "Erreur",
						JOptionPane.ERROR_MESSAGE) ;
			}
		}
		return new Jeu();
	}
}

