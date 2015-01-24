package Bubbles.FileToString;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileToString {

    public static String fileToString(String fileName) throws FileNotFoundException, IOException {

        String relativePathToFile = System.getProperty("user.dir") + File.separator + fileName;
        String fileContentAsString = null;
        File file = new File(relativePathToFile);
        FileReader fileReader = new FileReader(file);
        char[] charArray = new char[(int) file.length()];
        fileReader.read(charArray);
        fileContentAsString = new String(charArray);
        fileReader.close();

        return fileContentAsString;
    }
}
