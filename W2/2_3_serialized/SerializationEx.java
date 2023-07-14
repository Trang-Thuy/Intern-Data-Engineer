import java.io.IOException;

public class SerializationEx {
    public static void main(String []args){
        Student student = new Student();
        student.setName("Hoang");
        student.setAge(11);
        student.setAddress("Ha Noi"); 
        student.setMajor("Computer Science");       

        String fileName = "data.txt";
    try  {
        SerializationUtil.serialize(student, fileName);
        }
    catch(IOException i){
            i.printStackTrace();
        }
    
    System.err.println(student.getAge());
    System.out.println(student.getName());
    System.out.println(student.getAddress());
    System.out.println(student.getMajor());
    }
}
