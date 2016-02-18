/**
 * 
 */
package fr.emac.gipsi.gsi.truptil.element;

import java.util.ArrayList;

import org.mongodb.morphia.annotations.*;



/**
 * @author truptil
 *
 */
public class Joueur {

	@Id
	private String login;
	@Property
	private String pwd;
	@Property
	private int nbPoints;
	@Embedded
	private ArrayList<Pronostic> listPronos;
	
	
	/**
	 * 
	 */
	public Joueur() {
		// TODO Auto-generated constructor stub
	}




	/**
	 * @return the nbPoints
	 */
	public int getNbPoints() {
		return nbPoints;
	}


	/**
	 * @param nbPoints the nbPoints to set
	 */
	public void setNbPoints(int nbPoints) {
		this.nbPoints = nbPoints;
	}


	/**
	 * @return the listPronos
	 */
	public ArrayList<Pronostic> getListPronos() {
		if (listPronos==null){
			listPronos = new ArrayList<Pronostic>();
		}
		return listPronos;
	}




	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}




	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}




	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}




	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	
	
	
}
