package fr.emac.gipsi.gsi.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.emac.gipsi.gsi.dao.DAOFactory;
import fr.emac.gipsi.gsi.dao.UtilisateurDaoImpl;
import fr.emac.gipsi.gsi.forms.InscriptionForm;
import fr.emac.gipsi.gsi.model.Utilisateur;

/**
 * Servlet implementation class ServletInscription
 */
@WebServlet("/ServletInscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UtilisateurDaoImpl utilisateurDao;
    
    
    public void init(){
    	this.utilisateurDao = (UtilisateurDaoImpl) ((DAOFactory) getServletContext().getAttribute("daofactory")).getUtilisateurDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Inscription : connexion GET");
		
		request.getRequestDispatcher("inscription.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Inscription : envoi du formulaire");
		
		InscriptionForm form = new InscriptionForm(utilisateurDao);
		
		Utilisateur utilisateur = form.inscrireUtilisateur(request);
		request.setAttribute("form", form);
		request.setAttribute("utilisateur", utilisateur);
		System.out.println(form.getErreurs());
		
		
		request.getRequestDispatcher("inscription.jsp").forward(request, response);
	}

}
