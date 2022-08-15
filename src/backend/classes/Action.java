package backend.classes;

/**
 * Action conducted by appliances
 */
public class Action {
    private String name;
    private String alias;

    // Constructor
    public Action(String name, String alias) {
        this.name = name;
        this.alias = alias;
    }

    // Member Getters
    public String getName() { return this.name; }
    public String getAlias() { return this.alias; }

    // Is Equal
    public Boolean isEqual(Action toCheck) {
        if (!this.name.equals(toCheck.getName())
        || !this.alias.equals(toCheck.getAlias())) {
            return false;
        }
        return true;
    }

    // To String
    @Override
    public String toString() {
        return "{" +
            "name: " + this.name + ", " +
            "alias: " + this.alias +
        "}";
    }
}
