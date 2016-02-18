package fr.emac.gipsi.gsi.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import fr.emac.gipsi.gsi.xml.XMLParser;

public class InitialisationXML implements ServletContextListener {
	
	private XMLParser xmlParser;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		
		ServletContext servletContext = event.getServletContext();
		this.xmlParser = new XMLParser();
		servletContext.setAttribute("xmlparser", this.xmlParser);
	}

}
