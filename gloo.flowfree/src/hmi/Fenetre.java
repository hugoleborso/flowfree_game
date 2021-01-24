package hmi;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import control.IControleur;
import enumerations.Direction;
import game.Statistiques;

/**
 * Fenêtre de l'IHM pour le jeu FlowFree
 * 
 * @author Dominique Marcadet
 * @version 1.1
 *
 */
@SuppressWarnings( "serial" )
public class Fenetre extends JFrame implements KeyListener {

    public static final int COTE_FENETRE = 500;
    private static final int HAUTEUR_BARRE_FENETRE = 20;
    static final int HAUTEUR_ZONE_TEXTE = 40;
    private IControleur controleur;

    public Fenetre( IControleur controleur ) {
        this.controleur = controleur;
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setPreferredSize( new Dimension( COTE_FENETRE, COTE_FENETRE + HAUTEUR_BARRE_FENETRE + HAUTEUR_ZONE_TEXTE ));
        this.setTitle( "FreeFlow" );

        this.add( new Panneau( controleur ));
        this.addKeyListener( this );

        this.pack();
        this.setVisible( true );
    }

    @Override
    public void keyTyped( KeyEvent e ) {
        // nothing
    }

    @Override
    public void keyPressed( KeyEvent e ) {
        Direction direction = switch( e.getKeyCode() ) {
            case KeyEvent.VK_UP    -> Direction.HAUT;
            case KeyEvent.VK_DOWN  -> Direction.BAS;
            case KeyEvent.VK_LEFT  -> Direction.GAUCHE;
            case KeyEvent.VK_RIGHT -> Direction.DROITE;
            default                -> null;
        };
        if( direction == null ) return;
        if( controleur.action( direction )) {
            repaint();
            String message;
            JOptionPane.showMessageDialog( this, "Vous avez gagné !" );
            Statistiques stats = controleur.getStatistiques();
            if (stats.getNbCasesGrille()==stats.getNbActions()){
            	message = "Bravo ! Vous avez réussi avec le nombre d'actions minimum !";
            }
            else {
            	message = "Bien joué ! Vous avez réussi avec "+ Integer.toString(stats.getNbActions())+" actions. Le nombre minimum est "+ Integer.toString(stats.getNbCasesGrille())+".";
            }
			JOptionPane.showMessageDialog(this, message);
            System.exit( 0 );
        }
        repaint();
    }

    @Override
    public void keyReleased( KeyEvent e ) {
        // nothing
    }

}
