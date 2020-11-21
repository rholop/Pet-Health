import java.io.*;
import java.util.Date;
import java.util.ArrayList;

public class DeserializeDemo {

   public static void main(String [] args) {
      ArrayList<Pet> petList = null;
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
      
      System.out.println("Deserialized Pet...");
      for (Pet p : petList) {
          System.out.println(p);
        }
   }
}