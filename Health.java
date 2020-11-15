
/**
 * Write a description of class Health here.
 *
 * @author melmccord
 * @version 11/14/2020
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Scanner;

enum HealthTypes {
    Allergies,
    Behavioral,
    Eating,
    Healthy
}
public class Health {
    Pet myPet;
    HealthTypes myHealth;

    public Health(Pet p) {
        myPet = p;
    }
   public void setHealth(String s) {
        myPet.updateHealth(s);
        if (HealthTypes.valueOf(s) != HealthTypes.Healthy)
            System.out.println(myPet.name + " is suffering from " + s);
        else
            System.out.println(myPet.name + " is healthy");
        myHealth = HealthTypes.valueOf(s);
   }
    public HealthTypes getHealth() {
        return myHealth;
    }
}
class BehavioralHealth extends Health {
    private Pet p;
    private List<Symptom> symptoms;
    public BehavioralHealth(Pet p) {
        super(p);
    }
    public void setHealth(String s) {
        symptoms.add(new Symptom(s));
    }
    public List<Symptom> getSymptoms() {
        return symptoms;
    }
}
class Symptom {
    String symptom;
    public Symptom(String s) {
        this.symptom = s;
    }
    public void updateSymptom(String s) {
        this.symptom = s;
    }
    public void writeFile(String filename) throws IOException {
        File file = new File(System.getProperty("user.dir") + "\\data\\"+ filename);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(symptom + ",");
        bw.close();
    }
}
class Main {
    public static void main(String[] args) {
        Pet p = new Ferret("Mallory", new Date());
        p.display();
        p.updateHealth("Behavioral");
    }
}