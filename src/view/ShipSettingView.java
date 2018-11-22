package view;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

import controller.ListenerPoser;
import controller.MouseGlassListener;
import controller.MouseGlassMotionListener;

import model.players.Player;
import model.ship.Ship;

public class ShipSettingView extends JPanel implements Observer {
	
	private BufferedImage bateau;
	private Point location;
	private Composite transparence;
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
	
	public void setLocation(Point location){
	    this.location = location;        

	  }

	  public void setImage(BufferedImage image){

	    bateau = image;

	  }
	  
	  public void paintComponent(Graphics g){
		    //Si on n'a pas d'image à dessiner, on ne fait rien…
		    if(bateau == null)
		      return;

		    //Dans le cas contraire, on dessine l'image souhaitée
		    Graphics2D g2d = (Graphics2D)g;
		    g2d.setComposite(transparence);
		    g2d.drawImage(bateau, (int) (location.getX() - (bateau.getWidth(this)  / 2)), (int) (location.getY() - (bateau.getHeight(this) / 2)), null);

		  }  

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	public void nepasregarder() {
	//  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	       /* 
	        //Notre textearea avec son contenu déplaçable

	        JTextArea label = new JTextArea("Texte déplaçable !");
	        label.setPreferredSize(new Dimension(300, 130));
	        
	        label.setDragEnabled(true);
	        
	      //On crée le premier textfield avec contenu déplaçable

	        JTextField text = new JTextField();

	        //--------------------------------------------------

	        text.setDragEnabled(true);

	        //--------------------------------------------------

	        //Et le second, sans

	        JTextField text2 = new JTextField();
	        
	        contentPane.add(label);
	        contentPane.add(text, BorderLayout.NORTH);
	        contentPane.add(text2, BorderLayout.SOUTH);
	        
	      //Afin de ne peindre que ce qui nous intéresse

	        setOpaque(false);

	        //On définit la transparence

	        transparence = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.55f);
	        
	        ImageIcon imic = new ImageIcon("ship_list.png");
		       //bateau = new BufferedImage(imic.getIconWidth(),imic.getIconHeight(),BufferedImage.TYPE_INT_ARGB);
		       JButton b = new JButton();
		       b.setIcon(imic);
		       
		       b.addMouseListener(new MouseGlassListener(this));
		       b.addMouseMotionListener(new MouseGlassMotionListener(this));
		       
		       b.setTransferHandler(new TransferHandler("prout"));
		       contentPane.add(b);*/
		       //  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	}

}
