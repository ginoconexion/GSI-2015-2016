/**
 * 
 */
package fr.emac.gipsi.gsi.truptil;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import fr.emac.gipsi.gsi.truptil.ui.MatchResultatUI;

/**
 * @author truptil
 *
 */
public class ListofServlets {

	    @WebServlet(urlPatterns = "/*", name = "MatchResultatServlet", asyncSupported = true)
	    @VaadinServletConfiguration(ui = MatchResultatUI.class, productionMode = false)
	    public static class MatchResultatServlet extends VaadinServlet {
	    }
}
