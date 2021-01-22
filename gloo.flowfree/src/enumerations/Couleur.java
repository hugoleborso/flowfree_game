package enumerations;

import components.Case;
import components.Tuyau;

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
	
	}
