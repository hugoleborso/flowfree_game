package control;

import java.util.ArrayList;

import components.Case;
import components.Plateau;
import components.Plot;
import components.Position;
import components.Tuyau;
import enumerations.Couleur;
import enumerations.Direction;
import game.Niveau;

/**
 * Controleur pour éxécuter l'IHM du jeu FlowFree, adapté du Controleur bouchon
 * développé par Dominique Marcadet 
 * 
 * @authors Dominique Marcadet, Hugo Borsoni, Vincent Flattot
 * @version 2.1
 *
 */
public class Controleur implements IControleur {
    
	protected ArrayList<Position> positionsPlots;
	public static ArrayList<Couleur> couleursPlots;
	public static ArrayList<Couleur> listeCouleurs;
	
	protected Plateau plateau;
	protected Tuyau tuyauCourant;
	
	public static int compteurActions;
	public static int compteurTuyauxFinis;
	public static int nbTuyauxFinal;
	public static int nbCasesRemplies;
	public static int nbCasesGrille;
	
	protected ArrayList<Direction> directionsTuyauRouge  = new ArrayList<>();
	protected ArrayList<Direction> directionsTuyauOrange = new ArrayList<>();
	protected ArrayList<Direction> directionsTuyauBleu   = new ArrayList<>();
	protected ArrayList<Direction> directionsTuyauVert   = new ArrayList<>();
	protected ArrayList<Direction> directionsTuyauJaune  = new ArrayList<>();
	protected ArrayList<Direction> directionsTuyauCyan   = new ArrayList<>();
	protected ArrayList<Direction> directionsTuyauViolet = new ArrayList<>();
	protected ArrayList<Direction> directionsTuyauRose   = new ArrayList<>();
	protected ArrayList<Direction> directionsTuyauMarron = new ArrayList<>();
	
	protected int[] plotChoisiRouge  = new int[] {0,0};
	protected int[] plotChoisiOrange = new int[] {0,0};
	protected int[] plotChoisiBleu   = new int[] {0,0};
	protected int[] plotChoisiVert   = new int[] {0,0};
	protected int[] plotChoisiJaune  = new int[] {0,0};
	protected int[] plotChoisiCyan   = new int[] {0,0};
	protected int[] plotChoisiViolet = new int[] {0,0};
	protected int[] plotChoisiRose   = new int[] {0,0};
	protected int[] plotChoisiMarron = new int[] {0,0};
	
	protected int nbLignes;
	protected int nbColonnes;
	protected int[] parametres;
	protected Niveau niveauActuel;
	
	
	public Controleur(){
		
		this.niveauActuel = new Niveau();
		
		this.parametres = niveauActuel.parametres;
		this.nbLignes = parametres[0];
		this.nbColonnes = parametres[1];
		
		//on définit les plots
		couleursPlots = niveauActuel.couleursPlots;
		positionsPlots = niveauActuel.positionsPlots;
		
		//on définit les variables
		nbTuyauxFinal = niveauActuel.nbTuyauxFinal;
		nbCasesGrille = nbLignes*nbColonnes - nbTuyauxFinal;
		
		//on définit le plateau de jeu
		plateau = new Plateau(nbLignes, nbColonnes, positionsPlots, couleursPlots);
	}
		
	
	

	@Override
    public boolean selectionCase( int ligne, int colonne ) {
		Plot plotCourant = plateau.getPlot(ligne, colonne); 
        if (plotCourant==null) return false;
        
        Case caseCourante = plotCourant.getCase();
        Tuyau ancienTuyau = caseCourante.getTuyau();
        
        if(ancienTuyau!=null && ancienTuyau.isFini) compteurTuyauxFinis = compteurTuyauxFinis - 1;      
        
        tuyauCourant = plotCourant.nouveauTuyau();
        
		Case caseAutrePlotMemeCouleur = plateau.getCaseAutrePlotByCouleur(plotCourant, Controleur.couleursPlots, this.positionsPlots);
        caseAutrePlotMemeCouleur.deleteTuyau();
        destructionTuyau();
        notePlotChoisi(tuyauCourant.getCouleur(),ligne,colonne);
        nbCasesRemplies=getNbCasesremplies();
        
        return true;
    }


