package fr.emac.gipsi.gsi.xml;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import fr.emac.gipsi.gsi.matchs.Match;
import fr.emac.gipsi.gsi.matchs.Resultats;

public class XMLParser {
	
	private static final String FICHIER_XML = "WEB-INF/xml/Matchs_recap.xml";
	private Resultats resultats;
	
	
	
	public XMLParser() {
		super();
	}
	
	public Resultats lire(String path){
		JAXBContext context;
		
		try {
			context = JAXBContext.newInstance("fr.emac.gipsi.gsi.matchs");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			this.resultats = (Resultats) unmarshaller.unmarshal(new File(path + FICHIER_XML)); 
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return this.resultats;
	}
	
	public List<Match> getListeMatch(){
		return resultats.getMatch();
	}
	
	public Match getMatch(int e1, int e2) {
		Match match = null;
		for (Match m : resultats.getMatch()) {
			if (m.getEquipe().get(0).getId().intValue() == e1 && m.getEquipe().get(1).getId().intValue() == e2){
				match = m;
			}
		}
		return match;
	}
	
	public String getIdMatch(Match match){
		return match.getEquipe().get(0).getId().intValue() + "-" + match.getEquipe().get(1).getId().intValue();
	}

}
