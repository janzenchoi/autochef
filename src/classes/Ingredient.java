package classes;
import java.util.ArrayList;
import helpers.General;

/**
 * Combined ingredients
 * (pseudo-composite)
 */
public class Ingredient {
    private String name;
    private ArrayList<Action> actions;

    // Constructor
    public Ingredient(String name) {
        this.name       = name;
        this.actions    = new ArrayList<Action>();
    }

    // Clone constructor
    public Ingredient(Ingredient clone) {
        this.name = clone.getName();
        this.actions = new ArrayList<Action>();
        for (Action action : clone.getActions()) {
            Action newAction = new Action(action);
            this.actions.add(newAction);
        }
    }

    // Member Getters
    public String getName() { return this.name; }
    public ArrayList<Action> getActions() { return this.actions; }

    // Adds to the list of actions
    public void addAction(Action action) {
        this.actions.add(action);
    }

    // Is Equal
    public Boolean isEqual(Ingredient toCheck) {
        
        // Basic checks
        if (!this.name.equals(toCheck.getName())
        || this.actions.size() != toCheck.getActions().size()) {
            return false;
        }
        
        // Check actions
        ArrayList<Action> toCheckActions = toCheck.getActions();
        for (int i = 0; i < this.actions.size(); i++) {
            if (!this.actions.get(i).isEqual(toCheckActions.get(i))) {
                return false;
            }
        }

        // Passes all tests
        return true;
    }

    // To String
    @Override
    public String toString() {
        String actionString = General.arrayToString(this.actions);
        return "{" +
            "name: " + this.name + ", " +
            "actions: " + actionString +
        "}";
    }
}
