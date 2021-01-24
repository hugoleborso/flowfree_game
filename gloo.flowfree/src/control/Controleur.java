package control;

import java.util.ArrayList;

import components.Case;
import components.Plateau;
import components.Plot;
import components.Tuyau;
import enumerations.Couleur;
import enumerations.Direction;
import game.Niveau;
import game.Statistiques;

/**
 * Controleur pour éxécuter l'IHM du jeu FlowFree, adapté du Controleur bouchon
 * développé par Dominique Marcadet 
 * 
 * @authors Dominique Marcadet, Hugo Borsoni, Vincent Flattot
 * @version 2.1
 *
 */
public class Controleur implements IControleur {
	protected Plateau plateau;
	protected Tuyau tuyauCourant;
	protected Niveau niveauActuel;
	protected Statistiques statistiques;
	
	
	public Controleur(){
		niveauActuel = new Niveau();
		//on définit les statistiques 
		statistiques = new Statistiques(niveauActuel);
		//on définit le plateau de jeu
		plateau = new Plateau(statistiques.getNbLignes(), statistiques.getNbColonnes());
	}
		
	
	

	@Override
    public boolean selectionCase( int ligne, int colonne ) {
		Plot plotCourant = plateau.getPlot(ligne, colonne); 
        if (plotCourant==null) return false;
        
        Case caseCourante = plotCourant.getCase();
        Tuyau ancienTuyau = caseCourante.getTuyau();
        
        if(ancienTuyau!=null && ancienTuyau.isFini) statistiques.removeTuyauFini();    
        
        tuyauCourant = plotCourant.nouveauTuyau();
        
		Case caseAutrePlotMemeCouleur = plateau.getCaseAutrePlotByCouleur(plotCourant);
        caseAutrePlotMemeCouleur.deleteTuyau();
        destructionTuyau();
        notePlotChoisi(tuyauCourant.getCouleur(),ligne,colonne);
        statistiques.refreshCompteurCasesRemplies();
        
        return true;
    }


	@Override
    public boolean action( Direction direction ) {
        boolean[] booleans=tuyauCourant.modifier(direction);       
        
        //Si l'action est validée
        if (booleans[0]) {       
        	statistiques.addAction();
	        tuyauCourant.getCouleur().setDirectionsTuyau(direction);
	        statistiques.refreshCompteurCasesRemplies();
        }
        //Si l'action donne lieu à la destruction du tuyau
        if (booleans[1])  destructionTuyau();
        
        if (booleans[2]) statistiques.addTuyauFini();
        
        //A la fin des tests
        return plateau.plateauComplet();
    }
	
  
    private void notePlotChoisi(Couleur couleur, int ligne, int colonne) {
    	tuyauCourant.getCouleur().setCoordonnéesPlotChoisi(new int[] {ligne,colonne});
	}

    
	private void destructionTuyau() {
    	 tuyauCourant.getCouleur().clearDirectionsTuyau();
    }

    
    @Override
    public int getNbLignes() {
        return statistiques.getNbLignes();
    }

    
    @Override
    public int getNbColonnes() {
        return statistiques.getNbColonnes();
    }

    
    @Override
    public int[] getPositionPremierPlot( Couleur couleur ) {
        return new int[] {couleur.getPostionsPlots().get(0).getI(),couleur.getPostionsPlots().get(0).getJ()};
    }

    
    @Override
    public int[] getPositionSecondPlot( Couleur couleur ) {
        return new int[] {couleur.getPostionsPlots().get(1).getI(),couleur.getPostionsPlots().get(1).getJ()};
    }

    
    @Override
    public ArrayList<Direction> getDirections(Couleur couleur) {
        return couleur.getDirectionsTuyau();
    }


	@Override
	public int[] getPositionPlotChoisi(Couleur couleur) {
		return couleur.getCoordonnéesPlotChoisi();
	}
	
	public Statistiques getStatistiques() {
		return statistiques;
	}
	
}

