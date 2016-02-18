package fr.emac.gipsi.gsi.forms;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import fr.emac.gipsi.gsi.dao.UtilisateurDaoImpl;
import fr.emac.gipsi.gsi.model.Utilisateur;

public class InscriptionForm {
	
	private static final String CHAMP_PSEUDO = "pseudo";
	private static final String CHAMP_MDP1 = "mdp1";
	private static final String CHAMP_MDP2 = "mdp2";
	
	private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
	
	private Pattern pattern;
	
	private boolean resultat;
	private HashMap<String, String> erreurs = new HashMap <String, String>();
	private UtilisateurDaoImpl utilisateurDao;
	
	
	public InscriptionForm(UtilisateurDaoImpl utilisateurDao) {
		this.utilisateurDao = utilisateurDao;
	}

	public Utilisateur inscrireUtilisateur(HttpServletRequest request) {
		
		String pseudo = request.getParameter(CHAMP_PSEUDO);
		String mdp1 = request.getParameter(CHAMP_MDP1);
		String mdp2 = request.getParameter(CHAMP_MDP2);
		
		Utilisateur utilisateur = new Utilisateur();
		
		try {
			this.validationPseudo(pseudo);
		} catch (Exception e) {
			setErreur(CHAMP_PSEUDO, e.getMessage());
		}
		utilisateur.setLogin(pseudo);
		
		try {
			this.validationMotsDePasse(mdp1, mdp2);
		} catch (Exception e) {
			setErreur(CHAMP_MDP1, e.getMessage());
		}
		
		/** cryptage du mot de passe */
		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm("SHA-256");
		passwordEncryptor.setPlainDigest(false);
		String motDePasseChiffre = passwordEncryptor.encryptPassword(mdp1);
		
		utilisateur.setPwd(motDePasseChiffre);
		
		if (this.erreurs.isEmpty()) {
			resultat = true;
			utilisateurDao.creer(utilisateur);
		}
		else 
			resultat = false;
		
		return utilisateur;
		
	}
	
	public void validationPseudo(String pseudo) throws Exception{
		if (pseudo != null){
			if (pseudo.length() < 2)
				throw new Exception("Pseudo trop court");
			else if (utilisateurDao.trouver(pseudo) != null) {
				throw new Exception("Ce pseudo est déja utilisé");
			}
		}
		else 
			throw new Exception("Veuillez entrer un pseudo");
	}
	
	public void validationEmail(String email) throws Exception {
		if (email != null){
			Pattern pattern = Pattern.compile(EMAIL_PATTERN);
			Matcher matcher =  pattern.matcher(email);
			if (!matcher.matches())
				throw new Exception("Adresse email invalide");
		}
		else
			throw new Exception("Merci de saisir une adresse email");
	}
	
	public void validationMotsDePasse(String mdp1, String mdp2) throws Exception {
		if (mdp1 != null && mdp2 != null){
			if (!mdp1.equals(mdp2))
				throw new Exception("Les mots de passes entrés sont différent");
			else if (mdp1.length() < 2)
				throw new Exception("Mot de passe trop court");
		}
		else
			throw new Exception("Veuillez entrer des mots de passes.");
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
