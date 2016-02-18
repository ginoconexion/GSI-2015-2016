
package pronostic.util.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="nbPoints" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "nbPoints"
})
@XmlRootElement(name = "getPointJoueurResponse")
public class GetPointJoueurResponse {

    protected int nbPoints;

    /**
     * Obtient la valeur de la propri�t� nbPoints.
     * 
     */
    public int getNbPoints() {
        return nbPoints;
    }

    /**
     * D�finit la valeur de la propri�t� nbPoints.
     * 
     */
    public void setNbPoints(int value) {
        this.nbPoints = value;
    }

}
