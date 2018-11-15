package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.players.Player;
import controller.ListenerPoser;
import controller.ListenerShot;
import controller.ShipGridController;
import controller.ShotHistoryGridController;

public class ShotHistoryGridView extends JPanel implements Observer{
	
	protected Player player;
    protected JButton[][] grid  = new JButton[10][10];
    protected JPanel[] panels = new JPanel[10];
    
    public ShotHistoryGridView(Player p) {
    	
    	this.player = p;
    	JPanel contentPane = new JPanel();
    	for (int i = 0; i < panels.length; i++) {
            panels[i] = new JPanel();
        }
    	
    	// initialize button 100 casse 
        for(int i = 0; i < Player.SIZE; i++) {
        	for(int j = 0; j < Player.SIZE; j++){
                grid[i][j] = new JButton("~");
                grid[i][j].addActionListener(new ListenerShot(this.player));
                panels[i].setLayout(new FlowLayout(FlowLayout.LEFT));
                panels[i].add(grid[i][j]);
        	}
        }
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        for (JPanel jPanel : panels) {
            contentPane.add(jPanel);
        }
        
        this.add(contentPane);


    	
		this.player.addObserver(this);
    }

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
