package fr.pgm.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import gipsi.enstimac.fr.gsi.donneesclient.InfoClient;
import gipsi.enstimac.fr.gsi.donneesclient.ListeDonneesClient;
import gipsi.enstimac.fr.gsi.factures.Article;
import gipsi.enstimac.fr.gsi.factures.Client;
import gipsi.enstimac.fr.gsi.factures.Facture;
import gipsi.enstimac.fr.gsi.factures.ListeFactures;

public class XMLParser {
	
	private HashMap<String, Integer> infosPrix = new HashMap<String, Integer>();
	
	public XMLParser() {
		infosPrix.put("Iphone5G", 100);
		infosPrix.put("IpadMini", 200);
		infosPrix.put("IMac", 2200);
		infosPrix.put("Iphone3G", 50);
		infosPrix.put("Tosh89", 20);
		infosPrix.put("CDRx20", 10);
	}
	
	public ListeDonneesClient lireDonneesClient() throws JAXBException{
		
		String path1 = "/fichier/factures.xml";
		
		JAXBContext context = JAXBContext.newInstance("gipsi.enstimac.fr.gsi.factures");
		Marshaller marshaller = context.createMarshaller();
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		ListeDonneesClient liste  = (ListeDonneesClient)unmarshaller.unmarshal(new File(System.getProperty("user.dir")+ path1));
		
		return liste;
	}
	
	
	
}
