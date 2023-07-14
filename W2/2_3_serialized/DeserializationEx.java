import java.io.IOException;

public class DeserializationEx {
    
    public static void main(String [] args){
        Student student = null;
        String fileName = "data.txt";
    
    try {
        student = (Student) SerializationUtil.deserialize(fileName);
    }
    catch(IOException i){
        i.printStackTrace();
    }
    catch(ClassNotFoundException e){
        e.printStackTrace();
    }

    System.err.println(student.getAge());
    System.out.println(student.getName());
    System.out.println(student.getAddress());
    System.out.println(student.getMajor());
    
    }
}
