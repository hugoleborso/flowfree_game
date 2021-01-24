package enumerations;

import java.util.ArrayList;

import components.Case;
import components.Position;
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
	
	protected ArrayList<Direction> directionsTuyau;
	protected ArrayList<Position> positionsPlots;
	protected int[] plotChoisi;

	
	private Couleur() {
		directionsTuyau  = new ArrayList<>();
		positionsPlots   = new ArrayList<>();
		plotChoisi  = new int[] {0,0};
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

	public ArrayList<Direction> getDirectionsTuyau(){
		return this.directionsTuyau;
	}
	
	public void setDirectionsTuyau(Direction direction){
		this.directionsTuyau.add(direction);
	}
	
	public void clearDirectionsTuyau() {
		this.directionsTuyau = new ArrayList<>();
	}
	
	public int[] getCoordonnéesPlotChoisi(){
		return this.plotChoisi;
	}
	
	public void setCoordonnéesPlotChoisi(int[] coordonnées){
		this.plotChoisi = coordonnées;
	}
	
	public ArrayList<Position> getPostionsPlots(){
		return positionsPlots;
	}
	
	public void setPostionPlot(Position position){
		this.positionsPlots.add(position);
	}
}