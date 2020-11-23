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
import java.awt.Image;
import java.awt.Component;
import javax.imageio.ImageIO;
import java.util.*;
import java.io.*;
import java.awt.Font;
import javax.swing.Icon;
import java.text.ParseException;

/**
 * Home screen class.
 * 
 * @author rowanholop
 */

public class HomeScreen extends JFrame {
    BackEnd b;
    ImageIcon homeIcon;
    List<JButton> currentButtons;

    public HomeScreen() {
        b = new BackEnd();
        b.loadData();
        currentButtons = new ArrayList<JButton>();
        homeIcon = new ImageIcon("icons/home.jpg");
        initUI();
    }

    private void initUI() {
        updateButtons(b.getPets());
        createLayout();
        setIconImage(homeIcon.getImage());

        setTitle("Home Screen");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    b.close();
                }
            });
    }
    private void createLayout() {
        var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);
        setLayout(new FlowLayout());
        placeButtons();
    }
    private void placeButtons() {
        for (JButton b : currentButtons) {
            add ( b );
        }
        pack();
    }
    private void refreshUI() {
        for (JButton b : currentButtons) {
            remove ( b );
        }
        updateButtons(b.getPets());
        placeButtons();
    }
    private void updateButtons(List<Pet> pets){
        currentButtons.clear();
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
        
        Iterator iconIterator = buttons.entrySet().iterator();
        while (iconIterator.hasNext()) { 
            Map.Entry<Pet, Icon> pair = (Map.Entry)iconIterator.next(); 
            JButton button = new JButton();
            button.setIcon( pair.getValue() );
            button.setFocusPainted( false );
            makeButtonMenus(button, pair.getKey());
            currentButtons.add(button);
        } 
    }

    private void makeButtonMenus(JButton button, Pet key) {
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
                            Object selection = petDropDown.getSelectedItem();
                            boolean success = b.addPet(selection, name.getText(), birthday.getText());
                            if (!success) {
                                JOptionPane.showMessageDialog(null, "Error", "Invalid input", JOptionPane.PLAIN_MESSAGE);
                            }
                        }
                        refreshUI();
                    }
                });
            return;
        }

        JPopupMenu mainMenu = new JPopupMenu();
        mainMenu.add(makeSubMenu(key, "Current Symptoms"));
        mainMenu.add(menuItem(key, "All Symptoms"));
        mainMenu.add(menuItem(key, "Add New Symptom"));
        mainMenu.add(menuItem(key, "Delete Pet"));

        button.addMouseListener(new MouseAdapter() 
            {
                public void mousePressed(MouseEvent e) {
                    mainMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            });
    }

    private JMenuItem menuItem(Pet p, String title){
        JFrame frame = this;
        if (title.equals("All Symptoms")) {
            Health health = p.myHealth;
            return new JMenuItem(new AbstractAction(title) 
                {
                    public void actionPerformed(ActionEvent e) {
                        if (health.allSymptoms().isEmpty()) {
                            String noSymptoms = "No symptoms logged for " + p.getName();
                            JOptionPane.showMessageDialog(frame, noSymptoms, title, JOptionPane.PLAIN_MESSAGE, homeIcon);
                        }
                        JOptionPane.showMessageDialog(frame, health.allSymptoms(), title, JOptionPane.PLAIN_MESSAGE, homeIcon);
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
                                b.updatePetHealth(p, symptom.getText());
                            }
                            else {
                                b.updatePetHealth(p, date.getText(), symptom.getText());
                            }
                        }
                        refreshUI();
                    }
                });

        }
        else {
            return new JMenuItem(new AbstractAction(title) 
                {
                    public void actionPerformed(ActionEvent e) {
                        int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this pet?", title, JOptionPane.OK_CANCEL_OPTION);
                        if (option == JOptionPane.OK_OPTION) {
                            b.deletePet(p);
                        }
                        refreshUI();
                    }
                });
        }
    }

    private JMenu makeSubMenu(Pet p, String title) {
        JFrame frame = this;
        JMenu childMenu = new JMenu(title);
        if (title.equals("Current Symptoms")) {
            for (Symptom s : p.getHealth().getHealth()) {
                childMenu.add(new JMenuItem(new AbstractAction(s.symptom) 
                        {
                            public void actionPerformed(ActionEvent e) {
                                JOptionPane.showMessageDialog(frame, s, "Symptom Information", JOptionPane.PLAIN_MESSAGE, homeIcon);
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