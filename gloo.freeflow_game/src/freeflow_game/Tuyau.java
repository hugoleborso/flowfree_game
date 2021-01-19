package freeflow_game;

import java.util.ArrayList;

public class Tuyau {
	protected Couleur maCouleur;
	protected ArrayList<Case> caseTraverseesParTuyau= new ArrayList<>();
	protected boolean isFinis =false; 
	
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
	
	public boolean[] modifier(Direction direction) {
		if (this.isFinis) {
			System.out.println("on ne peut plus avancer car on a déjà atteitn le plot sorry bro");
			return new boolean[]{false,false};
		}
		else {
			Case laDerniereCase = (Case) this.caseTraverseesParTuyau.get(this.caseTraverseesParTuyau.size()-1);//on recupere la derniere
			Case laCaseVoulue = laDerniereCase.getCaseVoisine(direction);
			if (laCaseVoulue!=null) {
				if(laCaseVoulue.accepteTuyau(this)) return new boolean[]{true,false};
				return new boolean[]{false,true};
			} else {
				System.out.println("On ne peut pas modifier car la case n'existe pas ou n'est pas dispo");
				return new boolean[]{false,false};
			}
		}	
	}
	
	public void ajouterCase(Case laCase, boolean isPlotOnCase) {
		if (isPlotOnCase) {
			System.out.println("case ajoutée au tuyau et tuyau fini");
			this.caseTraverseesParTuyau.add(laCase);
			setIsFinis(true);
		}
		else {
			System.out.println("case ajoutée au tuyau");
			this.caseTraverseesParTuyau.add(laCase);
		}
			
	}
		
	
	
	public void deleteTuyauContent() {
		System.out.println("ERRRRURRRRR");
		for ( Case caseDuTuyau : this.caseTraverseesParTuyau) {
			caseDuTuyau.setTuyau(null);
		}
	}
	
	private void setIsFinis(boolean isFinis) {
		this.isFinis=isFinis;
	}
}
