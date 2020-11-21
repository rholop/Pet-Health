import java.io.*;
import java.util.Date;
import java.util.ArrayList;

public class SerializeDemo {

    public SerializeDemo() {
        run();
    }

    public void run() {
        Pet p1 = new Dog("Fido", new Date());
        Pet p2 = new Cat("Polly", new Date());
        Symptom s1 = new Symptom("Bellyache");
        Symptom s2 = new Symptom("Vomitting");
        p1.myHealth.addSymptom(s1);
        p2.myHealth.addSymptom(s2);
        ArrayList<Pet> petList = new ArrayList();
        petList.add(p1);
        petList.add(p2);

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