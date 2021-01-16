package freeflow_game;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
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
		
		
		System.out.println("====================");
		Plateau monPlateau =new Plateau(5,5,positionsPlots, couleurPlots);
		System.out.print(monPlateau);
		
		Plot plotCliqué=monPlateau.getPlot(0,0);
		if (plotCliqué!=null) {
			Tuyau tuyauCourant = plotCliqué.nouveauTuyau();
			tuyauCourant.modifier(Direction.BAS);
			System.out.print(monPlateau);
			tuyauCourant.modifier(Direction.BAS);
			System.out.print(monPlateau);
			tuyauCourant.modifier(Direction.BAS);
			System.out.print(monPlateau);
			tuyauCourant.modifier(Direction.BAS);
			System.out.print(monPlateau);
			tuyauCourant.modifier(Direction.DROITE);
			
		}
		Plot plotCliqué2=monPlateau.getPlot(0,1);
		if (plotCliqué!=null) {
			Tuyau tuyauCourant = plotCliqué.nouveauTuyau();
			tuyauCourant.modifier(Direction.BAS);
			System.out.print(monPlateau);
			tuyauCourant.modifier(Direction.BAS);
			System.out.print(monPlateau);
			tuyauCourant.modifier(Direction.BAS);
			System.out.print(monPlateau);
			tuyauCourant.modifier(Direction.DROITE);
			System.out.print(monPlateau);
			tuyauCourant.modifier(Direction.BAS);
			System.out.print(monPlateau);
			
		}
		Plot plotCliqué3=monPlateau.getPlot(0,2);
		if (plotCliqué!=null) {
			Tuyau tuyauCourant = plotCliqué.nouveauTuyau();
			tuyauCourant.modifier(Direction.DROITE);
			System.out.print(monPlateau);
			tuyauCourant.modifier(Direction.BAS);
			System.out.print(monPlateau);
			tuyauCourant.modifier(Direction.GAUCHE);
			System.out.print(monPlateau);
			tuyauCourant.modifier(Direction.BAS);
			System.out.print(monPlateau);
			tuyauCourant.modifier(Direction.DROITE);
			System.out.print(monPlateau);
			tuyauCourant.modifier(Direction.BAS);
			System.out.print(monPlateau);
			tuyauCourant.modifier(Direction.BAS);
			
			
		}
		Plot plotCliqué4=monPlateau.getPlot(0,4);
		if (plotCliqué!=null) {
			Tuyau tuyauCourant = plotCliqué.nouveauTuyau();
			tuyauCourant.modifier(Direction.BAS);
			System.out.print(monPlateau);
			tuyauCourant.modifier(Direction.BAS);
			System.out.print(monPlateau);
			tuyauCourant.modifier(Direction.BAS);
			System.out.print(monPlateau);
			tuyauCourant.modifier(Direction.BAS);
			
		}
		System.out.print(monPlateau);
	}

}
