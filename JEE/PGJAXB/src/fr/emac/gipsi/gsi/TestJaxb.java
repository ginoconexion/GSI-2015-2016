/**
 * 
 */
package fr.emac.gipsi.gsi;

import gipsi.enstimac.fr.gsi.donneesclient.InfoClient;
import gipsi.enstimac.fr.gsi.donneesclient.ListeDonneesClient;
import gipsi.enstimac.fr.gsi.factures.Article;
import gipsi.enstimac.fr.gsi.factures.Client;
import gipsi.enstimac.fr.gsi.factures.Facture;
import gipsi.enstimac.fr.gsi.factures.ListeFactures;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.Test;

/**
 * @author truptil
 *
 */
public class TestJaxb {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws JAXBException {
		
		Client client1 = new Client();
		client1.setId("client1");
		
		Article at1 = new Article();
		at1.setIdProduit("Tosh89");
		at1.setQuantite(8);
		
		Article at2 = new Article();
		at2.setIdProduit("Iphone5G");
		at2.setQuantite(5);
		
		Article at3 = new Article();
		at3.setIdProduit("IpadMini");
		at3.setQuantite(2);
		
		Facture f1 = new Facture();
		f1.setClient(client1);
		f1.setId("facture1");
		
		f1.getArticle().add(at1);
		f1.getArticle().add(at2);
		f1.getArticle().add(at3);
		
		
		ListeFactures lf = new ListeFactures();
		lf.getFacture().add(f1);
		
		//context
		
		JAXBContext context = JAXBContext.newInstance("gipsi.enstimac.fr.gsi.factures");
		Marshaller marshaller = context.createMarshaller();
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		
		marshaller.marshal(lf, new File(System.getProperty("user.dir")+"/save.xml"));
		
		
		ListeFactures lf2  = (ListeFactures)unmarshaller.unmarshal(new File(System.getProperty("user.dir")+"/save.xml"));
		
		System.out.println("nombre de factures = " + lf.getFacture().size() + 
				" premier = " + lf.getFacture().get(0).getClient().getId());
		
		ArrayList<Facture> liste = (ArrayList<Facture>) lf2.getFacture();
		HashMap<String, Integer> infosClient = new HashMap<String, Integer>();
		HashMap<String, Integer> infosPrix = new HashMap<String, Integer>();
		
		infosPrix.put("Iphone5G", 100);
		infosPrix.put("IpadMini", 200);
		infosPrix.put("IMac", 2200);
		infosPrix.put("Iphone3G", 50);
		infosPrix.put("Tosh89", 20);
		infosPrix.put("CDRx20", 10);
		
		
		/** on remplit tous les clients */
		/*
		for (Facture facture : liste) {
			Client client = facture.getClient();
			if (!infosClient.containsKey(client.getId())) {
				infosClient.put(client.getId(), 0);
			}
		}
		*/
		
		for (Facture facture : liste) {
			
			List<Article> listeArticle = facture.getArticle();
			Client client = facture.getClient();
			
			if (!infosClient.containsKey(client.getId())) {
				infosClient.put(client.getId(), 0);
			}
			
			int montant = infosClient.get(client.getId());
			
			for (Article article : listeArticle) {
				montant += infosPrix.get(article.getIdProduit())* article.getQuantite();
			}
			infosClient.put(client.getId(), montant);
		}
		
		ListeDonneesClient listeDonneesClient = new ListeDonneesClient();
		ArrayList<InfoClient> listeInfoClient = new ArrayList<InfoClient>();
		
		for (String key : infosClient.keySet()) {
		    InfoClient infoClient = new InfoClient();
		    infoClient.setId(key);
		    infoClient.setMontant(infosClient.get(key));
		    listeInfoClient.add(infoClient);
		}
		listeDonneesClient.setInfoClient(listeInfoClient);
		System.out.println(listeInfoClient.get(0).getId());
		System.out.println(listeInfoClient.get(0).getMontant());
		
		
		JAXBContext context1 = JAXBContext.newInstance("gipsi.enstimac.fr.gsi.donneesclient");
		Marshaller marshaller1 = context1.createMarshaller();
		Unmarshaller unmarshaller1 = context1.createUnmarshaller();
		
		
		marshaller1.marshal(listeDonneesClient, new File(System.getProperty("user.dir")+"/save1.xml"));
	}
}
