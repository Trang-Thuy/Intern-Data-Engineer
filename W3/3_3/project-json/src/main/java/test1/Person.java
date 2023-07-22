package test1;
import com.google.gson.annotations.SerializedName;

public class Person {
    private String name;
    private int age;
    private String email;
    @SerializedName("is_student")
    private boolean isStudent;
//    private Address address;
//    private String[] hobbies;

    public Person(String name, int age, String email, boolean isStudent) {
    	this.name = name;
    	this.age = age;
    	this.email = email;
    	this.isStudent = isStudent;
    }
    public String getName() {
    	return name;
    }
    public void setName(String name) {
    	this.name = name;
    }
    public  int getAge() {
    	return age;
    }
    public void setAge(int age) {
    	this.age = age;
    }
    
   public String getEmail() {
    	return email;
    }
    public void setEmail(String email) {
    	this.email = email;
    }
    public boolean isStudent() {
    	return isStudent;
    }
    public void setisStudent(boolean isStudent) {
    	this.isStudent = isStudent;
    }
    
    
}

