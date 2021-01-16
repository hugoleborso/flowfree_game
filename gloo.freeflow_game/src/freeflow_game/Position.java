package freeflow_game;

public class Position {
	protected int[] tableau;
	public Position(int i, int j) {
		this.tableau= new int[] {i,j};
	}
	public boolean equals (Position t1, Position t2) {
		if (t1.tableau[0]==t2.tableau[0] && t1.tableau[1]==t2.tableau[1]) {
			return true;
		}
		return false;
	}
	
	public String toString(){
		return ("i: "+this.tableau[0]+"j: "+this.tableau[1]);
	}
}
