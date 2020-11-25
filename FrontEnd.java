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
 * Home screen class. Offers a facade client application.
 * 
 * @author rowanholop
 * @version 11.22.2020
 */

public class FrontEnd extends JFrame {
    BackEnd b;
    ImageIcon homeIcon;
    List<JButton> currentButtons;

    /**
     * Constructor method. Creates the BackEnd object, loads the data, and creates home icome, calls the initializing function for the UI.
     */
    public FrontEnd() {
        b = new BackEnd();
        b.loadData();
        currentButtons = new ArrayList<JButton>();
        homeIcon = new ImageIcon("icons/home.jpg");
        initUI();
    }   
    /**
     * Initliazes the user interface.
     */
    private void initUI() {
        getButtons(b.getPets()); // 
        createLayout();
        setIconImage(homeIcon.getImage());

        setTitle("Home Screen");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    b.close();  // Calls the backEnd method to serialize data to a local file.
                }
            });
    }
    /**
     * Creates the layout initially. Sets some defaults for layout.
     */
    private void createLayout() {
        var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);
        setLayout(new FlowLayout());
        placeButtons();
    }
    /**
     * Places button in frame.
     */
    private void placeButtons() {
        for (JButton b : currentButtons) {
            add ( b );
        }
        pack();  // Resizes screen
    }
    /**
     * Refreshes the user interface - specifically the buttons in the layout.
     */
    private void refreshUI() {
        for (JButton b : currentButtons) {
            remove ( b );
        }
        getButtons(b.getPets());
        placeButtons();
    }
    /**
     * Populates/updates the locally stored button objects.
     * 
     * @param List<Pet> list of pets to create/update the buttons.
     */
    private void getButtons(List<Pet> pets){
        currentButtons.clear();  // Clear locally stored buttons
        Map<Pet,Icon> buttons = new LinkedHashMap<Pet,Icon>(pets.size());
        Font plainFont = new Font("Helvetica", Font.PLAIN, 24);  // Formatting

        for (Pet pet: pets) {  // Create the pet specific buttons
            CompoundIcon icon = new CompoundIcon(CompoundIcon.Axis.Y_AXIS,
                    pet.getIcon(),
                    new TextIcon(new JButton(), pet.getName(), plainFont));
            buttons.put(pet, icon);
        }

        // Create the button for adding a pet
        CompoundIcon icon = new CompoundIcon(CompoundIcon.Axis.Y_AXIS,
                new ImageIcon("icons/plus.jpg"),
                new TextIcon(new JButton(), "Add New Pet", plainFont));
        buttons.put(null, icon);
        
        // Call the method to make submenus/clickable actions for each button and add them to the locally stored list
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

    /**
     * Create the menus for buttons.
     * 
     * @param JButton the button to add menus to
     * @param Pet the pet's information for creating menus
     */
    private void makeButtonMenus(JButton button, Pet key) {
        JFrame frame = this;

        /**
         * If the button isn't for a pet, create some information for that specfically (the add new pet button).
         */
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
        mainMenu.add(menuItem(key, "Add New Symptom"));
        mainMenu.add(makeSubMenu(key, "Current Symptoms"));
        mainMenu.add(menuItem(key, "All Symptoms"));
        mainMenu.add(menuItem(key, "Delete Pet"));

        button.addMouseListener(new MouseAdapter() 
            {
                public void mousePressed(MouseEvent e) {
                    mainMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            });
    }
    /**
     * Create JMenuItem Objects to support the makeButton method.
     * 
     * @param Pet for this button's menu
     * @param String title for this menu
     * @return JMenuItem for button
     */
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
    /**
     * Create JMenu Objects to support the makeButton method.
     * 
     * @param Pet for this button's menu
     * @param String title for this menu
     * @return JMenu for button
     */
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
            childMenu.add(new JMenuItem(new AbstractAction("Clear All Symptoms") 
                        {
                            public void actionPerformed(ActionEvent e) {
                                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear all this pet's symptoms?", "Clear All Symptom", JOptionPane.OK_CANCEL_OPTION);
                                if (option == JOptionPane.OK_OPTION) {
                                    b.clearPetHealth(p);
                                }
                                refreshUI();
                            }
                        }));
        }
        return childMenu;
    }

    /**
     * Main method for running program.
     */
    public static void main() {
        EventQueue.invokeLater(() -> {
                var ex = new FrontEnd();
                ex.setVisible(true);
            });
    }
}