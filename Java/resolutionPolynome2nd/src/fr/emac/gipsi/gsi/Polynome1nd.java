package fr.emac.gipsi.gsi;

public class Polynome1nd {

	// de type a*x+b
	
	private double a;
	private double b;
	public Polynome1nd() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Polynome1nd(double a, double b) {
		super();
		this.a = a;
		this.b = b;
	}
	
	public Racine resoudre(){
		Racine res = new Racine();
		
		if (a == 0){
			if (b==0){
				System.out.println("infinité de solution");
			}
			else {
				System.out.println("aucune");
			}
		}
		else {
			res = new Racine(-b/a);
			System.out.println(res);
		}
		return res;
	}
}
