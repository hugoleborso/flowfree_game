package freeflow_game;

import java.util.ArrayList;

public class Tuyau {
	protected Couleur maCouleur;
	protected ArrayList<Case> caseTraverseesParTuyau= new ArrayList<>();
	
	public Tuyau(Couleur maCouleur, Case premiereCase) {
		this.maCouleur=maCouleur;
		caseTraverseesParTuyau.add(premiereCase);
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
	
	public void modifier(Direction direction) {
		Case laDerniereCase = (Case) this.caseTraverseesParTuyau.get(this.caseTraverseesParTuyau.size()-1);//on recupère la dernière
		Case laCaseVoulue = laDerniereCase.getCaseVoisine(direction);
		laCaseVoulue.accepteTuyau(this);
	}
	
	public void ajouterCase(Case laCase) {
		this.caseTraverseesParTuyau.add(laCase);
	}
}
