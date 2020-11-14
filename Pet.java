import java.util.Date;

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
    
    /** 
     * public #Object display() {
     *     call a display to this pet's health log
     * }
    **/
}
