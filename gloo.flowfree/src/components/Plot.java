package components;

import enumerations.Couleur;

public class Plot {
	protected Couleur maCouleur;
	protected Case maCase;
	
	public Plot(Couleur maCouleur, Case maCase) {
		this.maCouleur = maCouleur;
		this.maCase = maCase;
	}
	public boolean accepteTuyau(Tuyau tuyau) {
		if (tuyau.getCouleur() == maCouleur.getCouleur()) {
			return true;
		}
		return false; 
	}
	
	public Tuyau nouveauTuyau() {
		return this.maCouleur.nouveauTuyau(this.maCase);
	}
	
	public Couleur getCouleur() {
		return this.maCouleur.getCouleur();
	}
	
	public Case getCase() {
		return this.maCase;
	}
}
