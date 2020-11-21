import java.io.*;
import java.util.Date;

public class DeserializeDemo {

   public static void main(String [] args) {
      Pet p = null;
      try {
         FileInputStream fileIn = new FileInputStream("/tmp/pet.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         p = (Pet) in.readObject();
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
      System.out.println("Name: " + p.name);
      System.out.println("Type: " + p.type);
      System.out.println("Birthday: " + p.birthday);
   }
}