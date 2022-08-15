package backend.classes.instructions;
import java.util.ArrayList;

import backend.classes.Action;
import backend.classes.Ingredient;
import backend.classes.Recipe;
import backend.factories.Factory;

/**
 * To provide instructions
 */
public class Instructor {
    private Factory factory;

    // Constructor
    public Instructor() {
        this.factory = Factory.getFactory();
    }

    // Provides instructions on how to create a listing
    public ArrayList<Instruction> instruct(String name) {
        Ingredient ingredient = this.factory.getIngredient(name);
        ArrayList<Instruction> instructionList = getInstructions(ingredient);
        return instructionList;
    }

    // Creates an instruction based on the required ingredient
    public ArrayList<Instruction> getInstructions(Ingredient ingredient) {

        // Initialise
        ArrayList<Instruction> instructions = new ArrayList<Instruction>();
        Recipe recipe = this.factory.getRecipe(ingredient.getName());

        // If the ingredient is raw, return nothing
        if (recipe == null) {
            return instructions;
        }
            
        // Get subingredients
        ArrayList<Ingredient> subIngredients = recipe.getInputs();
        for (Ingredient subIngredient : subIngredients) {
            ArrayList<Instruction> subInstructions = getInstructions(subIngredient);
            instructions.addAll(subInstructions);
        }

        // Act on subingredients
        Action action = recipe.getAction();
        Instruction instruction = new Instruction(action, subIngredients);
        instructions.add(instruction);

        // Return instructions
        return instructions;
    }

    // Provides a nice display of instructions
    public String instructDisplay(String listingAlias) {
        String display = "";
        ArrayList<Instruction> instructions = instruct(listingAlias);
        for (Instruction instruction : instructions) {
            display += instruction.toString() + "\n";
        }
        return display;
    }
}
