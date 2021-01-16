package gloo.flow.control;

import java.util.ArrayList;

import freeflow_game.Position;
import freeflow_game.Tuyau;
import freeflow_game.Couleur;
import freeflow_game.Direction;
import freeflow_game.Plateau;

/**
 * Controleur bouchon pour tester l'IHM du jeu FlowFree 
 * 
 * @author Dominique Marcadet
 * @version 1.1
 *
 */
public class ControleurBouchon implements IControleur {
    
	protected ArrayList<Position> positionsPlots = new ArrayList<>();
	protected ArrayList<Couleur> couleursPlots = new ArrayList<>();
	protected Plateau plateau;
	protected Tuyau tuyauCourant;
	
	protected ArrayList<Direction> directionsTuyauRouge  = new ArrayList<>();
	protected ArrayList<Direction> directionsTuyauOrange = new ArrayList<>();
	protected ArrayList<Direction> directionsTuyauBleu   = new ArrayList<>();
	protected ArrayList<Direction> directionsTuyauVert   = new ArrayList<>();
	protected ArrayList<Direction> directionsTuyauJaune  = new ArrayList<>();
	
	public ControleurBouchon(){
	//on définit les plots
		couleursPlots.add(Couleur.ROUGE);
		couleursPlots.add(Couleur.ROUGE);
		positionsPlots.add(new Position(0,0));
		positionsPlots.add(new Position(4,1));
	
		couleursPlots.add(Couleur.ORANGE);
		couleursPlots.add(Couleur.ORANGE);
		positionsPlots.add(new Position(1,4));
		positionsPlots.add(new Position(4,3));
		
		couleursPlots.add(Couleur.BLEU);
		couleursPlots.add(Couleur.BLEU);
		positionsPlots.add(new Position(1,2));
		positionsPlots.add(new Position(4,2));
		
		couleursPlots.add(Couleur.VERT);
		couleursPlots.add(Couleur.VERT);
		positionsPlots.add(new Position(0,2));
		positionsPlots.add(new Position(3,1));
		
		couleursPlots.add(Couleur.JAUNE);
		couleursPlots.add(Couleur.JAUNE);
		positionsPlots.add(new Position(0,4));
		positionsPlots.add(new Position(3,3));
		
		plateau = new Plateau(5, 5, positionsPlots, couleursPlots);
	}
		
	
	
	
	@Override
    public boolean selectionCase( int ligne, int colonne ) {
        System.out.println("clic en l" + ligne + "c" + colonne);
        if (plateau.getPlot(ligne,colonne)==null) return false;
        
        tuyauCourant = plateau.getPlot(ligne, colonne).nouveauTuyau();
        return true;
    }

    @Override
    public boolean action( Direction direction ) {
        System.out.println("flèche " + direction.name());
        tuyauCourant.modifier(direction);
        
        switch(tuyauCourant.getCouleur()) {
		    case ROUGE  -> directionsTuyauRouge.add(direction) ;
		    case ORANGE -> directionsTuyauOrange.add(direction) ;
		    case BLEU   -> directionsTuyauBleu.add(direction) ;
		    case VERT   -> directionsTuyauVert.add(direction) ;
		    case JAUNE  -> directionsTuyauJaune.add(direction) ;
        }
        return false;
    }

    @Override
    public int getNbLignes() {
        return 5;
    }

    @Override
    public int getNbColonnes() {
        return 5;
    }

    @Override
    public int[] getPositionPlotDepartTuyau( Couleur couleur ) {
        return switch (couleur) {
            case ROUGE  -> new int[] { 0, 0 };
            case ORANGE -> new int[] { 1, 4 };
            case BLEU   -> new int[] { 1, 2 };
            case VERT   -> new int[] { 0, 2 };
            case JAUNE  -> new int[] { 0, 4 };
        };
    }

    @Override
    public int[] getPositionSecondPlot( Couleur couleur ) {
        return switch( couleur ) {
            case ROUGE  -> new int[] { 4, 1 };
            case ORANGE -> new int[] { 4, 3 };
            case BLEU   -> new int[] { 4, 2 };
            case VERT   -> new int[] { 3, 1 };
            case JAUNE  -> new int[] { 3, 3 };
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
        };
    }
}
