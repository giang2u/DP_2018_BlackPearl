package dao;


import java.io.*;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


import main.Jeu;
import model.players.Player;
import model.ship.Ship;
import model.ship.Ship_centuryXVI;
import model.ship.Ship_centuryXX;


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
/*
			 JFileChooser choix = new JFileChooser();
			choix.showOpenDialog(null);

			final File f = choix.getSelectedFile();
*/
			Player p1 = j.getP1();
			Player ai = j.getAi();

			//if (f != null) {
				//String nomF = f.getAbsolutePath();
				String nomF = "BlackPearl.csv";
				PrintWriter out = new PrintWriter(new FileWriter(nomF));
				writePlayer(p1, out);
				writePlayer(ai, out);

				out.println();
				out.close(); 


				JOptionPane.showMessageDialog(null,
						"fichier " + nomF + " enregistre", "Youpi",
						JOptionPane.INFORMATION_MESSAGE) ;
			//}

		} catch (FileNotFoundException e) {
			System.out.println("Fichier non trouve");
		} catch (IOException e) {
			System.out.println("Erreur IO");
		}
	}


	@Override
	public void load(Jeu j) {
		Jeu jeu_charge = null;
		ArrayList<Jeu> jeuArrayList = new ArrayList<Jeu>();
/*
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
*/
				String nomF = "BlackPearl.csv";
				
				try {
	
					Player p1 = j.getP1();
					Player ai = j.getAi();

					BufferedReader in = new BufferedReader(new FileReader(nomF)); 
					readPlayer(p1, in, j);
					readPlayer(ai, in, j);
					in.close(); 
					
			
					System.out.println("LOADING...");


				} 
				//j.initNewGame(jeuArrayList);  //Creation d'un nouveau jeu à partir de la sauvegarde dans jeuArrayList*/
				catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null,
							"fichier " + nomF + " non trouve", "argh",
							JOptionPane.INFORMATION_MESSAGE) ;
					System.out.println("Fichier " + nomF + " non trouve");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("Erreur IO");
					e.printStackTrace();
				}


				// if
