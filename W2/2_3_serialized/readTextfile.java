import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.IOException;

public class readTextfile {
  public static void main(String[] args) {
    try {
      String fileName = "data1.txt";
      String fileName1 = "data.txt";
      File myObj = new File(fileName);
      Scanner myReader = new Scanner(myObj);
    
        while (myReader.hasNextLine()) {
          String data = myReader.nextLine();
        try  {
            SerializationUtil.serialize(data, fileName1);
        }
        catch(IOException i){
            i.printStackTrace();
        }

        System.out.println(data);
        }
      myReader.close();
    } 
    catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}