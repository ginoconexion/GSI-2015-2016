package fr.emac.pronostique;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.1.4
 * 2015-12-21T16:09:24.722+01:00
 * Generated source version: 3.1.4
 * 
 */
@WebService(targetNamespace = "http://www.emac.fr/pronostique/", name = "PronostiqueInterface")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface PronostiqueInterface {

    @WebMethod
    @WebResult(name = "getPointJoueurResponse", targetNamespace = "http://www.emac.fr/pronostique/", partName = "parameters")
    public GetPointJoueurResponse getPointJoueur(
        @WebParam(partName = "parameters", name = "getPointJoueurInput", targetNamespace = "http://www.emac.fr/pronostique/")
        GetPointJoueurInput parameters
    );

    @WebMethod
    @WebResult(name = "addResultatReelResponse", targetNamespace = "http://www.emac.fr/pronostique/", partName = "parameters")
    public AddResultatReelResponse addResultatReel(
        @WebParam(partName = "parameters", name = "addResultatReelInput", targetNamespace = "http://www.emac.fr/pronostique/")
        AddResultatReelInput parameters
    );

    @WebMethod
    @WebResult(name = "addPronosticResponse", targetNamespace = "http://www.emac.fr/pronostique/", partName = "parameters")
    public AddPronosticResponse addPronostic(
        @WebParam(partName = "parameters", name = "addPronosticInput", targetNamespace = "http://www.emac.fr/pronostique/")
        AddPronosticInput parameters
    );

    @WebMethod
    @WebResult(name = "updatePointJoueurResponse", targetNamespace = "http://www.emac.fr/pronostique/", partName = "parameters")
    public UpdatePointJoueurResponse updatePointJoueur(
        @WebParam(partName = "parameters", name = "updatePointJoueurInput", targetNamespace = "http://www.emac.fr/pronostique/")
        UpdatePointJoueurInput parameters
    );

    @WebMethod
    @WebResult(name = "addJoueurResponse", targetNamespace = "http://www.emac.fr/pronostique/", partName = "parameters")
    public AddJoueurResponse addJoueur(
        @WebParam(partName = "parameters", name = "addJoueurInput", targetNamespace = "http://www.emac.fr/pronostique/")
        AddJoueurInput parameters
    );

    @WebMethod
    @WebResult(name = "getAllJoueurResponse", targetNamespace = "http://www.emac.fr/pronostique/", partName = "parameters")
    public GetAllJoueurResponse getAllJoueur(
        @WebParam(partName = "parameters", name = "getAllJoueurInput", targetNamespace = "http://www.emac.fr/pronostique/")
        GetAllJoueurInput parameters
    );

    @WebMethod
    @WebResult(name = "getPronosticByJoueurResponse", targetNamespace = "http://www.emac.fr/pronostique/", partName = "parameters")
    public GetPronosticByJoueurResponse getPronosticByJoueur(
        @WebParam(partName = "parameters", name = "getPronosticByJoueurInput", targetNamespace = "http://www.emac.fr/pronostique/")
        GetPronosticByJoueurInput parameters
    );
}
