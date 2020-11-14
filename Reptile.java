import java.util.Date;

/**
 * Write a description of class Reptile here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Reptile extends Pet
{
    String species;
    
    /**
     * Constructor for objects of class Reptile
     * 
     * @param name name of the Reptile
     */
    public Reptile(String name, Date birthday, String species)
    {
        super(name, "Reptile", birthday);
        this.species = species;
    }
}
