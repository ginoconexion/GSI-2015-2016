package fr.emac.gipsi.gsi.element;

public class Case {

	private int lig;
	private int col;
	private String symbole;
	
	public Case() {
		super();
	}

	public Case(int lig, int col) {
		super();
		this.lig = lig;
		this.col = col;
		this.symbole = "";
	}

	/**
	 * @return the lig
	 */
	public int getLig() {
		return lig;
	}

	/**
	 * @param lig the lig to set
	 */
	public void setLig(int lig) {
		this.lig = lig;
	}

	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @param col the col to set
	 */
	public void setCol(int col) {
		this.col = col;
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
