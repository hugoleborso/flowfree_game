package freeflow_game;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList<Position> positionsPlots = new ArrayList<>();
		ArrayList<Couleur> couleurPlots = new ArrayList<>();
		positionsPlots.add(new Position(1,1));
		positionsPlots.add(new Position(4,1));
		positionsPlots.add(new Position(2,1));
		positionsPlots.add(new Position(1,4));
		
		couleurPlots.add(Couleur.BLEU);
		couleurPlots.add(Couleur.BLEU);
		couleurPlots.add(Couleur.ROUGE);
		couleurPlots.add(Couleur.ROUGE);
		
		Plateau monPlateau =new Plateau(5,5,positionsPlots, couleurPlots);
		System.out.print(monPlateau);
	}

}
