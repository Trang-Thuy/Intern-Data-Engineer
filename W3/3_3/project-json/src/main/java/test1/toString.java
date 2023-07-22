package test1;
import com.google.gson.Gson;

public class toString {
    public static void main(String[] args) {
        // Create a Person object
        Person person = new Person("John Doe", 30, "johndoe@example.com", true);

        // Create a Gson object
        Gson gson = new Gson();

        // Convert the Person object to a JSON string
        String jsonString = gson.toJson(person);

        // Now you have the JSON string representation of the Person object
        System.out.println(jsonString);
    }
}
