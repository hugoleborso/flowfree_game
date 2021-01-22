package hmi;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JPanel;

import control.Controleur;
import control.IControleur;
import enumerations.Couleur;
import enumerations.Direction;

/**
 * Panneau de l'IHM pour le jeu FlowFree
 * 
 * @author Dominique Marcadet
 * @version 1.1
 *
 */
@SuppressWarnings( "serial" )
public class Panneau extends JPanel implements MouseListener {

    private static final float EPAISSEUR = 4;
    private boolean premierAffichage = true;
    private IControleur controleur;
    private int nbLignes;
    private int nbColonnes;
    private int coteCase;
    private int diametrePlot;
    private int demiRayon;
    private int largeurTuyau;
    private int arcRoundRect;
    private int[] selection = new int[] {0,0};
	private boolean premierClicPlot = false;
	private ArrayList<Couleur> listeCouleurs = Controleur.couleursPlots;

    public Panneau( IControleur controleur ) {
        this.controleur = controleur;
        this.addMouseListener( this );
    }

    private void calculeParametres() {
        nbLignes = controleur.getNbLignes();
        int cote_l = getSize().height / nbLignes;
        nbColonnes = controleur.getNbColonnes();
        int cote_c = getSize().width / nbColonnes;
        coteCase = cote_l < cote_c ? cote_l : cote_c;
        diametrePlot = coteCase * 2 / 3;
        demiRayon = diametrePlot / 4;
        largeurTuyau = coteCase / 4;
        arcRoundRect = coteCase / 4;
        premierAffichage = false;
    }

    public void paint( Graphics g ) {
        
    	if( premierAffichage ) calculeParametres();
        
    	//Le fond est noir pour se rapprocher du jeu sur téléphone
    	this.setBackground(Color.BLACK);
        super.paintComponent(g);
       
        //Dessin des lignes de la grille
        g.setColor( Color.WHITE );
        
        for( int i = 0; i <= nbLignes; ++i ) {
            g.drawLine( 0, i * coteCase + Fenetre.HAUTEUR_ZONE_TEXTE , nbColonnes * coteCase, i * coteCase + Fenetre.HAUTEUR_ZONE_TEXTE);
        	}
        for( int j = 0; j <= nbColonnes; ++j ) {
            g.drawLine( j * coteCase, Fenetre.HAUTEUR_ZONE_TEXTE, j * coteCase, nbLignes * coteCase + Fenetre.HAUTEUR_ZONE_TEXTE );
        	}
        
        for( Couleur couleur : Couleur.class.getEnumConstants() ) {
            if (listeCouleurs .contains(couleur)) {
	        	setCouleurGraphique( g, couleur );
	            
	            // Affichage du premier plot
	            int[] posDepart = controleur.getPositionPremierPlot( couleur );
	            g.fillOval( posDepart[1] * coteCase + demiRayon,
	                    posDepart[0] * coteCase + demiRayon + Fenetre.HAUTEUR_ZONE_TEXTE,
	                    diametrePlot, diametrePlot );
	            
	            // Affichage du second plot
	            int[] posArrivee = controleur.getPositionSecondPlot( couleur );
	            g.fillOval( posArrivee[1] * coteCase + demiRayon,
	                    posArrivee[0] * coteCase + demiRayon + Fenetre.HAUTEUR_ZONE_TEXTE,
	                    diametrePlot, diametrePlot );
	           
	            if (premierClicPlot) {
		            // Décoration d'un plot s'il est sélectionné
		            if( Arrays.equals( posDepart, selection )) {
		            	decorationPlot(g,posDepart,coteCase,demiRayon,EPAISSEUR,diametrePlot,Fenetre.HAUTEUR_ZONE_TEXTE);
		            }
		            if( Arrays.equals( posArrivee, selection )) {
		            	decorationPlot(g,posArrivee,coteCase,demiRayon,EPAISSEUR,diametrePlot,Fenetre.HAUTEUR_ZONE_TEXTE);
		            }
	            }
	            
	            // Affichage de l'éventuel tuyau partant d'un des deux plots de sa couleur
	            int[] posDepartTuyau = controleur.getPositionPlotChoisi( couleur );
	            paintDirections( g , posDepartTuyau, controleur.getDirections( couleur ) , Fenetre.HAUTEUR_ZONE_TEXTE);
	            
	            //Afficher le nombre d'actions et le pourcentage de complétion
	            afficheScores( g , Controleur.compteurActions ,  Controleur.compteurTuyauxFinis , Controleur.nbTuyauxFinal , Controleur.nbCasesRemplies , Controleur.nbCasesGrille );
	        }
        }
    }
    
