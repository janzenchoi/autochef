package backend.classes.appliances;
import java.util.ArrayList;

import backend.classes.Action;
import backend.classes.Ingredient;
import backend.classes.Order;
import backend.helpers.General;

/**
 * Appliance Class
 */
public class Appliance {
    private String name;
    private String alias;
    private int priceMultiplier;
    private int qualityMultiplier;
    private Boolean remote;
    private int capacity;
    private int time;
    private ArrayList<Action> actions;
    private ArrayList<Ingredient> inputs;
    private ArrayList<Ingredient> outputs;

    // Constructor
    public Appliance(String name, String alias, int priceMultiplier, int qualityMultiplier, Boolean remote, int capacity, int time, ArrayList<Action> actions) {
        this.name               = name;
        this.alias              = alias;
        this.priceMultiplier    = priceMultiplier;
        this.qualityMultiplier  = qualityMultiplier;
        this.remote             = remote;
        this.capacity           = capacity;
        this.time               = time;
        this.actions            = actions;
        this.inputs             = new ArrayList<Ingredient>();
        this.outputs            = new ArrayList<Ingredient>();
    }

    // Member getters
    public String getName() { return this.name; }
    public String getAlias() { return this.alias; }
    public int getPriceMultiplier() { return this.priceMultiplier; }
    public int getQualityMultiplier() { return this.qualityMultiplier; }
    public Boolean getRemote() { return this.remote; }
    public int getCapacity() { return this.capacity; }
    public int getTime() { return this.time; }
    public ArrayList<Action> getActions() { return this.actions; }
    public ArrayList<Ingredient> getInputs() { return this.inputs; }
    public ArrayList<Ingredient> getOutputs() { return this.outputs; }

    // Checks to see if there is space for more input
    public Boolean hasCapacity() {
        return this.inputs.size() < this.capacity;
    }

    // Adds an ingredient to input
    public void addInput(Ingredient input) {
        this.inputs.add(input);
    }

    // Adds an ingredient to output
    public void addOutput(Ingredient output) {
        this.outputs.add(output);
    }

    // Counts the number of ingredients in the output list
    public int hasOutput(Ingredient ingredient) {
        int numIngredients = 0;
        for (Ingredient output : this.outputs) {
            if (ingredient.isEqual(output)) {
                numIngredients += 1;
            }
        }
        return numIngredients;
    }

    // Removes an output from the appliance
    public void removeOutput(Ingredient ingredient) {
        for (int i = 0; i < this.outputs.size(); i++) {
            if (ingredient.isEqual(this.outputs.get(i))) {
                this.outputs.remove(i);
                break;
            }
        }
    }

    // Moves an ingredient from inputs to outputs
    public void moveInput(Ingredient ingredient) {
        for (int i = 0; i < this.inputs.size(); i++) {
            if (ingredient.isEqual(this.inputs.get(i))) {
                this.inputs.remove(i);
                this.outputs.add(ingredient);
                break;
            }
        }
    }

    // Applies the changes to the price and quantity of an order
    public void modifyOrder(Order order) {
        order.getPrice().increaseMultiplier(this.priceMultiplier);
        order.getQuality().increaseMultiplier(this.qualityMultiplier);
    }

    // To String
    public String toString() {
        String inputString = General.arrayToString(this.inputs);
        String outputString = General.arrayToString(this.outputs);
        String actionString = General.arrayToString(this.actions);
        return "{" +
            "name: " + this.name + ", " +
            "alias: " + this.alias + ", " +
            "priceMultiplier: " + this.priceMultiplier + ", " +
            "qualityMultiplier: " + this.qualityMultiplier + ", " +
            "remote: " + this.remote + ", " +
            "capacity: " + this.capacity + ", " +
            "time: " + this.time + ", " +
            "actions: " + actionString + ", " +
            "inputs: " + inputString + ", " +
            "outputs: " + outputString +
        "}";
    }
}
