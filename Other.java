import java.util.Date;
import javax.swing.*;

/**
 * Any other type of pet, extends the pet class.
 *
 * @author rowanholop
 * @version 11.22.2020
 */
public class Other extends Pet implements java.io.Serializable
{
    /**
     * Constructor for objects of class Other
     * 
     * @param name name of the Other
     * @param Date birthday of pet
     */
    public Other(String name, Date birthday)
    {
        super(name, "Other", birthday);
    }
}
