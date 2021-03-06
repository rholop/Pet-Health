import java.util.Date;
import javax.swing.*;

/**
 * Cat class that extends the pet class.
 *
 * @author rowanholop
 * @version 11.22.2020
 */
public class Cat extends Pet implements java.io.Serializable
{
    /**
     * Constructor for objects of class Cat
     * 
     * @param name name of the Cat
     * @param Date birthday of pet
     */
    public Cat(String name, Date birthday)
    {
        super(name, "Cat", birthday);
        super.icon = new ImageIcon("icons/cat.jpg");
    }
}
