package enumerations;

public enum Direction {
	GAUCHE,
	DROITE,
	HAUT,
	BAS;	
	
	public Direction getDirection() {
		return valueOf(this.name());
	}
}
