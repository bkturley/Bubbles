package Bubbles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServiceUrlGetter {

    public String getServiceUrl(String[] args) {
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

    private String getServiceUrlFromFile() throws IOException {
        FileContentsGetter fileContentsGetter = new FileContentsGetter();
        return fileContentsGetter.getTextFromFile("serviceUrl.txt");
    }



    private String getServiceUrlFromConsole() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Soap Service URI:");
        System.out.println("(ex http://localhost:8080/weather)");
        String returnMe = null;
        try {
            returnMe = br.readLine();
        } catch (IOException ex) {
            ConsoleMessagePrinter consoleMessagePrinter = new ConsoleMessagePrinter();
            consoleMessagePrinter.printMessageToConsole("Problem getting Service URL fron console");
        }
        return returnMe;
    }
}
