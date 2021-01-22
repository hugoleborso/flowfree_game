package components;

import java.util.ArrayList;

import enumerations.Couleur;
import enumerations.Direction;

public class Tuyau {
	protected Couleur maCouleur;
	protected ArrayList<Case> caseTraverseesParTuyau= new ArrayList<>();
	public boolean isFini = false; 
	
	public Tuyau(Couleur maCouleur, Case premiereCase) {
		this.maCouleur=maCouleur;
		caseTraverseesParTuyau.add(premiereCase);
		premiereCase.monTuyau=this;
	}
	
	public 	Couleur getCouleur() {
		return this.maCouleur.getCouleur();
	}
	
	public boolean estDansTuyau(Case laCase) {
		if (this.caseTraverseesParTuyau.contains(laCase)) {
			return true;
		}
		return false ;
	}
	
	/**
	 * Cette fonction permet de savoir plusieurs choses : 
	 * <ul>
     * <li> Si la modification du tuyau est acceptée avec le premier booleen
     * </li>
     * <li> Si il faut supprimer le tuyau avec le second.
     * </li>
     * <li> Si il faut ajouter +1 au compteur de tuyaux terminés avec le troisième.
     * </li>
     * </ul>
	 * @param direction
	 * @return boolean[3]
	 */
	public boolean[] modifier(Direction direction) {
		if (this.isFini) {
			return new boolean[]{false,false,false};
		}
		else {
			Case laDerniereCase = (Case) this.caseTraverseesParTuyau.get(this.caseTraverseesParTuyau.size()-1);//on recupere la derniere
			Case laCaseVoulue = laDerniereCase.getCaseVoisine(direction);
			if (laCaseVoulue!=null) {
				boolean[] laCaseAccepteTuyau=laCaseVoulue.accepteTuyau(this);
				if(laCaseAccepteTuyau[0]) return new boolean[]{true,false,laCaseAccepteTuyau[1]};
				return new boolean[]{false,true,false};
			} else {
				return new boolean[]{false,false,false};
			}
		}	
	}
	
	public boolean ajouterCase(Case laCase, boolean isPlotOnCase) {
		if (isPlotOnCase) {
			this.caseTraverseesParTuyau.add(laCase);
			setIsFini(true);
			return true;
		}
		else {
			this.caseTraverseesParTuyau.add(laCase);
			return false;
		}
			
	}
		
	
	
	public void deleteTuyauContent() {
		for ( Case caseDuTuyau : this.caseTraverseesParTuyau) {
			caseDuTuyau.setTuyau(null);
		}
	}
	
	private void setIsFini(boolean isFini) {
		this.isFini=isFini;
	}
}
