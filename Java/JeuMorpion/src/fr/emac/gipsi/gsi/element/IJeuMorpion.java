package fr.emac.gipsi.gsi.element;

public interface IJeuMorpion {

	public String getJoueurEnCours();
	public void joueurSuivant();
	public boolean isSymbole(int lig, int col);
	public void setSymbole(int lig, int col, String joueurEnCours);
	public String getSymbole(int lig, int col);
	public boolean isPartieFinie();
	public void init();
	
}
