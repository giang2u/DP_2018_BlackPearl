package view;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

import controller.ListenerPoser;
import controller.MouseGlassListener;
import controller.MouseGlassMotionListener;
import controller.MyMouseAdapter;

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
	protected ArrayList<Case> lcase; 

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

		lcase = new ArrayList<>();
		lcase.add(new Case(50,400,p));
		//addMouseMotionListener(new MyMouseAdapter(lcase));

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

		Graphics2D g2d = (Graphics2D)g;
		for (Case c : lcase) {
			g2d.setColor(c.getColor());
			g2d.fillRect(c.getX(), c.getY(), Case.size*5, Case.size);
			g2d.setColor(Color.black);
			g2d.drawRect(c.getX(), c.getY(), Case.size*5, Case.size);
			System.out.println(c.getX());
		}
		//Si on n'a pas d'image à dessiner, on ne fait rien…
		if(bateau == null)
			return;

		//Dans le cas contraire, on dessine l'image souhaitée
		g2d.setComposite(transparence);
		g2d.drawImage(bateau, (int) (location.getX() - (bateau.getWidth(this)  / 2)), (int) (location.getY() - (bateau.getHeight(this) / 2)), null);

	
	}  

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		repaint();
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
