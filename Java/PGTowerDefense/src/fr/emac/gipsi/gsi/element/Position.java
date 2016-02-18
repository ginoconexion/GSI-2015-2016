/**
 * 
 */
package fr.emac.gipsi.gsi.element;

/**
 * @author truptil
 *
 */
public class Position {

	private int x;
	private int y;
	
	/**
	 * 
	 */
	public Position() {

	}

	/**
	 * @param x
	 * @param y
	 */
	public Position(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	

	public boolean isDifferent(int i, int j, Position lastPos) {

		if (x+i==lastPos.getX() && y+j== lastPos.getY()){
			return false;
		}else{
		return true;
		}
	}

	public void changeValue(Position pos) {

		this.setX(pos.getX());
		this.setY(pos.getY());
		
	}

	public boolean is(int lig, int col) {
		if(x==lig && y==col){
			return true;
		}else{
		return false;
		}
	}

	public boolean isInZoneAttack(Position pos, int range) {
		
		int diffx = (x-pos.getX())*(x-pos.getX()); 
		int diffy = (y-pos.getY())*(y-pos.getY()); 
		
		double distance = Math.sqrt((diffx + diffy));
		
		if(distance <= range ){
			return true;
		}else{
			return false;
		}
		
	}
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
}
