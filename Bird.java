import java.util.Date;
import javax.swing.*;

/**
 * Write a description of class Bird here.
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
     */
    public Bird(String name, Date birthday)
    {
        super(name, "Bird", birthday);
        super.icon = new ImageIcon("icons/bird.jpg");
    }
}
