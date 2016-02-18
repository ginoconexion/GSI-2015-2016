/**
 * 
 */
package fr.emac.gipsi.gsi.truptil.element;

import java.util.ArrayList;

import org.mongodb.morphia.annotations.*;

/**
 * @author truptil
 *
 */
public class ResultatMatch {

	@Id
	private String idMatch;
	@Property
	private int score1;
	@Property
	private int score2;
	@Property
	private boolean fini;
	
	/**
	 * 
	 */
	public ResultatMatch() {
	}

	
	
	/**
	 * @param score1
	 * @param score2
	 * @param idMatch
	 * @param idJoueur
	 */
	public ResultatMatch(int score1, int score2, String idMatch) {
		super();
		  
		this.score1 = score1;
		this.score2 = score2;
		this.idMatch = idMatch;
		this.fini=false;
	}



	

	/**
	 * @return the score1
	 */
	public int getScore1() {
		return score1;
	}

	/**
	 * @param score1 the score1 to set
	 */
	public void setScore1(int score1) {
		this.score1 = score1;
	}

	/**
	 * @return the score2
	 */
	public int getScore2() {
		return score2;
	}

	/**
	 * @param score2 the score2 to set
	 */
	public void setScore2(int score2) {
		this.score2 = score2;
	}

	/**
	 * @return the idMatch
	 */
	public String getIdMatch() {
		return idMatch;
	}

	/**
	 * @param idMatch the idMatch to set
	 */
	public void setIdMatch(String idMatch) {
		this.idMatch = idMatch;
	}



	/**
	 * @return the fini
	 */
	public boolean isFini() {
		return fini;
	}



	/**
	 * @param fini the fini to set
	 */
	public void setFini(boolean fini) {
		this.fini = fini;
	}


	
	
	
}
