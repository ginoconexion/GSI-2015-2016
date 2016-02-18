/**
 * 
 */
package fr.emac.gipsi.gsi.element;

import fr.emac.gipsi.gsi.gui.Screen;

/**
 * @author truptil
 *
 */
public class Pion {

	private int NbVie;
	private Position pos;
	private Position lastPos;
	private int argentGagne;
	
	/**
	 * 
	 */
	public Pion() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param nbVie
	 * @param pos
	 */
	public Pion(int nbVie, Position pos) {
		super();
		NbVie = nbVie;
		this.pos = pos;
		this.lastPos=new Position(pos.getX(), pos.getY());
		this.argentGagne=10;
	}
	
	public void deplacement(Screen ecran) {
		
		//on deplace
		if(ecran.getColorByXY(pos.getX()+1, pos.getY()).getNomCouleur().equals("White")
				&& pos.isDifferent(1,0,lastPos)){
			lastPos.changeValue(pos);
			pos.setX(pos.getX()+1);
		}else if(ecran.getColorByXY(pos.getX(), pos.getY()+1).getNomCouleur().equals("White")
				&& pos.isDifferent(0,1,lastPos)){
			lastPos.changeValue(pos);
			pos.setY(pos.getY()+1);
		}else if(pos.getX()>0 &&
				ecran.getColorByXY(pos.getX()-1, pos.getY()).getNomCouleur().equals("White")
				&& pos.isDifferent(-1,0,lastPos)){
			lastPos.changeValue(pos);
			pos.setX(pos.getX()-1);
		}else if(pos.getY() >0 &&
				ecran.getColorByXY(pos.getX(), pos.getY()-1).getNomCouleur().equals("White")
				&& pos.isDifferent(0,-1,lastPos)){
			lastPos.changeValue(pos);
			pos.setY(pos.getY()-1);
		}
	}
	
	public boolean estArrive(Screen ecran){
		
		if (this.getPos().getX() == 13 && this.getPos().getY() == 13){
			return true;
		}
		
		return false;
	}
	
	/**
	 * @return the nbVie
	 */
	public int getNbVie() {
		return NbVie;
	}
	/**
	 * @param nbVie the nbVie to set
	 */
	public void setNbVie(int nbVie) {
		NbVie = nbVie;
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
	/**
	 * @return the argentGagne
	 */
	public int getArgentGagne() {
		return argentGagne;
	}
	/**
	 * @param argentGagne the argentGagne to set
	 */
	public void setArgentGagne(int argentGagne) {
		this.argentGagne = argentGagne;
	}
	

	
}
