import java.io.*;
import java.util.*;

public class Serialize {
    List<Pet> petList;

    public Serialize() {
        petList = new ArrayList();
    }
    
    public void add(Pet p) {
        petList.add(p);
    }

    public void run() {

        try {
            FileOutputStream fileOut =
                new FileOutputStream("/tmp/pet.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(petList);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /tmp/pet.ser");
        } 
        catch (IOException i) {
            i.printStackTrace();
        }
    }

}