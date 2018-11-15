package view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controller.ListenerPoser;

import model.players.Player;
import model.ship.Ship;

public class ShipSettingView extends JPanel implements Observer {
	
	protected JButton poser = new JButton("Poser") ;
	protected Player player;
	protected JTextField x;
	protected JTextField y;
    protected JButton[] grid;
    protected JPanel[] panels = new JPanel[6];
    
	public ShipSettingView(Player p){
		this.player = p;
		
		this.poser.addActionListener(new ListenerPoser(player));
    	JPanel contentPane = new JPanel();
    	int j = 0;
    	
    	// initialize panels 
        for (int i = 0; i < panels.length; i++) {
            panels[i] = new JPanel();
        }

    	
		for( Ship ship : this.player.getListShip() ){
			this.grid = new JButton[ship.getSize()];
			this.x = new JTextField("pos X");
			this.y = new JTextField("pos Y");
			for( int i = 0; i < ship.getSize(); i++){
				this.grid[i] = new JButton("B");
				this.panels[j].add(this.grid[i]);
				this.panels[j].add(x);
				this.panels[j].add(y);
				
			}
			JRadioButton h = new JRadioButton("Horizontal");
			this.panels[j].add(h);
			j++;
		}
		this.panels[this.panels.length-1].add(this.poser);
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
