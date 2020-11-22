import java.util.Date;
import javax.swing.*;

/**
 * Write a description of class Cat here.
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
     */
    public Cat(String name, Date birthday)
    {
        super(name, "Cat", birthday);
        super.icon = new ImageIcon("icons/cat.jpg");
    }
}
