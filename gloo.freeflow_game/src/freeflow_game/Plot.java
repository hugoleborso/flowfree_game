package freeflow_game;

public class Plot {
	protected Couleur maCouleur;
	protected Case maCase;
	
	public Plot(Couleur maCouleur, Case maCase) {
		this.maCouleur = maCouleur;
		this.maCase =maCase;
	}
	public boolean accepteTuyau(Tuyau tuyau) {
		if (tuyau.getCouleur() == maCouleur.getCouleur()) {
			return true;
		}
		return false; 
	}
	
	public void nouveauTuyau() {
		this.maCouleur.nouveauTuyau(this.maCase);
	}
	
	public Couleur getCouleur() {
		return this.maCouleur.getCouleur();
	}
}
