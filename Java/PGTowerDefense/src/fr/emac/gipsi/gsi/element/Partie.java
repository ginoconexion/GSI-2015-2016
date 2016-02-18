/**
 * 
 */
package fr.emac.gipsi.gsi.element;

import java.util.ArrayList;
import java.util.Iterator;

import fr.emac.gipsi.gsi.gui.InterfaceSimulation;
import fr.emac.gipsi.gsi.gui.ListScreen;
import fr.emac.gipsi.gsi.gui.Screen;
import fr.emac.gipsi.gsi.gui.couleurs.EchantillonCouleur;

/**
 * @author truptil
 *
 */
public class Partie {

	private InterfaceSimulation visualisationAnimation;
	private Screen ecran;
	private int nbVie;
	private int Argent;
	private double coeff;
	private Screen chemin = ListScreen.chemin();
	private Vague vag;
	private int nbVag;
	private ArrayList<AbstractTower> listTower;
	private AbstractTower towerEnCours;

	/**
	 * 
	 */
	public Partie() {
		nbVie=20;
		Argent=50;
		coeff=1.25;
		nbVag=1;
		ecran = ListScreen.chemin();
		listTower = new ArrayList<AbstractTower>();
		visualisationAnimation = new InterfaceSimulation(this);
	}

	public void launchPartie(){

		afficheEcran();
		System.out.println(ecran);
		
		while (nbVie > 0){ 
			vag = new Vague(nbVag);
			System.out.println("Les pions ont maintenant : "+vag.getPions().get(0).getNbVie() + " pts de vie.");
			while (!vag.isFinish()) {
				
				afficheEcran();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.gestionVague();
			}
			nbVag++;
			nbVie = nbVie - vag.getNbPionsArrives();
			System.out.println("Vague  "+ nbVag + " finie ! Vous avez  " + nbVie + " pts de vie.");
			Argent += 5;
			afficheEcran();
		}
	}
	
	
	public void gestionVague(){
		vag.deplacement(ecran);
		
		for (AbstractTower at : listTower) {
			vag.attackByTower(at);
		}
	}
	
	
	
	public void afficheEcran(){
		
		this.ecran = ListScreen.chemin();
		// s'il y a une vague
		if(vag != null){
			// on parcourt les pions et on les affiche sur l'écran
			for(Pion p : vag.getPions()){
			ecran.setColorByXY(p.getPos().getX(), p.getPos().getY(), "Aqua");
			}}
			
			// on parcourt les tours et on les affiche sur l'écran
			for(AbstractTower at : listTower ){
				ecran.setColorByXY(at.getPosition().getX(), at.getPosition().getY(), at.getColorName());
			}
		
		
		if(!visualisationAnimation.isVisible()){
			visualisationAnimation.setVisible(true);
		}
		visualisationAnimation.changeEcran();
		
	}



	public AbstractTower getInfoTower(int lig, int col) {
		for(AbstractTower at : listTower){
			if(at.getPosition().is(lig,col)){
				return at;
			}
		}
		return null;
	}

	public void addTower(String typeEnCours, int lig, int col) {
		for(TypeTower tt : TypeTower.values()){
			if (tt.getName().equals(typeEnCours)){
				try {
					AbstractTower at = tt.getType().newInstance();
					at.setPos(new Position(lig, col));
					
					if (this.Argent > at.getCostUpgrade()){
						listTower.add(at);
						this.Argent -= at.getCostUpgrade();
					}
					
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void upgradeTower(AbstractTower at){
		if (Argent > at.getCostUpgrade()){
			at.upgrade();
			Argent -= at.getCostUpgrade();
			System.out.println("Tour améliorée, power:" + at.getPower());
		}
		else 
			System.out.println("Vous n'avez pas assez d'argent");
	}
	/**
	 * @return the ecran
	 */
	public Screen getEcran() {
		return ecran;
	}

	/**
	 * @return the nbVie
	 */
	public int getNbVie() {
		return nbVie;
	}

	/**
	 * @return the argent
	 */
	public int getArgent() {
		return Argent;
	}

	/**
	 * @return the nbVag
	 */
	public int getNbVag() {
		return nbVag;
	}

	/**
	 * @return the listTower
	 */
	public ArrayList<AbstractTower> getListTower() {
		return listTower;
	}

	/**
	 * @return the towerEnCours
	 */
	public AbstractTower getTowerEnCours() {
		return towerEnCours;
	}

	/**
	 * @param at the towerEnCours to set
	 */
	public void setTowerEnCours(AbstractTower at) {
		this.towerEnCours = at;
	}
	
}
