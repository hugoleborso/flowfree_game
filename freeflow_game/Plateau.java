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
				System.out.println(new int[] {i,j});
				if (arrayPositionPlot.contains(new int[]{i,j})) {
					System.out.print("ça marche frero");
					int positionDansListe = arrayPositionPlot.indexOf(new int[] {i,j});
					this.plateau.get(i).add(new Case(new Plot(arrayCouleurs.get(positionDansListe)),null,this));
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
				return null;
			} 
		} else if(direction.getDirection()==Direction.BAS) {
			if (positionCase[0] < this.nbColonnes-1) {
				return this.plateau.get(positionCase[0]+1).get(positionCase[1]);
			} else {
				return null;
			} 
		} else if(direction.getDirection()==Direction.GAUCHE) {
			if (positionCase[1] >0) {
				return this.plateau.get(positionCase[0]).get(positionCase[1]-1);
			} else {
				return null;
			} 
		} else {
			//cas direction = DROITE
			if (positionCase[1] < this.nbColonnes-1) {
				return this.plateau.get(positionCase[0]).get(positionCase[1]+1);
			} else {
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
					monString+="P("+plateau.get(i).get(j).getPlot().maCouleur+"),";
				} else if(plateau.get(i).get(j).getTuyau()!=null) {
					monString+="T("+plateau.get(i).get(j).getTuyau().maCouleur+"),";
				}
			}
			monString+="]";
		}
		monString+="]";
		return monString;
	}
}
