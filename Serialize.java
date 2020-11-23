import java.io.*;
import java.util.*;

public class Serialize {

    public void run(List<Pet> petList) {
        try {
            FileOutputStream fileOut =
                new FileOutputStream("/tmp/pet.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(petList);
            out.close();
            fileOut.close();
        } 
        catch (IOException i) {
            i.printStackTrace();
        }
    }

}