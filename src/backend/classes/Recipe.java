package backend.classes;
import java.util.ArrayList;

import backend.helpers.General;

/**
 * Stores an instruction to do something
 */
public class Recipe {
    public Ingredient output;
    public Action action;
    public ArrayList<Ingredient> inputs;

    // Constructor
    public Recipe(Ingredient output, Action action, ArrayList<Ingredient> inputs) {
        this.output = output;
        this.action = action;
        this.inputs = inputs;
    }

    // Clone Constructor
    public Recipe(Recipe clone) {
        this.output = clone.getOutput();
        this.action = clone.getAction();
        this.inputs = new ArrayList<Ingredient>();
        for (Ingredient ingredient : clone.getInputs()) {
            this.inputs.add(ingredient);
        }
    }

    // Member getters
    public Ingredient getOutput() { return this.output; }
    public Action getAction() { return this.action; }
    public ArrayList<Ingredient> getInputs() { return this.inputs; }

    // To String
    @Override
    public String toString() {
        String inputString = General.arrayToString(this.inputs);
        return "{" +
            "output: " + this.output + ", " +
            "action: " + this.action + ", " +
            "inputs: " + inputString +
        "}";
    }
}
