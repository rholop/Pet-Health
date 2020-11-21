import java.util.Date;

/**
 * Write a description of class Other here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Other extends Pet implements java.io.Serializable
{
    String species;
    
    /**
     * Constructor for objects of class Other
     * 
     * @param name name of the Other
     */
    public Other(String name, Date birthday, String species)
    {
        super(name, "Other", birthday);
        this.species = species;
    }
}
