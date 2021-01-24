package game;

import java.util.ArrayList;

import enumerations.Couleur;
import enumerations.Direction;

public class Statistiques {
	protected int nbTuyauxFinal;
	protected int nbCasesGrille;
	protected int nbLignes;
	protected int nbColonnes;
	protected int compteurActions;
	protected int compteurTuyauxFinis;
	protected int compteurCasesRemplies;
	
	
	public Statistiques(Niveau niveauJeu){
		int niveau = niveauJeu.getNiveau();
		nbLignes = niveau;
		nbColonnes = niveau;
		compteurActions = 0;
		compteurTuyauxFinis = 0;
		compteurCasesRemplies = 0;
		
		switch(niveau) {
			 case 5 :{
			 	this.nbTuyauxFinal = 5;
			 	break;
			 }
			 case 6 :{
			 	this.nbTuyauxFinal = 4;
				break;
			 }
			 case 7 :{
			 	this.nbTuyauxFinal = 6;
				break;
			 }
			 case 8 :{
			 	this.nbTuyauxFinal = 9;
			 	break;
			 }
			 case 9 :{
			 	this.nbTuyauxFinal = 8;
			 	break;
			 }
		}
		nbCasesGrille = nbLignes*nbColonnes - nbTuyauxFinal;
	}

	public int getNbTuyauxFinal() {
		return nbTuyauxFinal;
	}
	
	public int getNbCasesGrille() {
		return nbCasesGrille;
	}
	
	public int getNbLignes() {
		return nbLignes;
	}
	
	public int getNbColonnes() {
		return nbColonnes;
	}
	
	public int getNbActions() {
		return compteurActions;
	}
	
	public void addAction() {
		compteurActions = compteurActions + 1 ;
	}
	
	public int getNbTuyauxFinis() {
		return compteurTuyauxFinis;
	}
	
	public void addTuyauFini() {
		compteurTuyauxFinis = compteurTuyauxFinis + 1 ;
	}
	
	public void removeTuyauFini() {
		compteurTuyauxFinis = compteurTuyauxFinis - 1 ;
	}
	
	public void refreshCompteurCasesRemplies() {
		int count = 0;
		for (Couleur couleur : Couleur.class.getEnumConstants() ) {
			ArrayList<Direction> directionsCouleur = couleur.getDirectionsTuyau();
			count = count + directionsCouleur.size();
		}
		compteurCasesRemplies=count;
	}
	
	public int getNbCasesRemplies() {
		return compteurCasesRemplies;
	}
}
 
