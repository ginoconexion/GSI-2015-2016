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
public class Pronostic {

	@Id
	private String id;
	@Property
	private int score1;
	@Property
	private int score2;
	@Property
	private String idMatch;
	@Property
	private String idJoueur;
	
	/**
	 * 
	 */
	public Pronostic() {
	}

	
	
	/**
	 * @param score1
	 * @param score2
	 * @param idMatch
	 * @param idJoueur
	 */
	public Pronostic(String score1, String score2, String idMatch,
			String idJoueur) {
		super();
		  
		this.score1 = Integer.parseInt(score1);
		this.score2 = Integer.parseInt(score2);
		this.idMatch = idMatch;
		this.idJoueur = idJoueur;
		this.id = idJoueur+idMatch;
	}



	/**
	 * @param score1
	 * @param score2
	 * @param idMatch
	 * @param idJoueur
	 */
	public Pronostic(int score1, int score2, String idMatch, String idJoueur) {
		super();
		this.score1 = score1;
		this.score2 = score2;
		this.idMatch = idMatch;
		this.idJoueur = idJoueur;
		this.id = idJoueur+idMatch;
	}



	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the idJoueur
	 */
	public String getIdJoueur() {
		return idJoueur;
	}

	/**
	 * @param idJoueur the idJoueur to set
	 */
	public void setIdJoueur(String idJoueur) {
		this.idJoueur = idJoueur;
	}

	
	
	
}
