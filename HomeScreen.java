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
    Map<String,ImageIcon> icons;

    public HomeScreen() {
        sd = new SerializeDemo();
        d = new DeserializeDemo();
        sd.run();
        d.run();
        initUI();
    }

    private void initUI() {
        pets = d.petList;

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
        Map<Pet,Icon> buttons = new LinkedHashMap<Pet,Icon>(pets.size());
        icons = new HashMap<String,ImageIcon>();

        var birdIcon = new ImageIcon("icons/bird.jpg");
        icons.put("Bird", birdIcon);
        var catIcon = new ImageIcon("icons/cat.jpg");
        icons.put("Cat", catIcon);
        var dogIcon = new ImageIcon("icons/dog.jpg");
        icons.put("Dog", dogIcon);
        var ferretIcon = new ImageIcon("icons/ferret.jpg");
        icons.put("Ferret", ferretIcon);
        var fishIcon = new ImageIcon("icons/fish.jpg");
        icons.put("Fish", fishIcon);
        var otherIcon = new ImageIcon("icons/other.jpg");
        icons.put("Other", otherIcon);
        var plusIcon = new ImageIcon("icons/plus.jpg");
        var rabbitIcon = new ImageIcon("icons/rabbit.jpg");
        icons.put("Rabbit", rabbitIcon);
        var reptileIcon = new ImageIcon("icons/reptile.jpg");
        icons.put("Reptile", reptileIcon);

        Font plainFont = new Font("Helvetica", Font.PLAIN, 24);

        for (Pet pet: pets) {
            String type = pet.getType();
            String name = pet.getName();

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
            else if (type.equals("Reptile")) {
                icon = new CompoundIcon(CompoundIcon.Axis.Y_AXIS,
                    reptileIcon,
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
        JFrame frame = this;
        JPopupMenu mainMenu = new JPopupMenu();   

        mainMenu.add(makeSubMenu(key, "Current Symptoms"));
        mainMenu.add(menuItem(key, "All Symptoms"));

        button.addMouseListener(new MouseAdapter() 
            {
                public void mousePressed(MouseEvent e) {
                    mainMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            });
    }
    
    public JMenuItem menuItem(Pet p, String title){
        JFrame frame = this;
        if (title.equals("All Symptoms")) {
            Health health = p.myHealth;
            String symptomList = health.allSymptoms();
            return new JMenuItem(new AbstractAction(title) 
                {
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(frame, symptomList, title, JOptionPane.PLAIN_MESSAGE, icons.get(p.getType()));
                    }
                });
        }
        else {
            return new JMenuItem(new AbstractAction(title) 
                {
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(frame, p, title, JOptionPane.PLAIN_MESSAGE, icons.get(p.getType()));
                    }
                });
        }
    }

    public JMenu makeSubMenu(Pet p, String title) {
        JFrame frame = this;
        JMenu childMenu = new JMenu(title);
        if (title.equals("Current Symptoms")) {
            Health health = p.myHealth;
            List<Symptom> symptoms = health.getHealth();
            for (Symptom s : symptoms) {
                childMenu.add(new JMenuItem(new AbstractAction(s.symptom) 
                {
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(frame, s, "Symptom information", JOptionPane.PLAIN_MESSAGE, icons.get(p.getType()));
                    }
                }));
            }
        }
        return childMenu;
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
                var ex = new HomeScreen();
                ex.setVisible(true);
            });
    }
}