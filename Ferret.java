import java.util.Date;

/**
 * Write a description of class Ferret here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Ferret extends Pet implements java.io.Serializable
{
    /**
     * Constructor for objects of class Ferret
     * 
     * @param name name of the Ferret
     */
    public Ferret(String name, Date birthday)
    {
        super(name, "Ferret", birthday);
    }
}
