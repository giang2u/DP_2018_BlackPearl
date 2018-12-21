package view;


import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import main.Jeu;

import controller.ControllerAiDifficulty;
import controller.ControllerEpochChoice;
import controller.ControllerLoad;
import controller.ControllerRestart;
import controller.ControllerSave;


public class VueMenu extends JMenuBar implements Observer{

	private Jeu jeu;
	private JMenuItem centuryXVI;
	private JMenuItem centuryXX;
	public VueMenu(Jeu j) {
		jeu = j;
		JMenu menu = new JMenu("pineapple");
		
		
		JMenuItem restart = new JMenuItem("Restart");
		restart.addActionListener(new ControllerRestart(jeu));
		menu.add(restart);
		
		JMenuItem difficulty = new JMenuItem("Difficulty");
		difficulty.addActionListener(new ControllerAiDifficulty(jeu));
		menu.add(difficulty);
		
		JMenuItem save = new JMenuItem("Sauvegarder");
		save.addActionListener(new ControllerSave(jeu));
		menu.add(save);
		
		JMenuItem load = new JMenuItem("Charger");
		load.addActionListener(new ControllerLoad(jeu));
		menu.add(load);
		
		
		JMenu menuepoch = new JMenu("Epoch");
		
		
		this.centuryXVI = new JMenuItem("Century XVI");
		centuryXVI.addActionListener(new ControllerEpochChoice(jeu, this.centuryXVI.getText()));
		menuepoch.add(centuryXVI);
		
		this.centuryXX = new JMenuItem("Century XX");
		centuryXX.addActionListener(new ControllerEpochChoice(jeu,this.centuryXX.getText()));
		menuepoch.add(centuryXX);
		
		
		this.add(menu);
		//this.add(menuepoch);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
