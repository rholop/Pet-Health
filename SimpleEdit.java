import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenu;
import java.awt.Cursor;
import java.awt.BorderLayout;
import java.util.Hashtable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import java.awt.Toolkit;

public class SimpleEdit extends javax.swing.JFrame
{
    // Used to set the number for new untitled windows
    private static int newWindows = -1;

    // Used to track all of the currently installed plugins
    private static Hashtable plugins = null;
    
    // The initial plugin configuration
    private static String[] argsconfig;
    
    /** Creates a new instance of SimpleEdit */
    public SimpleEdit(  )
    {
        init(  );
    }

/* --------------------- Application APIs ------------------------------------ */

    /** Used by tools to get the text actual text area.
     * This wouldn't generally be recommended, but in this
     * case it's ok.
     *
     * In general, you'd want to use something to make the
     * interface more opaque (thereby freeing up options to
     * switch to a different underlying toolkit), but in this
     * case it would cost readability (since everyone can look
     * up a JTextArea).
     */
    public javax.swing.JTextArea getJTextArea(  )
    {
        return this.mainTextArea;
    }
    
    /** Used by tools to get the current text */
    public String getDocumentText(  )
    {
        return this.mainTextArea.getText(  );
    }
    
    /** Used by tools to set the current text */
    public void setDocumentText(String in)
    {
        mainTextArea.setText(in);
        mainTextArea.setCaretPosition(mainTextArea.getText().length(  ));
        mainTextArea.requestFocus(  );
    }
    
    /** Used by tools to add to the current text */
    public void appendDocumentText(String in)
    {
        setDocumentText(mainTextArea.getText(  ) + in);
    }
    
    /** Used by tools to set the status text at the bottom
     * of a frame.
     */
    public void setStatusText(String in)
    {
        this.mainStatusText.setText(in);
    }

/* --------------------- Initialization ------------------------------------ */

    // Sets up and creates a "pristine" window environment
    private void init(  )
    {
        if(newWindows++ < 0)
            setTitle("Untitled");
        else
            setTitle("Untitled-" + newWindows);
                
        initPlugins(  );
        initComponents(  );
        initMenuBar(  );
    }

/* --------------------- Initialization: Plugins  ---------------------------- */
    
    // Installs all plugins as currently defined by the
    // private argsconfig.
    private void initPlugins(  )
    {
        if(plugins != null)
            return;
        if(argsconfig == null)
            return;
        if(argsconfig.length == 0)
            return;
        plugins = new Hashtable(  );
        
        for(int i = 0; i < argsconfig.length; i++)
        {
            // This may very well fail, as we are going
            // to be loading classes by name, which is
            // prone to errors (e.g. typos, etc.)
            try
            {
                // This requests the classloader to find a
                // given class by name.  We are using this to
                // implement a plugin architecture, based on
                // expecting classes to implement a specific
                // interface (SimpleEditPlugin).  If the class
                // can be loaded and cast without failure,
                // we are good to go.
                Class myClass = Class.forName(argsconfig[i]);
                SimpleEditPlugin myPlugin = 
                 (SimpleEditPlugin)myClass.getConstructor(null).newInstance(null);
                
                // Don't add the plugin if already installed. Allows for 
                // eventual support for dynamically adding new plugins later.  
                // Calls the Plugin init if this is the first time 
                // it's being loaded.
                if(plugins.containsKey(myPlugin.getAction(  )))
                {
                    return;
                } else
                {
                    myPlugin.init(this);
                }
                
                // If we made it this far, the plugin has been loaded
                // and initialized, so it's ok to add to the list of
                // valid plugins.
                plugins.put(myPlugin.getAction(  ), myPlugin);
            }
            catch(Exception e)
            {
                // This is not really adequate for a quality client 
                // application, but it's acceptable for our purposes.
                System.out.println("Couldn't load Plugin: " + argsconfig[i]);
                System.out.println(e.getMessage(  ));
                e.printStackTrace(  );
            }
        }
    }

/* --------------------- Initialization: GUI Components---------------------- */

