package game;

import javax.swing.SwingUtilities;

import control.Controleur;
import hmi.Fenetre;

/**
 * Programme de test du controleur bouchon du jeu FlowFree
 * 
 * @author Dominique Marcadet
 * @version 1.1
 *
 */
public class Game implements Runnable {

	public static void main( String[] args ) {
        SwingUtilities.invokeLater( new Game() );
	}

    @Override
    public void run() {
        new Fenetre( new Controleur() );
    }
}