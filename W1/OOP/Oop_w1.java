abstract class Object {
    Integer age;

    public Integer getYob() {
        Integer Yob = 2023 - age;
        return Yob;
    }

    abstract void PersonIntro();
}

class Person extends Object {

    String name;

    public Person(String x, Integer y) {
        age = y;
        name = x;

    }

    public void sayHi() {
        System.out.println("Hello");
    }

    public void EditInfor(String newName, Integer newAge) {
        name = newName;
        age = newAge;
        System.out.println("--Information changed!--");
    }

    public void PersonIntro() {
        System.out.println(name + " was born in " + getYob());
    }

}

class Student extends Person {
    String major;

    public Student(String x, Integer y, String a) {
        super(x, y);
        major = a;
    }

    public void PersonIntro() {
        System.out.println(name + " was born in " + getYob() + ". He/She is a student");
    }
}

class Teacher extends Person {
    String qualifi;

    public Teacher(String x, Integer y, String a, String b) {
        super(x, y);
        qualifi = b;
    }

    @Override
    public void PersonIntro() {
        System.out.println(
                name + " was born in " + getYob() + ". He/She is a " + qualifi);
    }
}

public class Oop_w1 {

    public static void main(String[] args) {
        Student stu = new Student("John", 12, "Computer Science");
        stu.PersonIntro();
        stu.EditInfor("Alice", 11);
        stu.PersonIntro();

        Teacher tch = new Teacher("Alice", 50, "Marketing", "Professional");
        tch.PersonIntro();
        tch.EditInfor("Bob", 60);
        tch.PersonIntro();
        tch.sayHi();
    }
}
