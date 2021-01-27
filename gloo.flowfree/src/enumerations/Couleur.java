package enumerations;

import components.Case;
import components.Tuyau;
import game.DataCouleur;

public enum Couleur {
	BLEU,
	ROUGE,
	JAUNE,
	VERT,
	ORANGE,
	CYAN,
	VIOLET,
	ROSE,
	MARRON;
	
	protected DataCouleur dataCouleur;

	private Couleur() {
		dataCouleur = new DataCouleur();
		//directionsTuyau  = new ArrayList<>();
		//positionsPlots   = new ArrayList<>();
		//plotChoisi  = new int[] {0,0};
	}
	
	public Tuyau nouveauTuyau(Case laCase) {
		if (laCase.getTuyau()!=null) {
			//s'il y a déjà un tuyau sur la case on le supprime
			laCase.getTuyau().deleteTuyauContent();
			laCase.setTuyau(null);
		}
		return (new Tuyau( valueOf(this.name()), laCase));
	}
	
	public Couleur getCouleur() {
		return valueOf(this.name());
	}
	
	public DataCouleur getDataCouleur(){
		return dataCouleur;
	}

}