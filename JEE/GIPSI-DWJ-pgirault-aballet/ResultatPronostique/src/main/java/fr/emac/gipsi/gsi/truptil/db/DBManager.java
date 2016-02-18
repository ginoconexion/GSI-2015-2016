package fr.emac.gipsi.gsi.truptil.db;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.vaadin.server.VaadinService;

import fr.emac.gipsi.gsi.truptil.element.*;



public class DBManager {

	private static Logger log = Logger.getLogger("adeDBManager");
	private static DBManager instance;
	private static Set<Class<?>> collections = new HashSet<Class<?>>();

	private Morphia morphia = new Morphia();
	private MongoClient mongo;
	private Datastore ds;
	private DBProperties dbProps;

	public DBManager() throws UnknownHostException{

		URL propsUrl = null;
		try {
//			propsUrl = new URL("file://"+VaadinService.getCurrent().getBaseDirectory().getAbsolutePath()+"/adedb.properties");
			propsUrl = new URL("file://"+System.getProperty("user.dir")+"/adedb.properties");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if(propsUrl != null){
			try {
				// Si un fichier demio.properties existe, on le prend comme fichier de propri�t�s
				Properties props = new Properties();
				props.load(propsUrl.openStream());
				dbProps = new DBProperties(props);
			} catch (Exception e) {
				dbProps = new DBProperties();
				log.info("simpetra.properties file not available: " + e.getMessage());
			}
		} else {
			// sinon on charge les propri�t�s par d�faut
			dbProps = new DBProperties();
		}

		// Connexion � la base
		mongo = new MongoClient(dbProps.getDbHost(), dbProps.getDbPort());

		// R�cup�ration du Datastore
		collections.add(Joueur.class);
		collections.add(Pronostic.class);
		collections.add(ResultatMatch.class);
		
		
		Morphia morphia = new Morphia();
		System.out.println("collections in DBManager size = "+collections.size());
		Iterator<Class<?>> it = collections.iterator();
		while(it.hasNext()){
			morphia.map(it.next());
		}
		ds = morphia.createDatastore(mongo, dbProps.getDbName());
		ds.ensureIndexes();
		ds.ensureCaps();
	}

	/**
	 * Retourne le datastore permettant la persistance et la gestion des donn�es
	 * Pour plus d'infos, se r�f�rer � la documentation : 
	 * http://code.google.com/p/morphia/wiki/Datastore
	 * http://code.google.com/p/morphia/wiki/Query
	 * 
	 * @return
	 */
	public Datastore getDatastore(){
		return this.ds;
	}

	/**
	 * Ajoute une collection (un POJO) au datastore
	 * 
	 * @param collection Classe correspondant au POJO � persister
	 */
	public static void addCollection(Class<?> collection){
		// Si l'instance est d�j� cr��e, on s'occupe des indexes
		if(instance != null
				&& ! collections.contains(collection)) {
			instance.morphia.map(collection);
			instance.ds.ensureIndexes(collection);
			instance.ds.ensureCaps();
		}
		collections.add(collection);
	}

	public static DBManager getInstance() throws UnknownHostException{
		if(instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	/**
	 * @return the dbProps
	 */
	public DBProperties getDbProps() {
		return dbProps;
	}

	

	

}
