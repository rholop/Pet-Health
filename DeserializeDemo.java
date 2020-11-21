import java.io.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class DeserializeDemo {
   List<Pet> petList;
   
   public DeserializeDemo() {
       run();
    }
    
    

   public void run() {
      try {
         FileInputStream fileIn = new FileInputStream("/tmp/pet.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         petList = (ArrayList<Pet>) in.readObject();
         in.close();
         fileIn.close();
      } catch (IOException i) {
         i.printStackTrace();
         return;
      } catch (ClassNotFoundException c) {
         System.out.println("Employee class not found");
         c.printStackTrace();
         return;
      }
      
      System.out.println("Deserialized Pets...");
      for (Pet p : petList) {
          System.out.println(p);
        }
   }
   
}