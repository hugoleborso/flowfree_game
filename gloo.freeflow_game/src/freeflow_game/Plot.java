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
			System.out.println(" plot accepte le tuyau");
			return true;
		}
		System.out.println("plot refuse le tuyau");
		return false; 
	}
	
	public Tuyau nouveauTuyau() {
		System.out.println("création tuyau");
		return this.maCouleur.nouveauTuyau(this.maCase);
	}
	
	public Couleur getCouleur() {
		return this.maCouleur.getCouleur();
	}
}
