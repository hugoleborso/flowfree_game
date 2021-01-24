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

import control.IControleur;
import enumerations.Couleur;
import enumerations.Direction;
import game.Statistiques;

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
	//private ArrayList<Couleur> listeCouleurs = Controleur.couleursPlots;

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
            if (couleur.getPostionsPlots().size() != 0) {
	        	setCouleurGraphique( g, couleur );
	        	
	        	//On récupère les coordonnées des plots
	        	int[] posPremierPlot = new int[] {couleur.getPostionsPlots().get(0).getI(),couleur.getPostionsPlots().get(0).getJ()};
	        	int[] posDeuxiemePlot = new int[] {couleur.getPostionsPlots().get(1).getI(),couleur.getPostionsPlots().get(1).getJ()};
	        	int[] posDepartTuyau = couleur.getCoordonnéesPlotChoisi();
	        	
	        	//On éclaire d'abord avec la couleur d'arrière plan
	        	paintCasesDirections( g , posDepartTuyau, couleur.getDirectionsTuyau() , Fenetre.HAUTEUR_ZONE_TEXTE , couleur );
	        	
	        	
	            // Affichage du premier plot
	            g.fillOval( posPremierPlot[1] * coteCase + demiRayon,
	                    posPremierPlot[0] * coteCase + demiRayon + Fenetre.HAUTEUR_ZONE_TEXTE,
	                    diametrePlot, diametrePlot );
	            
	            // Affichage du second plot
	            g.fillOval( posDeuxiemePlot[1] * coteCase + demiRayon,
	                    posDeuxiemePlot[0] * coteCase + demiRayon + Fenetre.HAUTEUR_ZONE_TEXTE,
	                    diametrePlot, diametrePlot );
	           
	            if (premierClicPlot) {
		            // Décoration d'un plot s'il est sélectionné
		            if( Arrays.equals( posPremierPlot, selection )) {
		            	decorationPlot(g,posPremierPlot,coteCase,demiRayon,EPAISSEUR,diametrePlot,Fenetre.HAUTEUR_ZONE_TEXTE);
		            }
		            if( Arrays.equals( posDeuxiemePlot, selection )) {
		            	decorationPlot(g,posDeuxiemePlot,coteCase,demiRayon,EPAISSEUR,diametrePlot,Fenetre.HAUTEUR_ZONE_TEXTE);
		            }
	            }
	            
	            // Affichage de l'éventuel tuyau partant d'un des deux plots de sa couleur
	            paintDirections( g , posDepartTuyau, couleur.getDirectionsTuyau() , Fenetre.HAUTEUR_ZONE_TEXTE);
	            
	            
	            //Afficher le nombre d'actions et le pourcentage de complétion
	            Statistiques stats = controleur.getStatistiques();
	            afficheScores( g , stats.getNbActions() ,  stats.getNbTuyauxFinis() , stats.getNbTuyauxFinal() , stats.getNbCasesRemplies() , stats.getNbCasesGrille());
	        }
        }
    }
    
    private void paintCasesDirections(Graphics g, int[] posDepartTuyau, ArrayList<Direction> directions, int hauteurZoneTexte, Couleur couleur) {
    	setCouleurGraphiquePlusClaire( g, couleur);
    	int x = posDepartTuyau[1] * coteCase;
        int y = posDepartTuyau[0] * coteCase + hauteurZoneTexte;
        if (directions.size() !=0) g.fillRect( x, y, coteCase, coteCase);
    	for( Direction dir : directions ) {
            switch( dir ) {
	            case HAUT:
	                y -= coteCase;
	                break;
	            case BAS:
	                y += coteCase;
	                break;
	            case GAUCHE:
	                x -= coteCase;
	                break;
	            case DROITE:
	                x += coteCase;
	                break;
            }
            g.fillRect( x, y, coteCase, coteCase);
    	}
    	setCouleurGraphique( g, couleur );
		
	}

	private void decorationPlot(Graphics g, int[] posPremierPlot, int coteCase, int demiRayon, float epaisseur2, int diametrePlot , int hauteurZoneTexte ) {
    	Color c = g.getColor();
        g.setColor( Color.GRAY );
        Graphics2D g2 = ( Graphics2D ) g;
        Stroke s = g2.getStroke();
        g2.setStroke( new BasicStroke( epaisseur2 ));
        g.drawOval( posPremierPlot[1] * coteCase + demiRayon,
                posPremierPlot[0] * coteCase + demiRayon + hauteurZoneTexte,
                diametrePlot, diametrePlot );
        g2.setStroke( s );
        g.setColor( c );
    }

    private void paintDirections( Graphics g, int[] posPremierPlot, ArrayList<Direction> directions , int hauteurZoneTexte) {
        // Ici :
        // x <=> posPremierPlot[1] et y <=> posPremierPlot[0]
        int x0 = posPremierPlot[1] * coteCase + coteCase / 2 - largeurTuyau / 2;
        int y0 = posPremierPlot[0] * coteCase + coteCase / 2 - largeurTuyau / 2 + hauteurZoneTexte;
        
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
    

    private void setCouleurGraphiquePlusClaire( Graphics g, Couleur c ) {
        g.setColor( brighten(switch( c ) {
            case ROUGE -> Color.RED;
            case ORANGE -> new Color(255,102,0);
            case BLEU -> Color.BLUE;
            case VERT -> Color.GREEN;
            case JAUNE -> Color.YELLOW;
            case CYAN -> Color.CYAN;
            case VIOLET -> Color.MAGENTA;
            case ROSE -> Color.PINK;
            case MARRON -> new Color(102,51,0);
        } ));
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
    
   
    /**
     * Rend une couleur plus claire.
     *
     * @param color la couleur à rendre plus claire.
     * @return Une couleur plus claire.
     * @author fonction trouvée sur internet et simplifiée par Hugo Borsoni
     */
    public Color brighten(Color color) {

        int red = (int) Math.round(Math.min(255, color.getRed() + 255 * 0.3));
        int green = (int) Math.round(Math.min(255, color.getGreen() + 255 * 0.3));
        int blue = (int) Math.round(Math.min(255, color.getBlue() + 255 * 0.3));

        int alpha = (int) Math.round(Math.min(255,color.getAlpha()/4));

        return new Color(red, green, blue, alpha);

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
