package fr.emac.gipsi.gsi.servlets;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.emac.gipsi.gsi.firewall.Firewall;
import fr.emac.gipsi.gsi.forms.AddResultForm;
import fr.emac.gipsi.gsi.matchs.Match;
import fr.emac.gipsi.gsi.matchs.Resultats;
import fr.emac.gipsi.gsi.xml.XMLParser;
import pronostic.util.client.GetAllJoueurInput;
import pronostic.util.client.GetPointJoueurInput;
import pronostic.util.client.PronostiqueInterface;
import pronostic.util.client.PronostiqueService;

/**
 * Servlet implementation class ServletMatchs
 */
@WebServlet("/ServletMatchs")
public class ServletMatchs extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Resultats resultats;
	private Firewall firewall;
	private XMLParser xmlParser;
	private PronostiqueInterface pronostiqueService;
	
	public void init(){
		this.xmlParser = (XMLParser) getServletContext().getAttribute("xmlparser");
		this.resultats = xmlParser.lire(getServletContext().getRealPath("/"));
		this.pronostiqueService = new PronostiqueService().getPronostiqueSOAP();
		this.firewall = new Firewall();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isAdmin = firewall.isAdmin(request);
		if (!this.firewall.isAuthenticated(request)){
			firewall.redirect(response);
		}
		else {
			Map<String, Integer> classement = this.getClassement();
			List<Match> matchs = this.resultats.getMatch();
			request.setAttribute("classement", classement);
			request.setAttribute("matchs", matchs);
			request.setAttribute("isAdmin", isAdmin);
			request.getRequestDispatcher("liste.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isAdmin = firewall.isAdmin(request);
		if (!this.firewall.isAuthenticated(request)){
			firewall.redirect(response);
		}
		else if (request.getParameter("addResult") != null) {
			/** ajout du résultat réel */
			
			AddResultForm form = new AddResultForm(pronostiqueService, xmlParser);
			form.addResultatReel(request);
			
			if (form.getResultat()){
				System.out.println("form ok");
				request.getRequestDispatcher("message.jsp").forward(request, response);
			}
			else {
				System.out.println("form not ok");
				request.getRequestDispatcher("liste.jsp").forward(request, response);
			}
		}
		else {
			List<Match> matchs = this.resultats.getMatch();
			request.setAttribute("matchs", matchs);
			request.getRequestDispatcher("liste.jsp").forward(request, response);
		}
	}
	
	private Map<String, Integer> getClassement(){
		GetAllJoueurInput input = new GetAllJoueurInput();
		List<String> listeLogin = pronostiqueService.getAllJoueur(new GetAllJoueurInput()).getIdJoueur();
		HashMap<String, Integer> classement = new HashMap<String, Integer>();
		GetPointJoueurInput inputPointJoueur = new GetPointJoueurInput();
		for (String login : listeLogin) {
			inputPointJoueur.setIdJoueur(login);
			Integer nbPts = pronostiqueService.getPointJoueur(inputPointJoueur).getNbPoints();
			classement.put(login, nbPts);
			System.out.println(login + " - " + nbPts);
		}
		Map<String, Integer> map = sortByValues(classement); 
		return map;
	}
	
	
	private static HashMap sortByValues(HashMap map) { 
	       List list = new LinkedList(map.entrySet());
	       // Defined Custom Comparator here
	       Collections.sort(list, new Comparator() {
	            public int compare(Object o1, Object o2) {
	               return ((Comparable) ((Map.Entry) (o2)).getValue())
	                  .compareTo(((Map.Entry) (o1)).getValue());
	            }
	       });

	       // Here I am copying the sorted list in HashMap
	       // using LinkedHashMap to preserve the insertion order
	       HashMap sortedHashMap = new LinkedHashMap();
	       for (Iterator it = list.iterator(); it.hasNext();) {
	              Map.Entry entry = (Map.Entry) it.next();
	              sortedHashMap.put(entry.getKey(), entry.getValue());
	       } 
	       return sortedHashMap;
	  }

}
