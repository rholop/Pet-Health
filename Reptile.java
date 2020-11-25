import java.util.Date;
import javax.swing.*;

/**
 * Reptile class that extends the pet class.
 *
 * @author rowanholop
 * @version 11.22.2020
 */
public class Reptile extends Pet implements java.io.Serializable
{    
    /**
     * Constructor for objects of class Reptile
     * 
     * @param name name of the Reptile
     * @param Date birthday of pet
     */
    public Reptile(String name, Date birthday)
    {
        super(name, "Reptile", birthday);
        super.icon = new ImageIcon("icons/reptile.jpg");
    }
}
