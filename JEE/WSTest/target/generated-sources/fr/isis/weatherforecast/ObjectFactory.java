
package fr.isis.weatherforecast;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.isis.weatherforecast package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetCities_QNAME = new QName("http://www.isis.fr/WeatherForecast/", "getCities");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.isis.weatherforecast
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCitiesResponse }
     * 
     */
    public GetCitiesResponse createGetCitiesResponse() {
        return new GetCitiesResponse();
    }

    /**
     * Create an instance of {@link CitiesType }
     * 
     */
    public CitiesType createCitiesType() {
        return new CitiesType();
    }

    /**
     * Create an instance of {@link GetWeather }
     * 
     */
    public GetWeather createGetWeather() {
        return new GetWeather();
    }

    /**
     * Create an instance of {@link GetWeatherResponse }
     * 
     */
    public GetWeatherResponse createGetWeatherResponse() {
        return new GetWeatherResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.isis.fr/WeatherForecast/", name = "getCities")
    public JAXBElement<String> createGetCities(String value) {
        return new JAXBElement<String>(_GetCities_QNAME, String.class, null, value);
    }

}
