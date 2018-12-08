package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.players.Player;

import controller.ListenerShot;

public class Case extends JButton {
	
	private int x, y;
	public static int size = 50;
	public Color color = Color.white;
	
	
	public Case(int x,int y){
		super("");
		this.x=x;
		this.y=y;
		setPreferredSize(new Dimension(size, size));
	}

	
	public Case(int x,int y, Player p){
		super("");
		addMouseListener(new ListenerShot(p));
		this.x=x;
		this.y=y;
		setPreferredSize(new Dimension(size, size));
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
