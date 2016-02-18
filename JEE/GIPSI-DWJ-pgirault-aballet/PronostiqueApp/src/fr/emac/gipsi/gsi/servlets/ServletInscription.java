package fr.emac.gipsi.gsi.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.emac.gipsi.gsi.forms.InscriptionForm;
import fr.emac.gipsi.gsi.matchs.Resultats;
import fr.emac.gipsi.gsi.xml.XMLParser;
import pronostic.util.client.GetAllJoueurInput;
import pronostic.util.client.PronostiqueInterface;
import pronostic.util.client.PronostiqueService;

/**
 * Servlet implementation class ServletInscription
 */
@WebServlet("/ServletInscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Resultats resultats;
	private List<String> listeLogin;
	private PronostiqueInterface pronosticService;

	public void init(){
		this.resultats = ((XMLParser) getServletContext().getAttribute("xmlparser")).lire(getServletContext().getRealPath("/"));
		this.pronosticService = new PronostiqueService().getPronostiqueSOAP();
		this.listeLogin = pronosticService.getAllJoueur(new GetAllJoueurInput()).getIdJoueur();
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
		
		System.out.println("Inscription : connexion POST");
		InscriptionForm form = new InscriptionForm(listeLogin, this.pronosticService);
		form.inscrire(request);
		
		if (form.getResultat()){
			response.sendRedirect("login");
		}
		else {
			System.out.println(form.getErreurs().get("login"));
			request.setAttribute("erreurs", form.getErreurs());
			request.getRequestDispatcher("inscription.jsp").forward(request, response);
		}
	}

}
