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
		Case laDerniereCase = (Case) this.caseTraverseesParTuyau.get(this.caseTraverseesParTuyau.size()-1);//on recupere la derniere
		Case laCaseVoulue = laDerniereCase.getCaseVoisine(direction);
		if (laCaseVoulue!=null) {
			laCaseVoulue.accepteTuyau(this);
		} else {
			System.out.println("On ne peut pas modifier car la case n'existe pas");
		}
		
	}
	
	public void ajouterCase(Case laCase) {
		if (!this.caseTraverseesParTuyau.contains(laCase)) {
			System.out.println("case ajoutée au tuyau");
			this.caseTraverseesParTuyau.add(laCase);
		} else {
			System.out.println("case non ajoutée car elle etait déjà dedans ");
		}
		
	}
}
