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
    public void setHealth() {
        if (myHealth == null)
            myHealth = new Health(this);
        Scanner sc = new Scanner(System.in);
        System.out.println("How is your pet feeling today?");
        System.out.println("Healthy, Behavioral, or Allergies");
        String health = sc.next();
        sc.close();
        HealthTypes healthType = HealthTypes.valueOf(health);

    }
    /** 
     * public #Object display() {
     *     call a display to this pet's health log
     * }
    **/
}
