package components;

import java.util.ArrayList;

import enumerations.Couleur;
import enumerations.Direction;

public class Plateau {
	protected int nbLignes;
	protected int nbColonnes;
	protected ArrayList<ArrayList<Case>> plateau;
	public static int TAILLE;
	public static int NombreCasesOccupées;
	
	public Plateau(int nbLignes, int nbColonnes){//, ArrayList<Position> arrayPositionPlot,ArrayList<Couleur> arrayCouleurs) {
		
		this.nbLignes = nbLignes;
		this.nbColonnes = nbColonnes;
		TAILLE=nbLignes;
		
		this.plateau = new ArrayList<>(nbLignes);
		
		for (int i = 0; i<nbLignes ; i++) {
			this.plateau.add(new ArrayList<Case>(nbColonnes));
			for (int j = 0; j <nbColonnes; j++) {
				this.plateau.get(i).add(new Case(this));
				
				for( Couleur couleur : Couleur.class.getEnumConstants() ) {
					if (couleur.getPostionsPlots().contains(new Position(i,j))) {					
						//On initialise le plot
						Case casePourPlot = this.plateau.get(i).get(j);
						Couleur couleurPlot = couleur;
						casePourPlot.setPlot(new Plot(couleurPlot,casePourPlot));
					}
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
			if (positionCase.getI() >0) {
				return this.plateau.get(positionCase.getI()-1).get(positionCase.getJ());
			} else {
				return null;
			} 
		} else if(direction.getDirection()==Direction.BAS) {
			if (positionCase.getI() < this.nbLignes-1) {
				return this.plateau.get(positionCase.getI()+1).get(positionCase.getJ());
			} else {
				return null;
			} 
		} else if(direction.getDirection()==Direction.GAUCHE) {
			if (positionCase.getJ() >0) {
				return this.plateau.get(positionCase.getI()).get(positionCase.getJ()-1);
			} else {
				return null;
			} 
		} else {
			//cas direction = DROITE
			if (positionCase.getJ() < this.nbColonnes-1) {
				return this.plateau.get(positionCase.getI()).get(positionCase.getJ()+1);
			} else {
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
			return new Position(-1,-1); //-1,-1 signifiera que ce n'est pas dedans 
		} else {
			int j= this.plateau.get(iInterest).indexOf(laCase);
			return new Position(iInterest,j);
		}	
	}
	
	// Méthode uniquement utilisée pour l'affichage lors des test, elle serait supprimée en prod
	public String toString() {
		String monString ="[";
		for (int i =0; i<nbLignes;i++) {
			monString=monString+"[";
			for (int j =0; j<nbColonnes;j++) {
				if (plateau.get(i).get(j).getPlot()==null && plateau.get(i).get(j).getTuyau()==null) {
					monString+=",N";
				} else if (plateau.get(i).get(j).getPlot()!=null && plateau.get(i).get(j).getTuyau()!=null) {
					monString+="P("+plateau.get(i).get(j).getPlot().maCouleur+")x"+"T("+plateau.get(i).get(j).getTuyau().maCouleur+")";
				}
				else if(plateau.get(i).get(j).getPlot()!=null) {
					monString+="P("+plateau.get(i).get(j).getPlot().maCouleur+")";
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
					return false;
				}
			}	
		}
		return true;
	}
	///A SUPPRIMER
	public Case getCaseAutrePlotByCouleur(Plot plotCourant , ArrayList<Couleur> arrayCouleurs ,  ArrayList<Position> arrayPositionPlot ) {
		Case maCase = plotCourant.getCase();
		Couleur couleurPlot = plotCourant.getCouleur();
		
		for (int k = 0; k<arrayCouleurs.size() ; k++) {
			if(arrayCouleurs.get(k).getCouleur()==couleurPlot.getCouleur()) {
				Position positionPossible=arrayPositionPlot.get(k);
				Case casePotentielle = this.plateau.get(positionPossible.getI()).get(positionPossible.getJ());
				if (!(casePotentielle==maCase)){
					return casePotentielle;
				}
			}
		}
		return null;
	}
	
	public Case getCaseAutrePlotByCouleur(Plot plotCourant) {
		Case maCase = plotCourant.getCase();
		Couleur couleurPlot = plotCourant.getCouleur();
		Position positionPossible1 = couleurPlot.getPostionsPlots().get(0);
		Case casePotentielle1 = this.plateau.get(positionPossible1.getI()).get(positionPossible1.getJ());
		if (!(casePotentielle1==maCase)){
			return casePotentielle1;
		}else {
			Position positionPossible2 = couleurPlot.getPostionsPlots().get(1);
			Case casePotentielle2 = this.plateau.get(positionPossible2.getI()).get(positionPossible2.getJ());
			return casePotentielle2;
		}
	}
}
