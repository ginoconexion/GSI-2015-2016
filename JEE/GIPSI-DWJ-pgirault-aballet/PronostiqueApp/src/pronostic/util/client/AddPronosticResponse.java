
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
 *         &lt;element name="out" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "out"
})
@XmlRootElement(name = "addPronosticResponse")
public class AddPronosticResponse {

    @XmlElement(required = true)
    protected String out;

    /**
     * Obtient la valeur de la propri�t� out.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOut() {
        return out;
    }

    /**
     * D�finit la valeur de la propri�t� out.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOut(String value) {
        this.out = value;
    }

}
