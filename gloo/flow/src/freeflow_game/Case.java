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
		if (this.monPlot!=null) {
			if (this.monTuyau!=null) {
				//la personne a recliqué sur un plot où elle avait déjà créé un tuyau, on le supprime
			
				this.monTuyau.deleteTuyauContent();
				this.monTuyau=null; //on détruit le tuyau 	
			}
			return(this.monPlot);//on retourne le plot dans les deux cas 
		}
		else {
			//System.out.println("PAS DE PLOT SELECTIONNE");
			return null;//à changer pour une erreur maybe
		}
	}
	//nécessaire pour l'affichage à supprimer apres 
	public Plot getPlot(String impression) {
		if (this.monPlot!=null) {
			return(this.monPlot);//on retourne le plot dans les deux cas 
		}
		else {
			//System.out.println("PAS DE PLOT SELECTIONNE");
			return null;//à changer pour une erreur maybe
		}
	}
	///////////////////////////////////////////
	//necessaire pour l'affichage mnt mais a supprimer apres 
	public Tuyau getTuyau() {
		return this.monTuyau;
	}
	//////////////////////////////////////////////////////////
	
	public Case getCaseVoisine(Direction dir) {
		Case caseVoisine = monPlateau.getMaCaseVoisine(this,dir);
		return(caseVoisine);
	}
	
	public boolean accepteTuyau(Tuyau tuyau) {
		if (this.monPlot == null && this.monTuyau == null) {
			System.out.println("la case accepte le tuyau car vide");
			setTuyau(tuyau);
			monTuyau.ajouterCase(this,false);
			return true;
		} else if (this.monPlot!=null && this.monPlot.accepteTuyau(tuyau) && !tuyau.estDansTuyau(this)) {
			System.out.println("la case accepte le tuyau car le tuyau est de la bonne couleur");
			setTuyau(tuyau); //là on finit une chemin plot - tuyau -plot donc on dit au tuyau qu'il est terminé 
			monTuyau.ajouterCase(this,true);
			return true;
		}else {
			
			tuyau.deleteTuyauContent();
			tuyau=null; //on détruit le tuyau 
			return false;
		}
	}
	
	public void setTuyau(Tuyau tuyau) {
		if (tuyau!=null) {
			System.out.println("le tuyau a été ajouté à la case ");
			this.monTuyau = tuyau; 
		} else {
			System.out.println("le tuyau a été supprimé sur la case ");
			this.monTuyau = null;
		}
		
	}
	
	public boolean valideFinJeu() {
		if (this.monPlot != null && this.monTuyau != null) {
			//Si il y a un plot, pour que le jeu soit fini il faut qu'il y ai aussi un tuyau dessus
			return true;
		} else if (this.monPlot==null && this.monTuyau!=null) {
			//si il n'y a pas de plot sur la case, on a juste besoin d'un tuyau 
			return true;
		} else {
			return false;
		}
		
	}
	public void setPlot(Plot lePlot) {
		this.monPlot= lePlot;
	} 
	
}
