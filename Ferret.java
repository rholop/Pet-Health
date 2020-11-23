import java.util.Date;
import javax.swing.*;

/**
 * Write a description of class Ferret here.
 *
 * @author rowanholop
 * @version 11.22.2020
 */
public class Ferret extends Pet implements java.io.Serializable
{
    /**
     * Constructor for objects of class Ferret
     * 
     * @param name name of the Ferret
     * @param Date birthday of pet
     */
    public Ferret(String name, Date birthday)
    {
        super(name, "Ferret", birthday);
        super.icon = new ImageIcon("icons/ferret.jpg");
    }
}
