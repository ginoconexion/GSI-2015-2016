
package fr.isis.weatherforecast;

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
 *         &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Temperature" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Humidity" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="UselessInformation" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "date",
    "temperature",
    "humidity",
    "uselessInformation"
})
@XmlRootElement(name = "getWeatherResponse")
public class GetWeatherResponse {

    @XmlElement(name = "Date", required = true)
    protected String date;
    @XmlElement(name = "Temperature", required = true)
    protected String temperature;
    @XmlElement(name = "Humidity", required = true)
    protected String humidity;
    @XmlElement(name = "UselessInformation", required = true)
    protected String uselessInformation;

    /**
     * Obtient la valeur de la propriété date.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * Définit la valeur de la propriété date.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
    }

    /**
     * Obtient la valeur de la propriété temperature.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * Définit la valeur de la propriété temperature.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemperature(String value) {
        this.temperature = value;
    }

    /**
     * Obtient la valeur de la propriété humidity.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHumidity() {
        return humidity;
    }

    /**
     * Définit la valeur de la propriété humidity.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHumidity(String value) {
        this.humidity = value;
    }

    /**
     * Obtient la valeur de la propriété uselessInformation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUselessInformation() {
        return uselessInformation;
    }

    /**
     * Définit la valeur de la propriété uselessInformation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUselessInformation(String value) {
        this.uselessInformation = value;
    }

}
