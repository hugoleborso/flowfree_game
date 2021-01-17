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
	
	protected int[] PlotChoisiRouge  = new int[] {0,0};
	protected int[] PlotChoisiOrange = new int[] {0,0};
	protected int[] PlotChoisiBleu   = new int[] {0,0};
	protected int[] PlotChoisiVert   = new int[] {0,0};
	protected int[] PlotChoisiJaune  = new int[] {0,0};
	
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
		
		plateau = new Plateau(getNbLignes(), getNbColonnes(), positionsPlots, couleursPlots);
	}
		
	
	
	
	@Override
    public boolean selectionCase( int ligne, int colonne ) {
        System.out.println("clic en l" + ligne + "c" + colonne);
        if (plateau.getPlot(ligne,colonne)==null) return false;
        tuyauCourant = plateau.getPlot(ligne, colonne).nouveauTuyau();
        destructionTuyau();
        notePlotChoisi(tuyauCourant.getCouleur(),ligne,colonne);
        return true;
    }

    private void notePlotChoisi(Couleur couleur, int ligne, int colonne) {
    	switch(tuyauCourant.getCouleur()) {
		    case ROUGE  -> PlotChoisiRouge = new int[] {ligne,colonne} ;
		    case ORANGE -> PlotChoisiOrange = new int[] {ligne,colonne} ;
		    case BLEU   -> PlotChoisiBleu = new int[] {ligne,colonne} ;
		    case VERT   -> PlotChoisiVert = new int[] {ligne,colonne} ;
		    case JAUNE  -> PlotChoisiJaune = new int[] {ligne,colonne} ;
    }
		
	}




	@Override
    public boolean action( Direction direction ) {
        System.out.println("flèche " + direction.name());
        boolean[] booleans=tuyauCourant.modifier(direction);       
        
        //Si l'action est validée
        if (booleans[0])        
	        switch(tuyauCourant.getCouleur()) {
			    case ROUGE  -> directionsTuyauRouge.add(direction) ;
			    case ORANGE -> directionsTuyauOrange.add(direction) ;
			    case BLEU   -> directionsTuyauBleu.add(direction) ;
			    case VERT   -> directionsTuyauVert.add(direction) ;
			    case JAUNE  -> directionsTuyauJaune.add(direction) ;
	        }
        //Si l'action donne lieu à la destruction du tuyau
        if (booleans[1])  destructionTuyau();
        return plateau.plateauComplet();
    }

    public void destructionTuyau() {
    	 switch(tuyauCourant.getCouleur()) {
	        case ROUGE  -> directionsTuyauRouge  = new ArrayList<>();
		    case ORANGE -> directionsTuyauOrange = new ArrayList<>();
		    case BLEU   -> directionsTuyauBleu   = new ArrayList<>();
		    case VERT   -> directionsTuyauVert   = new ArrayList<>();
		    case JAUNE  -> directionsTuyauJaune  = new ArrayList<>();
    	 }
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
            case ROUGE  -> new int[] {positionsPlots.get(0).getI(),positionsPlots.get(0).getJ()};
            case ORANGE -> new int[] {positionsPlots.get(2).getI(),positionsPlots.get(2).getJ()};
            case BLEU   -> new int[] {positionsPlots.get(4).getI(),positionsPlots.get(4).getJ()};
            case VERT   -> new int[] {positionsPlots.get(6).getI(),positionsPlots.get(6).getJ()};
            case JAUNE  -> new int[] {positionsPlots.get(8).getI(),positionsPlots.get(8).getJ()};
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




	@Override
	public int[] getPositionPlotChoisi(Couleur couleur) {
		return switch (couleur) {
        case ROUGE  -> PlotChoisiRouge ;
        case ORANGE -> PlotChoisiOrange ;
        case BLEU   -> PlotChoisiBleu ;
        case VERT   -> PlotChoisiVert ;
        case JAUNE  -> PlotChoisiJaune ;
    };
	}
}
