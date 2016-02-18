package fr.emac.gipsi.gsi.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.emac.gipsi.gsi.forms.LoginForm;
import fr.emac.gipsi.gsi.matchs.Match;
import fr.emac.gipsi.gsi.matchs.Resultats;
import fr.emac.gipsi.gsi.xml.XMLParser;
import pronostic.util.client.GetAllJoueurInput;
import pronostic.util.client.PronostiqueInterface;
import pronostic.util.client.PronostiqueService;

/**
 * Servlet implementation class ServletChat
 */
@WebServlet("/ServletChat")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PronostiqueInterface pronostiqueService;
	private Resultats resultats;
	private List<String> listeLogin;

	public void init(){
		this.resultats = ((XMLParser) getServletContext().getAttribute("xmlparser")).lire(getServletContext().getRealPath("/"));
		this.pronostiqueService = new PronostiqueService().getPronostiqueSOAP();
		
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("login : connexion GET");
		this.listeLogin = pronostiqueService.getAllJoueur(new GetAllJoueurInput()).getIdJoueur();
		List<Match> liste = this.resultats.getMatch();
		request.setAttribute("matchs", liste);
		request.setAttribute("listeLogin", listeLogin);
		request.getRequestDispatcher("login.jsp").forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("login : connexion POST");
		String login = request.getParameter("login");
		LoginForm form = new LoginForm();
		form.login(request);
		
		if (form.getResultat()){
			response.sendRedirect("matchs");
		}
		else {
			request.setAttribute("erreurs", form.getErreurs());
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}
