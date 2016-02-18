/**
 * 
 */
package fr.emac.gipsi.gsi.truptil.element;

/**
 * @author truptil
 *
 */
public class LigneClassement {

	private int groupe;
	private int pos;
	private String equipe;
	private int point;
	private int victoire;
	private int nul;
	private int defaite;
	private int butPour;
	private int butContre;
	private int goalAverage;
	
	/**
	 * 
	 */
	public LigneClassement() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param groupe
	 * @param equipe
	 */
	public LigneClassement(int groupe, String equipe) {
		super();
		this.groupe = groupe;
		this.equipe = equipe;
	}

	/**
	 * @return the groupe
	 */
	public int getGroupe() {
		return groupe;
	}

	/**
	 * @param groupe the groupe to set
	 */
	public void setGroupe(int groupe) {
		this.groupe = groupe;
	}

	/**
	 * @return the pos
	 */
	public int getPos() {
		return pos;
	}

	/**
	 * @param pos the pos to set
	 */
	public void setPos(int pos) {
		this.pos = pos;
	}

	/**
	 * @return the equipe
	 */
	public String getEquipe() {
		return equipe;
	}

	/**
	 * @param equipe the equipe to set
	 */
	public void setEquipe(String equipe) {
		this.equipe = equipe;
	}

	/**
	 * @return the point
	 */
	public int getPoint() {
		return point;
	}

	/**
	 * @param point the point to set
	 */
	public void setPoint(int point) {
		this.point = point;
	}

	/**
	 * @return the victoire
	 */
	public int getVictoire() {
		return victoire;
	}

	/**
	 * @param victoire the victoire to set
	 */
	public void setVictoire(int victoire) {
		this.victoire = victoire;
	}

	/**
	 * @return the nul
	 */
	public int getNul() {
		return nul;
	}

	/**
	 * @param nul the nul to set
	 */
	public void setNul(int nul) {
		this.nul = nul;
	}

	/**
	 * @return the defaite
	 */
	public int getDefaite() {
		return defaite;
	}

	/**
	 * @param defaite the defaite to set
	 */
	public void setDefaite(int defaite) {
		this.defaite = defaite;
	}

	/**
	 * @return the butPour
	 */
	public int getButPour() {
		return butPour;
	}

	/**
	 * @param butPour the butPour to set
	 */
	public void setButPour(int butPour) {
		this.butPour = butPour;
	}

	/**
	 * @return the butContre
	 */
	public int getButContre() {
		return butContre;
	}

	/**
	 * @param butContre the butContre to set
	 */
	public void setButContre(int butContre) {
		this.butContre = butContre;
	}

	/**
	 * @return the goalAverage
	 */
	public int getGoalAverage() {
		return goalAverage;
	}

	/**
	 * @param goalAverage the goalAverage to set
	 */
	public void setGoalAverage(int goalAverage) {
		this.goalAverage = goalAverage;
	}

	
}
