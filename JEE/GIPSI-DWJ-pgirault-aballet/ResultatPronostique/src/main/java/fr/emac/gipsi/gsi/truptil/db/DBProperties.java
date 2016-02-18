package fr.emac.gipsi.gsi.truptil.db;

import java.util.ArrayList;
import java.util.Properties;

public class DBProperties {

	private ArrayList<String> listProprietes;

	public final static String DB_HOST = "mise.ncc.db.host";
	public final static String DB_PORT = "mise.ncc.db.port";
	public final static String DB_NAME = "mise.ncc.db.name";


	private String dbHost = "127.0.0.1";
	private int dbPort = 27017;
	private String dbName = "adesynchro";

	public DBProperties(){
	}

	public DBProperties(Properties props){
		listProprietes = new ArrayList<String>();
		for(Object k : props.keySet()){
			String key = k.toString().trim();
			listProprietes.add(key);
			if(DB_HOST.equals(key)){
				this.setDbHost(props.get(key).toString().trim());
			} else if(DB_PORT.equals(key)){
				this.setDbPort(Integer.parseInt(props.get(key).toString().trim()));
			} else if(DB_NAME.equals(key)){
				this.setDbName(props.get(key).toString().trim());
			}
		}
	}

	public void majValeur(String key, String valeur){
		if(DB_HOST.equals(key)){
			this.setDbHost(valeur);
		} else if(DB_PORT.equals(key)){
			this.setDbPort(Integer.parseInt(valeur));
		} else if(DB_NAME.equals(key)){
			this.setDbName(valeur);
		}
	}

	public String getValeur(String key){
		if(DB_HOST.equals(key)){
			return this.getDbHost();
		} else if(DB_PORT.equals(key)){
			return Integer.toString(this.getDbPort());
		} else if(DB_NAME.equals(key)){
			return this.getDbName();
		} 
		return "";
	}


	public String getDbHost() {
		return dbHost;
	}

	public void setDbHost(String dbHost) {
		this.dbHost = dbHost;
	}

	public int getDbPort() {
		return dbPort;
	}

	public void setDbPort(int dbPort) {
		this.dbPort = dbPort;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	
	/**
	 * @return the listProprietes
	 */
	public ArrayList<String> getListProprietes() {
		return listProprietes;
	}

}
