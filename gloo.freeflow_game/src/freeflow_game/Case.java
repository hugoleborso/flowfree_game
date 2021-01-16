package freeflow_game;

public class Case {
	protected Plot monPlot=null;
	protected Tuyau monTuyau=null;
	protected Plateau monPlateau;
	
	public Case(Plot monPlot, Tuyau monTuyau, Plateau monPlateau) {
		this.monPlot = monPlot;
		this.monTuyau = monTuyau;
		this.monPlateau=monPlateau;
	}
	
	public Plot getPlot() {
		return(this.monPlot);
	}
	//necessaire pour l'affichage mnt mais a supprimer apres 
	public Tuyau getTuyau() {
		return this.monTuyau;
	}
	//////////////////////////////////////////////////////////
	
	public Case getCaseVoisine(Direction dir) {
		Case caseVoisine = monPlateau.getMaCaseVoisine(this,dir);
		return(caseVoisine);
	}
	
	public void accepteTuyau(Tuyau tuyau) {
		if (this.monPlot == null && this.monTuyau == null) {
			setTuyau(tuyau);
			tuyau.ajouterCase(this);
		} else if (this.monPlot!=null && this.monPlot.accepteTuyau(tuyau)) {
			setTuyau(tuyau); //là on finit une chemin plot - tuyau -plot donc a voir 
			tuyau.ajouterCase(this);
		}else {
			tuyau = null; // on detruit le tuyau 
		}
	}
	
	private void setTuyau(Tuyau tuyau) {
		this.monTuyau = tuyau; 
	}
	
	public boolean valideFinJeu() {
		if (this.monTuyau != null || this.monPlot != null) {
			return true;
		}
		return false;
	}
	public void setPlot(Plot lePlot) {
		this.monPlot= lePlot;
	}
	
}
