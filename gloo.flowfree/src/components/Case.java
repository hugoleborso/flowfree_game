package components;

import enumerations.Direction;

public class Case {
	protected Plot monPlot=null;
	protected Tuyau monTuyau=null;
	protected static Plateau lePlateau=null;
	
	public Case(Plateau monPlateau) {
		if(lePlateau==null) lePlateau=monPlateau; // on initialise le plateau une seule fois vu que la variable
												  //sera partag�e par toutes les cases
	}
	
	public Plot getPlot() {
		return(this.monPlot);//on retourne le plot dans les deux cas 
	}
		
	public Tuyau getTuyau() {
		return this.monTuyau;
	}
	
	public Case getCaseVoisine(Direction dir) {
		Case caseVoisine = lePlateau.getMaCaseVoisine(this,dir);
		return(caseVoisine);
	}
	
	/**
	 * Cette fonction permet de savoir si la case accepte un tuyau.
	 * Elle renvoie deux booleens : 
	 * <ul>
	 * <li>Le premier permet de savoir si la case accepte le tuyau
	 * </li>
	 * <li>Le second permet de savoir s'il faut ajouter +1 au compteur de tuyaux finis
	 * </li>
	 * </ul>
	 * @param tuyau
	 * @return boolean[2]
	 */
	
	public boolean[] accepteTuyau(Tuyau tuyau) {
		if (this.monPlot == null && this.monTuyau == null) {
			setTuyau(tuyau);
			monTuyau.ajouterCase(this,false);
			return new boolean[] {true,false};
			
		} else if (this.monPlot!=null && this.monPlot.accepteTuyau(tuyau) && !tuyau.estDansTuyau(this)) {
			setTuyau(tuyau); //ici on termine un chemin plot - tuyau -plot donc on dit au tuyau qu'il est complet 
			return new boolean[]{true,monTuyau.ajouterCase(this,true)};
			
		}else {
			tuyau.deleteTuyauContent();
			tuyau=null; //on supprime le tuyau 
			return new boolean[] {false,false};
		}
	}
	
	public void setTuyau(Tuyau tuyau) {
		if (tuyau!=null) {
			this.monTuyau = tuyau;
		} else {
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

	public boolean deleteTuyau() {
		boolean tuyauEtaitFini = false;
		if(this.monTuyau!=null) {
			if(this.monTuyau.isFini) {
				tuyauEtaitFini = true;
			}
			this.monTuyau.deleteTuyauContent();
			this.monTuyau=null;
		}
		return tuyauEtaitFini;
	} 
	
}
