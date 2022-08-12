package classes;

/**
 * Combined ingredients
 * (pseudo-composite)
 */
public class Ingredient {
    private String name;
    private String alias;

    // Constructor
    public Ingredient(String name, String alias) {
        this.name = name;
        this.alias = alias;
    }

    // Clone constructor
    public Ingredient(Ingredient clone) {
        this.name = clone.getName();
        this.alias = clone.getAlias();
    }

    // Member Getters
    public String getName() { return this.name; }
    public String getAlias() { return this.alias; }

    // Is Equal
    public Boolean isEqual(Ingredient toCheck) {
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
