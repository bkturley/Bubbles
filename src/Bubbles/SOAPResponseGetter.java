package Bubbles;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

class SOAPResponseGetter {

    public SOAPMessage getSoapResponse(String url, SOAPMessage soapRequest) {
        SOAPConnection soapConnection = getSoapConnection();
        SOAPMessage soapResponse = null;
        try {
            soapResponse = soapConnection.call(soapRequest, url);
            soapConnection.close();
        } catch (SOAPException ex) {
            ConsoleMessagePrinter consoleMessagePrinter = new ConsoleMessagePrinter();
            consoleMessagePrinter.printMessageToConsole(ex.toString());
        }
        return soapResponse;
    }
    
        private SOAPConnection getSoapConnection() {
        SOAPConnectionFactory soapConnectionFactory;
        try {
            soapConnectionFactory = SOAPConnectionFactory.newInstance();
            return soapConnectionFactory.createConnection();

        } catch (SOAPException ex) {
            ConsoleMessagePrinter consoleMessagePrinter = new ConsoleMessagePrinter();
            consoleMessagePrinter.printMessageToConsole(ex.toString());
        } catch (UnsupportedOperationException ex) {
            ConsoleMessagePrinter consoleMessagePrinter = new ConsoleMessagePrinter();
            consoleMessagePrinter.printMessageToConsole(ex.toString());
        }
        return null;
    }
}
