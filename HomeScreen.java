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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.awt.Font;
import javax.swing.Icon;
import java.text.ParseException;

/**
 * Home screen class.
 * 
 * @author rowanholop
 */

public class HomeScreen extends JFrame {
    Serialize s;
    Deserialize d;
    List<Pet> pets;
    Map<String,ImageIcon> icons;

    public HomeScreen() {
        s = new Serialize();
        d = new Deserialize();
        d.run();
        pets = d.petList;
        if (d.petList.size() > 0) {
            for (Pet p : pets) {
                s.add(p);
            }
        }
        initUI();
    }

    private void initUI() {
        createLayout(getButtons(pets));

        setTitle("Home Screen");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    s.run();
                }
            });

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
        Font plainFont = new Font("Helvetica", Font.PLAIN, 24);

        for (Pet pet: pets) {
            CompoundIcon icon = new CompoundIcon(CompoundIcon.Axis.Y_AXIS,
                    pet.getIcon(),
                    new TextIcon(new JButton(), pet.getName(), plainFont));
            buttons.put(pet, icon);
        }

        CompoundIcon icon = new CompoundIcon(CompoundIcon.Axis.Y_AXIS,
                new ImageIcon("icons/plus.jpg"),
                new TextIcon(new JButton(), "Add New Pet", plainFont));
        buttons.put(null, icon);

        return buttons;
    }

    public void makeButtonMenus(JButton button, Pet key) {
        JFrame frame = this;

        if (key == null) {
            JTextField name = new JTextField();
            JTextField birthday = new JTextField();
            String[] petOptions = {"Bird", "Cat", "Dog", "Ferret", "Fish", "Rabbit", "Reptile", "Other"};
            JComboBox petDropDown = new JComboBox(petOptions);    
            Object[] fields  = {
                    "Name:", name,
                    "Birthday: (MM-DD-YYYY)", birthday,
                    "Pet type:", petDropDown,
                };
            button.addMouseListener(new MouseAdapter() 
                {
                    public void mousePressed(MouseEvent e) {
                        int option = JOptionPane.showConfirmDialog(null, fields, "Add New Pet", JOptionPane.OK_CANCEL_OPTION);
                        if (option == JOptionPane.OK_OPTION) {
                            System.out.println(name.getText() + birthday.getText() + petDropDown.getSelectedItem());
                            Object selection = petDropDown.getSelectedItem();
                            try {
                                Pet temp = Functions.addPet(selection, name.getText(), birthday.getText());
                                s.add(temp);
                            }
                            catch (Exception p) {
                                 JOptionPane.showMessageDialog(null, "Error", "Invalid input", JOptionPane.PLAIN_MESSAGE);
                            }
                        }
                    }
                });
            return;
        }

        JPopupMenu mainMenu = new JPopupMenu();
        mainMenu.add(makeSubMenu(key, "Current Symptoms"));
        mainMenu.add(menuItem(key, "All Symptoms"));
        mainMenu.add(menuItem(key, "Add New Symptom"));


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
            return new JMenuItem(new AbstractAction(title) 
                {
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(frame, health.allSymptoms(), title, JOptionPane.PLAIN_MESSAGE, p.getIcon());
                    }
                });
        }
        else if (title.equals("Add New Symptom")) {
            JTextField symptom = new JTextField();
            JTextField date = new JTextField(); 
            Object[] fields  = {
                    "Symptom Name:", symptom,
                    "Date: (MM-DD-YYYY or leave blank)", date,
                };
            
            return new JMenuItem(new AbstractAction(title)
                {
                    public void actionPerformed(ActionEvent e) {
                        int option = JOptionPane.showConfirmDialog(null, fields, title, JOptionPane.OK_CANCEL_OPTION);
                        if (option == JOptionPane.OK_OPTION) {
                            if (date.getText().isEmpty()) {
                                p.log(symptom.getText());
                            }
                            else {
                                p.log(date.getText(), symptom.getText());
                            }
                        }
                    }
                });

        }
        else {
            return new JMenuItem(new AbstractAction(title) 
                {
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(frame, p, title, JOptionPane.PLAIN_MESSAGE, p.getIcon());
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
                                JOptionPane.showMessageDialog(frame, s, "Symptom Information", JOptionPane.PLAIN_MESSAGE, p.getIcon());
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