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
    Serialize sd;
    Deserialize d;
    List<Pet> pets;
    Map<String,ImageIcon> icons;

    public HomeScreen() {
        sd = new Serialize();
        d = new Deserialize();
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
        if (key == null) {
            /**
            button.addMouseListener(new MouseAdapter() 
            {
                public void mousePressed(MouseEvent e) {
                    mainMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            });**/
            return;
        }
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
            return new JMenuItem(new AbstractAction(title) 
                {
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(frame, health.allSymptoms(), title, JOptionPane.PLAIN_MESSAGE, p.getIcon());
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