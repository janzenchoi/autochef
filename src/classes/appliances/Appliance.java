package classes.appliances;
import classes.Action;

/**
 * Appliance Class
 */
public class Appliance {
    private String name;
    private Action action;

    // Constructor
    public Appliance(String name) {
        this.name = name;
    }

    // Member setters and getters
    public void setAction(Action action) { this.action = action; }
    public String getName() { return this.name; }
    public Action getAction() { return this.action; }
}
