/**
 * 
 */
package fr.emac.gipsi.gsi.element;

/**
 * @author truptil
 *
 */
public abstract class AbstractTower {

	private Position pos;
	
	
	/**
	 * 
	 */
	public AbstractTower() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * 
	 */
	public AbstractTower(Position pos) {
		this.pos=pos;
	}

	public abstract int getCostUpgrade() ;

	public abstract String getColorName();


	public abstract int getRange() ;

	public abstract String getType() ;

	public abstract int getPower() ;
	public abstract void upgrade();

	public Position getPosition() {
		// TODO Auto-generated method stub
		return pos;
	}


	/**
	 * @return the pos
	 */
	public Position getPos() {
		return pos;
	}


	/**
	 * @param pos the pos to set
	 */
	public void setPos(Position pos) {
		this.pos = pos;
	}

}
