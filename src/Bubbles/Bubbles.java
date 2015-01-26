package Bubbles;

import java.io.IOException;
import javax.xml.soap.SOAPException;

public class Bubbles {

    public Bubbles(String[] args) throws IOException, SOAPException {
        ServiceUrlGetter serviceUrlGetter = new ServiceUrlGetter();
        SOAPRequestGetter soapRequestGetter = new SOAPRequestGetter();
        SOAPResponseGetter soapResponseGetter = new SOAPResponseGetter();
        SOAPMessageSaver soapMessageSaver = new SOAPMessageSaver();
        soapMessageSaver.saveSoapResponse(soapResponseGetter.getSoapResponse(serviceUrlGetter.getServiceUrl(args), soapRequestGetter.getSoapRequest()));
    }

    Bubbles(String[] args, ServiceUrlGetter serviceUrlGetter, SOAPRequestGetter soapRequestGetter, SOAPResponseGetter soapResponseGetter, SOAPMessageSaver soapMessageSaver) throws IOException, SOAPException {
        soapMessageSaver.saveSoapResponse(soapResponseGetter.getSoapResponse(serviceUrlGetter.getServiceUrl(args), soapRequestGetter.getSoapRequest()));
    }
}
