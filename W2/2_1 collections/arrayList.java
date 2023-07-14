import java.util.ArrayList;
import java.util.Collections;

class methodCar {
    static void getCar(ArrayList<String> x) {
        for (Integer i = 0; i < x.size(); i++) {
            System.out.println(x.get(i));
        }
        System.out.println("There are " + x.size() + " items");
    }

    static void addCar(ArrayList<String> x, String y) {
        System.out.println("***Add item***");
        x.add(y);
        for (Integer i = 0; i < x.size(); i++) {
            System.out.println(x.get(i));
        }
        System.out.println("There are " + x.size() + " items");
    }

    static void changeCar(ArrayList<String> x, Integer y, String z) {
        System.out.println("***Change Item***");
        x.set(y, z);
        for (Integer i = 0; i < x.size(); i++) {
            System.out.println(x.get(i));
        }
        System.out.println("There are " + x.size() + " items");
    }
    static void removeCar (ArrayList<String> x, Integer y){
    System.out.println("***Remove Item*");
    x.remove(y.intValue());
    for (Integer i = 0; i < x.size(); i++){
    System.out.println(x.get(i));
    }
    }

    static void removeAllCar(ArrayList<String> x) {
        System.out.println("**Remove all Items***");
        x.clear();
        for (Integer i = 0; i < x.size(); i++) {
            System.out.println(x.get(i));
        }
        System.out.println("There are " + x.size() + " items");
    }

}

public class arrayList {
    public static void main(String[] args) {
        ArrayList<String> cars = new ArrayList<String>();
        cars.add("Volvo");
        cars.add("BMW");
        cars.add("Ford");
        cars.add("Mazda");
        System.out.println(cars);
        methodCar.getCar(cars);
        methodCar.addCar(cars, "Opel");
        
        Collections.sort(cars);
        for (String i : cars) {
            System.out.println(i);
          }
        methodCar.changeCar(cars, 2, "Honda");
        System.out.println("***Remove 1 Item***");
        methodCar.removeCar(cars, 0);
       cars.remove(0);
        System.out.println("There are " + cars.size() + " items");
        // 
        methodCar.removeAllCar(cars);
    if (cars.isEmpty()){
        System.out.println("true");
    }
    else{
        System.out.println("False");
    }
    }

}