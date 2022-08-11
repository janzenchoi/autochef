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
            this.actions.add(newAction);
        }

        // Clone ingredients
        this.ingredients = new ArrayList<Ingredient>();
        for (Ingredient ingredient : clone.getIngredients()) {
            Ingredient newIngredient = new Ingredient(ingredient);
            this.ingredients.add(newIngredient);
        }
    }

    // Clone empty ingredient
    public Ingredient getEmptyClone() {
        return new Ingredient(this.name, this.price, this.quality);
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

    // Is Equal
    public Boolean isEqual(Ingredient toCheck) {
        
        // Basic checks
        if (!this.name.equals(toCheck.getName())
        || this.price != toCheck.getPrice()
        || this.quality != toCheck.getQuality()
        || this.actions.size() != toCheck.getActions().size()
        || this.ingredients.size() != toCheck.getIngredients().size()) {
            return false;
        }
        
        // Check actions
        ArrayList<Action> toCheckActions = toCheck.getActions();
        for (int i = 0; i < this.actions.size(); i++) {
            if (!this.actions.get(i).isEqual(toCheckActions.get(i))) {
                return false;
            }
        }
        
        // Check subingredients
        ArrayList<Ingredient> toCheckIngredients = toCheck.getIngredients();
        for (int i = 0; i < this.ingredients.size(); i++) {
            if (!this.ingredients.get(i).isEqual(toCheckIngredients.get(i))) {
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
        String ingredientsString = General.arrayToString(this.ingredients);
        return "{" +
            "name: " + this.name + ", " +
            "price: " + this.price + ", " +
            "quality: " + this.quality + ", " +
            "actions: " + actionString + ", " +
            "ingredients: " + ingredientsString +
        "}";
    }
}
