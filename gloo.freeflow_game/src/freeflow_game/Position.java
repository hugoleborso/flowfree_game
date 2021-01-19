package freeflow_game;

public class Position {
	protected int i;
	protected int j;
	public Position(int i, int j) {
		this.i= i;
		this.j = j;
	}
	public boolean equals ( Object t2) {
		if (!(t2 instanceof Position)) return false;
		else {
			Position t2Position= (Position) t2;
			return this.i == t2Position.i && this.j==t2Position.j;
		}
	}
	public int hashCode() {
		//On donne un identifiant unique à chaque position 
		return ((this.j-1)*Plateau.TAILLE+(i-1));
	}
	
	public int getI() {
		return this.i;
	}
	public int getJ() {
		return this.j;
	}
	// Méthode uniquement utilisées pour l'affichage lors des test, elle serait supprimée en prod
	public String toString(){
		return ("("+this.i+", "+this.j+")");
	}
}
