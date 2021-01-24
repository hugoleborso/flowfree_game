package components;

import java.util.ArrayList;
import enumerations.Direction;

public class Infos {

	protected ArrayList<Direction> directionsTuyau;
	protected int[] plotChoisi;
	
	public Infos() {
		directionsTuyau  = new ArrayList<>();
		plotChoisi  = new int[] {0,0};
	}
	
	public ArrayList<Direction> getDirectionsTuyau(){
		return this.directionsTuyau;
	}
	
	public void setDirectionsTuyau(Direction direction){
		this.directionsTuyau.add(direction);
	}
	
	public int[] getCoordonnéesPlotChoisi(){
		return this.plotChoisi;
	}
	
	public void setCoordonnéesPlotChoisi(int[] coordonnées){
		this.plotChoisi=coordonnées;
	}
}
