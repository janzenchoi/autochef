package factories;
import java.util.ArrayList;
import java.util.HashMap;

import classes.Action;
import classes.Ingredient;
import classes.Instruction;
import classes.Order;
import helpers.Constants;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Provides orders and instructions
 */
public class FactoryOrder {
    private FactoryAction factoryAction;
    private FactoryIngredient factoryIngredient;
    private HashMap<String, Order> orderMap;

    // Constructor
    public FactoryOrder(FactoryAction factoryAction, FactoryIngredient factoryIngredient) {
        try {
            this.factoryAction = factoryAction;
            this.factoryIngredient = factoryIngredient;
            this.orderMap = new HashMap<String, Order>();
            setOrders();
        } catch (IOException e) {}
    }

    // Get all the Actions
    private void setOrders() throws IOException {

        // Initialisation
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.ORDER_DATA_PATH));
        String line = bufferedReader.readLine();

        // Read row by row (alias | name)
        while ((line = bufferedReader.readLine()) != null) {
            String[] columns = line.split(",");
            if (columns.length == 0) {
                continue;
            }
            Order order = createOrder(columns[0], columns[1]);
            this.orderMap.put(columns[0], order);
        }

        // Close buffer
        bufferedReader.close();
    }

    // Creates an order
    public Order createOrder(String alias, String name) {
        
        // Create empty order
        Order order = new Order(alias, name);

        // Add instructions to order
        Ingredient ingredient = this.factoryIngredient.getIngredient(name);
        addMainInstructions(order, ingredient);
        addEndInstructions(order, ingredient);
        refineInstructions(order);
        return order;
    }
    
    // Gets instructions to create an ingredient, recursively
    private void addMainInstructions(Order order, Ingredient ingredient) {

        // Add instructions to create subingredients
        for (Ingredient subIngredient : ingredient.getIngredients()) {
            addMainInstructions(order, subIngredient);
        }

        // If only one subingredient, act
        if (ingredient.getIngredients().size() == 1) {
            Ingredient subIngredient = ingredient.getIngredients().get(0);
            for (Action action : subIngredient.getActions()) {
                Instruction instruction = new Instruction(action);
                instruction.addIngredient(subIngredient);
                order.addInstruction(instruction);
            }
        }

        // If multiple subingredients, assemble
        if (ingredient.getIngredients().size() > 1) {
            Action assembleAction = this.factoryAction.getAction(Constants.ASSEMBLE_ACTION);
            Instruction assembleInstruction = new Instruction(assembleAction);
            for (Ingredient subIngredient : ingredient.getIngredients()) {
                assembleInstruction.addIngredient(subIngredient);
            }
            order.addInstruction(assembleInstruction);
        }
        
        // If ingredient is base ingredient, act
        if (ingredient.getIngredients().size() == 0) {
            for (Action action : ingredient.getActions()) {
                Instruction instruction = new Instruction(action);
                instruction.addIngredient(ingredient);
                order.addInstruction(instruction);
            }
        }
    }
    
    // Serves the order
    private void addEndInstructions(Order order, Ingredient ingredient) {
        Action serveAction = this.factoryAction.getAction(Constants.SERVE_ACTION);
        Instruction serveInstruction = new Instruction(serveAction);
        serveInstruction.addIngredient(ingredient);
        order.addInstruction(serveInstruction);
    }

    // Refine the instructions by updating the required actions
    private void refineInstructions(Order order) {

        // Stores action and ingredient names
        HashMap<String, ArrayList<Action>> ingredientStateMap = new HashMap<String, ArrayList<Action>>();
        
        // Iterate through each instruction
        for (Instruction instruction : order.getInstructions()) {
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

    // Gets the name of the ingredient given its alias
    public Order getOrder(String alias) {
        return this.orderMap.get(alias);
    }
}
