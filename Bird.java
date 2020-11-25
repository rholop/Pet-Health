import java.util.Date;
import javax.swing.*;

/**
 * Bird class that extends the pet class.
 *
 * @author rowanholop
 * @version 11.22.2020
 */
public class Bird extends Pet implements java.io.Serializable
{
    /**
     * Constructor for objects of class Bird
     * 
     * @param name name of the Bird
     * @param Date birthday of pet
     */
    public Bird(String name, Date birthday)
    {
        super(name, "Bird", birthday);
        super.icon = new ImageIcon("icons/bird.jpg");
    }
}
