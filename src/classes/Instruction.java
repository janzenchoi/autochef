package classes;
import java.util.ArrayList;
import helpers.General;

/**
 * Stores an instruction to do something
 */
public class Instruction {
    public Action action;
    public ArrayList<Ingredient> ingredients;

    // Constructor
    public Instruction(Action action) {
        this.action = action;
        this.ingredients = new ArrayList<Ingredient>();
    }

    // Clone Constructor
    public Instruction(Instruction clone) {
        this.action = clone.getAction();
        this.ingredients = new ArrayList<Ingredient>();
        for (Ingredient ingredient : clone.getIngredients()) {
            this.ingredients.add(ingredient);
        }
    }

    // Member getters
    public Action getAction() { return this.action; }
    public ArrayList<Ingredient> getIngredients() { return this.ingredients; }

    // Adds a subject
    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient.getEmptyClone());
    }

    // To String
    @Override
    public String toString() {
        String subjectString = General.arrayToString(this.ingredients);
        return "{" +
            "action: " + this.action + ", " +
            "ingredients: " + subjectString +
        "}";
    }
}
