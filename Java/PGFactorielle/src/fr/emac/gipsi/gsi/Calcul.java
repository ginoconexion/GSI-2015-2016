/**
 * 
 */
package fr.emac.gipsi.gsi;

/**
 * @author Paul
 *
 */
public class Calcul {
	
	public int calculFactorielle(int n){
		int resultat = 1;
		if (n>1) {
			for (int i = 2; i <= n; i++) {
				resultat = resultat*i;
			}
			return resultat;
		}
		else {
			if (n == 1 || n ==0)
				return 1;
			else{
				System.out.println("Impossible");
				return 0;
			}
				
		}
	}
	
	public int calculerCoefficientBinomial(int n, int k){
		if (k>-1 && k>=0 && k < n){
			return this.calculFactorielle(n)/(this.calculFactorielle(n)*this.calculFactorielle(n-k));
		}
		else {
			System.out.println("impossible");
			return 0;
		}
	}

}
