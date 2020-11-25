import java.util.Date;
import javax.swing.*;

/**
 * Fish class that extends the pet class.
 *
 * @author rowanholop
 * @version 11.22.2020
 */
public class Fish extends Pet implements java.io.Serializable
{
    /**
     * Constructor for objects of class Fish
     * 
     * @param name name of the Fish
     * @param Date birthday of pet
     */
    public Fish(String name, Date birthday)
    {
        super(name, "Fish", birthday);
        super.icon = new ImageIcon("icons/fish.jpg");
    }
}
