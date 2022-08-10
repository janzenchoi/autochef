package classes.instructions;
import java.util.ArrayList;
import classes.Action;
import classes.Ingredient;
import factories.Factory;
import helpers.Constants;

/**
 * Instructs the system to do things
 */
public class Instructor {
    private Factory factory;

    // Constructor
    public Instructor() {
        this.factory = Factory.getFactory();
    }

    // Gets instructions to create an ingredient
    public ArrayList<Instruction> getInstructions(Ingredient ingredient) {
        ArrayList<Instruction> instructions = new ArrayList<Instruction>();
        
        // Add subingredients as instructions
        for (Ingredient subIngredient : ingredient.getIngredients()) {
            ArrayList<Instruction> subInstructions = getInstructions(subIngredient);
            instructions.addAll(subInstructions);
        }
        
        // If multiple subingredients, assemble
        if (ingredient.getIngredients().size() > 0) {
            for (Action action : ingredient.getActions()) {
                Instruction instruction = new Instruction(action.getName());
                for (Ingredient subIngredient : ingredient.getIngredients()) {
                    instruction.addSubject(subIngredient.getName());
                }
                instructions.add(instruction);
            }
        
        // Add own actions
        } else {
            for (Action action : ingredient.getActions()) {
                Instruction instruction = new Instruction(action.getName());
                instruction.addSubject(ingredient.getName());
                instructions.add(instruction);
            }
        }
        return instructions;
    }

    // Returns a list of instructions to create an ingredient
    public ArrayList<Instruction> getInstructions(String ingredientName) {
        Ingredient ingredient = this.factory.getIngredient(ingredientName);
        ArrayList<Instruction> instructions = getInstructions(ingredient);
        return instructions;
    }

    // Gets a string of the instructions
    public String getInstructionsString(String ingredientName) {
        ArrayList<Instruction> instructions = getInstructions(ingredientName);
        String instructionsString = "";
        for (Instruction instruction : instructions) {
            instructionsString += instruction.toString() + "\n";
        }
        return instructionsString;
    }
}