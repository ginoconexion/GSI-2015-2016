package fr.emac.gipsi.gsi.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.emac.gipsi.gsi.model.Message;
import fr.emac.gipsi.gsi.model.Utilisateur;

/**
 * Servlet implementation class ServletMessage
 */
@WebServlet("/ServletMessage")
public class ServletMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static ArrayList<Message> listMessages;
       
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession().getAttribute("utilisateur") == null){
			System.out.println("Utilisateur non identifié");
			response.sendRedirect("login");
		}
		else {
			System.out.println("Chat : connexion GET");
			request.getSession().setAttribute("messages", listMessages);
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Envoi d'un nouveau Message");
		
		String text = request.getParameter("message");
		Utilisateur u = (Utilisateur) request.getSession().getAttribute("utilisateur");
		Message message = new Message("id-"+System.currentTimeMillis(), u, u.getGroupe(), text);
		getListMessages().add(message);
		request.getSession().setAttribute("messages", listMessages);
		request.getRequestDispatcher("message.jsp").forward(request, response);
	}
	
	public static ArrayList<Message> getListMessages() {
		if (listMessages == null)
			listMessages = new ArrayList<Message>();
		return listMessages;
	}

}
