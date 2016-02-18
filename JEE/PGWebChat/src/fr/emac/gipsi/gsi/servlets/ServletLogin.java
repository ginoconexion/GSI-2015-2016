package fr.emac.gipsi.gsi.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.emac.gipsi.gsi.dao.DAOFactory;
import fr.emac.gipsi.gsi.dao.UtilisateurDaoImpl;
import fr.emac.gipsi.gsi.forms.LoginForm;
import fr.emac.gipsi.gsi.model.Utilisateur;

/**
 * Servlet implementation class ServletChat
 */
@WebServlet("/ServletChat")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UtilisateurDaoImpl utilisateurDao;
	
	public void init(){
	   this.utilisateurDao = (UtilisateurDaoImpl) ((DAOFactory) getServletContext().getAttribute("daofactory")).getUtilisateurDao();
	}
	

	//private static ArrayList<Utilisateur> listUser;
	
    
	/*
    public static ArrayList<Utilisateur> getListUser() {
    	
    	if (listUser == null){
    		
    		listUser = new ArrayList<Utilisateur>();
    		
    		Groupe groupe = new Groupe("groupe1");
    		Utilisateur toto = new Utilisateur("toto", "test", groupe);
    		Utilisateur titi = new Utilisateur("titi", "test", groupe);
    		
    		listUser.add(toto);
    		listUser.add(titi);
    	}
		return listUser;
	}
	*/

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("login : connexion GET");
		
		 request.getRequestDispatcher("login.jsp").forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("login : envoi du formulaire");
		
		LoginForm form = new LoginForm(utilisateurDao);
		Utilisateur utilisateur = form.trouverUtilisateur(request);
		
		request.setAttribute("form", form);
		request.setAttribute("utilisateur", utilisateur);
		
		if (form.isResultat())
			response.sendRedirect("messages");
		else
			request.getRequestDispatcher("login.jsp").forward( request, response );
			
		/*
	    listUser = this.getListUser();
	    boolean acces = false;
	    
	    for (Utilisateur u : listUser) {
			if (u.getLogin().equals(login) && u.getPwd().equals(mdp)) {
				message = "Identification réussie";
				acces = true;
			}
		}
	    
	    if (acces) {
	    	System.out.println("Accés réussi");
	    	Utilisateur utilisateur = new Utilisateur(login, mdp, null);
	    	request.getSession().setAttribute("utilisateur", utilisateur);
	    	response.sendRedirect("messages");
	    } 
	    else {
	    	message = "Echec d'Authentification";
	    	request.setAttribute("message", message);
		    request.getRequestDispatcher("login.jsp").forward( request, response );
	    }
	    */
		
	}
}
