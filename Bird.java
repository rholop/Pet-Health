import java.util.Date;

/**
 * Write a description of class Bird here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bird extends Pet
{
    /**
     * Constructor for objects of class Bird
     * 
     * @param name name of the Bird
     */
    public Bird(String name, Date birthday)
    {
        super(name, "Bird", birthday);
    }
}
