
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
import java.util.*;
import java.util.Date;

public class Health {
    Pet myPet;
    List<Symptom> symptoms;
    public Health(Pet p) {
        myPet = p;
        symptoms = new ArrayList<Symptom>();
    }
   public void addSymptom(String s) {
       symptoms.add(new Symptom(s));

   }
    public List<Symptom> getHealth() {
        return symptoms;
    }
   public String toString() {
       return myPet.name + " " + symptoms;
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
    Date myDate;
    public Symptom(String s) {
        this.symptom = s;
        myDate = new Date();
    }
    public Symptom(String s, Date date) {
        this.symptom = s;
        myDate = date;
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
    public String toString() {
        return "Symptom: " + symptom + " Date: " + myDate;
    }
}