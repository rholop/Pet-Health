import java.util.Date;

/**
 * Write a description of class Rabbit here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Rabbit extends Pet implements java.io.Serializable
{
    /**
     * Constructor for objects of class Rabbit
     * 
     * @param name name of the Rabbit
     */
    public Rabbit(String name, Date birthday)
    {
        super(name, "Rabbit", birthday);
    }
}
