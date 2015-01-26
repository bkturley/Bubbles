package Bubbles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

class SOAPMessageSaver {

    public void saveSoapResponse(SOAPMessage soapResponse) throws IOException, SOAPException {
        writeSoapResponseToFile(soapResponse, getPathToResponseFile());
        ConsoleMessagePrinter consoleMessagePrinter = new ConsoleMessagePrinter();
        consoleMessagePrinter.printSoapMessageToConsole(soapResponse, "SOAP RESPONSE");
    }

    private String getPathToResponseFile() {
        return System.getProperty("user.dir") + File.separator + "response.xml";
    }

    private void writeSoapResponseToFile(SOAPMessage soapRequest, String pathToResponseFile) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(pathToResponseFile);
            try {
                soapRequest.writeTo(fileOutputStream);
            } catch (SOAPException ex) {
                ConsoleMessagePrinter consoleMessagePrinter = new ConsoleMessagePrinter();
                consoleMessagePrinter.printMessageToConsole(ex.toString());
            } catch (IOException ex) {
                ConsoleMessagePrinter consoleMessagePrinter = new ConsoleMessagePrinter();
                consoleMessagePrinter.printMessageToConsole(ex.toString());
            }

        } catch (FileNotFoundException ex) {
            ConsoleMessagePrinter consoleMessagePrinter = new ConsoleMessagePrinter();
            consoleMessagePrinter.printMessageToConsole(ex.toString());
        }

    }
}
