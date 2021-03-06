
package pronostic.util.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "PronostiqueInterface", targetNamespace = "http://www.emac.fr/pronostique/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PronostiqueInterface {


    /**
     * 
     * @param parameters
     * @return
     *     returns pronostic.util.client.GetPointJoueurResponse
     */
    @WebMethod
    @WebResult(name = "getPointJoueurResponse", targetNamespace = "http://www.emac.fr/pronostique/", partName = "parameters")
    public GetPointJoueurResponse getPointJoueur(
        @WebParam(name = "getPointJoueurInput", targetNamespace = "http://www.emac.fr/pronostique/", partName = "parameters")
        GetPointJoueurInput parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns pronostic.util.client.AddResultatReelResponse
     */
    @WebMethod
    @WebResult(name = "addResultatReelResponse", targetNamespace = "http://www.emac.fr/pronostique/", partName = "parameters")
    public AddResultatReelResponse addResultatReel(
        @WebParam(name = "addResultatReelInput", targetNamespace = "http://www.emac.fr/pronostique/", partName = "parameters")
        AddResultatReelInput parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns pronostic.util.client.AddPronosticResponse
     */
    @WebMethod
    @WebResult(name = "addPronosticResponse", targetNamespace = "http://www.emac.fr/pronostique/", partName = "parameters")
    public AddPronosticResponse addPronostic(
        @WebParam(name = "addPronosticInput", targetNamespace = "http://www.emac.fr/pronostique/", partName = "parameters")
        AddPronosticInput parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns pronostic.util.client.AddJoueurResponse
     */
    @WebMethod
    @WebResult(name = "addJoueurResponse", targetNamespace = "http://www.emac.fr/pronostique/", partName = "parameters")
    public AddJoueurResponse addJoueur(
        @WebParam(name = "addJoueurInput", targetNamespace = "http://www.emac.fr/pronostique/", partName = "parameters")
        AddJoueurInput parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns pronostic.util.client.UpdatePointJoueurResponse
     */
    @WebMethod
    @WebResult(name = "updatePointJoueurResponse", targetNamespace = "http://www.emac.fr/pronostique/", partName = "parameters")
    public UpdatePointJoueurResponse updatePointJoueur(
        @WebParam(name = "updatePointJoueurInput", targetNamespace = "http://www.emac.fr/pronostique/", partName = "parameters")
        UpdatePointJoueurInput parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns pronostic.util.client.GetAllJoueurResponse
     */
    @WebMethod
    @WebResult(name = "getAllJoueurResponse", targetNamespace = "http://www.emac.fr/pronostique/", partName = "parameters")
    public GetAllJoueurResponse getAllJoueur(
        @WebParam(name = "getAllJoueurInput", targetNamespace = "http://www.emac.fr/pronostique/", partName = "parameters")
        GetAllJoueurInput parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns pronostic.util.client.GetPronosticByJoueurResponse
     */
    @WebMethod
    @WebResult(name = "getPronosticByJoueurResponse", targetNamespace = "http://www.emac.fr/pronostique/", partName = "parameters")
    public GetPronosticByJoueurResponse getPronosticByJoueur(
        @WebParam(name = "getPronosticByJoueurInput", targetNamespace = "http://www.emac.fr/pronostique/", partName = "parameters")
        GetPronosticByJoueurInput parameters);

}
