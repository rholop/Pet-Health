import java.util.Date;

/**
 * Write a description of class Dog here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Dog extends Pet
{
    /**
     * Constructor for objects of class Dog
     * 
     * @param name name of the Dog
     */
    public Dog(String name, Date birthday)
    {
        super(name, "Dog", birthday);
    }
}
