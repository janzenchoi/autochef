package classes.instructions;
import java.util.ArrayList;
import classes.Action;
import classes.Ingredient;
import classes.Listing;
import classes.Recipe;
import factories.Factory;
import helpers.Constants;

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
    public ArrayList<Instruction> instruct(String listingAlias) {
        Listing listing = this.factory.getListing(listingAlias);
        Ingredient ingredient = listing.getIngredient();
        ArrayList<Instruction> instructionList = getInstructions(ingredient);
        return instructionList;
    }

    // Creates an instruction based on the required ingredient
    public ArrayList<Instruction> getInstructions(Ingredient ingredient) {

        // Initialise
        ArrayList<Instruction> instructions = new ArrayList<Instruction>();
        Recipe recipe = this.factory.getRecipe(ingredient.getName());

        // If there are subingredients
        if (recipe != null) {
            
            // Get subingredients
            ArrayList<Ingredient> subIngredients = recipe.getInputs();
            for (Ingredient subIngredient : subIngredients) {
                ArrayList<Instruction> subInstructions = getInstructions(subIngredient);
                instructions.addAll(subInstructions);
            }

            // Assemble ingredients if there are multiple
            if (subIngredients.size() > 1) {
                Action action = this.factory.getAction(Constants.ASSEMBLE_ACTION);
                Instruction instruction = new Instruction(action, subIngredients);
                instructions.add(instruction);
            }
        }

        // Add instructions to deal with this ingredient
        ArrayList<Action> actions = ingredient.getActions();
        for (int i = 0; i < actions.size(); i++) {
            Ingredient emptyIngredient = new Ingredient(ingredient.getName());
            for (int j = 0; j < i; j++) {
                emptyIngredient.addAction(actions.get(j));
            }
            Instruction instruction = new Instruction(actions.get(i), emptyIngredient);
            instructions.add(instruction);
        }
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
