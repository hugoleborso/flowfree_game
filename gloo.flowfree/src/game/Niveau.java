package game;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import components.Position;
import enumerations.Couleur;


public class Niveau {
	public ArrayList<Position> positionsPlots = new ArrayList<>();
	public ArrayList<Couleur> couleursPlots = new ArrayList<>();
	public int nbTuyauxFinal;
	public int nbCasesGrille;
	public int[] parametres;
	
	public Niveau(){
		 String niveauString=(String)JOptionPane.showInputDialog(null,
                 "Choisissez votre niveau", 
                 "Choix du niveau", 
                 JOptionPane.PLAIN_MESSAGE,
                 null,
                 new String[] {"5","6","7","8","9"}, 
                 "5");
		 
		int niveau = Integer.valueOf(niveauString);
		
        switch(niveau) {
	        case 5 :{
	        	this.parametres = new int[] {5,5};
	        	this.nbTuyauxFinal = 5;
	        	
	        	//on définit les plots
	    		couleursPlots.add(Couleur.ROUGE);
	    		couleursPlots.add(Couleur.ROUGE);
	    		positionsPlots.add(new Position(0,0));
	    		positionsPlots.add(new Position(4,1));
	    	
	    		couleursPlots.add(Couleur.ORANGE);
	    		couleursPlots.add(Couleur.ORANGE);
	    		positionsPlots.add(new Position(1,4));
	    		positionsPlots.add(new Position(4,3));
	    		
	    		couleursPlots.add(Couleur.BLEU);
	    		couleursPlots.add(Couleur.BLEU);
	    		positionsPlots.add(new Position(1,2));
	    		positionsPlots.add(new Position(4,2));
	    		
	    		couleursPlots.add(Couleur.VERT);
	    		couleursPlots.add(Couleur.VERT);
	    		positionsPlots.add(new Position(0,2));
	    		positionsPlots.add(new Position(3,1));
	    		
	    		couleursPlots.add(Couleur.JAUNE);
	    		couleursPlots.add(Couleur.JAUNE);
	    		positionsPlots.add(new Position(0,4));
	    		positionsPlots.add(new Position(3,3));
	        	break;
	        }
	        case 6 :{
	        	this.parametres = new int[] {6,6};
	        	this.nbTuyauxFinal = 4;
	        	
	        	//on définit les plots
	    		couleursPlots.add(Couleur.ROUGE);
	    		couleursPlots.add(Couleur.ROUGE);
	    		positionsPlots.add(new Position(1,3));
	    		positionsPlots.add(new Position(5,5));
	    		
	    		couleursPlots.add(Couleur.ORANGE);
	    		couleursPlots.add(Couleur.ORANGE);
	    		positionsPlots.add(new Position(1,4));
	    		positionsPlots.add(new Position(2,2));
	    		
	    		couleursPlots.add(Couleur.BLEU);
	    		couleursPlots.add(Couleur.BLEU);
	    		positionsPlots.add(new Position(1,5));
	    		positionsPlots.add(new Position(3,2));
	    		
	    		couleursPlots.add(Couleur.VERT);
	    		couleursPlots.add(Couleur.VERT);
	    		positionsPlots.add(new Position(0,5));
	    		positionsPlots.add(new Position(5,4));
	    		break;
	        }
	        case 7 :{
	        	this.parametres = new int[] {7,7};
	        	this.nbTuyauxFinal = 6;
	        	
	        	//on définit les plots
	    		couleursPlots.add(Couleur.ROUGE);
	    		couleursPlots.add(Couleur.ROUGE);
	    		positionsPlots.add(new Position(6,0));
	    		positionsPlots.add(new Position(2,6));
	    		
	    		couleursPlots.add(Couleur.ORANGE);
	    		couleursPlots.add(Couleur.ORANGE);
	    		positionsPlots.add(new Position(2,0));
	    		positionsPlots.add(new Position(5,3));
	    		
	    		couleursPlots.add(Couleur.BLEU);
	    		couleursPlots.add(Couleur.BLEU);
	    		positionsPlots.add(new Position(1,0));
	    		positionsPlots.add(new Position(2,1));
	    		
	    		couleursPlots.add(Couleur.VERT);
	    		couleursPlots.add(Couleur.VERT);
	    		positionsPlots.add(new Position(1,6));
	    		positionsPlots.add(new Position(5,5));
	    		
	    		couleursPlots.add(Couleur.JAUNE);
	    		couleursPlots.add(Couleur.JAUNE);
	    		positionsPlots.add(new Position(1,1));
	    		positionsPlots.add(new Position(3,2));
	    		
	    		couleursPlots.add(Couleur.CYAN);
	    		couleursPlots.add(Couleur.CYAN);
	    		positionsPlots.add(new Position(1,5));
	    		positionsPlots.add(new Position(4,5));
	    		break;
	        }
	        case 8 :{
	        	this.parametres = new int[] {8,8};
	        	this.nbTuyauxFinal = 9;
	        	
	        	//on définit les plots
	    		couleursPlots.add(Couleur.ROUGE);
	    		couleursPlots.add(Couleur.ROUGE);
	    		positionsPlots.add(new Position(1,2));
	    		positionsPlots.add(new Position(5,3));
	    		
	    		couleursPlots.add(Couleur.ORANGE);
	    		couleursPlots.add(Couleur.ORANGE);
	    		positionsPlots.add(new Position(3,4));
	    		positionsPlots.add(new Position(6,6));
	    		
	    		couleursPlots.add(Couleur.BLEU);
	    		couleursPlots.add(Couleur.BLEU);
	    		positionsPlots.add(new Position(2,2));
	    		positionsPlots.add(new Position(6,1));
	    		
	    		couleursPlots.add(Couleur.VERT);
	    		couleursPlots.add(Couleur.VERT);
	    		positionsPlots.add(new Position(3,2));
	    		positionsPlots.add(new Position(6,3));
	    		
	    		couleursPlots.add(Couleur.JAUNE);
	    		couleursPlots.add(Couleur.JAUNE);
	    		positionsPlots.add(new Position(0,4));
	    		positionsPlots.add(new Position(6,7));
	    		
	    		couleursPlots.add(Couleur.CYAN);
	    		couleursPlots.add(Couleur.CYAN);
	    		positionsPlots.add(new Position(0,0));
	    		positionsPlots.add(new Position(0,3));
	    		
	    		couleursPlots.add(Couleur.VIOLET);
	    		couleursPlots.add(Couleur.VIOLET);
	    		positionsPlots.add(new Position(1,3));
	    		positionsPlots.add(new Position(4,4));
	    		
	    		couleursPlots.add(Couleur.ROSE);
	    		couleursPlots.add(Couleur.ROSE);
	    		positionsPlots.add(new Position(1,4));
	    		positionsPlots.add(new Position(1,6));
	    		
	    		couleursPlots.add(Couleur.MARRON);
	    		couleursPlots.add(Couleur.MARRON);
	    		positionsPlots.add(new Position(3,5));
	    		positionsPlots.add(new Position(7,7));
	        	break;
	        }
	        case 9 :{
	        	this.parametres = new int[] {9,9};
	        	this.nbTuyauxFinal = 8;
	        	
	        	//on définit les plots
	    		couleursPlots.add(Couleur.ROUGE);
	    		couleursPlots.add(Couleur.ROUGE);
	    		positionsPlots.add(new Position(2,3));
	    		positionsPlots.add(new Position(8,7));
	    		
	    		couleursPlots.add(Couleur.ORANGE);
	    		couleursPlots.add(Couleur.ORANGE);
	    		positionsPlots.add(new Position(3,5));
	    		positionsPlots.add(new Position(5,2));
	    		
	    		couleursPlots.add(Couleur.BLEU);
	    		couleursPlots.add(Couleur.BLEU);
	    		positionsPlots.add(new Position(0,0));
	    		positionsPlots.add(new Position(5,0));
	    		
	    		couleursPlots.add(Couleur.VERT);
	    		couleursPlots.add(Couleur.VERT);
	    		positionsPlots.add(new Position(6,0));
	    		positionsPlots.add(new Position(7,5));
	    		
	    		couleursPlots.add(Couleur.JAUNE);
	    		couleursPlots.add(Couleur.JAUNE);
	    		positionsPlots.add(new Position(6,3));
	    		positionsPlots.add(new Position(8,8));
	    		
	    		couleursPlots.add(Couleur.CYAN);
	    		couleursPlots.add(Couleur.CYAN);
	    		positionsPlots.add(new Position(5,3));
	    		positionsPlots.add(new Position(4,5));
	    		
	    		couleursPlots.add(Couleur.VIOLET);
	    		couleursPlots.add(Couleur.VIOLET);
	    		positionsPlots.add(new Position(3,2));
	    		positionsPlots.add(new Position(7,7));
	    		
	    		couleursPlots.add(Couleur.ROSE);
	    		couleursPlots.add(Couleur.ROSE);
	    		positionsPlots.add(new Position(7,1));
	    		positionsPlots.add(new Position(5,5));
	        	break;
	        }
        }
	}

}

