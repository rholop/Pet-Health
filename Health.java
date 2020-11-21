
/**
 * Write a description of class Health here.
 * Stores a pet's symptoms
 * @author melmccord
 * @version 11/14/2020
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Date;

public class Health implements java.io.Serializable {
    Pet myPet;
    List<Symptom> symptoms;
    public Health(Pet p) {
        myPet = p;
        symptoms = new ArrayList<Symptom>();
    }
   public void addSymptom(String s, String type) {
       symptoms.add(new Symptom(s));
   }
   public void addSymptom(Symptom s) {
    symptoms.add(s);
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
/**
 * Class Symptom
 * Stores the type of symptom, date entered or experienced and the type
 * Used as part of the health main class
 * @author melmccord
 * @version 11/14/2020
 */
class Symptom implements java.io.Serializable {
    String type;
    String symptom;
    Date myDate;
    public Symptom(String symptom) {
        this.type = "General";
        this.symptom = symptom;
        this.myDate = new Date();
    }
    public Symptom(String symptom, String type) {
        this.symptom = symptom;
        myDate = new Date();
    }
    public Symptom(String symptom, String type, Date date) {
        this.symptom = symptom;
        myDate = new Date();
    }
    public Symptom(String s, Date date) {
        this.symptom = s;
        myDate = date;
    }
    public void updateSymptom(String s) {
        this.symptom = s;
    }
    public String toString() {
        return "Symptom: " + symptom + " Date: " + myDate;
    }
}
class UpdateHealthLog implements java.io.Serializable {
    static void updateHealth(Pet p) {
        try {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the type of health problem and then the symptom ");
        String type = sc.next();
        String symptom = sc.next();
        sc.close();
        p.log(type, symptom);
    } catch(NoSuchElementException e) {
            p.log("Healthy", "Okay");
        }
    }
}