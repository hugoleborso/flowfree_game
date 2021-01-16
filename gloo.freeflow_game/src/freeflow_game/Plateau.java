package freeflow_game;

import java.util.ArrayList;

public class Plateau {
	protected int nbLignes;
	protected int nbColonnes;
	protected ArrayList<ArrayList<Case>> plateau;
	
	public Plateau(int nbLignes, int nbColonnes, ArrayList<Position> arrayPositionPlot,ArrayList<Couleur> arrayCouleurs) {
		this.nbLignes = nbLignes;
		this.nbColonnes = nbColonnes;
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
		int[] positionCase =this.getPosition(laCase);
		if(direction.getDirection()==Direction.HAUT) {
			if (positionCase[0] >0) {
				return this.plateau.get(positionCase[0]-1).get(positionCase[1]);
			} else {
				System.out.println("On sort du plateau !!");
				return null;
			} 
		} else if(direction.getDirection()==Direction.BAS) {
			if (positionCase[0] < this.nbColonnes-1) {
				return this.plateau.get(positionCase[0]+1).get(positionCase[1]);
			} else {
				System.out.println("On sort du plateau !!");
				return null;
			} 
		} else if(direction.getDirection()==Direction.GAUCHE) {
			if (positionCase[1] >0) {
				return this.plateau.get(positionCase[0]).get(positionCase[1]-1);
			} else {
				System.out.println("On sort du plateau !!");
				return null;
			} 
		} else {
			//cas direction = DROITE
			if (positionCase[1] < this.nbColonnes-1) {
				return this.plateau.get(positionCase[0]).get(positionCase[1]+1);
			} else {
				System.out.println("On sort du plateau !!");
				return null; 
			} 
		}
	}
	
	private int[] getPosition(Case laCase) {
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
			return new int[]{-1,-1}; //-1,-1 signifiera que c'est pas dedans 
		} else {
			int j= this.plateau.get(iInterest).indexOf(laCase);
			return new int[] {iInterest,j};
		}	
	}
	
	public String toString() {
		String monString ="[";
		for (int i =0; i<nbLignes;i++) {
			monString=monString+"[";
			for (int j =0; j<nbColonnes;j++) {
				if (plateau.get(i).get(j).getPlot()==null && plateau.get(i).get(j).getTuyau()==null) {
					monString+=",N";
				} else if(plateau.get(i).get(j).getPlot()!=null) {
					monString+=",P("+plateau.get(i).get(j).getPlot().maCouleur+"),";
				} else if(plateau.get(i).get(j).getTuyau()!=null) {
					monString+=",T("+plateau.get(i).get(j).getTuyau().maCouleur+"),";
				}
			}
			monString+="]";
		}
		monString+="]";
		return monString;
	}
}
