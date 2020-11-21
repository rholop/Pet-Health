import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.FlowLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.awt.Font;
import javax.swing.Icon;

/**
 * Home screen class.
 * 
 * @author rowanholop
 */

public class HomeScreen extends JFrame {
    SerializeDemo sd;
    DeserializeDemo d;
    List<Pet> pets;

    public HomeScreen() {
        sd = new SerializeDemo();
        d = new DeserializeDemo();
        sd.run();
        d.run();
        initUI();
    }

    private void initUI() {
        pets = d.petList;
        System.out.println(pets);

        createLayout(getButtons(pets));

        setTitle("Home Screen");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createLayout(Map<Pet,Icon> icons) {

        var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);
        setLayout(new FlowLayout());

        Iterator iconIterator = icons.entrySet().iterator();
        while (iconIterator.hasNext()) { 
            Map.Entry<Pet, Icon> pair = (Map.Entry)iconIterator.next(); 
            JButton button = new JButton();
            button.setIcon( pair.getValue() );
            button.setFocusPainted( false );
            add( button );
            makeButtonMenus(button, pair.getKey());
        } 
        pack();
    }

    public Map<Pet,Icon> getButtons(List<Pet> pets){

        System.out.println(pets.getClass());
        Map<Pet,Icon> buttons = new LinkedHashMap<Pet,Icon>(pets.size());

        var birdIcon = new ImageIcon("icons/bird.jpg");
        var catIcon = new ImageIcon("icons/cat.jpg");
        var dogIcon = new ImageIcon("icons/dog.jpg");
        var ferretIcon = new ImageIcon("icons/ferret.jpg");
        var fishIcon = new ImageIcon("icons/fish.jpg");
        var otherIcon = new ImageIcon("icons/other.jpg");
        var plusIcon = new ImageIcon("icons/plus.jpg");
        var rabbitIcon = new ImageIcon("icons/rabbit.jpg");
        var repitleIcon = new ImageIcon("icons/reptile.jpg");

        Font plainFont = new Font("Helvetica", Font.PLAIN, 24);

        for (Pet pet: pets) {
            String type = pet.getType();
            String name = pet.getName();
            System.out.println(type + " " + name);
            CompoundIcon icon;
            if (type.equals("Cat")) {
                icon = new CompoundIcon(CompoundIcon.Axis.Y_AXIS,
                    catIcon,
                    new TextIcon(new JButton(), name, plainFont));
            }
            else if (type.equals("Bird")) {
                icon = new CompoundIcon(CompoundIcon.Axis.Y_AXIS,
                    birdIcon,
                    new TextIcon(new JButton(), name, plainFont));
            }
            else if (type.equals("Ferret")) {
                icon = new CompoundIcon(CompoundIcon.Axis.Y_AXIS,
                    ferretIcon,
                    new TextIcon(new JButton(), name, plainFont));
            }
            else if (type.equals("Fish")) {
                icon = new CompoundIcon(CompoundIcon.Axis.Y_AXIS,
                    fishIcon,
                    new TextIcon(new JButton(), name, plainFont));
            }
            else if (type.equals("Other")) {
                icon = new CompoundIcon(CompoundIcon.Axis.Y_AXIS,
                    otherIcon,
                    new TextIcon(new JButton(), name, plainFont));
            }
            else if (type.equals("Rabbit")) {
                icon = new CompoundIcon(CompoundIcon.Axis.Y_AXIS,
                    rabbitIcon,
                    new TextIcon(new JButton(), name, plainFont));
            }
            else if (type.equals("Repitle")) {
                icon = new CompoundIcon(CompoundIcon.Axis.Y_AXIS,
                    repitleIcon,
                    new TextIcon(new JButton(), name, plainFont));
            }
            else {
                icon = new CompoundIcon(CompoundIcon.Axis.Y_AXIS,
                    dogIcon,
                    new TextIcon(new JButton(), name, plainFont));
            }
            buttons.put(pet, icon);
        }

        CompoundIcon icon = new CompoundIcon(CompoundIcon.Axis.Y_AXIS,
                plusIcon,
                new TextIcon(new JButton(), "Add New Pet", plainFont));
        buttons.put(null, icon);

        return buttons;
    }

    public void makeButtonMenus(JButton button, Pet key) {
        if (key == null) {return;}
        Pet currentPet = key;
        JFrame frame = this;
        JPopupMenu mainMenu = new JPopupMenu();
        JMenu childMenu = new JMenu("Options");
        
        childMenu.add(new JMenuItem(new AbstractAction("Option 1") 
                {
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(frame, "Option 1 selected");
                    }
                }));
        childMenu.add(new JMenuItem(new AbstractAction("Option 2") 
                {
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(frame, "Option 2 selected");
                    }
                }));
        JMenu chilMenu = new JMenu("Options");
        
        chilMenu.add(new JMenuItem(new AbstractAction("Option 1") 
                {
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(frame, "Option 1 selected");
                    }
                }));
        chilMenu.add(new JMenuItem(new AbstractAction("Option 2") 
                {
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(frame, "Option 2 selected");
                    }
                }));     
       
        mainMenu.add(childMenu);
        mainMenu.add(chilMenu);
       
        button.addMouseListener(new MouseAdapter() 
            {
                public void mousePressed(MouseEvent e) {
                    mainMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
                var ex = new HomeScreen();
                ex.setVisible(true);
        });
    }
}