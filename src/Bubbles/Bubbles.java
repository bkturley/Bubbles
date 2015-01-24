package Bubbles;

import Bubbles.FileToString.FileToString;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.xml.soap.*;

public class Bubbles {

    public static void main(String args[]) throws Exception {
        saveSoapResponse(getSoapResponse(getServiceUrl(args), getSoapRequest()));
    }

    static String getServiceUrl(String[] args) {
        try {
            return args[0];
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            try {
                return getServiceUrlFromFile();
            } catch (IOException ex) {
                return getServiceUrlFromConsole();
            }
        }
    }

    static SOAPMessage getSoapRequest() throws IOException, SOAPException {
        SOAPMessage soapRequest = MessageFactory.newInstance().createMessage(null, getSoapRequestInputStream());
        return soapRequest;
    }

    static SOAPMessage getSoapResponse(String url, SOAPMessage soapRequest) {
        SOAPConnection soapConnection = getSoapConnection();
        SOAPMessage soapResponse = null;
        try {
            soapResponse = soapConnection.call(soapRequest, url);
            soapConnection.close();
        } catch (SOAPException ex) {
            printMessageToConsole(ex.toString());
        }
        return soapResponse;
    }

    static void saveSoapResponse(SOAPMessage soapResponse) throws IOException, SOAPException {
        writeSoapResponseToFile(soapResponse, getPathToResponseFile());
        printSoapMessageToConsole(soapResponse, "SOAP RESPONSE");
    }

    static InputStream getSoapRequestInputStream() {
        InputStream requestInputStream = null;
        String fileName = "request.xml";
        try {
            requestInputStream = new ByteArrayInputStream(getTextFromFile(fileName).getBytes());
            return requestInputStream;
        } catch (IOException ex) {
            printMessageToConsole("Problem reading " + fileName + "      Exiting");
            System.exit(0);
        } finally {
            try {
                requestInputStream.close();
            } catch (IOException ex) {
                printMessageToConsole(ex.toString());
            }
        }
        return requestInputStream;
    }

    static void printSoapMessageToConsole(SOAPMessage soapResponse, String titleMessage) throws SOAPException, IOException {
        printMessageToConsole(titleMessage);
        soapResponse.writeTo(System.out);
        System.out.println();
    }

    static void printMessageToConsole(String message) {
        System.out.println();
        System.out.println("***************************************************");
        System.out.println("**   " + message);
        System.out.println("***************************************************");
        System.out.println();
    }

    static String getPathToResponseFile() {
        return System.getProperty("user.dir") + File.separator + "response.xml";
    }

    static String getTextFromFile(String relativePath) throws IOException {
        FileToString fileToString = new FileToString();
        String requestString = null;
        requestString = fileToString.fileToString(relativePath);

        return requestString;
    }

    static SOAPConnection getSoapConnection() {
        SOAPConnectionFactory soapConnectionFactory;
        try {
            soapConnectionFactory = SOAPConnectionFactory.newInstance();
            return soapConnectionFactory.createConnection();

        } catch (SOAPException ex) {
            printMessageToConsole(ex.toString());
        } catch (UnsupportedOperationException ex) {
            printMessageToConsole(ex.toString());
        }
        return null;
    }

    static String getServiceUrlFromConsole() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Soap Service URI:");
        System.out.println("(ex http://localhost:8080/weather)");
        String returnMe = null;
        try {
            returnMe = br.readLine();
        } catch (IOException ex) {
            printMessageToConsole("Problem getting Service URL fron console");
        }
        return returnMe;
    }

    static void writeSoapResponseToFile(SOAPMessage soapRequest, String pathToResponseFile) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(pathToResponseFile);
            try {
                soapRequest.writeTo(fileOutputStream);
            } catch (SOAPException ex) {
                printMessageToConsole(ex.toString());
            } catch (IOException ex) {
                printMessageToConsole(ex.toString());
            }

        } catch (FileNotFoundException ex) {
            printMessageToConsole(ex.toString());
        }

    }

    static String getServiceUrlFromFile() throws IOException {
        return getTextFromFile("serviceUrl.txt");
    }

}
