import java.util.Date;
import java.util.Scanner;
import javax.swing.*;

/**
 * Write a description of interface Pet here.
 *
 * @author rowanholop
 * @author melaniemccord
 * @version 11.22.2030
 */
enum TypesOfPets {
    Bird,
    Cat,
    Dog,
    Ferret,
    Fish,
    Rabbit,
    Reptile,
    Other
}
public abstract class Pet implements java.io.Serializable
{
    ImageIcon icon;
    String name;
    String type;
    Date birthday;
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
        this.myHealth = new Health();
    }
    /**
     * Method to update the pet's health.
     * 
     * @param date string that represents the date
     * @param data string that represents the symptom
     */
    public void log(String date, String data) {
        Date myDate = new Date(date);
        this.myHealth.addSymptom(new Symptom(data, myDate));
    }
    /**
     * Overloaded to update the pet's health.
     * 
     * @param data string that represents the symptom
     */
    public void log(String data) {
        this.myHealth.addSymptom(new Symptom(data));
    }
    /**
     * Reset the pet's health.
     */
    public void clearHealth() {
        myHealth = new Health();
    }
    /**
     * To String method.
     * 
     * @return String
     */
    public String toString() {
         return name + ": " +"Type: " + type + " Birthday " + birthday;
    }
    /**
     * Display the pet's information and health to the console.
     */
    public void display() {
        if (myHealth.symptoms.isEmpty())
            System.out.println(name + " profile " + " has no recorded data.");
        else
            System.out.println(myHealth);
    }
    /**
     * Getter for pet's type.
     * 
     * @return type string representing pet's type
     */
    public String getType() {
        return type;
    }
    /**
     * Getter for pet's name.
     * 
     * @return type string representing pet's name
     */
    public String getName() {
        return name;
    }
    /**
     * Getter for pet's icon.
     * 
     * @return ImageIcon icon for this specific pet
     */
    public ImageIcon getIcon() {
        return icon;
    }
    /**
     * Getter for pet's pet.
     * 
     * @return Health for this specific pet
     */
    public Health getHealth() {
        return myHealth;
    }
}