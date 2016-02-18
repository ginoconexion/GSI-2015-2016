package fr.emac.gipsi.gsi;

import java.util.ArrayList;

public class Polynome2nd {

	// de type a*x²+bx+c
	
	private double a;
	private double b;
	private double c;
	
	public Polynome2nd() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Polynome2nd(double a, double b, double c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
	}
	private double calculerDelta(){
		return b*b - 4*a*c;
	}
	
	public ArrayList<Racine> resoudre(){
			
		ArrayList<Racine> res = new ArrayList<Racine>();
		
		
		if (a != 0){
			double delta = this.calculerDelta();
			
			if (delta > 0){
				double r1 = -b + Math.sqrt(delta)/(2*a);
				Racine rr1 = new Racine(r1);
				double r2 = -b - Math.sqrt(delta)/(2*a);
				Racine rr2 = new Racine(r2);
				res.add(rr1);
				res.add(rr2);
			}
			else if (delta == 0) {
				double r = -b/(2*a);
				Racine rr = new Racine(r);
				res.add(rr);
				System.out.println("racine double : " + r);
			}
			else {
				double rr = -b/(2*a);
				double im = +Math.round(-delta)/(2*a);
				
				Racine r1 = new Racine(rr, im);
				Racine r2 = new Racine(rr, -im);
				res.add(r1);
				res.add(r2);
			}
			
		}
		else {
			Polynome1nd polynome1nd = new Polynome1nd(this.b, this.c);
			polynome1nd.resoudre();
		}
		
		return res;
		
	}
	
}
