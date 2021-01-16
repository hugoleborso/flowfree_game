package freeflow_game;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList<int[]> positionsPlots = new ArrayList<>();
		ArrayList<Couleur> couleurPlots = new ArrayList<>();
		positionsPlots.add(new int[] {1,1});
		positionsPlots.add(new int[] {4,1});
		positionsPlots.add(new int[] {2,1});
		positionsPlots.add(new int[] {1,4});
		
		couleurPlots.add(Couleur.BLEU);
		couleurPlots.add(Couleur.BLEU);
		couleurPlots.add(Couleur.ROUGE);
		couleurPlots.add(Couleur.ROUGE);
		
		Plateau monPlateau =new Plateau(5,5,positionsPlots, couleurPlots);
		System.out.print(monPlateau);
	}

}
