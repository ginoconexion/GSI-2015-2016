package fr.emac.gipsi.gsi;

public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Polynome2nd p1 = new Polynome2nd(0,0,0);
		Polynome2nd p2 = new Polynome2nd(0,2,2);
		Polynome2nd p3 = new Polynome2nd(1,4,1);
		
		System.out.println(p1.resoudre());
		System.out.println(p2.resoudre());
		System.out.println(p3.resoudre());
	}

}