/*			} else{
				JOptionPane.showMessageDialog(null,
						"Mauvais fichier", "Erreur",
						JOptionPane.ERROR_MESSAGE) ;
			}
		}
*/	}


	public void writePlayer(Player p1, PrintWriter out)  {

		// tableau bateau
		for (int i = 0; i < p1.getShipGrill().length; i++) {
			for (int k = 0; k < p1.getShipGrill()[i].length; k++) {
				out.print(p1.getShipGrill()[i][k]);
				if (k < p1.getShipGrill()[i].length-1) out.print(",");
			}
			out.println();
		}

		// tableau des tirs
		for (int i = 0; i < p1.getHistoryGrill().length; i++) {
			for (int k = 0; k < p1.getHistoryGrill()[i].length; k++) {
				out.print( p1.getHistoryGrill()[i][k]);
				if (k < p1.getHistoryGrill()[i].length-1) out.print(",");
			}
			out.println();
		}

		// nom du joueur
		out.println(p1.getPlayerName());

		out.println(p1.getNbTireToucher());
		out.println(p1.getNbTireMiss());

		out.println(p1.isReady());
		
		out.println(p1.getEpoch());

		out.println(p1.getxClick()+","+p1.getyClick());

		// liste bateau
		for (Ship s: p1.getListShip()) {
			out.println(s.getPosX()+","+ s.getPosY()+","+s.getHp()+","+s.getSize()+","+s.isHorizontal());
		}
		out.println("fin liste bateau");


		// placement des bateaux
		for (int i = 0; i < Player.SIZE; i++) {
			for (int k = 0; k < Player.SIZE; k++) {
				if  (p1.getCheckShip()[i][k] != null) {
					int y = p1.getCheckShip()[i][k].getPosY();
					int x = p1.getCheckShip()[i][k].getPosX();
					boolean horizontal = p1.getCheckShip()[i][k].isHorizontal();
					int touche = 0;
					if (horizontal) touche = p1.getCheckShip()[i][k].getShipPart()[i - x] ;
					else touche = p1.getCheckShip()[i][k].getShipPart()[k - y] ;
					out.print( horizontal + ";"+ touche);
				}
				else out.print(0);

				if (k < Player.SIZE-1) out.print(",");
			}
			out.println();
		}

		// nombre de bateau place
		for (int i=0; i < p1.getShipCount().length; i++) {
			out.print(p1.getShipCount()[i]);
			if (i < p1.getShipCount().length-1) out.print(",");
			else out.println();
		}
		
		
		
	}

	
	/*------------------------------------------------*/
	
	
	
	public void readPlayer(Player p1, BufferedReader in, Jeu j ) throws IOException {
		String str = new String();		
		int[][] grill = new int[Player.SIZE][Player.SIZE];
		for (int i = 0; i < Player.SIZE; i++) {
			str = in.readLine();
			String[] parts1 = str.split(",");	

			for (int k = 0; k < Player.SIZE; k++) {
				grill[i][k] = Integer.parseInt(parts1[k]);
			}	
		}
		p1.setShipGrill(grill);

		int[][] history = new int[Player.SIZE][Player.SIZE];
		for (int i = 0; i < Player.SIZE; i++) {
			str = in.readLine();
			String[] parts1 = str.split(",");	
			for (int k = 0; k < Player.SIZE; k++) {
				history[i][k] = Integer.parseInt(parts1[k]);
			}	
		}
		p1.setHistoryGrill(history);

		// player name
		str = in.readLine();
		String name = str;


		// player number succesful shot
		str = in.readLine();
		p1.setNbTireToucher(Integer.parseInt(str));


		// player number miss shot
		str = in.readLine();
		p1.setNbTireMiss(Integer.parseInt(str));

		// player is ready
		str = in.readLine();
		p1.setReady(Boolean.parseBoolean(str));

		// player is ready
		str = in.readLine();
		p1.setEpoch(str);

		// player last click position
		str = in.readLine();
		String[] parts2 = str.split(",");	
		p1.setxClick((Integer.parseInt(parts2[0])));
		p1.setxClick((Integer.parseInt(parts2[1])));


		str = in.readLine();
		ArrayList<Ship> listShip = new ArrayList<>();
		while (!str.equals( "fin liste bateau" ) ) {
			String[] part = str.split(",");
			int x = Integer.parseInt(part[0]);
			int y = Integer.parseInt(part[1]);
			int hp = Integer.parseInt(part[2]);
			boolean horizontal = Boolean.parseBoolean((part[4]));
			if(j.getEpoch().toString().equals("16eme")) {
				listShip.add(new Ship_centuryXVI(x,y,hp,horizontal));
			}
			if(j.getEpoch().toString().equals("20eme")) {
				listShip.add(new Ship_centuryXVI(x,y,hp,horizontal));
			}
			str = in.readLine();
		}
		
		p1.setShipList(listShip);

		Ship[][] checkShip = new Ship[Player.SIZE][Player.SIZE];
		for (int i = 0; i < Player.SIZE; i++) {
			str = in.readLine();
			String[] parts1 = str.split(",");	
			for (int k = 0; k < Player.SIZE; k++) {
				if (parts1[k].equals("0") ) checkShip[i][k] = null;
				else {
					String parts12[] = parts1[k].split(";");
					if(j.getEpoch().toString().equals("16eme")) {
						checkShip[i][k] = new Ship_centuryXVI(i,k,1,Boolean.parseBoolean(parts12[0]));
						if (parts12[1].equals("1") ) checkShip[i][k].setShipPart(i, k);
					}
					if(j.getEpoch().toString().equals("20eme")) {
						checkShip[i][k] = new Ship_centuryXX(i,k,1,Boolean.parseBoolean(parts12[0]));
						if (parts12[1].equals("1") ) checkShip[i][k].setShipPart(i, k);
					}
				}
			}	
		}
		p1.setCheckShip(checkShip);

		str = in.readLine();
		String[] parts3 = str.split(",");
		int[] shipCount = new int[5];
		for (int i=0; i < parts3.length; i++) {
			shipCount[i] = Integer.parseInt(parts3[i]);
		}
		p1.setShipCount(shipCount);
	}


}

