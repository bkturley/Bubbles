package Bubbles;

import java.io.IOException;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class ConsoleMessagePrinter {

    public void printMessageToConsole(String message) {
        System.out.println();
        System.out.println("***************************************************");
        System.out.println("**   " + message);
        System.out.println("***************************************************");
        System.out.println();
    }

    public void printSoapMessageToConsole(SOAPMessage soapResponse, String titleMessage) throws SOAPException, IOException {
        ConsoleMessagePrinter consoleMessagePrinter = new ConsoleMessagePrinter();
        consoleMessagePrinter.printMessageToConsole(titleMessage);
        soapResponse.writeTo(System.out);
        System.out.println();
    }

}
