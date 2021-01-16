package gloo.flow.control;

import gloo.flow.model.Couleur;
import gloo.flow.model.Direction;

/**
 * Controleur bouchon pour tester l'IHM du jeu FlowFree 
 * 
 * @author Dominique Marcadet
 * @version 1.1
 *
 */
public class ControleurBouchon implements IControleur {
    
    @Override
    public boolean selectionCase( int ligne, int colonne ) {
        System.out.println("clic en l" + ligne + "c" + colonne);
        if(( ligne == 0 ) && ( colonne % 2 == 0 )) return true;
        if(( ligne == 1 ) && (( colonne == 2 ) || ( colonne == 4 ))) return true;
        return false;
    }

    @Override
    public boolean action( Direction direction ) {
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
    public int[] getPositionPlotDepartTuyau( Couleur couleur ) {
        return switch (couleur) {
            case ROUGE -> new int[] { 0, 0 };
            case ORANGE -> new int[] { 1, 4 };
            case BLEU -> new int[] { 1, 2 };
            case VERT -> new int[] { 0, 2 };
            case JAUNE -> new int[] { 0, 4 };
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