package game;

import javax.swing.JOptionPane;

import components.Position;
import enumerations.Couleur;


public class Niveau {
	protected int niveau;
	public int nbTuyauxFinal;
	public int nbCasesGrille;
	
	public Niveau(){
		 String niveauString=(String)JOptionPane.showInputDialog(null,
                 "Choisissez votre niveau", 
                 "Choix du niveau", 
                 JOptionPane.PLAIN_MESSAGE,
                 null,
                 new String[] {"5","6","7","8","9"}, 
                 "5");
		 
		
		if (niveauString != null) {
			niveau = Integer.valueOf(niveauString);
	        switch(niveau) {
		        case 5 :{
		        	this.nbTuyauxFinal = 5;
		    		
		    		//on définit les plots
		    		Couleur.ROUGE.setPostionPlot(new Position(0,0));
		    		Couleur.ROUGE.setPostionPlot(new Position(4,1));
		    		
		    		Couleur.ORANGE.setPostionPlot(new Position(1,4));
		    		Couleur.ORANGE.setPostionPlot(new Position(4,3));
		    		
		    		Couleur.BLEU.setPostionPlot(new Position(1,2));
		    		Couleur.BLEU.setPostionPlot(new Position(4,2));
		    		
		    		Couleur.VERT.setPostionPlot(new Position(0,2));
		    		Couleur.VERT.setPostionPlot(new Position(3,1));
		    		
		    		Couleur.JAUNE.setPostionPlot(new Position(0,4));
		    		Couleur.JAUNE.setPostionPlot(new Position(3,3));
		    		
		        	break;
		        }
		        case 6 :{
		        	this.nbTuyauxFinal = 4;
		        	
		        	//on définit les plots
		    		Couleur.ROUGE.setPostionPlot(new Position(1,3));
		    		Couleur.ROUGE.setPostionPlot(new Position(5,5));
		    		
		    		Couleur.ORANGE.setPostionPlot(new Position(1,4));
		    		Couleur.ORANGE.setPostionPlot(new Position(2,2));

		    		Couleur.BLEU.setPostionPlot(new Position(1,5));
		    		Couleur.BLEU.setPostionPlot(new Position(3,2));
		    		
		    		Couleur.VERT.setPostionPlot(new Position(0,5));
		    		Couleur.VERT.setPostionPlot(new Position(5,4));
		    		
		    		break;
		        }
		        case 7 :{
		        	this.nbTuyauxFinal = 6;
		        	
		        	//on définit les plots
		    		Couleur.ROUGE.setPostionPlot(new Position(6,0));
		    		Couleur.ROUGE.setPostionPlot(new Position(2,6));
		    		
		    		Couleur.ORANGE.setPostionPlot(new Position(2,0));
		    		Couleur.ORANGE.setPostionPlot(new Position(5,3));
		    		
		    		Couleur.BLEU.setPostionPlot(new Position(1,0));
		    		Couleur.BLEU.setPostionPlot(new Position(2,1));
		    		
		    		Couleur.VERT.setPostionPlot(new Position(1,6));
		    		Couleur.VERT.setPostionPlot(new Position(5,5));

		    		Couleur.JAUNE.setPostionPlot(new Position(1,1));
		    		Couleur.JAUNE.setPostionPlot(new Position(3,2));

		    		Couleur.CYAN.setPostionPlot(new Position(1,5));
		    		Couleur.CYAN.setPostionPlot(new Position(4,5));

		    		break;
		        }
		        case 8 :{
		        	this.nbTuyauxFinal = 9;
		        	
		        	//on définit les plots
		    		Couleur.ROUGE.setPostionPlot(new Position(1,2));
		    		Couleur.ROUGE.setPostionPlot(new Position(5,3));
		    	
		    		Couleur.ORANGE.setPostionPlot(new Position(3,4));
		    		Couleur.ORANGE.setPostionPlot(new Position(6,6));

		    		Couleur.BLEU.setPostionPlot(new Position(2,2));
		    		Couleur.BLEU.setPostionPlot(new Position(6,1));

		    		Couleur.VERT.setPostionPlot(new Position(3,2));
		    		Couleur.VERT.setPostionPlot(new Position(6,3));
		    		
		    		Couleur.JAUNE.setPostionPlot(new Position(0,4));
		    		Couleur.JAUNE.setPostionPlot(new Position(6,7));
	
		    		Couleur.CYAN.setPostionPlot(new Position(0,0));
		    		Couleur.CYAN.setPostionPlot(new Position(0,3));
		    		
		    		Couleur.VIOLET.setPostionPlot(new Position(1,3));
		    		Couleur.VIOLET.setPostionPlot(new Position(4,4));

		    		Couleur.ROSE.setPostionPlot(new Position(1,4));
		    		Couleur.ROSE.setPostionPlot(new Position(1,6));

		    		Couleur.MARRON.setPostionPlot(new Position(3,5));
		    		Couleur.MARRON.setPostionPlot(new Position(7,7));		
		   
		        	break;
		        }
		        case 9 :{
		        	this.nbTuyauxFinal = 8;
		        	
		        	//on définit les plots
		    		Couleur.ROUGE.setPostionPlot(new Position(2,3));
		    		Couleur.ROUGE.setPostionPlot(new Position(8,7));

		    		Couleur.ORANGE.setPostionPlot(new Position(3,5));
		    		Couleur.ORANGE.setPostionPlot(new Position(5,2));

		    		Couleur.BLEU.setPostionPlot(new Position(0,0));
		    		Couleur.BLEU.setPostionPlot(new Position(5,0));

		    		Couleur.VERT.setPostionPlot(new Position(6,0));
		    		Couleur.VERT.setPostionPlot(new Position(7,5));

		    		Couleur.JAUNE.setPostionPlot(new Position(6,3));
		    		Couleur.JAUNE.setPostionPlot(new Position(8,8));

		    		Couleur.CYAN.setPostionPlot(new Position(5,3));
		    		Couleur.CYAN.setPostionPlot(new Position(4,5));
		    		
		    		Couleur.VIOLET.setPostionPlot(new Position(3,2));
		    		Couleur.VIOLET.setPostionPlot(new Position(7,7));
		    		
		    		Couleur.ROSE.setPostionPlot(new Position(7,1));
		    		Couleur.ROSE.setPostionPlot(new Position(5,5));
		    		
		        	break;
		        }
	        }
		}
		else {
			niveau=0;
		}
	}

	public int getNiveau() {
		return niveau;
	}
	
}

