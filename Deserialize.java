import java.io.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Deserialize {
   public List<Pet> run() {
      try {
         FileInputStream fileIn = new FileInputStream("/tmp/pet.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         List<Pet> petList = (ArrayList<Pet>) in.readObject();
         in.close();
         fileIn.close();
         return petList;
      } catch (IOException i) {
         return null;
      } catch (ClassNotFoundException c) {
         System.out.println("Pet class not found");
         return null;
      }
   }
}