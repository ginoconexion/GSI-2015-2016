package fr.emac.gipsi.gsi.element;

public class Case {
	
	private int lig;
	private int col;
	private int nbBombeAlentour;
	private boolean isVisible;
	private boolean isBombe;
	
	
	public Case(int lig, int col, boolean isBombe) {
		super();
		this.lig = lig;
		this.col = col;
		this.isBombe = isBombe;
		nbBombeAlentour = 0;
		isVisible = false;
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
	 * @return the nbBombeAlentour
	 */
	public int getNbBombeAlentour() {
		return nbBombeAlentour;
	}


	/**
	 * @param nbBombeAlentour the nbBombeAlentour to set
	 */
	public void setNbBombeAlentour(int nbBombeAlentour) {
		this.nbBombeAlentour = nbBombeAlentour;
	}


	/**
	 * @return the isVisible
	 */
	public boolean isVisible() {
		return isVisible;
	}


	/**
	 * @param isVisible the isVisible to set
	 */
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}


	/**
	 * @return the isBombe
	 */
	public boolean isBombe() {
		return isBombe;
	}


	/**
	 * @param isBombe the isBombe to set
	 */
	public void setBombe(boolean isBombe) {
		this.isBombe = isBombe;
	}
	
	
	
	

}
