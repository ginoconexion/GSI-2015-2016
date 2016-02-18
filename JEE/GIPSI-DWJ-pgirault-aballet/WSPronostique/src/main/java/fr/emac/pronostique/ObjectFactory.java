
package fr.emac.pronostique;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.emac.pronostique package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.emac.pronostique
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddPronosticInput }
     * 
     */
    public AddPronosticInput createAddPronosticInput() {
        return new AddPronosticInput();
    }

    /**
     * Create an instance of {@link AddPronosticResponse }
     * 
     */
    public AddPronosticResponse createAddPronosticResponse() {
        return new AddPronosticResponse();
    }

    /**
     * Create an instance of {@link GetPointJoueurInput }
     * 
     */
    public GetPointJoueurInput createGetPointJoueurInput() {
        return new GetPointJoueurInput();
    }

    /**
     * Create an instance of {@link GetPointJoueurResponse }
     * 
     */
    public GetPointJoueurResponse createGetPointJoueurResponse() {
        return new GetPointJoueurResponse();
    }

    /**
     * Create an instance of {@link UpdatePointJoueurInput }
     * 
     */
    public UpdatePointJoueurInput createUpdatePointJoueurInput() {
        return new UpdatePointJoueurInput();
    }

    /**
     * Create an instance of {@link UpdatePointJoueurResponse }
     * 
     */
    public UpdatePointJoueurResponse createUpdatePointJoueurResponse() {
        return new UpdatePointJoueurResponse();
    }

    /**
     * Create an instance of {@link AddResultatReelInput }
     * 
     */
    public AddResultatReelInput createAddResultatReelInput() {
        return new AddResultatReelInput();
    }

    /**
     * Create an instance of {@link AddResultatReelResponse }
     * 
     */
    public AddResultatReelResponse createAddResultatReelResponse() {
        return new AddResultatReelResponse();
    }

}
