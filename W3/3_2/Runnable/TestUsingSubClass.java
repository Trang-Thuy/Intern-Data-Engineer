public class TestUsingSubClass extends Thread{
    public void run(){
        System.out.println("Run method executed by child");
    }
    public static void main(String [] args){
        TestUsingSubClass  t  = new TestUsingSubClass ();
        t.start();
        System.out.println("Main method executed by main");
    }
    
}
