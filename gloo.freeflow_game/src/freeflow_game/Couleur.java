package freeflow_game;

public enum Couleur {
	BLEU,
	ROUGE,
	JAUNE,
	VERT,
	ORANGE;
	
	public Tuyau nouveauTuyau(Case laCase) {
		System.out.println("La couleur" +this.name()+" créé le nouveau tuyau");
		return (new Tuyau( valueOf(this.name()), laCase));
	}
	
	public Couleur getCouleur() {
		return valueOf(this.name());
	}
	
	}