	@Override
    public boolean action( Direction direction ) {
        boolean[] booleans=tuyauCourant.modifier(direction);       
        
        //Si l'action est validée
        if (booleans[0]) {       
        	compteurActions = compteurActions + 1;
	        switch(tuyauCourant.getCouleur()) {
			    case ROUGE  -> directionsTuyauRouge.add(direction) ;
			    case ORANGE -> directionsTuyauOrange.add(direction) ;
			    case BLEU   -> directionsTuyauBleu.add(direction) ;
			    case VERT   -> directionsTuyauVert.add(direction) ;
			    case JAUNE  -> directionsTuyauJaune.add(direction) ;
			    case CYAN   -> directionsTuyauCyan.add(direction) ;
			    case VIOLET -> directionsTuyauViolet.add(direction) ;
			    case ROSE   -> directionsTuyauRose.add(direction) ;
			    case MARRON -> directionsTuyauMarron.add(direction) ;
	        }
	        nbCasesRemplies=getNbCasesremplies();
        }
        //Si l'action donne lieu à la destruction du tuyau
        if (booleans[1])  destructionTuyau();
        
        if (booleans[2]) compteurTuyauxFinis=compteurTuyauxFinis + 1;
        
        //A la fin des tests
        return plateau.plateauComplet();
    }

	
    private int getNbCasesremplies() {
		int count = 0;
		for (Couleur couleur : Couleur.class.getEnumConstants() ) {
			ArrayList<Direction> directionsCouleur = getDirections(couleur);
			count = count + directionsCouleur.size();
		}
		return count;
	}

  
    private void notePlotChoisi(Couleur couleur, int ligne, int colonne) {
    	switch(tuyauCourant.getCouleur()) {
		    case ROUGE  -> plotChoisiRouge  = new int[] {ligne,colonne} ;
		    case ORANGE -> plotChoisiOrange = new int[] {ligne,colonne} ;
		    case BLEU   -> plotChoisiBleu   = new int[] {ligne,colonne} ;
		    case VERT   -> plotChoisiVert   = new int[] {ligne,colonne} ;
		    case JAUNE  -> plotChoisiJaune  = new int[] {ligne,colonne} ;
		    case CYAN 	-> plotChoisiCyan   = new int[] {ligne,colonne} ;
		    case VIOLET -> plotChoisiViolet = new int[] {ligne,colonne} ;
		    case ROSE   -> plotChoisiRose   = new int[] {ligne,colonne} ;
		    case MARRON -> plotChoisiMarron = new int[] {ligne,colonne} ;
    	}	
	}

    
	private void destructionTuyau() {
    	 switch(tuyauCourant.getCouleur()) {
	        case ROUGE  -> directionsTuyauRouge  = new ArrayList<>();
		    case ORANGE -> directionsTuyauOrange = new ArrayList<>();
		    case BLEU   -> directionsTuyauBleu   = new ArrayList<>();
		    case VERT   -> directionsTuyauVert   = new ArrayList<>();
		    case JAUNE  -> directionsTuyauJaune  = new ArrayList<>();
		    case CYAN 	-> directionsTuyauCyan   = new ArrayList<>();
		    case ROSE   -> directionsTuyauRose   = new ArrayList<>();
		    case VIOLET -> directionsTuyauViolet = new ArrayList<>();
		    case MARRON -> directionsTuyauMarron = new ArrayList<>();
    	 }
    }

    
    @Override
    public int getNbLignes() {
        return nbLignes;
    }

    
    @Override
    public int getNbColonnes() {
        return nbColonnes;
    }

    
    @Override
    public int[] getPositionPremierPlot( Couleur couleur ) {
        return switch (couleur) {
            case ROUGE  -> new int[] {positionsPlots.get(0).getI(),positionsPlots.get(0).getJ()};
            case ORANGE -> new int[] {positionsPlots.get(2).getI(),positionsPlots.get(2).getJ()};
            case BLEU   -> new int[] {positionsPlots.get(4).getI(),positionsPlots.get(4).getJ()};
            case VERT   -> new int[] {positionsPlots.get(6).getI(),positionsPlots.get(6).getJ()};
            case JAUNE  -> new int[] {positionsPlots.get(8).getI(),positionsPlots.get(8).getJ()};
            case CYAN 	-> new int[] {positionsPlots.get(10).getI(),positionsPlots.get(10).getJ()};
            case VIOLET -> new int[] {positionsPlots.get(12).getI(),positionsPlots.get(12).getJ()};
            case ROSE   -> new int[] {positionsPlots.get(14).getI(),positionsPlots.get(14).getJ()};
            case MARRON -> new int[] {positionsPlots.get(16).getI(),positionsPlots.get(16).getJ()};
        };
    }

    
    @Override
    public int[] getPositionSecondPlot( Couleur couleur ) {
        return switch( couleur ) {
        case ROUGE  -> new int[] {positionsPlots.get(1).getI(),positionsPlots.get(1).getJ()};
        case ORANGE -> new int[] {positionsPlots.get(3).getI(),positionsPlots.get(3).getJ()};
        case BLEU   -> new int[] {positionsPlots.get(5).getI(),positionsPlots.get(5).getJ()};
        case VERT   -> new int[] {positionsPlots.get(7).getI(),positionsPlots.get(7).getJ()};
        case JAUNE  -> new int[] {positionsPlots.get(9).getI(),positionsPlots.get(9).getJ()};
        case CYAN 	-> new int[] {positionsPlots.get(11).getI(),positionsPlots.get(11).getJ()};
        case VIOLET -> new int[] {positionsPlots.get(13).getI(),positionsPlots.get(13).getJ()};
        case ROSE   -> new int[] {positionsPlots.get(15).getI(),positionsPlots.get(15).getJ()};
        case MARRON -> new int[] {positionsPlots.get(17).getI(),positionsPlots.get(17).getJ()};
        };
    }

    
    @Override
    public ArrayList<Direction> getDirections(Couleur couleur) {
        return switch (couleur) {
            case ROUGE  -> directionsTuyauRouge ;
            case ORANGE -> directionsTuyauOrange ;
            case BLEU   -> directionsTuyauBleu ;
            case VERT   -> directionsTuyauVert ;
            case JAUNE  -> directionsTuyauJaune ;
            case CYAN 	-> directionsTuyauCyan ;
            case VIOLET -> directionsTuyauViolet ;
            case ROSE   -> directionsTuyauRose ;
            case MARRON -> directionsTuyauMarron ;
        };
    }


	@Override
	public int[] getPositionPlotChoisi(Couleur couleur) {
		return switch (couleur) {
	        case ROUGE  -> plotChoisiRouge ;
	        case ORANGE -> plotChoisiOrange ;
	        case BLEU   -> plotChoisiBleu ;
	        case VERT   -> plotChoisiVert ;
	        case JAUNE  -> plotChoisiJaune ;
	        case CYAN  	-> plotChoisiCyan ;
	        case VIOLET -> plotChoisiViolet ;
	        case ROSE   -> plotChoisiRose ;
	        case MARRON -> plotChoisiMarron ;
		};
	}
	
}

