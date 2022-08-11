package classes;
import java.util.ArrayList;
import java.util.HashMap;
import helpers.Constants;
import helpers.General;

/**
 * Stores a customer's order
 */
public class Order {
    private String alias;
    private String name;
    private ArrayList<Instruction> instructions;
    private Action assembleAction;
    private Action serveAction;

    // Constructor
    public Order(String alias, Ingredient ingredient) {
        
        // Initailise
        this.alias = alias;
        this.name = ingredient.getName();
        this.instructions = new ArrayList<Instruction>();

        // Define get, assemble, and serve instructions (hardcoded for simplicity)
        this.assembleAction = new Action(Constants.ASSEMBLE_ACTION, 0, 0);
        this.serveAction    = new Action(Constants.SERVE_ACTION, 0, 0);

        // Add instructions
        addMainInstructions(ingredient);
        addEndInstructions(ingredient);
        refineInstructions();
    }

    // Member getters
    public String getAlias() { return this.alias; }
    public String getName() { return this.name; }
    public ArrayList<Instruction> getInstructions() {return this.instructions; }

    // Gets instructions to create an ingredient, recursively
    private void addMainInstructions(Ingredient ingredient) {

        // Add instructions to create subingredients
        for (Ingredient subIngredient : ingredient.getIngredients()) {
            addMainInstructions(subIngredient);
        }

        // If only one subingredient, act
        if (ingredient.getIngredients().size() == 1) {
            Ingredient subIngredient = ingredient.getIngredients().get(0);
            for (Action action : subIngredient.getActions()) {
                Instruction instruction = new Instruction(action);
                instruction.addIngredient(subIngredient);
                this.instructions.add(instruction);
            }
        }

        // If multiple subingredients, assemble
        if (ingredient.getIngredients().size() > 1) {
            Instruction assembleInstruction = new Instruction(this.assembleAction);
            for (Ingredient subIngredient : ingredient.getIngredients()) {
                assembleInstruction.addIngredient(subIngredient);
            }
            this.instructions.add(assembleInstruction);
        }
        
        // If ingredient is base ingredient, act
        if (ingredient.getIngredients().size() == 0) {
            for (Action action : ingredient.getActions()) {
                Instruction instruction = new Instruction(action);
                instruction.addIngredient(ingredient);
                this.instructions.add(instruction);
            }
        }
    }
    
    // Serves the order
    private void addEndInstructions(Ingredient ingredient) {
        Instruction serveInstruction = new Instruction(this.serveAction);
        serveInstruction.addIngredient(ingredient);
        this.instructions.add(serveInstruction);
    }

    // Refine the instructions by updating the required actions
    private void refineInstructions() {

        // Stores action and ingredient names
        HashMap<String, ArrayList<Action>> ingredientStateMap = new HashMap<String, ArrayList<Action>>();
        
        // Iterate through each instruction
        for (Instruction instruction : this.instructions) {
            Action currentAction = instruction.getAction();
            
            // Iterate through each ingredient
            for (Ingredient ingredient : instruction.getIngredients()) {
                String ingredientName = ingredient.getName();

                // If ingredient does not exist in map, add to map
                if (ingredientStateMap.get(ingredientName) == null) {
                    ArrayList<Action> actions = new ArrayList<Action>();
                    actions.add(currentAction);
                    ingredientStateMap.put(ingredientName, actions);
                
                // If the ingredient does exist, add actions and update map
                } else {
                    ArrayList<Action> pastActions = ingredientStateMap.get(ingredientName);
                    for (Action action : pastActions) {
                        ingredient.addAction(action);
                    }
                    pastActions.add(currentAction);
                }
            }

        }
    }

    // To String
    @Override
    public String toString() {
        String instructionString = General.arrayToString(this.instructions);
        return "{" +
            "alias: " + this.alias + ", " +
            "name: " + this.name + ", " +
            "instructions: " + instructionString +
        "}";
    }

    // To String (with newline formatting)
    public String toStringFormatted() {
        String formatted = "";
        for (Instruction instruction : this.instructions) {
            formatted += instruction.toString() + "\n";
        }
        return formatted;
    }
}
