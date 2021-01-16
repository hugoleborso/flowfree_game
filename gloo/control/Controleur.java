package gloo.flow.control;

import freeflow_game.Case;
import freeflow_game.Plot;
import freeflow_game.Plateau;
import freeflow_game.Tuyau;
import gloo.flow.model.Couleur;
import gloo.flow.model.Direction;

public class Controleur implements IControleur {	
	
	Plateau plateau;
	

	@Override
	public boolean selectionCase(int ligne, int colonne) {
		System.out.println("clic en l" + ligne + "c" + colonne);
		if (plateau.getPlot(ligne,colonne) == null) return false;
			
		plateau.getPlot(ligne,colonne).nouveauTuyau();
		return true;
        
		
	}

	@Override
	public boolean action(Direction direction) {
		
		System.out.println("flèche " + direction.name());
		
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
	public int[] getPositionPlotDepartTuyau(Couleur couleur) {
		return switch (couleur) {
        case ROUGE -> new int[] { 0, 0 };
        case ORANGE -> new int[] { 1, 4 };
        case BLEU -> new int[] { 1, 2 };
        case VERT -> new int[] { 0, 2 };
        case JAUNE -> new int[] { 0, 4 };
    };
}

	@Override
	public int[] getPositionSecondPlot(Couleur couleur) {
		return switch( couleur ) {
        case ROUGE  -> new int[] { 4, 1 };
        case ORANGE -> new int[] { 4, 3 };
        case BLEU   -> new int[] { 4, 2 };
        case VERT   -> new int[] { 3, 1 };
        case JAUNE  -> new int[] { 3, 3 };
    };
	}

	@Override
    public Direction[] getDirections(Couleur couleur) {
        return switch (couleur) {
            case ROUGE  -> new Direction[] { Direction.BAS, Direction.DROITE, Direction.BAS, Direction.GAUCHE };
            case ORANGE -> new Direction[] { Direction.BAS, Direction.BAS, Direction.BAS };
            case BLEU   -> new Direction[] { Direction.BAS, Direction.BAS };
            case VERT   -> new Direction[] { Direction.GAUCHE };
            case JAUNE  -> new Direction[] { Direction.GAUCHE, Direction.BAS, Direction.BAS };
        };
    }


}