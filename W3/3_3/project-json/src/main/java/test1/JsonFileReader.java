package test1;
import test1.*;
import com.google.gson.JsonParser;
import com.google.gson.JsonIOException;
import com.google.gson.stream.JsonReader;
import java.io.StringReader;
import java.io.FileReader;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonFileReader {
    public static void main(String[] args) {
        String filePath = "../dataa2.json";

        try (FileReader fileReader = new FileReader("src/main/resources/dataa.json"); 
        	     BufferedReader reader = new BufferedReader(fileReader)) {
        	    String inputStream = reader.lines()
        	      .collect(Collectors.joining(System.lineSeparator()));

            
            if (inputStream != null) {
                // Create a Gson object
                Gson gson = new Gson();

                // Parse the JSON data into a Person object (assuming you have the Person class)
                Person person = gson.fromJson(new InputStreamReader(inputStream), Person.class);

                // Display the parsed data
                System.out.println("Tên: " + person.getName());
                System.out.println("Tuổi: " + person.getAge());
                System.out.println("Email: " + person.getEmail());
                System.out.println("Học sinh: " + person.isStudent());

                // Close the InputStream
                inputStream.close();
            } else {
                System.err.println("File not found: " + filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
