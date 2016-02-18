
package fr.emac.pronostique;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idMatch" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="idJoueur" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="score1" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="score2" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idMatch",
    "idJoueur",
    "score1",
    "score2"
})
@XmlRootElement(name = "addPronosticInput")
public class AddPronosticInput {

    @XmlElement(required = true)
    protected String idMatch;
    @XmlElement(required = true)
    protected String idJoueur;
    protected int score1;
    protected int score2;

    /**
     * Obtient la valeur de la propriété idMatch.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdMatch() {
        return idMatch;
    }

    /**
     * Définit la valeur de la propriété idMatch.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdMatch(String value) {
        this.idMatch = value;
    }

    /**
     * Obtient la valeur de la propriété idJoueur.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdJoueur() {
        return idJoueur;
    }

    /**
     * Définit la valeur de la propriété idJoueur.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdJoueur(String value) {
        this.idJoueur = value;
    }

    /**
     * Obtient la valeur de la propriété score1.
     * 
     */
    public int getScore1() {
        return score1;
    }

    /**
     * Définit la valeur de la propriété score1.
     * 
     */
    public void setScore1(int value) {
        this.score1 = value;
    }

    /**
     * Obtient la valeur de la propriété score2.
     * 
     */
    public int getScore2() {
        return score2;
    }

    /**
     * Définit la valeur de la propriété score2.
     * 
     */
    public void setScore2(int value) {
        this.score2 = value;
    }

}
