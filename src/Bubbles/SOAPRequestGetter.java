package Bubbles;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

/**
 *
 * @author SuprTEK User
 */
class SOAPRequestGetter {

    public SOAPMessage getSoapRequest() throws IOException, SOAPException {
        SOAPMessage soapRequest = MessageFactory.newInstance().createMessage(null, getSoapRequestInputStream());
        return soapRequest;
    }

    private InputStream getSoapRequestInputStream() {
        InputStream requestInputStream = null;
        String fileName = "request.xml";
        try {
            FileContentsGetter fileContentsGetter = new FileContentsGetter();
            requestInputStream = new ByteArrayInputStream(fileContentsGetter.getTextFromFile(fileName).getBytes());
            return requestInputStream;
        } catch (IOException ex) {
            ConsoleMessagePrinter consoleMessagePrinter = new ConsoleMessagePrinter();
            consoleMessagePrinter.printMessageToConsole("Problem reading " + fileName + "      Exiting");
            System.exit(0);
        } finally {
            try {
                requestInputStream.close();
            } catch (IOException ex) {
                ConsoleMessagePrinter consoleMessagePrinter = new ConsoleMessagePrinter();
                consoleMessagePrinter.printMessageToConsole(ex.toString());
            }
        }
        return requestInputStream;
    }

}
