
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
 
public class ConcurrentHM {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
 
        // Adding elements to the map
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
 
        System.out.println("Map size: " + map.size());      
       
        // Getting values from the map
        int valueA = map.get("A");
        System.out.println("Value of A: " + valueA);

        //forEach()
        map.forEach(1, (key, value) -> System.out.printf("key: %s; value: %s; thread: %s\n", key, value, Thread.currentThread().getName()));
        
        //putIfAbsent() puts new value into map only if no value exist for the given key
        map.putIfAbsent("Nga",12);
        map.putIfAbsent("Na",12);

        // Removing elements from the map
        map.remove("B");

        //getOrDefault()
        System.out.println("getOrDefault result: "+ map.getOrDefault("Nga",4));
        //replaceAll()
        map.replaceAll( (key, value) -> "A".equals(key)? 12: value);
        System.out.println(map.get("A"));

        //search
        Integer result = map.search(1, (key, value) -> {
            System.out.println(Thread.currentThread().getName());
            if ("C".equals(key)) {
                return value;
            }
            return null;
        });

        System.out.println("Result: " + result);
        
        System.out.println("Map size: " + map.size());
        map.forEach(1, (key, value) -> System.out.printf("key: %s; value: %s; thread: %s\n", key, value, Thread.currentThread().getName()));
    }
}