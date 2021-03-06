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
 * Controleur pour executer l'IHM du jeu FlowFree, adapte du Controleur bouchon
 * developpe par Dominique Marcadet 
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
	protected Plot plotCourant;
	
	
	public Controleur(){
		niveauActuel = new Niveau();
		//on definit les statistiques 
		statistiques = new Statistiques(niveauActuel);
		//on definit le plateau de jeu
		plateau = new Plateau(statistiques.getNbLignes(), statistiques.getNbColonnes());
	}
		
	
	

	@Override
    public boolean selectionCase( int ligne, int colonne ) {
		plotCourant = plateau.getPlot(ligne, colonne); 
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
        boolean[] resultatsModification = tuyauCourant.modifier(direction);       
        
        //Si l'action est validee
        if (resultatsModification[0]) {       
        	statistiques.addAction();
	        tuyauCourant.getCouleur().getDataCouleur().setDirectionsTuyau(direction);
	        statistiques.refreshCompteurCasesRemplies();
        }
        //Si l'action donne lieu à la destruction du tuyau
        if (resultatsModification[1]) {
        	destructionTuyau();
        	tuyauCourant = plotCourant.nouveauTuyau();
        	
        }
        
        if (resultatsModification[2]) statistiques.addTuyauFini();
        
        //A la fin des tests
        return plateau.plateauComplet();
    }
	
  
    private void notePlotChoisi(Couleur couleur, int ligne, int colonne) {
    	tuyauCourant.getCouleur().getDataCouleur().setCoordonneesPlotChoisi(new int[] {ligne,colonne});
	}

    
	private void destructionTuyau() {
    	 tuyauCourant.getCouleur().getDataCouleur().clearDirectionsTuyau();
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
        return new int[] {couleur.getDataCouleur().getPositionsPlots().get(0).getI(),couleur.getDataCouleur().getPositionsPlots().get(0).getJ()};
    }

    
    @Override
    public int[] getPositionSecondPlot( Couleur couleur ) {
        return new int[] {couleur.getDataCouleur().getPositionsPlots().get(1).getI(),couleur.getDataCouleur().getPositionsPlots().get(1).getJ()};
    }

    
    @Override
    public ArrayList<Direction> getDirections(Couleur couleur) {
        return couleur.getDataCouleur().getDirectionsTuyau();
    }


	@Override
	public int[] getPositionPlotChoisi(Couleur couleur) {
		return couleur.getDataCouleur().getCoordonneesPlotChoisi();
	}
	
	public Statistiques getStatistiques() {
		return statistiques;
	}
	
}

