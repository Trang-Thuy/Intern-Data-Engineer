import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    transient private String address;
    static String major;
    private Integer pw;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getAge(){
        return age;
    }
    public void setAge (Integer age){
        this.age = age;
    }
    
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getMajor(){
        return major;
    }
    public void setMajor (String major){
        Student.major = major;
    }
    public Integer getPw(){
        return pw;
    }
    public void setPw(Integer pw){
        this.pw = pw;
    }
}
