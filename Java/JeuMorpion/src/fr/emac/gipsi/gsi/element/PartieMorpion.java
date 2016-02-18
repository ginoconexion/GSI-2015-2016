/**
 * 
 */
package fr.emac.gipsi.gsi.element;

/**
 * @author Paul
 *
 */
public class PartieMorpion implements IJeuMorpion {
	
	private Grille grille;
	private Joueur j1;
	private Joueur j2;
	private Joueur joueurEncours;
	
  
	public PartieMorpion() {
		super();
		j1 = new Joueur("seb", "X");
		j2 = new Joueur("fred", "O");
		joueurEncours = j1;
		grille = new Grille();
	}

	/* (non-Javadoc)
	 * @see fr.emac.gipsi.gsi.element.IJeuMorpion#getJoueurEnCours()
	 */
	@Override
	public String getJoueurEnCours() {
		// TODO Auto-generated method stub
		return this.joueurEncours.getNom();
	}

	/* (non-Javadoc)
	 * @see fr.emac.gipsi.gsi.element.IJeuMorpion#joueurSuivant()
	 */
	@Override
	public void joueurSuivant() {
		if (joueurEncours.getNom().equals(j1.getNom())){
			joueurEncours = j2;
		}
		else {
			joueurEncours = j1;
		}
	}

	/* (non-Javadoc)
	 * @see fr.emac.gipsi.gsi.element.IJeuMorpion#isSymbole(int, int)
	 */
	@Override
	public boolean isSymbole(int lig, int col) {
		if (isPartieFinie()){
			return true;
		}
		else {
			if (grille.getSymbole(lig, col).equals("")){
				return false;
			} else {
				return false;
			}
		}
	}

	/* (non-Javadoc)
	 * @see fr.emac.gipsi.gsi.element.IJeuMorpion#setSymbole(int, int, java.lang.String)
	 */
	@Override
	public void setSymbole(int lig, int col, String joueurEnCours) {
		if (!isSymbole(lig, col)){
			String symbole = "";
			
			if (joueurEnCours.equals(j1.getNom())){
				symbole = j1.getSymbole();
			}
			else{
				symbole = j2.getSymbole();
			}
 			grille.setSymbole(lig, col, symbole);
		}
	}

	/* (non-Javadoc)
	 * @see fr.emac.gipsi.gsi.element.IJeuMorpion#getSymbole(int, int)
	 */
	@Override
	public String getSymbole(int lig, int col) {
		return grille.getSymbole(lig, col);
	}

	/* (non-Javadoc)
	 * @see fr.emac.gipsi.gsi.element.IJeuMorpion#isPartieFinie()
	 */
	@Override
	public boolean isPartieFinie() {
		if (grille.isVictory())
			return true;
		else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see fr.emac.gipsi.gsi.element.IJeuMorpion#init()
	 */
	@Override
	public void init() {
		grille.initialiser();

	}

}
