package freeflow_game;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		//exemple d'un test où on finit le jeu. 
		//On ne met pas les autres tentatives que l'on a fait afin de ne pas alourdir
		//mais le concept à été le même pour tous nos tests 
		ArrayList<Position> positionsPlots = new ArrayList<>();
		ArrayList<Couleur> couleurPlots = new ArrayList<>();
		positionsPlots.add(new Position(0,0));
		positionsPlots.add(new Position(0,1));
		positionsPlots.add(new Position(0,2));
		positionsPlots.add(new Position(0,4));
		positionsPlots.add(new Position(4,1));
		positionsPlots.add(new Position(4,2));
		positionsPlots.add(new Position(4,3));
		positionsPlots.add(new Position(4,4));
		
		couleurPlots.add(Couleur.BLEU);
		couleurPlots.add(Couleur.ROUGE);
		couleurPlots.add(Couleur.JAUNE);
		couleurPlots.add(Couleur.ORANGE);
		couleurPlots.add(Couleur.BLEU);
		couleurPlots.add(Couleur.ROUGE);
		couleurPlots.add(Couleur.JAUNE);
		couleurPlots.add(Couleur.ORANGE);
		
		
		System.out.println("===INITIALISATION===");
		Plateau monPlateau =new Plateau(5,5,positionsPlots, couleurPlots);
		System.out.println(monPlateau);
		System.out.println("====================");
		
		Plot plotCliqué=monPlateau.getPlot(0,0);
		if (plotCliqué!=null) {
			System.out.println("===TUYAU BLEU===");
			Tuyau tuyauCourant = plotCliqué.nouveauTuyau();
			tuyauCourant.modifier(Direction.BAS);
			System.out.println(monPlateau);
			tuyauCourant.modifier(Direction.BAS);
			System.out.println(monPlateau);
			tuyauCourant.modifier(Direction.BAS);
			System.out.println(monPlateau);
			tuyauCourant.modifier(Direction.BAS);
			System.out.println(monPlateau);
			tuyauCourant.modifier(Direction.DROITE);
			System.out.println(monPlateau);
			System.out.println("=================");
			
			
			
		}
		Plot plotCliqué2=monPlateau.getPlot(0,1);
		if (plotCliqué2!=null) {
			System.out.println("===TUYAU ROUGE===");
			Tuyau tuyauCourant = plotCliqué2.nouveauTuyau();
			tuyauCourant.modifier(Direction.BAS);
			System.out.println(monPlateau);
			tuyauCourant.modifier(Direction.BAS);
			System.out.println(monPlateau);
			tuyauCourant.modifier(Direction.BAS);
			System.out.println(monPlateau);
			tuyauCourant.modifier(Direction.DROITE);
			System.out.println(monPlateau);
			tuyauCourant.modifier(Direction.BAS);
			System.out.println(monPlateau);
			System.out.println("=================");
			
		}
		Plot plotCliqué3=monPlateau.getPlot(0,2);
		if (plotCliqué3!=null) {
			System.out.println("===TUYAU JAUNE===");
			Tuyau tuyauCourant = plotCliqué3.nouveauTuyau();
			tuyauCourant.modifier(Direction.DROITE);
			System.out.println(monPlateau);
			tuyauCourant.modifier(Direction.BAS);
			System.out.println(monPlateau);
			tuyauCourant.modifier(Direction.GAUCHE);
			System.out.println(monPlateau);
			tuyauCourant.modifier(Direction.BAS);
			System.out.println(monPlateau);
			tuyauCourant.modifier(Direction.DROITE);
			System.out.println(monPlateau);
			tuyauCourant.modifier(Direction.BAS);
			System.out.println(monPlateau);
			tuyauCourant.modifier(Direction.BAS);
			System.out.println(monPlateau);
			System.out.println("=================");
			
			
		}
		monPlateau.plateauComplet();
		Plot plotCliqué4=monPlateau.getPlot(0,4);
		if (plotCliqué4!=null) {
			System.out.println("===TUYAU ORANGE===");
			Tuyau tuyauCourant = plotCliqué4.nouveauTuyau();
			tuyauCourant.modifier(Direction.BAS);
			System.out.println(monPlateau);
			tuyauCourant.modifier(Direction.BAS);
			System.out.println(monPlateau);
			tuyauCourant.modifier(Direction.BAS);
			System.out.println(monPlateau);
			tuyauCourant.modifier(Direction.BAS);
			System.out.println(monPlateau);
			tuyauCourant.modifier(Direction.BAS);
			System.out.println(monPlateau);
			System.out.println("=================");
			
			
		}
		System.out.println("====PLATEAU FINAL=====");
		System.out.print(monPlateau);
		System.out.println("====VALIDATION PLATEAU COMPLET");
		if(monPlateau.plateauComplet()) System.out.println("Le plateau est bien complet");
		else System.out.println("Le plateau n'est pas complet : ERREUR ");
	}

}
