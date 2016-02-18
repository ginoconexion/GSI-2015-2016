/**
 * 
 */
package fr.emac.gipsi.gsi.truptil.util;

import java.io.File;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.vaadin.server.VaadinService;

import fr.emac.gipsi.gsi.truptil.db.DBManager;
import fr.emac.gipsi.gsi.truptil.element.Joueur;
import fr.emac.gipsi.gsi.truptil.element.LigneClassement;
import fr.emac.gipsi.gsi.truptil.element.Pronostic;
import fr.emac.gipsi.gsi.truptil.element.ResultatMatch;
import fr.emac.gipsi.gsi.truptil.equipe.Equipe;
import fr.emac.gipsi.gsi.truptil.equipe.ListeEquipe;
import fr.emac.gipsi.gsi.truptil.extract.Competition;
import fr.emac.gipsi.gsi.truptil.extract.Match;

/**
 * @author truptil
 *
 */
public class GestionMatch {

	/**
	 * 
	 */
	public GestionMatch() {
		// TODO Auto-generated constructor stub
	}


	public static ArrayList<LigneClassement> updateClassement(int numPoule) throws UnknownHostException, JAXBException{

		ArrayList<LigneClassement> res = new ArrayList<LigneClassement>();
		HashMap<String, LigneClassement> hl = new HashMap<String, LigneClassement>();

		Competition comp = DBManager.getInstance().getDatastore().find(Competition.class).asList().get(0);

		for(Match m : comp.getGroupe().get(numPoule).getMatch()){
			Equipe eq1 = findEquipe( m.getEquipe1());
			Equipe eq2 = findEquipe( m.getEquipe2());
			ResultatMatch rm = findMatch(m.getId());
			if(!hl.containsKey(eq1.getId())){
				hl.put(eq1.getId(), new LigneClassement(numPoule, eq1.getId()));
			}
			if(!hl.containsKey(eq2.getId())){
				hl.put(eq2.getId(), new LigneClassement(numPoule, eq2.getId()));
			}


			hl.get(eq1.getId()).setButPour(hl.get(eq1.getId()).getButPour()+rm.getScore1());
			hl.get(eq1.getId()).setButContre(hl.get(eq1.getId()).getButContre()+rm.getScore2());
			hl.get(eq2.getId()).setButPour(hl.get(eq2.getId()).getButPour()+rm.getScore2());
			hl.get(eq2.getId()).setButContre(hl.get(eq2.getId()).getButContre()+rm.getScore1());
			
			if(rm.getScore1()>rm.getScore2()){
				hl.get(eq1.getId()).setVictoire(hl.get(eq1.getId()).getVictoire()+1);
				hl.get(eq2.getId()).setDefaite(hl.get(eq2.getId()).getDefaite()+1);
			}else if(rm.getScore2()>rm.getScore1()){
				hl.get(eq2.getId()).setVictoire(hl.get(eq2.getId()).getVictoire()+1);
				hl.get(eq1.getId()).setDefaite(hl.get(eq1.getId()).getDefaite()+1);
			}else{
				hl.get(eq1.getId()).setNul(hl.get(eq1.getId()).getNul()+1);
				hl.get(eq2.getId()).setNul(hl.get(eq2.getId()).getNul()+1);
			}
		}
		
		//on calcul point
		for(LigneClassement lc : hl.values()){
			lc.setGoalAverage(lc.getButPour()-lc.getButContre());
			lc.setPoint(lc.getVictoire()*3+lc.getNul());
		}

		return res ;

	}

	public static Equipe findEquipe(String equipe1) throws JAXBException {

		File f = new File(VaadinService.getCurrent().getBaseDirectory()+"/equipe16.xml");

		Unmarshaller unm = JAXBContext.newInstance("fr.emac.gipsi.gsi.truptil.equipe").createUnmarshaller();

		ListeEquipe le = (ListeEquipe) unm.unmarshal(f);

		for(Equipe eq : le.getEquipe()){
			//		System.out.println(eq.getPosition().getNumGroupe() + " " + equipe1.subSequence(0, 1));
			if(eq.getPosition().getNumGroupe().equals(Integer.parseInt(equipe1.substring(0, 1)))){
				//			System.out.println(eq.getPosition().getNumEquipe() + " " + equipe1.substring(1, 2));

				if(eq.getPosition().getNumEquipe().equals(Integer.parseInt(equipe1.substring(1, 2)))){
					//	System.out.println(" equipe = " +eq.getId());
					return eq;
				}
			}
		}

		return null;
	}
	public static ArrayList<LigneClassement> updateClassementPronostic(int numPoule) throws UnknownHostException{

		ArrayList<LigneClassement> res = new ArrayList<LigneClassement>();

		List<Pronostic> prono = DBManager.getInstance().getDatastore().find(Pronostic.class).asList();

		for(Pronostic p : prono){
			//	if(p.)
		}


		return res ;

	}


	private static ResultatMatch findMatch(String idMatch) {
		List<ResultatMatch> rms;
		try {
			rms = DBManager.getInstance().getDatastore().find(ResultatMatch.class).asList();
			for( ResultatMatch rm : rms){
				if(rm.getIdMatch().equals(idMatch)){
					return rm;
				}
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static HashMap<String, Integer> createNombreBonPronostic(Joueur j) {

		HashMap<String, Integer> res = new HashMap<String,Integer>();
		res.put("Nombre exact de But",0);
		res.put("Bon Resultat",0);
		res.put("Mauvais Resultat",0);

		try {
			List<Pronostic> prono = DBManager.getInstance().getDatastore().find(Pronostic.class).asList();

			for(Pronostic p : prono){
				if(p.getIdJoueur().equals(j.getLogin())){
					ResultatMatch m =findMatch(p.getIdMatch());
					if(p.getScore1()== m.getScore1() && p.getScore2() == m.getScore2()){
						res.put("Nombre exact de But", res.get("Nombre exact de But")+1);
					}else if(p.getScore1()-m.getScore1() == p.getScore2()-m.getScore2()){
						res.put("Bon Resultat", res.get("Bon Resultat")+1);
					}else{
						res.put("Mauvais Resultat", res.get("Mauvais Resultat")+1);		
					}
				}
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(res);
		return res;
	}

	public static HashMap<String, Integer> createNombreBonPronostic(ResultatMatch m) {

		HashMap<String, Integer> res = new HashMap<String,Integer>();
		res.put("Nombre exact de But",0);
		res.put("Bon Resultat",0);
		res.put("Mauvais Resultat",0);

		try {
			List<Pronostic> prono = DBManager.getInstance().getDatastore().find(Pronostic.class).asList();

			for(Pronostic p : prono){
				if(p.getIdMatch().equals(m.getIdMatch())){
					if(p.getScore1()== m.getScore1() && p.getScore2() == m.getScore2()){
						res.put("Nombre exact de But", res.get("Nombre exact de But")+1);
					}else if(p.getScore1()-m.getScore1() == p.getScore2()-m.getScore2()){
						res.put("Bon Resultat", res.get("Bon Resultat")+1);
					}else{
						res.put("Mauvais Resultat", res.get("Mauvais Resultat")+1);		
					}
				}
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(res);
		return res;
	}



}
