package freeflow_game;

import java.util.ArrayList;

public class Tuyau {
	protected Couleur maCouleur;
	protected ArrayList<Case> caseTravers�esParTuyau= new ArrayList<>();
	
	public Tuyau(Couleur maCouleur, Case premi�reCase) {
		this.maCouleur=maCouleur;
		caseTravers�esParTuyau.add(premi�reCase);
	}
	
	public 	Couleur getCouleur() {
		return this.maCouleur.getCouleur();
	}
	
	public boolean estDansTuyau(Case laCase) {
		if (this.caseTravers�esParTuyau.contains(laCase)) {
			return true;
		}
		return false ;
	}
	
	public void modifier(Direction direction) {
		Case laDerni�reCase = (Case) this.caseTravers�esParTuyau.get(this.caseTravers�esParTuyau.size()-1);//on r�cup�re la derni�re
		Case laCaseVoulue = laDerni�reCase.getCaseVoisine(direction);
		laCaseVoulue.accepteTuyau(this);
	}
	
	public void ajouterCase(Case laCase) {
		this.caseTravers�esParTuyau.add(laCase);
	}
}