    private void decorationPlot(Graphics g, int[] posDepart, int coteCase, int demiRayon, float epaisseur2, int diametrePlot , int hauteurZoneTexte ) {
    	Color c = g.getColor();
        g.setColor( Color.GRAY );
        Graphics2D g2 = ( Graphics2D ) g;
        Stroke s = g2.getStroke();
        g2.setStroke( new BasicStroke( epaisseur2 ));
        g.drawOval( posDepart[1] * coteCase + demiRayon,
                posDepart[0] * coteCase + demiRayon + hauteurZoneTexte,
                diametrePlot, diametrePlot );
        g2.setStroke( s );
        g.setColor( c );
    }

    private void paintDirections( Graphics g, int[] posDepart, ArrayList<Direction> directions , int hauteurZoneTexte) {
        // Ici :
        // x <=> posDepart[1] et y <=> posDepart[0]
        int x0 = posDepart[1] * coteCase + coteCase / 2 - largeurTuyau / 2;
        int y0 = posDepart[0] * coteCase + coteCase / 2 - largeurTuyau / 2 + hauteurZoneTexte;
        for( Direction dir : directions ) {
            int w = largeurTuyau;
            int h = largeurTuyau;
            int x1 = x0;
            int y1 = y0;
            switch( dir ) {
            case HAUT:
                y0 -= coteCase;
                y1 -= coteCase;
                h += coteCase;
                break;
            case BAS:
                h += coteCase;
                y1 += coteCase;
                break;
            case GAUCHE:
                x0 -= coteCase;
                x1 -= coteCase;
                w += coteCase;
                break;
            case DROITE:
                w += coteCase;
                x1 += coteCase;
                break;
            }
            g.fillRoundRect( x0, y0, w, h, arcRoundRect, arcRoundRect );
            x0 = x1;
            y0 = y1;
        }
    }

    private void setCouleurGraphique( Graphics g, Couleur c ) {
        g.setColor( switch( c ) {
            case ROUGE -> Color.RED;
            case ORANGE -> new Color(255,102,0);
            case BLEU -> Color.BLUE;
            case VERT -> Color.GREEN;
            case JAUNE -> Color.YELLOW;
            case CYAN -> Color.CYAN;
            case VIOLET -> Color.MAGENTA;
            case ROSE -> Color.PINK;
            case MARRON -> new Color(102,51,0);
        } );
    }

    private void afficheScores(Graphics g , int compteurActions , int compteurTuyauxFinis , int nbTuyauxControleur , int compteurCases , int nbCasesControleur) {
    	//Actions
    	g.setColor(Color.WHITE);
    	int x1 = 10;
    	int y1 = 25;
    	String count = Integer.toString(compteurActions);
    	String dessin1 = "Actions : "+ count ;
    	g.drawString(dessin1, x1, y1);
    	
    	int x2 = (int) Fenetre.COTE_FENETRE/2 -45;
    	int y2 = 25;
    	String countTuyau = Integer.toString(compteurTuyauxFinis);
    	String nbTuyaux = Integer.toString(nbTuyauxControleur);
    	String dessin2 = "Flux : "+ countTuyau + " / " + nbTuyaux ;
    	g.drawString(dessin2, x2, y2);
    	
    	int x3 = Fenetre.COTE_FENETRE -100;
    	int y3 = 25;
    	String percentage = Integer.toString((int)compteurCases*100/nbCasesControleur);
    	String dessin3 = "Tuyau : " + percentage + " %"  ;
    	g.drawString(dessin3, x3, y3);
    }
    
    @Override
    public void mouseClicked( MouseEvent e ) {
        if( controleur.selectionCase( (e.getPoint().y - Fenetre.HAUTEUR_ZONE_TEXTE)/ coteCase , e.getPoint().x / coteCase )) {
            selection = new int[] { (e.getPoint().y - Fenetre.HAUTEUR_ZONE_TEXTE)/ coteCase , e.getPoint().x / coteCase };
            premierClicPlot  = true;
        }
        repaint();
    }

    @Override
    public void mousePressed( MouseEvent e ) {
        // nothing
    }

    @Override
    public void mouseReleased( MouseEvent e ) {
    }

    @Override
    public void mouseEntered( MouseEvent e ) {
        // nothing
    }

    @Override
    public void mouseExited( MouseEvent e ) {
        // nothing
    }
}
