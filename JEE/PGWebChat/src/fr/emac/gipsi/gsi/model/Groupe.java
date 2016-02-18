package fr.emac.gipsi.gsi.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Groupe {

    private String                       name;
    private ArrayList<Message>           listMessages;
    private HashMap<String, Utilisateur> listUtilisateurs;

    public Groupe( String name ) {
        this.name = name;
        listMessages = new ArrayList<Message>();
        listUtilisateurs = new HashMap<String, Utilisateur>();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName( String name ) {
        this.name = name;
    }

    /**
     * @return the listMessages
     */
    public ArrayList<Message> getListMessages() {

        for ( Message m : listMessages ) {
            System.out.println( "source : " + m.getSource().getLogin() + "text : " + m.getText() );
        }

        return listMessages;
    }

    /**
     * @return the listUtilisateurs
     */
    public HashMap<String, Utilisateur> getListUtilisateurs() {
        return listUtilisateurs;
    }

}
