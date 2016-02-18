package fr.emac.gipsi.gsi.forms;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import fr.emac.gipsi.gsi.matchs.Match;
import pronostic.util.client.AddPronosticInput;
import pronostic.util.client.PronostiqueInterface;

public class PronostiqueForm {
	
	private static final String CHAMP_SCORE1 = "score1";
	private static final String CHAMP_SCORE2 = "score2";
	private boolean resultat;
	private HashMap<String, String> erreurs = new HashMap <String, String>();
	private PronostiqueInterface pronostiqueService;
	
	
	public PronostiqueForm(PronostiqueInterface pronostiqueService) {
		this.pronostiqueService = pronostiqueService;
	}

	public AddPronosticInput envoyerPronostique(HttpServletRequest request, Match match){
		
		AddPronosticInput input = new AddPronosticInput(); 
		
		try {
			input.setScore1(Integer.parseInt(request.getParameter(CHAMP_SCORE1)));
			input.setScore2(Integer.parseInt(request.getParameter(CHAMP_SCORE2)));
		}
		catch (NumberFormatException e){
			setErreur("score", "Les valeurs rentrées ne sont pas valides");
		}
		String login = (String) request.getSession().getAttribute("login");
		input.setIdJoueur(login);
		input.setIdMatch(getIdMatch(match));
		
		if (erreurs.isEmpty()){
			this.resultat = true;
			pronostiqueService.addPronostic(input);
			
			request.setAttribute("erreurs", this.erreurs);
			request.setAttribute("match", match);
			request.setAttribute("input", input);
			request.setAttribute("message", "Pronostique ajouté avec succés.");
		}
		else {
			this.resultat = false;
		}
		return input;
		
	}
	
	public String getIdMatch(Match match){
		return match.getEquipe().get(0).getId().intValue() + "-" + match.getEquipe().get(1).getId().intValue();
	}
	
	public void setErreur(String champ, String message){
		this.erreurs.put(champ, message);
	}
	
	public boolean getResultat() {
		return resultat;
	}
	public HashMap<String, String> getErreurs() {
		return erreurs;
	}
}
