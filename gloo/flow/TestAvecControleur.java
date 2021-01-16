package gloo.flow;

import javax.swing.SwingUtilities;

import gloo.flow.control.Controleur;
import gloo.flow.hmi.Fenetre;


public class TestAvecControleur implements Runnable {

	public static void main( String[] args ) {
        SwingUtilities.invokeLater( new TestAvecControleur() );
	}

    @Override
    public void run() {
        new Fenetre( new Controleur() );
    }
}
