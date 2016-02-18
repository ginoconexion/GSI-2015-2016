/**
 * 
 */
package fr.emac.gipsi.gsi.model;

/**
 * @author Paul
 *
 */
public class Utilisateur {
	
	private Long id;
    private String login;
    private String pwd;
    private Groupe groupe;

    public Utilisateur( String login, String pwd, Groupe groupe ) {
        super();
        
        this.login = login;
        this.pwd = pwd;
        this.groupe = groupe;
    }

    public Utilisateur() {
        super();
    }

    public Groupe getGroupe() {
		return groupe;
	}

	/**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login
     *            the login to set
     */
    public void setLogin( String login ) {
        this.login = login;
    }

    /**
     * @return the pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * @param pwd
     *            the pwd to set
     */
    public void setPwd( String pwd ) {
        this.pwd = pwd;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
