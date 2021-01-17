package freeflow_game;

public enum Couleur {
	BLEU,
	ROUGE,
	JAUNE,
	VERT,
	ORANGE;
	
	public Tuyau nouveauTuyau(Case laCase) {
		System.out.println("La couleur" +this.name()+" créé le nouveau tuyau");
		if (laCase.monTuyau!=null) {
			//s'il y a déjà un tuyau sur la case on le supprime
			laCase.monTuyau.deleteTuyauContent();
			laCase.setTuyau(null);
		}
		return (new Tuyau( valueOf(this.name()), laCase));
	}
	
	public Couleur getCouleur() {
		return valueOf(this.name());
	}
	
	}
