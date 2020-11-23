import java.util.Date;
import javax.swing.*;

/**
 * Write a description of class Dog here.
 *
 * @author rowanholop
 * @version 11.22.2020
 */
public class Dog extends Pet implements java.io.Serializable
{
    /**
     * Constructor for objects of class Dog
     * 
     * @param name name of the Dog
     * @param Date birthday of pet
     */
    public Dog(String name, Date birthday)
    {
        super(name, "Dog", birthday);
        super.icon = new ImageIcon("icons/dog.jpg");
    }
}
