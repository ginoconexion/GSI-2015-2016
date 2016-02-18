
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
 *         &lt;element name="Cities" type="{http://www.isis.fr/WeatherForecast/}CitiesType"/&gt;
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
    "cities"
})
@XmlRootElement(name = "getCitiesResponse")
public class GetCitiesResponse {

    @XmlElement(name = "Cities", required = true)
    protected CitiesType cities;

    /**
     * Obtient la valeur de la propriété cities.
     * 
     * @return
     *     possible object is
     *     {@link CitiesType }
     *     
     */
    public CitiesType getCities() {
        return cities;
    }

    /**
     * Définit la valeur de la propriété cities.
     * 
     * @param value
     *     allowed object is
     *     {@link CitiesType }
     *     
     */
    public void setCities(CitiesType value) {
        this.cities = value;
    }

}
