package fr.emac.gipsi.gsi.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.emac.gipsi.gsi.model.Ascenseur;
import fr.emac.gipsi.gsi.model.ManagerAscenseur;

/**
 * Servlet implementation class ServletAscenseur
 */
@WebServlet("/ServletAscenseur")
public class ServletAscenseur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerAscenseur manager;
	
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// nb ascenseur = 5, nb etages = 7
		this.manager = new ManagerAscenseur(5, 7);
		request.setAttribute("manager", manager);
		request.getRequestDispatcher("ascenseur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("post");
		String numero = request.getParameter("numero");
		int nb = Integer.parseInt(numero);
		System.out.println(nb);
		this.manager.faireMonterAscenseur(nb);
		request.setAttribute("manager", this.manager);
		
		request.getRequestDispatcher("ascenseur.jsp").forward(request, response);
	}

}
