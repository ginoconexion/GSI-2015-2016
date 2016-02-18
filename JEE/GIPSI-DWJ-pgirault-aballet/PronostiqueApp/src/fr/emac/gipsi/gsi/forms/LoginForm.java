package fr.emac.gipsi.gsi.forms;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

public class LoginForm {
	
	private HashMap<String, String> erreurs = new HashMap<String, String>();
	private boolean resultat;
	private static final String CHAMP_LOGIN = "login";
	
	public void login(HttpServletRequest request){
		String login = request.getParameter(CHAMP_LOGIN);
		
		if (login != ""){
			request.getSession().setAttribute("login", login);
			resultat = true;
		}
		else{
			erreurs.put("login", "Le login est vide.");
			resultat = false;
		}
	}

	public HashMap<String, String> getErreurs() {
		return erreurs;
	}

	public void setErreurs(HashMap<String, String> erreurs) {
		this.erreurs = erreurs;
	}

	public boolean getResultat() {
		return resultat;
	}

	public void setResultat(boolean resultat) {
		this.resultat = resultat;
	}
	
	

}
