package fr.emac.gipsi.gsi.servlets;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.emac.gipsi.gsi.firewall.Firewall;
import fr.emac.gipsi.gsi.forms.PronostiqueForm;
import fr.emac.gipsi.gsi.matchs.Match;
import fr.emac.gipsi.gsi.matchs.Resultats;
import fr.emac.gipsi.gsi.xml.XMLParser;
import pronostic.util.client.AddPronosticInput;
import pronostic.util.client.GetPronosticByJoueurInput;
import pronostic.util.client.GetPronosticByJoueurResponse;
import pronostic.util.client.PronostiqueInterface;
import pronostic.util.client.PronostiqueService;

/**
 * Servlet implementation class ServletPronostic
 */
@WebServlet(name = "ServletPronostique", urlPatterns = { "/ServletPronostique" })
public class ServletPronostique extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Resultats resultats;
	private Firewall firewall;
	private PronostiqueInterface pronostiqueService;
	
	public void init(){
		this.resultats = ((XMLParser) getServletContext().getAttribute("xmlparser")).lire(getServletContext().getRealPath("/"));
		this.firewall = new Firewall();
		this.pronostiqueService = new PronostiqueService().getPronostiqueSOAP();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int e1 = Integer.parseInt(request.getParameter("e1"));
		int e2 = Integer.parseInt(request.getParameter("e2"));
		Match match = this.getMatch(e1, e2);
		
		if (!this.firewall.isAuthenticated(request)){
			firewall.redirect(response);
		}
		else if (match == null) {
			request.setAttribute("message", "La page demandée n'existe pas.");
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}
		else {
			
			GetPronosticByJoueurInput input = new GetPronosticByJoueurInput();
			input.setIdJoueur((String) request.getSession().getAttribute("login"));
			input.setIdMatch(match.getEquipe().get(0).getId().intValue() + "-" + match.getEquipe().get(1).getId().intValue());
			GetPronosticByJoueurResponse pronostique = pronostiqueService.getPronosticByJoueur(input);
			
			if (pronostique.getScoreEq1() != 0 || pronostique.getScoreEq2() != 0){
				request.setAttribute("message", "Vous avez déjà pronostiqué " + pronostique.getScoreEq1() + "-" + pronostique.getScoreEq2() + " à ce match.");
				request.getRequestDispatcher("message.jsp").forward(request, response);
			}
			else {
				request.setAttribute("match", match);
				request.getRequestDispatcher("pronostic.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Pronostic Post");
		int e1 = Integer.parseInt(request.getParameter("e1"));
		int e2 = Integer.parseInt(request.getParameter("e2"));
		Match match = this.getMatch(e1, e2);
		
		if (!this.firewall.isAuthenticated(request)){
			firewall.redirect(response);
		}
		else if (match == null) {
			request.setAttribute("message", "La page demandée n'existe pas.");
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}
		else {
			PronostiqueForm form = new PronostiqueForm(pronostiqueService);
			form.envoyerPronostique(request, match);
			
			if (form.getResultat()){
				System.out.println("form ok");
				request.getRequestDispatcher("message.jsp").forward(request, response);
			}
			else {
				System.out.println("form not ok");
				request.getRequestDispatcher("pronostic.jsp").forward(request, response);
			}
		}
	}
	
	private Match getMatch(int e1, int e2) {
		Match match = null;
		for (Match m : resultats.getMatch()) {
			if (m.getEquipe().get(0).getId().intValue() == e1 && m.getEquipe().get(1).getId().intValue() == e2){
				match = m;
			}
		}
		return match;
	}
	
}
