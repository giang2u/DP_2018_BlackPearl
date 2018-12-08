package controller;



import javax.swing.*;

import model.players.Niveau;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControllerVueObjet implements ActionListener {

    private String ope;
    private Niveau niv;
    private JTextField lon, haut;

    public ControllerVueObjet(Niveau niv,String ope){
        this.ope = ope;
        this.niv = niv;
    }

    public ControllerVueObjet(Niveau niv, String ope, JTextField lon, JTextField haut) {
        this.ope = ope;
        this.niv = niv;
        this.haut = haut;
        this.lon = lon;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
