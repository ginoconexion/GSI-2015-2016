package fr.emac.gipsi.gsi.model;

public class Message {

    private String      id;
    private Utilisateur source;
    private Groupe      destinataire;
    private String      text;

    public Message() {
        super();
    }

    public Message( String id, Utilisateur source, Groupe destinataire, String text ) {
        super();
        this.source = source;
        this.destinataire = destinataire;
        this.text = text;
    }

    /**
     * @return the source
     */
    public Utilisateur getSource() {
        return source;
    }

    /**
     * @param source
     *            the source to set
     */
    public void setSource( Utilisateur source ) {
        this.source = source;
    }

    /**
     * @return the destinataire
     */
    public Groupe getDestinataire() {
        return destinataire;
    }

    /**
     * @param destinataire
     *            the destinataire to set
     */
    public void setDestinataire( Groupe destinataire ) {
        this.destinataire = destinataire;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text
     *            the text to set
     */
    public void setText( String text ) {
        this.text = text;
    }

}
