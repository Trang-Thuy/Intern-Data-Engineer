// Import the HashSet class
import java.util.HashSet;



class methodPeople{
    static void listPeople(HashSet<String> x){
        for (String i : x) {
            System.out.println(i);
          }
        System.out.println("Number of items: " + x.size());
    }
    static void checkExist(HashSet<String> x, String y){
       
    if  (x.contains(y)){
        System.out.println("Check item: " + y+ " => Exist");
    }else{
        System.out.println("Check item: " + y+ " => Not Exist");
    }
    }
    
    static void removeItem(HashSet<String> x, String y){
        System.out.println("***Remove 1 Item***");
        x.remove(y);
        System.out.println("Number of items: " + x.size());
    }
    static void removeAllItem(HashSet<String> x){
        System.out.println("***Remove All Items***");
        x.clear();
        System.out.println("Number of items: " + x.size());
    }
    }
public class hashSet {
  public static void main(String[] args) {
    HashSet<String> people = new HashSet<>();
    people.add ("Lan");
    people.add("An");
    people.add("Trung");
    people.add("Binh");
    people.add("Hoang");
    people.add("Yen");
    people.add("Nga");
    methodPeople.listPeople(people);
    methodPeople.checkExist(people,"Ng");
    methodPeople.removeItem(people, "Binh");
    methodPeople.removeAllItem(people);

    
    System.out.println("The hashcode value of the map: " + people.hashCode());
  }
}
