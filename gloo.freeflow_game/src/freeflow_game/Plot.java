package freeflow_game;

public class Plot {
	protected Couleur maCouleur;
	
	public Plot(Couleur maCouleur) {
		this.maCouleur = maCouleur;
	}
	public boolean accepteTuyau(Tuyau tuyau) {
		if (tuyau.getCouleur() == maCouleur.getCouleur()) {
			return true;
		}
		return false; 
	}
	
	public Couleur getCouleur() {
		return this.maCouleur.getCouleur();
	}
}
