package fr.emac.gipsi.gsi.model;

import java.util.ArrayList;
import java.util.Random;

public class ManagerAscenseur {
	
	private ArrayList<Ascenseur> listeAscenseur;
	private int nbAscenseurs;
	private int nbEtages;

	public ManagerAscenseur(int nbAscenseurs, int nbEtages) {
		
		ArrayList<Ascenseur> listeAscenseur = new ArrayList<Ascenseur>();
		this.nbAscenseurs = nbAscenseurs;
		this.nbEtages = nbEtages;
		
		// on crée n ascenseur avec Y = 0 
		for (int i = 1; i <= nbAscenseurs; i++) {
			Ascenseur ascenseur = new Ascenseur( i, 1);
			listeAscenseur.add(ascenseur);
		}
		this.listeAscenseur = listeAscenseur;
		
	}
	
	public void faireMonterAscenseur(int etage){
		Random rd = new Random();
		int numero = rd.nextInt(nbAscenseurs);
		this.listeAscenseur.get(numero).setPosY(etage);
	}

	public ArrayList<Ascenseur> getListeAscenseur() {
		return listeAscenseur;
	}

	public int getNbEtages() {
		return nbEtages;
	}

	public int getNbAscenseurs() {
		return nbAscenseurs;
	}
	
	
	
	
}
