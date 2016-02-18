//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.12.25 à 07:24:04 PM CET 
//


package fr.emac.gipsi.gsi.matchs;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.emac.gipsi.gsi.matchs package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.emac.gipsi.gsi.matchs
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Drapeau }
     * 
     */
    public Drapeau createDrapeau() {
        return new Drapeau();
    }

    /**
     * Create an instance of {@link Equipe }
     * 
     */
    public Equipe createEquipe() {
        return new Equipe();
    }

    /**
     * Create an instance of {@link Match }
     * 
     */
    public Match createMatch() {
        return new Match();
    }

    /**
     * Create an instance of {@link Resultats }
     * 
     */
    public Resultats createResultats() {
        return new Resultats();
    }

}