    // The main visual components
    private javax.swing.JScrollPane mainScrollPane = new javax.swing.JScrollPane(  );
    private javax.swing.JTextArea mainTextArea = new javax.swing.JTextArea(  );
    private javax.swing.JToolBar mainToolBar = new javax.swing.JToolBar(  );
    private javax.swing.JTextField mainStatusText= new javax.swing.JTextField(  );
    
    private void initComponents(  )
    {
        this.getContentPane(  ).setBackground(java.awt.Color.white);
        this.getContentPane().setLayout(new BorderLayout(  ));
        this.getContentPane(  ).add(mainScrollPane, BorderLayout.CENTER);
        this.getContentPane(  ).add(mainToolBar, BorderLayout.NORTH);
        this.getContentPane(  ).add(mainStatusText, BorderLayout.SOUTH);
        
        // This text field serves two purposes. It provides useful information
        // to the user, and also serves as a graceful "bump" for the Mac OS
        // grow box on the Mac OS platform.
        mainStatusText.setText("Ready.");
           
        mainStatusText.setCursor(
             Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        
        mainScrollPane.setViewportView(mainTextArea);
        
        mainTextArea.setEditable(true);
        mainTextArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        mainTextArea.setFont(
            new java.awt.Font("serif", java.awt.Font.PLAIN, 12)
            );

        // Perhaps a tool might be added later to control this dynamically?
        mainTextArea.setLineWrap(true);
        
        // Generally looks terrible on all platforms, and requires
        // a fair amount of work to get it to work right.
        mainToolBar.setFloatable(false);
        initToolBar(mainToolBar, this);
        
        // Determine the offset value and stagger new windows
        // (with a reset every ten windows). A somewhat
        // unscientific mechanism, but it works well enough.
        int top_offset = 0;
        if((newWindows % 10) > 0)
        {
            top_offset =((this.newWindows) % 10) * 20 + 20;
            
            this.setLocation(
                 new Double(getLocation().getX() + top_offset - 20).intValue(  ),
                 new Double(getLocation().getY() + top_offset).intValue(  )
            );
        }
        int bottom_offset = 0;
        if (top_offset > 0)
            bottom_offset = top_offset - 20;
        
        // In a later chapter, we can use the JDirect and the
        // Carbon API GetAvailableWindowPositioningBounds(  )
        // to properly position this.
        java.awt.Dimension screensize =
             java.awt.Toolkit.getDefaultToolkit().getScreenSize(  );
        screensize = 
             new java.awt.Dimension(640, screensize.height -128 - bottom_offset);
        this.setSize(screensize);
    }

    // Default items that always appear on the toolbar.
    // null items are treated as separators.
    String[] toolbarItems = {"New", "Open", null, "Timestamp"};

    private void initToolBar(javax.swing.JToolBar myToolBar, SimpleEdit myFrame)
    {
        JButton newButton;
        for(int i = 0; i < toolbarItems.length; i++)
        {
            if(toolbarItems[i] != null)
            {
                // It would be nice to provide icons
                // instead of just text labels.
                newButton = new JButton(toolbarItems[i]);
                
                // Used to track the targets more easily
                newButton.putClientProperty("window", myFrame);
                newButton.addActionListener(actionListenerHandler);
                myToolBar.add(newButton);
            } else
            {
                myToolBar.add(new javax.swing.JToolBar.Separator(  ));
            }
        }
        
        // Load all plugins into the toolbar
        if(plugins != null)
            if(plugins.size(  ) > 0)
            {
                java.util.Enumeration e = plugins.elements(  );
                SimpleEditPlugin currentPlugin;
                while(e.hasMoreElements(  ))
                {
                    currentPlugin = (SimpleEditPlugin)e.nextElement(  );
                    newButton = new JButton(currentPlugin.getAction(  ));
                    // We are using Swing client properties to 
                    // track additional information without having
                    // to subclass - in effect, using the
                    // client properties mechanism as a form of
                    // delegation.
                    newButton.putClientProperty("window", myFrame);
                    newButton.putClientProperty("plugin", currentPlugin);
                    newButton.addActionListener(actionListenerHandler);
                    myToolBar.add(newButton);
                }
            }
    }

/* --------------------- Initialization: Menu Bar  ---------------------------- */

    // The menu bar for the window
    private javax.swing.JMenuBar mainMenuBar = new javax.swing.JMenuBar(  );
    
    // The menus attached to the menu bar
    private JMenu menuFile = new JMenu(  );
    private JMenu menuEdit = new JMenu(  );
    private JMenu menuTools = new JMenu(  );
    
    // A Hashtable holding all of the default menu items, keyed by title
    protected static Hashtable menuItemsHashtable = new Hashtable(  );
    
    /*
     * The items to be installed into the menus.
     * Each item consists of an identification string and
     * a corresponding virtual key.
     *
     * For a "real" application, the default item titles
     * and virtual keys would be loaded from resource bundles,
     * and ideally the user would be able to configure their
     * own toolbar and menu structure.
     *
     * For this demonstration, however, this is adequate.
     */
    private Object[][] fileItems =
    {
        {"New", new Integer(KeyEvent.VK_N)},
        {"Open", new Integer(KeyEvent.VK_O)},
        {"Close", new Integer(KeyEvent.VK_W)},
        {null, null},
        {"Save", new Integer(KeyEvent.VK_S)},
        {"Revert to Saved", null},
        {null, null},
        {"Print...", new Integer(KeyEvent.VK_P)},
        {"Print Setup...", null}
    };
    private Object[][] editItems =
    {
        {"Undo", new Integer(KeyEvent.VK_Z)},
        {"Redo", new Integer(KeyEvent.VK_Y)},
        {null, null},
        {"Cut", new Integer(KeyEvent.VK_X)},
        {"Copy", new Integer(KeyEvent.VK_C)},
        {"Paste", new Integer(KeyEvent.VK_V)},
        {"Delete", null},
        {"Select All", new Integer(KeyEvent.VK_A)}
    };
    private Object[][] toolItems =
    {
        {"Timestamp", null}
    };

    private void dispatchEvent(ActionEvent evt, String tag)
    {
        SimpleEdit myFrame = null;
        SimpleEditPlugin myPlugin = null;
        if(evt.getSource(  ) instanceof JComponent)
        {
            myFrame = (SimpleEdit)
                        (((JComponent)evt.getSource(  )).getClientProperty("window"));
            myPlugin =  (SimpleEditPlugin)
                        (((JComponent)evt.getSource(  )).getClientProperty("plugin"));
        }
        
        // If it's a plugin, hand off to the plugin to handle
        if(myPlugin != null)
        {
            myPlugin.doAction(myFrame, evt);
            return;
        }
        
        // Handle minimal required functionality.
        // It could legitimately be argued that even this
        // functionality should be split off into an
        // overarching set of plugin functionality...
        // but this is adequate for now, and reinforces
        // the notion of certain "default" services.
        if(tag.compareTo("New") == 0)
            doNew(  );
        if(tag.compareTo("Close") == 0)
            doClose(myFrame);
        if(tag.compareTo("Timestamp") == 0)
            doTimestamp(myFrame);
    }

    /*
     * Default event processing.
     */
    private void doNew(  )
    {
        (new SimpleEdit()).show(  );
    }
    
    private void doTimestamp(SimpleEdit myFrame)
    {
        if(myFrame != null)
           myFrame.mainTextArea.setText(myFrame.mainTextArea.getText(  ) +
                 System.getProperty("line.separator")  + new java.util.Date(  ) + " : ");
        
             myFrame.mainTextArea.setCaretPosition(
              myFrame.mainTextArea.getText().length(  ));
        myFrame.mainTextArea.requestFocus(  );
    }

    // Used to track the number of open windows, and
    // automatically quit when they are all closed.
    private static int openWindows = 0;
    
    // Overrides the default hide to see how many windows are currently
    // showing. If none are visible, quit the app.
    /** Hides the window. If no windows are visible, terminates quietly. */
    public void hide(  )
    {
        super.hide(  );
        openWindows--;
        if(openWindows == 0)
            System.exit(0);
    }
    
    public void show(  )
    {
        super.show(  );
        openWindows++;
        // All ready to go, go ahead and get ready for input.
        this.appendDocumentText("");
    }
    
    private void doClose(SimpleEdit myFrame)
    {
        myFrame.hide(  );
    }

    /* This variable is used to track the default accelerator 
     * key for this platform.
     */
    private int preferredMetaKey =
        Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(  );

    private void setupMenu(JMenu myMenu, Object[][] menuconfig, 
        SimpleEdit thisFrame)
    {
        JMenuItem currentMenuItem;
        for(int i = 0; i < menuconfig.length; i++)
        {
            if(menuconfig[i][0] != null)
            {
                currentMenuItem = new JMenuItem(  );
                currentMenuItem.setLabel((String)menuconfig[i][0]);
                
                if(menuconfig[i][1] != null)
                {
                    int keyCode = ((Integer)menuconfig[i][1]).intValue(  );
                    KeyStroke key = 
                 KeyStroke.getKeyStroke(keyCode, preferredMetaKey);
                    currentMenuItem.setAccelerator(key);
                }
                
                currentMenuItem.setEnabled(false);
                currentMenuItem.setActionCommand((String)menuconfig[i][0]);
                currentMenuItem.putClientProperty("window", thisFrame);
                
                currentMenuItem.addActionListener(actionListenerHandler);
                
                // Put the menu item into the menu hash to add handlers later
                menuItemsHashtable.put((String)menuconfig[i][0], currentMenuItem);
                myMenu.add(currentMenuItem);
            } else
            {
                javax.swing.JSeparator sep = new javax.swing.JSeparator(  );
                myMenu.add(sep);
            }
        }
    }

    // A single default ActionListener that punts to dispatchEvent(  ).
    private ActionListener actionListenerHandler = new ActionListener(  )
    {
        public void actionPerformed(ActionEvent evt)
        {
            Object src = evt.getSource(  );
            if(src instanceof JMenuItem)
            {
                String input = ((JMenuItem)src).getLabel(  );
                dispatchEvent(evt, input);
            }
            if(src instanceof JButton)
            {
                String input = ((JButton)src).getLabel(  );
                dispatchEvent(evt, input);
            }
        }
    };

    private void initMenuBar(  )
    {
        mainMenuBar = new javax.swing.JMenuBar(  );
        
        menuFile = new JMenu("File");
        setupMenu(menuFile, fileItems, this);
        mainMenuBar.add(menuFile);
        
        menuEdit = new JMenu("Edit");
        setupMenu(menuEdit, editItems, this);
        mainMenuBar.add(menuEdit);
        
        menuTools = new JMenu("Tools");
        setupMenu(menuTools, toolItems, this);
        mainMenuBar.add(menuTools);
        
        JMenuItem newMenuItem;
        if(plugins != null)
            if(plugins.size(  ) > 0)
            {
                java.util.Enumeration e = plugins.elements(  );
                SimpleEditPlugin currentPlugin;
                while(e.hasMoreElements(  ))
                {
                    currentPlugin = (SimpleEditPlugin)e.nextElement(  );
                    newMenuItem = new JMenuItem(  );
                    newMenuItem.setLabel(currentPlugin.getAction(  ));
                    newMenuItem.setEnabled(true);
                    newMenuItem.setActionCommand(currentPlugin.getAction(  ));
                    newMenuItem.putClientProperty("window", this);
                    newMenuItem.putClientProperty("plugin", currentPlugin);
                    newMenuItem.addActionListener(actionListenerHandler);
                    menuTools.add(newMenuItem);
                }
            }
        
        ((JMenuItem)menuItemsHashtable.get("New")).setEnabled(true);
        ((JMenuItem)menuItemsHashtable.get("Timestamp")).setEnabled(true);
        ((JMenuItem)menuItemsHashtable.get("Close")).setEnabled(true);
        
        setJMenuBar(mainMenuBar);
    }
/* ----------------- The Main Method: Menu Bar  ---------------------------- */

    public static void main(String[] args)
    {
        argsconfig = args;
        (new SimpleEdit()).show(  );
    }
}