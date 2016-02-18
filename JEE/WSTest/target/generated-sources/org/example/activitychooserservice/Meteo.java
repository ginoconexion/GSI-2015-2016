
package org.example.activitychooserservice;

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
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="temperature" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="humidite" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
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
    "temperature",
    "humidite"
})
@XmlRootElement(name = "meteo")
public class Meteo {

    protected double temperature;
    protected double humidite;

    /**
     * Obtient la valeur de la propri�t� temperature.
     * 
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * D�finit la valeur de la propri�t� temperature.
     * 
     */
    public void setTemperature(double value) {
        this.temperature = value;
    }

    /**
     * Obtient la valeur de la propri�t� humidite.
     * 
     */
    public double getHumidite() {
        return humidite;
    }

    /**
     * D�finit la valeur de la propri�t� humidite.
     * 
     */
    public void setHumidite(double value) {
        this.humidite = value;
    }

}
