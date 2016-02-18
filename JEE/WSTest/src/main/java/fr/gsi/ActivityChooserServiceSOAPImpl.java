
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package fr.gsi;

import java.util.ArrayList;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.example.activitychooserservice.Activite;
import org.example.activitychooserservice.ActivityChooserService;
import org.example.activitychooserservice.ListeActivites;
import org.example.activitychooserservice.Meteo;

/**
 * This class was generated by Apache CXF 3.1.4
 * 2015-12-11T16:37:44.281+01:00
 * Generated source version: 3.1.4
 * 
 */

@javax.jws.WebService(
                      serviceName = "ActivityChooserService",
                      portName = "ActivityChooserServiceSOAP",
                      targetNamespace = "http://www.example.org/ActivityChooserService/",
                      wsdlLocation = "file:/C:/Users/Paul/workspace/WebServices/Helloworld/Helloworld/src/main/resources/wsdl/ActivityChooserService.wsdl",
                      endpointInterface = "org.example.activitychooserservice.ActivityChooserService")
                      
public class ActivityChooserServiceSOAPImpl implements ActivityChooserService {

    private static final Logger LOG = Logger.getLogger(ActivityChooserServiceSOAPImpl.class.getName());
    

    /* (non-Javadoc)
     * @see org.example.activitychooserservice.ActivityChooserService#newOperation(org.example.activitychooserservice.Meteo  meteo )*
     */
    public org.example.activitychooserservice.ListeActivites newOperation(Meteo meteo) { 
        LOG.info("Executing operation newOperation");
        System.out.println(meteo);
        double temp = meteo.getTemperature();
        ListeActivites liste = new ListeActivites();
        
        if (temp < 0) {
        	Activite activite1 = new Activite();
            activite1.setNom("ski");
        	liste.getActivite().add(activite1);
        }
        else {
            Activite activite2 = new Activite();
            activite2.setNom("foot");
        	liste.getActivite().add(activite2);
        }
        
        return liste;
    }
    

}
