
package calcul.util.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idMatch" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idJoueur" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="score1" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="score2" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="scoreProno1" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="scoreProno2" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "score1",
    "score2",
    "scoreProno1",
    "scoreProno2"
})
@XmlRootElement(name = "calculerPointsJoueurInput")
public class CalculerPointsJoueurInput {

    @XmlElement(required = true)
    protected String idMatch;
    @XmlElement(required = true)
    protected String idJoueur;
    protected int score1;
    protected int score2;
    protected int scoreProno1;
    protected int scoreProno2;

    /**
     * Obtient la valeur de la propri�t� idMatch.
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
     * D�finit la valeur de la propri�t� idMatch.
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
     * Obtient la valeur de la propri�t� idJoueur.
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
     * D�finit la valeur de la propri�t� idJoueur.
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
     * Obtient la valeur de la propri�t� score1.
     * 
     */
    public int getScore1() {
        return score1;
    }

    /**
     * D�finit la valeur de la propri�t� score1.
     * 
     */
    public void setScore1(int value) {
        this.score1 = value;
    }

    /**
     * Obtient la valeur de la propri�t� score2.
     * 
     */
    public int getScore2() {
        return score2;
    }

    /**
     * D�finit la valeur de la propri�t� score2.
     * 
     */
    public void setScore2(int value) {
        this.score2 = value;
    }

    /**
     * Obtient la valeur de la propri�t� scoreProno1.
     * 
     */
    public int getScoreProno1() {
        return scoreProno1;
    }

    /**
     * D�finit la valeur de la propri�t� scoreProno1.
     * 
     */
    public void setScoreProno1(int value) {
        this.scoreProno1 = value;
    }

    /**
     * Obtient la valeur de la propri�t� scoreProno2.
     * 
     */
    public int getScoreProno2() {
        return scoreProno2;
    }

    /**
     * D�finit la valeur de la propri�t� scoreProno2.
     * 
     */
    public void setScoreProno2(int value) {
        this.scoreProno2 = value;
    }

}
