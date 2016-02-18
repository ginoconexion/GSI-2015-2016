
package pronostic.util.client;

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
 *         &lt;element name="score1" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="score2" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "score1",
    "score2"
})
@XmlRootElement(name = "addResultatReelInput")
public class AddResultatReelInput {

    @XmlElement(required = true)
    protected String idMatch;
    protected int score1;
    protected int score2;

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

}
