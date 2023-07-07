import java.io.*;
import java.nio.file.*;

/**
 * Copy one file to another using low level byte streams, one byte at a time.
 * 
 * @author www.codejava.net
 */
public class Readbinary_w1 {
    public static void main(String[] args) {

        String inputFile = "D:\\Downloads\\Session_3_Assignment\\data.txt";
        String outputFile = "D:\\Downloads\\Session_3_Assignment\\outputdata.txt";

        try {
            byte[] allBytes = Files.readAllBytes(Paths.get(inputFile));
            Files.write(Paths.get(outputFile), allBytes);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}