package model;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Case extends JPanel {
	
	private int x, y;
	public static int size = 100;
	public Color color = Color.white;
	
	public Case(int x,int y){
		//this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.x=x;this.y=y;
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

}
