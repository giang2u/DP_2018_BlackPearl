package controller;


import javax.swing.*;

import model.players.Player;

import view.Case;
import view.CaseLabel;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class MyTransferHandler extends TransferHandler {
	 protected CaseLabel c;
	 protected Player p;
	 protected static int cpt ;
	 
	 public MyTransferHandler(Player p){
		 this.p = p;
	 }

    //private Image image;
    //private final DataFlavor flavors[] = { DataFlavor.imageFlavor };
    private final DataFlavor flavors[] = { DataFlavor.imageFlavor };

    /**
     * Méthode permettant à l'objet de savoir si les données reçues
     * via un drop sont autorisées à être importées
     * @param info
     * @return boolean
     */
    public boolean canImport(TransferHandler.TransferSupport info) {
        if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return false;
        }
        return true;
    }

    /**
     * C'est ici que l'insertion des données dans notre composant est réalisée
     * @param support
     * @return boolean
     */
    public boolean importData(TransferHandler.TransferSupport support){
        /*if(!canImport(support))
            return false;*/
    	
        Transferable data = support.getTransferable();
        String str = "";

        try {
            str = (String)data.getTransferData(DataFlavor.stringFlavor);
            c = (CaseLabel)support.getComponent();
            c.setType(str);
            c.ajouterElement();
            cpt++;
        } catch (UnsupportedFlavorException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
/*
        if(!(support.getComponent().getName() == "noChange")){
            Case c = (Case)support.getComponent();
            c.setType(str);
            c.ajouterElement();
        }*/
        return true;
    }

    protected Transferable createTransferable(JComponent c) {
        //On retourne un nouvel objet implémentant l'interface Transferable
        //StringSelection implémente cette interface,  nous l'utilisons donc
        return new StringSelection(((CaseLabel)c).getType());
    }

 

    /**
     * Cette méthode est invoquée à la fin de l'action DROP
     * Si des actions sont à faire ensuite, c'est ici qu'il faudra coder le comportement désiré
     * @param c
     * @param t
     * @param action
     */
    protected void exportDone(JComponent c, Transferable t, int action){
    	int taille  = p.getListShip().size();
    	if(action == MOVE 
    	&& taille > 0
    	&& p.getListShip().get(taille-1).getSize() ==  ((CaseLabel) c).getTaille()
    	&& taille == cpt){
        	c.setVisible(false);
        	c.setEnabled(false);
    	}
    	else{
    		cpt--;
    	}
    	
    }

    /**
     * Cette méthode est utilisée afin de déterminer le comportement
     * du composant vis-à-vis du drag'n drop : nous retrouverons
     * nos variables statiques COPY, MOVE, COPY_OR_MOVE, LINK ou NONE
     * @param c
     * @return int
     */
    public int getSourceActions(JComponent c) {
        return MOVE;
    }
}