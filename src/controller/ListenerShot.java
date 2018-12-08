package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import dragdrop.Cases;
import view.Case;

import model.players.Player;

public class ListenerShot implements MouseListener {
	private Player p;
	private Case[][] lcase;
	private Cases[][] lcases;
	public ListenerShot(Player p){
		this.p = p; 
	}
	
	public ListenerShot(Player p,Case[][] lcase){
		this.p = p; 
		this.lcase = lcase;
	}
	
	
	public ListenerShot(Player p,Cases[][] lcase){
		this.p = p; 
		this.lcases = lcase;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println(e.getX()/50 +"**" + e.getY()/50);
		if (lcase != null) {
			if(p.cibleToucher(e.getX()/50, e.getY()/50)){
				lcase[e.getX()/50][ e.getY()/50].setColor(Color.red);
				p.toucher();
				System.out.println("tire toucher" + p.getNbTireToucher());
			}
			else{
				p.rater();
				System.out.println("tire louper" + p.getNbTireMiss());
			}
		}
		/*
		if (lcases != null) {
			//lcases[e.getX()/50][ e.getY()/50].setColor(Color.red);
			if(p.cibleToucher(e.getX()/50, e.getY()/50)){
				lcases[e.getX()/50][ e.getY()/50].setColor(Color.red);
				p.toucher();
				System.out.println("tir toucher" + p.getNbTireToucher());
			}
			else{
				p.rater();
				System.out.println("tir louper" + p.getNbTireMiss());
			}
		}*/
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
