package fr.emac.gipsi.gsi.forms;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import fr.emac.gipsi.gsi.dao.UtilisateurDaoImpl;
import fr.emac.gipsi.gsi.model.Utilisateur;

public class LoginForm {
	
	private HashMap<String, String> erreurs = new HashMap<String, String>();
	private boolean resultat;
	private UtilisateurDaoImpl utilisateurDao;
	
	public LoginForm(UtilisateurDaoImpl utilisateurDao) {
		this.utilisateurDao = utilisateurDao;
	}
	
	public Utilisateur trouverUtilisateur(HttpServletRequest request){
		
		String pseudo = request.getParameter("utilisateur");
		String mdp = request.getParameter("password");
		String mdpChiffre = this.crypterMdp(mdp);
		resultat = false;
		
		Utilisateur utilisateur = utilisateurDao.trouver(pseudo);
		
		if (utilisateur == null)
			erreurs.put("utilisateur", "utilisateur inconnu");
		else {
			if (utilisateur.getPwd() == mdpChiffre)
				resultat = true;
			else
				erreurs.put("password", "mot de passe incorrect");
		}
		
		
		return utilisateur;
	}
	
	public String crypterMdp(String mdp){
		
		/** cryptage du mot de passe */
		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm("SHA-256");
		passwordEncryptor.setPlainDigest(false);
		String motDePasseChiffre = passwordEncryptor.encryptPassword(mdp);
		
		return motDePasseChiffre;
	}

	public HashMap<String, String> getErreurs() {
		return erreurs;
	}

	public void setErreurs(HashMap<String, String> erreurs) {
		this.erreurs = erreurs;
	}

	public boolean isResultat() {
		return resultat;
	}

	public void setResultat(boolean resultat) {
		this.resultat = resultat;
	}
	
	

}
