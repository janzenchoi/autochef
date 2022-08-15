package backend.classes.instructions;
import java.util.ArrayList;

import backend.classes.Action;
import backend.classes.Ingredient;
import backend.helpers.General;

/**
 * Instructs what the AI should do
 */
public class Instruction {
    private Action action;
    private ArrayList<Ingredient> ingredients;

    // Constructor (multiple ingredients)
    public Instruction(Action action, ArrayList<Ingredient> ingredients) {
        this.action = action;
        this.ingredients = ingredients;
    }

    // Consutrctor (single ingredient)
    public Instruction(Action action, Ingredient ingredient) {
        this.action = action;
        this.ingredients = new ArrayList<Ingredient>();
        this.ingredients.add(ingredient);
    }

    // Member getters
    public Action getAction() { return this.action; }
    public ArrayList<Ingredient> getIngredients() { return this.ingredients; }

    // To String
    @Override
    public String toString() {
        String ingredientString = General.arrayToString(this.ingredients);
        return "{" +
            "action: " + this.action + ", " +
            "ingredients: " + ingredientString +
        "}";
    }
}
