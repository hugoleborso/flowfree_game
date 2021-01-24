package game;

import java.util.ArrayList;

import components.Position;
import enumerations.Direction;

public class DataCouleur {

	protected ArrayList<Direction> directionsTuyau;
	protected ArrayList<Position> positionsPlots;
	protected int[] plotChoisi;

	public DataCouleur() {
		directionsTuyau  = new ArrayList<>();
		positionsPlots   = new ArrayList<>();
		plotChoisi  = new int[] {0,0};
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
