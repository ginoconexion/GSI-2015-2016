package fr.emac.gipsi.gsi.firewall;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class Firewall {
	
	
	public boolean isAuthenticated(HttpServletRequest request){
		String login = this.getLogin(request);
		if (login == null){
			return false;
		}
		return true;
	}
	public void redirect(HttpServletResponse response) throws IOException{
		response.sendRedirect("login");
	}
	public String getLogin(HttpServletRequest request){
		return (String) request.getSession().getAttribute("login");
	}
	
	public boolean isAdmin(HttpServletRequest request){
		String login = this.getLogin(request);
		if (login.equals("admin")){
			System.out.println("admin");
			return true;
		}
		else{
			System.out.println("not admin");
			return false;
		}
	}
}
