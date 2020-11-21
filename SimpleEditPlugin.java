public interface SimpleEditPlugin
{
    // Returns a list of actions which will be registered
    // The tool will then be notified if an action is
    // selected.
    public String getAction(  );
    
    // Notification of an action which was registered by
    // this tool.
    public void doAction(SimpleEdit frame, java.awt.event.ActionEvent evt);
    
    // Called once when the plugin is first loaded
    public void init(SimpleEdit frame);
    
}