package classes;
import java.util.ArrayList;
import helpers.General;

/**
 * Ingredient Class
 */
public class Ingredient {
    private String name;
    private int price;
    private int quality;
    private ArrayList<Action> actions;

    // Constructor
    public Ingredient(String name, int price, int quality) {
        this.name       = name;
        this.price      = price;
        this.quality    = quality;
        this.actions    = new ArrayList<Action>();
    }

    // Member Getters
    public String getName() { return this.name; }
    public int getPrice() { return this.price; }
    public int getQuality() { return this.quality; }
    public ArrayList<Action> getActions() { return this.actions; }

    // Adds to the list of actions
    public void addAction(Action action) {
        this.actions.add(action);
    }

    // To String
    public String toString() {
        String actionsString = General.arrayToString(this.actions);
        return "{" +
            "name: " + name + ", " +
            "price: " + price + ", " +
            "quality: " + quality + ", " +
            "actions: " + actionsString +
        "}";
    }
}
