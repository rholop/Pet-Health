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

public class Health implements java.io.Serializable{
    List<Symptom> symptoms;
    public Health() {
        symptoms = new ArrayList<Symptom>();
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
    public String allSymptoms() {
        String symptom = "";
        for (Symptom s : symptoms) {
                symptom = symptom + s + "\n";
        }
        return symptom;
    }
    public String toString() {
       return symptoms.toString();
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
    String symptom;
    Date myDate;
    public Symptom(String symptom) {
        this.symptom = symptom;
        this.myDate = new Date();
    }
    public Symptom(String s, Date date) {
        this.symptom = s;
        myDate = date;
    }
    public void updateSymptom(String s) {
        this.symptom = s;
    }
    public String toString() {
        return "Symptom: " + symptom + "\n" + " Date: " + myDate;
    }
}