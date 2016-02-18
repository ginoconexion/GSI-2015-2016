/**
 * 
 */
package fr.emac.gipsi.gsi.truptil.doa;

import java.util.ArrayList;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.mongodb.MongoClient;

import fr.emac.gipsi.gsi.truptil.element.Joueur;
import fr.emac.gipsi.gsi.truptil.element.Pronostic;
import fr.emac.gipsi.gsi.truptil.element.ResultatMatch;
import fr.emac.pronostique.AddJoueurInput;
import fr.emac.pronostique.AddPronosticInput;
import fr.emac.pronostique.AddPronosticResponse;
import fr.emac.pronostique.AddResultatReelInput;
import fr.emac.pronostique.GetAllJoueurResponse;
import fr.emac.pronostique.GetPointJoueurInput;
import fr.emac.pronostique.GetPointJoueurResponse;
import fr.emac.pronostique.GetPronosticByJoueurInput;
import fr.emac.pronostique.GetPronosticByJoueurResponse;
import fr.emac.pronostique.UpdatePointJoueurInput;

/**
 * @author truptil
 *
 */
public class ConnectionMongo {

	private Datastore datastore;
	private static ConnectionMongo instance;

	/**
	 * 
	 */
	public ConnectionMongo() {
		final Morphia morphia = new Morphia();
		morphia.mapPackage("fr.emac.gipsi.gsi.truptil.element");
		datastore = morphia.createDatastore(new MongoClient(), "resultatprono");
		datastore.ensureIndexes();
	}

	/**
	 * @return the instance
	 */
	public static ConnectionMongo getInstance() {
		if(instance==null){
			instance= new ConnectionMongo();
		}
		return instance;
	}

	/**
	 * @return the datastore
	 */
	public Datastore getDatastore() {
		return datastore;
	}

	public AddPronosticResponse addProno(AddPronosticInput parameters){
		AddPronosticResponse res = new AddPronosticResponse();

		Pronostic prono = new Pronostic(parameters.getScore1(),parameters.getScore2(), parameters.getIdMatch(), parameters.getIdJoueur());

		getDatastore().save(prono);

		return res ;	
	}

	public GetPointJoueurResponse getPointJoueur(GetPointJoueurInput parameters) {
		GetPointJoueurResponse res = new GetPointJoueurResponse();

		for(Joueur j : getDatastore().find(Joueur.class).asList()){
			if(j.getLogin().equals(parameters.getIdJoueur())){
				res.setNbPoints(j.getNbPoints());
			}
		}

		return res ;

	}

	public void updatePointJoueur(UpdatePointJoueurInput parameters) {

		for(Joueur j : getDatastore().find(Joueur.class).asList()){
			if(j.getLogin().equals(parameters.getIdJoueur())){
				j.setNbPoints(parameters.getNbPoints());
				getDatastore().save(j);
			}
		}
	}

	public void addResultatReel(AddResultatReelInput parameters) {

		ResultatMatch rm =  new ResultatMatch(parameters.getScore1(), parameters.getScore2(), parameters.getIdMatch());
		getDatastore().save(rm);
	}

	public GetPronosticByJoueurResponse getPronoByGameByJoueur(
			GetPronosticByJoueurInput parameters) {

		GetPronosticByJoueurResponse res = new GetPronosticByJoueurResponse();
		res.setIdJoueur(parameters.getIdJoueur());
		res.setIdMatch(parameters.getIdMatch());

		for(Pronostic rm : getDatastore().find(Pronostic.class).asList()){
			if(rm.getIdJoueur().equals(parameters.getIdJoueur())){
				if(rm.getIdMatch().equals(parameters.getIdMatch())){
					res.setScoreEq1(rm.getScore1());
					res.setScoreEq2(rm.getScore2());
				}
			}
		}

		return res;
	}

	public GetAllJoueurResponse getAllJoueur() {
		GetAllJoueurResponse res =  new GetAllJoueurResponse();

		for (Joueur jj : getDatastore().find(Joueur.class).asList()){
			res.getIdJoueur().add(jj.getLogin());
		}
		return res;
	}

	public void addJoueur(AddJoueurInput parameters) {

		Joueur jj = new Joueur();
		jj.setLogin(parameters.getIdJoueur());
		jj.setPwd("");
		jj.setNbPoints(0);
		getDatastore().save(jj);
	}
	public ArrayList<Pronostic> getPronosticsByMatch(String idMatch){
		ArrayList<Pronostic> liste = new ArrayList<Pronostic>();
		
		for (Pronostic p : getDatastore().find(Pronostic.class).asList()){
			System.out.println(p.getIdMatch());
			System.out.println(idMatch);
			if (p.getIdMatch().equals(idMatch)){
				liste.add(p);
			}
		}
		return liste;
	}

}
