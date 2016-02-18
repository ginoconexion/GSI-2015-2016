package fr.emac.gipsi.gsi;

public class Racine {
	
	private double partieReelle;
	private double partieImaginaire;
	public Racine() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Racine(double partieReelle) {
		super();
		this.partieReelle = partieReelle;
	}
	public Racine(double partieReelle, double partieImaginaire) {
		super();
		this.partieReelle = partieReelle;
		this.partieImaginaire = partieImaginaire;
	}
	
	public String toString(){
		return "racine :" + this.partieReelle + " + " + this.partieImaginaire;
	}
	
	public double getPartieReelle(){
		return this.partieReelle;
	}
	
	
	
	

}
