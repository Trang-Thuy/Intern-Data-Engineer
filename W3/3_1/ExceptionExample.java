import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExceptionExample {
    public static void main(String[] args) {
 
        /* checked exception example */
        try {
            File myObj = new File("data.txt");
            Scanner myReader = new Scanner(myObj);
            System.out.println("No Checked Exception!");
        myReader.close();
            
        }
        catch (FileNotFoundException e) {
        System.out.println("Checked Exception: " + e);
        e.printStackTrace();
        }
           
         /* unchecked exception example */
        try{    
            int a[]=new int[5];    
            // a[18]=30/0;   
            a[1]=30/3; 
            String s = null;
            System.out.println(s.length());
            System.out.println("No UnChecked Exception!");
           }    
        catch(ArithmeticException e)  
              {  
               System.out.println("Unchecked Exception: "+ e);  
              }    
        catch(ArrayIndexOutOfBoundsException e)  
              {  
               System.out.println("Unchecked Exception: " + e);  
              }    
        catch(Exception e)  
              {  
               System.out.println("Exception occurs");  
              }             
           System.out.println("***End Test***");    
        }   
}
