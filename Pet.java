import java.util.Date;
import java.util.Scanner;

/**
 * Write a description of interface Pet here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Pet
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
        // log = new #Object();
        // generate an ID?
    }
    
    /**
     * Method to update the pet's log.
     * 
     * @param
     */
    public void log(String date, String time, String healthLogType, String data) {
        // update the Object
    }
    public void updateHealth(String health) {
        if (myHealth == null)
            myHealth = new Health(this);
        myHealth.setHealth(health);
    }

     public String toString() {
         return name + ": " +"Type: " + type + " Birthday " + birthday;
}
public void display() {
        if (myHealth == null)
            System.out.println("Nothing to display");
        else if (myHealth.getHealth() == HealthTypes.Healthy)
            System.out.println(name + " is " + HealthTypes.Healthy);
        else
            System.out.println(name + " is suffering from " + myHealth.getHealth());
}
}
