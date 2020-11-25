import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Health class, stores and manages a pet's symptoms.
 * 
 * @author melmccord
 * @version 11/14/2020
 */

public class Health implements java.io.Serializable{
    List<Symptom> symptoms;
    
    /**
     * Constructor.
     */
    public Health() {
        symptoms = new ArrayList<Symptom>();
    }
    /**
     * Method for adding a symptom.
     * 
     * @param Symptom the symptom object to add
     */
    public void addSymptom(Symptom s) {
        symptoms.add(s);
    }
    /**
     * Overloaded method that adds a string and creates a symptom object.
     * 
     * @param String for the name of the symptom
     */
    public void addSymptom(String s) {
        addSymptom(new Symptom(s));
    }
    /**
     * Overloaded method that adds a string and creates a symptom object.
     * 
     * @param String for the name of the symptom 
     * @param String for the date in MM-DD-YYYY formatting
     */
    public void addSymptom(String s, String date) {
        addSymptom(new Symptom(s, date));
    }
    /**
     * Returns the list of symptoms stored in this health class.
     * 
     * @return <List>Symptom this health's symptoms
     */
    public List<Symptom> getHealth() {
        return symptoms;
    }
    /**
     * Formatting for all of the symptoms.
     * 
     * @return String formatted list of symptom
     */
    public String allSymptoms() {
        String symptom = "";
        for (Symptom s : symptoms) {
                symptom = symptom + s + "\n";
        }
        return symptom;
    }
    /**
     * To String method.
     * 
     * @return String representation
     */
    @Override
    public String toString() {
       return symptoms.toString();
    }
}

/**
 * Stores the type of symptom, date entered or experienced and the type
 * Used as part of the health main class
 * 
 * @author melmccord
 * @version 11/14/2020
 */
class Symptom implements java.io.Serializable {
    String symptom;
    Date myDate;
    SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
    /**
     * Constructor method
     * 
     * @param String description of symptom
     */
    public Symptom(String symptom) {
        this.symptom = symptom;
        this.myDate = new Date();
    }
    /**
     * Overloaded constructor method.
     * 
     * @param String for the name of the symptom 
     * @param String for the date in MM-DD-YYYY formatting
     */
    public Symptom(String s, String date) {
        this.symptom = s;
        try {
            myDate = format.parse(date);
        } catch (Exception e) {
            myDate = new Date();
        }
    }
    /**
     * To String method.
     * 
     * @return String representation
     */
    @Override
    public String toString() {
        return "Symptom: " + symptom + "\n" + " Date: " + myDate;
    }
}