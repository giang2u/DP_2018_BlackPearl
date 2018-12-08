package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import view.CaseLabel;

public class MyMouseAdapterTest extends MouseAdapter {
	
	private ArrayList<CaseLabel> lcase;
/*
    @Override
    public void mousePressed(MouseEvent e) {
        preX = rect.x - e.getX();
        preY = rect.y - e.getY();

        if (rect.contains(e.getX(), e.getY())) {
            updateLocation(e);
        } else {
            pressOut = true;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!pressOut) {
            updateLocation(e);
        } else {
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (rect.contains(e.getX(), e.getY())) {
            updateLocation(e);
        } else {
            pressOut = false;
        }
    }

    public void updateLocation(MouseEvent e) {
        rect.setLocation(preX + e.getX(), preY + e.getY());
        checkRect();

        repaint();
    }
    
    https://stackoverflow.com/questions/13220971/how-to-drag-object
*/

}
