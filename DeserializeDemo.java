import java.io.*;
import java.util.Date;
import java.util.ArrayList;

public class DeserializeDemo {
    ArrayList<Pet> petList;
    
    

   public void main(String [] args) {
      try {
         FileInputStream fileIn = new FileInputStream("/data/pet.ser");
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
