//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.12.25 à 07:24:04 PM CET 
//


package fr.emac.gipsi.gsi.matchs;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element name="pays" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="poule" type="{http://emac.fr/gipsi/gsi/matchs}typePoule"/&gt;
 *         &lt;element ref="{http://emac.fr/gipsi/gsi/matchs}drapeau"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="score" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pays",
    "poule",
    "drapeau"
})
@XmlRootElement(name = "equipe")
public class Equipe {

    @XmlElement(required = true)
    protected String pays;
    @XmlElement(required = true)
    protected BigInteger poule;
    @XmlElement(required = true)
    protected Drapeau drapeau;
    @XmlAttribute(name = "score")
    protected BigInteger score;
    @XmlAttribute(name = "id")
    protected BigInteger id;

    /**
     * Obtient la valeur de la propriété pays.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPays() {
        return pays;
    }

    /**
     * Définit la valeur de la propriété pays.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPays(String value) {
        this.pays = value;
    }

    /**
     * Obtient la valeur de la propriété poule.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPoule() {
        return poule;
    }

    /**
     * Définit la valeur de la propriété poule.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPoule(BigInteger value) {
        this.poule = value;
    }

    /**
     * Obtient la valeur de la propriété drapeau.
     * 
     * @return
     *     possible object is
     *     {@link Drapeau }
     *     
     */
    public Drapeau getDrapeau() {
        return drapeau;
    }

    /**
     * Définit la valeur de la propriété drapeau.
     * 
     * @param value
     *     allowed object is
     *     {@link Drapeau }
     *     
     */
    public void setDrapeau(Drapeau value) {
        this.drapeau = value;
    }

    /**
     * Obtient la valeur de la propriété score.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getScore() {
        return score;
    }

    /**
     * Définit la valeur de la propriété score.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setScore(BigInteger value) {
        this.score = value;
    }

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setId(BigInteger value) {
        this.id = value;
    }

}
