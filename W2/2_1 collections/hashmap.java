import java.util.HashMap;

public class hashmap {

    public static void main(String[] args) {

        HashMap<String, String> capitalCities = new HashMap<>();
        capitalCities.put("England", "London");
        capitalCities.put("Germany", "Berlin");
        capitalCities.put("Norway", "Oslo");
        capitalCities.put("USA", "Washington DC");
        for (String i : capitalCities.keySet()) {
            System.out.println(i + " - " + capitalCities.get(i));
        }

        System.out.println("***Remove item***");
        capitalCities.remove("USA");
        for (String i : capitalCities.keySet()) {
            System.out.println(i + " - " + capitalCities.get(i));
        }
        System.out.println("***Add item***");
        capitalCities.put("VietNam", "HaNoi");
        for (String i : capitalCities.keySet()) {
            System.out.println(i + " - " + capitalCities.get(i));
        }
        System.out.println(capitalCities.keySet());
        System.out.println("***HashMap Size***");
        System.out.println("There are " + capitalCities.size() + " items in HashMap");

        System.out.println("***Remove all items***");
        capitalCities.clear();
        System.out.println("There are " + capitalCities.size() + " items in HashMap");

    }
}
