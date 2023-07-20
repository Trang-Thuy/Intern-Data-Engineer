import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
class Geeks{
    public void m1(){
        System.out.println("Hello ");
    }
}
public class TestUsingRunnable extends Geeks implements Runnable {
    public void run(){

        
        
        System.out.println("Run method executed by child thread. Thread name: " + Thread.currentThread().getName());
        //do not throw checked exception. Runnable must handle checked exception itself
        
        try {
            File myObj = new File("data.txt");
            Scanner myReader = new Scanner(myObj);
            System.out.println("No Checked Exception!");
            myReader.close();
        // throw new FileNotFoundException();
        }
        catch (FileNotFoundException e) {
            System.out.println("Checked Exception: " + e);
            e.printStackTrace();
        }

        //throw unchecked exception 
        try{    
            int a[]=new int[5];    
            //  a[18]=30/1;   
            a[1]=30/3; 
            // String s = null;
            // System.out.println(s.length());
            System.out.println("No UnChecked Exception!");
           }    
        catch(ArithmeticException e)  
              {  
               System.out.println("Unchecked Exception 1: "+ e);  
              }    
        catch(ArrayIndexOutOfBoundsException e)  
              {  
               System.out.println("Unchecked Exception 2: " + e);  
              }    
        catch(Exception e)  
              {  
               System.out.println("Exception occurs");  
              }             
           System.out.println("***End Test***");  
           
           
    // int i = 100/0;
    }

    
    
    public static void main(String [] args){
        TestUsingRunnable t = new TestUsingRunnable();
        t.m1();
        System.out.println("Main method executed by main thread. Thread name: " + Thread.currentThread().getName());
        Thread t1 = new Thread(t);
        t1.start();
        
        
    }
}


