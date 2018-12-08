package dragdrop;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.players.Player;

import controller.ListenerShot;

public class Cases extends JLabel {
	
	private int x, y;
	public static int size = 50;
	public Color color = Color.white;
	
	public Cases(int x,int y/*, Player p*/){
		super("");
		//addMouseListener(new ListenerShot(p));
		this.x=x;
		this.y=y;
		//setPreferredSize(new Dimension(size, size));
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;

	}
	
	
	   /*
    public void paintComponent(Graphics g) {
    	Graphics2D g2 = (Graphics2D) g;
    	
    	 for (int i = 0; i < 11 ; i++ )  {
           	for (int j = 0; j < 11 ; j++ ) {
           		
           		int x = lcase[i][j].getX();
        		int y = lcase[i][j].getY();

        		g2.setColor(lcase[i][j].getColor());
        		g2.fillRect(x,y, view.Case.size, view.Case.size);
        		g2.setColor(Color.black);
        		g2.drawRect(x, y, view.Case.size, view.Case.size);
           	}
         }
    }
    
        /*
        p = new Human("oui");
    	lcase = new Cases[11][11];
    	
    	 for (int i = 0; i < 11 ; i++ )  {
          	for (int j = 0; j < 11 ; j++ ) {
          		lcase[i][j] = new Cases(i*Cases.size, j*Cases.size);
          		
          		lcase[i][j].setTransferHandler(new MyTransferHandler());
          		
          		if (i == 0 || j == 0) ;
          		else   lcase[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
          		lcase[i][j].setBounds(i*50,j*50,50,50);
                jp.add(lcase[i][j]);
          	}
    	 }
    	addMouseListener(new ListenerShot(p,lcase));*/
    

}
