package controller;

import javax.swing.*;

import view.VueObjets;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class MyMouseAdapter extends MouseAdapter {

    VueObjets vo;

    public MyMouseAdapter(VueObjets vueObjets) {
        super();
        this.vo = vueObjets;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        JComponent comp = (JComponent) e.getSource();
        TransferHandler th = comp.getTransferHandler();

        th.exportAsDrag(comp, e, TransferHandler.MOVE);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        super.mouseWheelMoved(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
    }
}
