import java.io.*;
import java.util.Date;

public class SerializeDemo {

   public static void main(String [] args) {
      Pet p = new Dog("Fido", new Date());
      
      try {
         FileOutputStream fileOut =
         new FileOutputStream("/tmp/pet.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(p);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved in /tmp/pet.ser");
      } catch (IOException i) {
         i.printStackTrace();
      }
   }
}