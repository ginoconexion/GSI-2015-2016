
package org.example.activitychooserservice;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.example.activitychooserservice package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.example.activitychooserservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NewOperation }
     * 
     */
    public NewOperation createNewOperation() {
        return new NewOperation();
    }

    /**
     * Create an instance of {@link NewOperationResponse }
     * 
     */
    public NewOperationResponse createNewOperationResponse() {
        return new NewOperationResponse();
    }

    /**
     * Create an instance of {@link Meteo }
     * 
     */
    public Meteo createMeteo() {
        return new Meteo();
    }

    /**
     * Create an instance of {@link Activite }
     * 
     */
    public Activite createActivite() {
        return new Activite();
    }

    /**
     * Create an instance of {@link ListeActivites }
     * 
     */
    public ListeActivites createListeActivites() {
        return new ListeActivites();
    }

}
