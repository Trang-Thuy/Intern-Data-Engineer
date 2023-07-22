package test1;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class StringtoObject {
    public static void main(String[] args) {
        // Sample JSON array containing multiple Person objects
        String jsonArray = "[{\"name\":\"John Doe\",\"age\":30,\"email\":\"johndoe@example.com\"}, " +
                          "{\"name\":\"Alice Smith\",\"age\":25,\"email\":\"alice@example.com\"}, " +
                          "{\"name\":\"Bob Johnson\",\"age\":28,\"email\":\"bob@example.com\"}]";

        // Create a Gson object
        Gson gson = new Gson();

        // Define the type of the list of Person objects using TypeToken
        Type personListType = new TypeToken<List<Person>>() {}.getType();

        // Parse the JSON array into a list of Person objects
        List<Person> personList = gson.fromJson(jsonArray, personListType);

        // Now you can work with the list of Person objects
        for (Person person : personList) {
            System.out.println("Name: " + person.getName());
            System.out.println("Age: " + person.getAge());
            System.out.println("Email: " + person.getEmail());
            System.out.println("----------------------");
        }
    
        for (Person person : personList) {

            // Create a Gson object
            Gson gson1 = new Gson();

            // Convert the Person object to a JSON string
            String jsonString = gson1.toJson(person);

            // Now you have the JSON string representation of the Person object
            System.out.println(jsonString);
        
    
    }
}
}
