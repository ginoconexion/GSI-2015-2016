package fr.emac.gipsi.gsi.element;

public class Joueur {

	private String nom;
	private String symbole;
	
	public Joueur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Joueur(String nom, String symbole) {
		super();
		this.nom = nom;
		this.symbole = symbole;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the symbole
	 */
	public String getSymbole() {
		return symbole;
	}

	/**
	 * @param symbole the symbole to set
	 */
	public void setSymbole(String symbole) {
		this.symbole = symbole;
	}
	
}
