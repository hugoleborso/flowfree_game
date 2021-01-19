package freeflow_game;

import java.util.ArrayList;

public class Plateau {
	protected int nbLignes;
	protected int nbColonnes;
	protected ArrayList<ArrayList<Case>> plateau;
	public static int TAILLE;
	
	public Plateau(int nbLignes, int nbColonnes, ArrayList<Position> arrayPositionPlot,ArrayList<Couleur> arrayCouleurs) {
		this.nbLignes = nbLignes;
		this.nbColonnes = nbColonnes;
		TAILLE=nbLignes;
		this.plateau = new ArrayList<>(nbLignes);
		for (int i = 0; i<nbLignes ; i++) {
			this.plateau.add(new ArrayList<Case>(nbColonnes));
			for (int j = 0; j <nbColonnes; j++) {
				if (arrayPositionPlot.contains(new Position(i,j))) {
					int positionDansListe = arrayPositionPlot.indexOf(new Position(i,j));
					this.plateau.get(i).add(new Case(null,null,this));
					//On récupère la case où on doit mettre le plot pour initialiser le plot
					Case casePourPlot = this.plateau.get(i).get(j);
					casePourPlot.setPlot(new Plot(arrayCouleurs.get(positionDansListe),casePourPlot));
				} else {
					this.plateau.get(i).add(new Case(null,null,this));
				}
				
			}
		}

	}
	
	public Plot getPlot(int ligne , int colonne) {
		return this.plateau.get(ligne).get(colonne).getPlot();
	}
	
	public Case getMaCaseVoisine(Case laCase, Direction direction) {
		Position positionCase =this.getPosition(laCase);
		if (positionCase.getI()==-1 && positionCase.getJ()==-1) {
			return null;
		} else if(direction.getDirection()==Direction.HAUT) {
			System.out.println("On va vers le haut");
			if (positionCase.getI() >0) {
				return this.plateau.get(positionCase.getI()-1).get(positionCase.getJ());
			} else {
				System.out.println("On sort du plateau !!");
				return null;
			} 
		} else if(direction.getDirection()==Direction.BAS) {
			System.out.println("On va vers le bas");
			if (positionCase.getI() < this.nbLignes-1) {
				return this.plateau.get(positionCase.getI()+1).get(positionCase.getJ());
			} else {
				System.out.println("On sort du plateau !!");
				return null;
			} 
		} else if(direction.getDirection()==Direction.GAUCHE) {
			System.out.println("On va vers la gaiche");
			if (positionCase.getJ() >0) {
				return this.plateau.get(positionCase.getI()).get(positionCase.getJ()-1);
			} else {
				System.out.println("On sort du plateau !!");
				return null;
			} 
		} else {
			//cas direction = DROITE
			System.out.println("On va vers la droite");
			if (positionCase.getJ() < this.nbColonnes-1) {
				return this.plateau.get(positionCase.getI()).get(positionCase.getJ()+1);
			} else {
				System.out.println("On sort du plateau !!");
				return null; 
			} 
		}
	}
	
	private Position getPosition(Case laCase) {
		boolean isPresent=false;
		int iInterest=0;
		for( int i = 0; i<nbLignes;i++) {
			if (this.plateau.get(i).contains(laCase)) {
				iInterest=i;
				isPresent=true;
				break;
			}
		}
		if(!isPresent) {
			return new Position(-1,-1); //-1,-1 signifiera que c'est pas dedans 
		} else {
			int j= this.plateau.get(iInterest).indexOf(laCase);
			return new Position(iInterest,j);
		}	
	}
	// Méthode uniquement utilisées pour l'affichage lors des test, elle serait supprimée en prod
	public String toString() {
		String monString ="[";
		for (int i =0; i<nbLignes;i++) {
			monString=monString+"[";
			for (int j =0; j<nbColonnes;j++) {
				if (plateau.get(i).get(j).getPlot("Impression")==null && plateau.get(i).get(j).getTuyau()==null) {
					monString+=",N";
				} else if (plateau.get(i).get(j).getPlot("Impression")!=null && plateau.get(i).get(j).getTuyau()!=null) {
					monString+="P("+plateau.get(i).get(j).getPlot("Impression").maCouleur+")x"+"T("+plateau.get(i).get(j).getTuyau().maCouleur+")";
				}
				else if(plateau.get(i).get(j).getPlot("Impression")!=null) {
					monString+="P("+plateau.get(i).get(j).getPlot("Impression").maCouleur+")";
				} else if(plateau.get(i).get(j).getTuyau()!=null) {
					monString+="T("+plateau.get(i).get(j).getTuyau().maCouleur+")";
				} 
			}
			monString+="]";
		}
		monString+="]";
		return monString;
	}
	
	public boolean plateauComplet() {
		for (ArrayList<Case> ligneDuPlateau : this.plateau) {
			for (Case caseDuPlateau: ligneDuPlateau) {
				if (!caseDuPlateau.valideFinJeu()) {
					System.out.println("Jeu non terminé");
					return false;
				}
			}	
		}
		System.out.println("Jeu terminé");
		return true;
	}
}
