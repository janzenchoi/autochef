package classes;

/**
 * Action conducted by appliances
 */
public class Action {
    private String name;

    // Constructor
    public Action(String name) {
        this.name = name;
    }

    // Clone constructor
    public Action(Action clone) {
        this.name = clone.getName();
    }

    // Member Getters
    public String getName() { return this.name; }

    // Is Equal
    public Boolean isEqual(Action toCheck) {
        if (!this.name.equals(toCheck.getName())) {
            return false;
        }
        return true;
    }

    // To String
    @Override
    public String toString() {
        return "{" +
            "name: " + this.name +
        "}";
    }
}
