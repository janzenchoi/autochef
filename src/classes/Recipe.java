package classes;
import java.util.ArrayList;
import helpers.General;

/**
 * Stores an instruction to do something
 */
public class Recipe {
    public ArrayList<Ingredient> inputs;
    public Ingredient output;

    // Constructor
    public Recipe(ArrayList<Ingredient> inputs, Ingredient output) {
        this.inputs = inputs;
        this.output = output;
    }

    // Clone Constructor
    public Recipe(Recipe clone) {
        this.inputs = new ArrayList<Ingredient>();
        for (Ingredient ingredient : clone.getInputs()) {
            this.inputs.add(ingredient);
        }
        this.output = clone.getOutput();
    }

    // Member getters
    public ArrayList<Ingredient> getInputs() { return this.inputs; }
    public Ingredient getOutput() { return this.output; }

    // To String
    @Override
    public String toString() {
        String inputString = General.arrayToString(this.inputs);
        return "{" +
            "inputs: " + inputString + ", " +
            "output: " + this.output +
        "}";
    }
}
