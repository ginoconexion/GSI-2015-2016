//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2015.12.25 � 07:24:04 PM CET 
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
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
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
     * Obtient la valeur de la propri�t� pays.
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
     * D�finit la valeur de la propri�t� pays.
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
     * Obtient la valeur de la propri�t� poule.
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
     * D�finit la valeur de la propri�t� poule.
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
     * Obtient la valeur de la propri�t� drapeau.
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
     * D�finit la valeur de la propri�t� drapeau.
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
     * Obtient la valeur de la propri�t� score.
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
     * D�finit la valeur de la propri�t� score.
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
     * Obtient la valeur de la propri�t� id.
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
     * D�finit la valeur de la propri�t� id.
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
