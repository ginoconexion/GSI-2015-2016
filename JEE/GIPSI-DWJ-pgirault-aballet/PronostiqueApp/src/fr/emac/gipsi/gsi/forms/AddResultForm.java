package fr.emac.gipsi.gsi.forms;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import fr.emac.gipsi.gsi.matchs.Match;
import fr.emac.gipsi.gsi.xml.XMLParser;
import pronostic.util.client.AddPronosticInput;
import pronostic.util.client.AddResultatReelInput;
import pronostic.util.client.PronostiqueInterface;

public class AddResultForm {
	
	private static final String CHAMP_SCORE1 = "e1";
	private static final String CHAMP_SCORE2 = "e2";
	private boolean resultat;
	private HashMap<String, String> erreurs = new HashMap <String, String>();
	private PronostiqueInterface pronostiqueService;
	private XMLParser xmlparser;
	
	public AddResultForm(PronostiqueInterface pronostiqueService, XMLParser xmlparser) {
		this.pronostiqueService = pronostiqueService;
		this.xmlparser = xmlparser;
	}
	
public void addResultatReel(HttpServletRequest request){
		
	
		
		int e1 = 0;
		int e2 = 0;
		
		try {
			e1 = Integer.parseInt(request.getParameter(CHAMP_SCORE1));
			e2 = Integer.parseInt(request.getParameter(CHAMP_SCORE2));
		}
		catch (NumberFormatException e){
			setErreur("addResult", "Les valeurs rentrées ne sont pas valides");
		}
		Match match = xmlparser.getMatch(e1, e2);
		
		if (match != null){
			AddResultatReelInput input = new AddResultatReelInput();
			input.setIdMatch(xmlparser.getIdMatch(match));
			input.setScore1(match.getEquipe().get(0).getScore().intValue());
			input.setScore2(match.getEquipe().get(1).getScore().intValue());
			pronostiqueService.addResultatReel(input);
		}

		if (erreurs.isEmpty()){
			this.resultat = true;
			request.setAttribute("message", "Résultat réel ajouté avec succés.");
		}
		else {
			this.resultat = false;
			request.setAttribute("erreurs", this.erreurs);
		}
		
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
