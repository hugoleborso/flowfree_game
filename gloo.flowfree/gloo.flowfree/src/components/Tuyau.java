package components;

import java.util.ArrayList;

import enumerations.Couleur;
import enumerations.Direction;

public class Tuyau {
	protected Couleur maCouleur;
	protected ArrayList<Case> casesTraverseesParTuyau= new ArrayList<>();
	public boolean isFini = false; 
	
	public Tuyau(Couleur maCouleur, Case premiereCase) {
		this.maCouleur=maCouleur;
		casesTraverseesParTuyau.add(premiereCase);
		premiereCase.monTuyau=this;
	}
	
	public 	Couleur getCouleur() {
		return this.maCouleur.getCouleur();
	}
	
	public boolean estDansTuyau(Case laCase) {
		if (this.casesTraverseesParTuyau.contains(laCase)) {
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
		boolean modificationAcceptee = false;
		boolean supprimerLeTuyau = false;
		boolean ajouterCompteurTuyauxFinis = false;
		if (! this.isFini) {
			Case laDerniereCase = (Case) this.casesTraverseesParTuyau.get(this.casesTraverseesParTuyau.size()-1);//on recupere la derniere
			Case laCaseVoulue = laDerniereCase.getCaseVoisine(direction);
			if (laCaseVoulue!=null) {
				boolean[] laCaseAccepteTuyau = laCaseVoulue.accepteTuyau(this);
				if(laCaseAccepteTuyau[0]) {
					modificationAcceptee = laCaseAccepteTuyau[0];
					ajouterCompteurTuyauxFinis = laCaseAccepteTuyau[1];
				}else{
					modificationAcceptee = false;
					supprimerLeTuyau = true;
				}
			}
		}
		return new boolean[]{modificationAcceptee,supprimerLeTuyau,ajouterCompteurTuyauxFinis};
	}
	
	public boolean ajouterCase(Case laCase, boolean isPlotOnCase) {
		if (isPlotOnCase) {
			this.casesTraverseesParTuyau.add(laCase);
			setIsFini(true);
			return true;
		}
		else {
			this.casesTraverseesParTuyau.add(laCase);
			return false;
		}
			
	}
		
	
	
	public void deleteTuyauContent() {
		for ( Case caseDuTuyau : this.casesTraverseesParTuyau) {
			caseDuTuyau.setTuyau(null);
		}
	}
	
	private void setIsFini(boolean isFini) {
		this.isFini=isFini;
	}
	
}


