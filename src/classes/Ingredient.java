package classes;
import java.util.ArrayList;
import helpers.General;

/**
 * Combined ingredients
 * (pseudo-composite)
 */
public class Ingredient {
    private String name;
    private int price;
    private int quality;
    private ArrayList<Action> actions;
    private ArrayList<Ingredient> ingredients;

    // Constructor
    public Ingredient(String name, int price, int quality) {
        this.name       = name;
        this.price      = price;
        this.quality    = quality;
        this.actions    = new ArrayList<Action>();
        this.ingredients = new ArrayList<Ingredient>();
    }

    // Clone constructor
    public Ingredient(Ingredient clone) {

        // Clone basic members
        this.name = clone.getName();
        this.price = clone.getPrice();
        this.quality = clone.getQuality();

        // Clone actions
        this.actions = new ArrayList<Action>();
        for (Action action : clone.getActions()) {
            Action newAction = new Action(action);
            addAction(newAction);
        }

        // Clone ingredients
        this.ingredients = new ArrayList<Ingredient>();
        for (Ingredient ingredient : clone.getIngredients()) {
            Ingredient newIngredient = new Ingredient(ingredient);
            this.ingredients.add(newIngredient);
        }
    }

    // Member Getters
    public String getName() { return this.name; }
    public int getPrice() { return this.price; }
    public int getQuality() { return this.quality; }
    public ArrayList<Action> getActions() { return this.actions; }
    public ArrayList<Ingredient> getIngredients() { return this.ingredients; }

    // Adds to the list of actions
    public void addAction(Action action) {
        this.actions.add(action);
        this.price += action.getPrice();
        this.quality += action.getQuality();
    }

    // Adds to the list of ingredients
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        this.price += ingredient.getPrice();
        this.quality += ingredient.getQuality();
    }

    // To String
    @Override
    public String toString() {
        String actionString = General.arrayToString(this.actions);
        String ingredientsString = General.arrayToString(this.ingredients);
        return "{" +
            "name: " + name + ", " +
            "price: " + price + ", " +
            "quality: " + quality + ", " +
            "actions: " + actionString + ", " +
            "ingredients: " + ingredientsString +
        "}";
    }
}
