
package pronostic.util.client;

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
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idMatch" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idJoueur" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="scoreEq1" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="scoreEq2" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idMatch",
    "idJoueur",
    "scoreEq1",
    "scoreEq2"
})
@XmlRootElement(name = "getPronosticByJoueurResponse")
public class GetPronosticByJoueurResponse {

    @XmlElement(required = true)
    protected String idMatch;
    @XmlElement(required = true)
    protected String idJoueur;
    protected int scoreEq1;
    protected int scoreEq2;

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
     * Obtient la valeur de la propriété scoreEq1.
     * 
     */
    public int getScoreEq1() {
        return scoreEq1;
    }

    /**
     * Définit la valeur de la propriété scoreEq1.
     * 
     */
    public void setScoreEq1(int value) {
        this.scoreEq1 = value;
    }

    /**
     * Obtient la valeur de la propriété scoreEq2.
     * 
     */
    public int getScoreEq2() {
        return scoreEq2;
    }

    /**
     * Définit la valeur de la propriété scoreEq2.
     * 
     */
    public void setScoreEq2(int value) {
        this.scoreEq2 = value;
    }

}
