package freeflow_game;

import java.util.ArrayList;

public class Tuyau {
	protected Couleur maCouleur;
	protected ArrayList<Case> caseTraverséesParTuyau= new ArrayList<>();
	
	public Tuyau(Couleur maCouleur, Case premièreCase) {
		this.maCouleur=maCouleur;
		caseTraverséesParTuyau.add(premièreCase);
	}
	
	public 	Couleur getCouleur() {
		return this.maCouleur.getCouleur();
	}
	
	public boolean estDansTuyau(Case laCase) {
		if (this.caseTraverséesParTuyau.contains(laCase)) {
			return true;
		}
		return false ;
	}
	
	public void modifier(Direction direction) {
		Case laDernièreCase = (Case) this.caseTraverséesParTuyau.get(this.caseTraverséesParTuyau.size()-1);//on récupère la dernière
		Case laCaseVoulue = laDernièreCase.getCaseVoisine(direction);
		laCaseVoulue.accepteTuyau(this);
	}
	
	public void ajouterCase(Case laCase) {
		this.caseTraverséesParTuyau.add(laCase);
	}
}
