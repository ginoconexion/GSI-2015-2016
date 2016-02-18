package fr.emac.gipsi.gsi.element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Grille {
	
	private int ligne;
	private int colonne;
	private int nbBombes;
	private boolean isDefaite;
	private ArrayList<ArrayList<Case>> cases;
	
	public Grille(int ligne, int colonne, int nbBombes) {
		super();
		this.ligne = ligne;
		this.colonne = colonne;
		this.nbBombes = nbBombes;
		this.isDefaite = false;
		cases = initialiser(ligne, colonne, nbBombes);
	}

	private ArrayList<ArrayList<Case>> initialiser(int ligne, int colonne, int nbBombes) {

		ArrayList<ArrayList<Case>> grille = new ArrayList<ArrayList<Case>>();
		for (int lig = 0; lig < ligne; lig++) {
			ArrayList<Case> l = new ArrayList<Case>();
			for (int col = 0; col < colonne; col++) {
				Case c = new Case(lig, col, false);
				l.add(c);
			}
			grille.add(l);
		}
		
		for (int i = 0; i < nbBombes; i++) {
			int flag = 0;
			while (flag == 0){
				Random r = new Random();
				int lig = r.nextInt(ligne);
				int col = r.nextInt(colonne);
				
				if (!grille.get(lig).get(col).isBombe()){
					grille.get(lig).get(col).setBombe(true);
					flag = 1;
				}
			}
		}
		
		grille = calculNbBombeAlentour(grille);
		
		return grille;
	}

	private ArrayList<ArrayList<Case>> calculNbBombeAlentour(ArrayList<ArrayList<Case>> grille) {

		for (int lig = 0; lig < ligne; lig++) {
			for (int col = 0; col < colonne; col++) {
				if (!grille.get(lig).get(col).isBombe()){
					grille.get(lig).get(col).setNbBombeAlentour(calculNbBombeAlentour(grille, lig, col));
					
					
				}
			}
		}
		
		return grille;
	}

	private int calculNbBombeAlentour(ArrayList<ArrayList<Case>> grille, int lig, int col) {
		int nbBombeCase = 0;
		
		int ligDebut = lig - 1;
		int ligFin = lig + 1;
		int colDebut = col - 1;
		int colFin = col + 1;
		
		if (ligDebut<0)
			ligDebut = 0;
		if (colDebut<0)
			colDebut = 0;
		if (ligFin>ligne-1)
			ligFin=ligne-1;
		if (colFin>colonne-1)
			colFin=colonne-1;
		
		for (int i = ligDebut; i <= ligFin; i++) {
			for (int j = colDebut; j <= colFin; j++) {
				if (grille.get(i).get(j).isBombe()){
					nbBombeCase++;
				}
			}
		}
		
		return nbBombeCase;
	}
	
	public void decouvrir1Case(int lig, int col){
		if (cases.get(lig).get(col).isBombe())
			this.isDefaite = true;
		else
			cases.get(lig).get(col).setVisible(true);
	}
	public boolean isVictory(){
		for (int lig = 0; lig < ligne; lig++) {
			for (int col = 0; col < colonne; col++) {
				if(!cases.get(lig).get(col).isBombe() && !cases.get(lig).get(col).isVisible())
					return false;
			}
		} 
		return true;
	}
	public boolean isDefaite(){
		return isDefaite;
	}
	
	public Case getCase(int lig, int col){
		return this.cases.get(lig).get(col);
	}

	/**
	 * @return the ligne
	 */
	public int getLigne() {
		return ligne;
	}

	/**
	 * @param ligne the ligne to set
	 */
	public void setLigne(int ligne) {
		this.ligne = ligne;
	}

	/**
	 * @return the colonne
	 */
	public int getColonne() {
		return colonne;
	}

	/**
	 * @param colonne the colonne to set
	 */
	public void setColonne(int colonne) {
		this.colonne = colonne;
	}
	
	
	

}
