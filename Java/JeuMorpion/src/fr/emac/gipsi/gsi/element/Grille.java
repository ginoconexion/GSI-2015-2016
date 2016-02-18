package fr.emac.gipsi.gsi.element;

public class Grille {
	
	private Case[][] cases;

	public Grille() {
		super();
		cases = new Case[3][3];
	}
	
	public boolean isVictory(){
		
		// symboles alignés sur une ligne
		for (int lig = 0; lig < 3; lig++) {
			if (!cases[lig][0].getSymbole().equals("")){
				if (cases[lig][0].getSymbole().equals(cases[lig][1].getSymbole()) && cases[lig][0].getSymbole().equals(cases[lig][2].getSymbole())){
					return true;
				}
			}
		}
		
		// symboles alignés sur une colonne
		for (int col = 0; col < 3; col++) {
			if (!cases[0][col].getSymbole().equals("")){
				if (cases[0][col].getSymbole().equals(cases[1][col].getSymbole()) && cases[0][col].getSymbole().equals(cases[2][col].getSymbole())){
					return true;
				}
			}
		}
		// symboles alignés en diagonale haut-gauche bas droit
		if (!cases[0][0].getSymbole().equals("")){
			if (cases[0][0].getSymbole().equals(cases[1][1].getSymbole()) && cases[0][0].getSymbole().equals(cases[2][2].getSymbole())){
				return true;
			}
		}
		if (!cases[2][0].getSymbole().equals("")){
			if (cases[2][0].getSymbole().equals(cases[1][1].getSymbole()) && cases[1][1].getSymbole().equals(cases[0][2].getSymbole())){
				return true;
			}
		}
		return false;
	}
	
	public void setSymbole(int lig, int col, String symbole){
		cases[lig-1][col-1].setSymbole(symbole);
	}
	// on suppose que lig et col appartiennet à l'ensemble {1,2,3}
	public String getSymbole(int lig, int col){
		return cases[lig-1][col-1].getSymbole();
	}
	
	public void initialiser(){
		for (int lig = 0; lig < 3; lig++) {
			for (int col = 0; col < 3; col++) {
				cases[lig][col] = new Case(lig, col);
				
			}
		}
	}
	

}
