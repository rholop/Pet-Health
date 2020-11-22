import java.util.Date;
import javax.swing.*;

/**
 * Write a description of class Rabbit here.
 *
 * @author rowanholop
 * @version 11.22.2020
 */
public class Rabbit extends Pet implements java.io.Serializable
{
    /**
     * Constructor for objects of class Rabbit
     * 
     * @param name name of the Rabbit
     */
    public Rabbit(String name, Date birthday)
    {
        super(name, "Rabbit", birthday);
        super.icon = new ImageIcon("icons/rabbit.jpg");
    }
}
