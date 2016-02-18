/**
 * 
 */
package fr.emac.gipsi.gsi.element;

import java.util.ArrayList;
import java.util.Iterator;

import fr.emac.gipsi.gsi.gui.Screen;

/**
 * @author truptil
 *
 */
public class Vague {

	private int difficulty;
	private ArrayList<Pion> pions;
	private ArrayList<Pion> pionsArrives = new ArrayList<Pion>();
	private ArrayList<Pion> pionsDetruits = new ArrayList<Pion>();

	public Vague(int d) {
		difficulty=d;
		pions = new ArrayList<Pion>();
		for(int i=0;i<10;i++){
			Pion p = new Pion(10*d,new Position(i, 1));
			pions.add(p);
		}

	}

	public boolean isFinish() {
		if (pions.size()==0){
			return true;
		}
		return false;
	}

	public int attackByTower(AbstractTower at) {
		int res = 0;
		
		for (Pion p : pions) {
			if (this.getDistance(at, p) <= at.getRange()){
				p.setNbVie(p.getNbVie()- at.getPower());
				if (p.getNbVie() <= 0)
					this.pionsDetruits.add(p);
			}
		}
		// suppression des pions détruits
		this.supprimerPions(this.pionsDetruits);
		
		return res;
	}
	
	public double getDistance(AbstractTower at, Pion p){
		
		double dX = Math.pow((double) (p.getPos().getX() - at.getPos().getX()), 2);
		double dY = Math.pow((double) (p.getPos().getY() - at.getPos().getY()), 2);
		double d = Math.sqrt(dX + dY);
		int distance = (int) Math.ceil(d);
		return distance;
	}

	public int deplacement(Screen ecran) {
		int res = 0;
		//TODO:deplacement des pions
		
		for (Pion p : pions) {
			if (p.estArrive(ecran)){
				pionsArrives.add(p);
			}
			
			p.deplacement(ecran);
			
		}
		res = pionsArrives.size();
		// suppression des pions arrivés
		this.supprimerPions(this.pionsArrives);
		
		
		return res;
	}
	
	public void supprimerPions(ArrayList<Pion> list){
		for (Pion p : list){
			pions.remove(p);
		}
	}
	
	public int getNbPionsArrives(){
		return this.pionsArrives.size();
	}

	/**
	 * @return the pions
	 */
	public ArrayList<Pion> getPions() {
		return pions;
	}

}
