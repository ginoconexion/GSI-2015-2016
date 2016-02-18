package fr.emac.gipsi.gsi.forms;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import pronostic.util.client.AddJoueurInput;
import pronostic.util.client.PronostiqueInterface;

public class InscriptionForm {
	
	private static final String CHAMP_LOGIN = "login";
	private boolean resultat;
	private HashMap<String, String> erreurs = new HashMap <String, String>();
	private List<String> listeLogin;
	private PronostiqueInterface pronosticService;
	
	
	public InscriptionForm(List<String> listeLogin, PronostiqueInterface pronosticService) {
		super();
		this.listeLogin = listeLogin;
		this.pronosticService = pronosticService;
	}

	public void inscrire(HttpServletRequest request){
		String login = request.getParameter(CHAMP_LOGIN);
		System.out.println(login);
		AddJoueurInput input = new AddJoueurInput();
		input.setIdJoueur(login);
		
		try {
			validerLogin(login);
		} catch (Exception e) {
			setErreur(CHAMP_LOGIN, e.getMessage());
			request.setAttribute("input", input);
		}
		if (this.getErreurs().isEmpty()){
			resultat = true;
			pronosticService.addJoueur(input);
		}
		else {
			resultat = false;
		}
	}
	
	public void validerLogin(String login) throws Exception{
		if (login == ""){
			throw new Exception("Le login choisi est vide");
		}
		else if (login.length() <= 2){
			System.out.println("login trop court");
			throw new Exception("Le login choisi est trop court.");
		}
		else if (listeLogin.contains(login)) {
			System.out.println("login déjà utilisé");
			throw new Exception("Le login est déjà utilisé.");
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
