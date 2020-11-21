import java.util.Date;
import java.util.Scanner;

/**
 * Write a description of interface Pet here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
enum TypesOfPets {
    Bird,
    Cat,
    Dog,
    Ferret,
    Fish,
    Rabbit,
    Reptile
}
public abstract class Pet implements java.io.Serializable
{
    String name;
    String type;
    Date birthday;
    // #Object for healthlog log;
    Health myHealth;
    /**
     * Constructor for objects of class Pet
     * 
     * @param name string with pet's name
     * @param type string of pet's type
     */
    public Pet(String name, String type, Date birthday) {
        this.name = name;
        this.type = type;
        this.birthday = birthday;
        this.myHealth = new Health(this);
        // log = new #Object();
        // generate an ID?
    }
    
    /**
     * Method to update the pet's log.
     * 
     * @param
     */
    public void log(String date, String time, String healthLogType, String data) {
        Date myDate = new Date(date);
        Symptom s = new Symptom(data, healthLogType, myDate);
        this.myHealth.addSymptom(s);
    }
    public void log(String healthLogType, String data) {
        Symptom s = new Symptom(data, healthLogType);
        this.myHealth.addSymptom(s);
    }
    public void clearHealth() {
        myHealth = new Health(this);
    }
    public void updateHealth(String s) {
        this.myHealth.addSymptom(s);
    }
    public String toString() {
         return name + ": " +"Type: " + type + " Birthday " + birthday;
    }
    public void display() {
        if (myHealth.symptoms.isEmpty())
            System.out.println(name + " profile " + " has no recorded data.");
        else
            System.out.println(myHealth);
    }
    public String getType() {
        return type;
    }
    public String getName() {
        return name;
    }
}
