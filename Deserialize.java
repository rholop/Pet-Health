import java.io.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Deserialize {
   List<Pet> petList;
   boolean prevData;
   
   public Deserialize() {
       prevData = false;
    }
   
   public List<Pet> run() {
      try {
         FileInputStream fileIn = new FileInputStream("/tmp/pet.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         petList = (ArrayList<Pet>) in.readObject();
         in.close();
         fileIn.close();
         prevData = true;
         return petList;
      } catch (IOException i) {
         return null;
      } catch (ClassNotFoundException c) {
         System.out.println("Pet class not found");
         return null;
      }
   }
   
   public List<Pet> getList() {
       if (prevData == false) {
           return null;
        }
       return petList;
   }
}