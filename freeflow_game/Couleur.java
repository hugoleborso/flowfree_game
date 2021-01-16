package freeflow_game;

public enum Couleur {
	BLEU,
	ROUGE,
	JAUNE,
	VERT,
	ORANGE;
	
	public Tuyau nouveauTuyau(Case laCase) {
		return (new Tuyau( valueOf(this.name()), laCase));
	}
	
	public Couleur getCouleur() {
		return valueOf(this.name());
	}
	
	}
