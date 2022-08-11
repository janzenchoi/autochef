package factories;
import classes.Action;
import classes.Ingredient;
import classes.Order;

/**
 * Factory that contains other factories
 * (singleton)
 */
public class Factory {
    private static Factory factory = new Factory();
    public static Factory getFactory() { return factory; }

    // Factories
    private FactoryAction factoryAction;
    private FactoryIngredient factoryIngredient;
    private FactoryOrder factoryOrder;

    // Single Constructor
    private Factory() {
        this.factoryAction = new FactoryAction();
        this.factoryIngredient = new FactoryIngredient(this.factoryAction);
        this.factoryOrder = new FactoryOrder(this.factoryIngredient);
    }

    // Passes function call to appropriate factory
    public Action getAction(String name) { return this.factoryAction.getAction(name); }
    public Action getNewAction(String name) { return this.factoryAction.getNewAction(name); }
    public Ingredient getIngredient(String name) { return this.factoryIngredient.getIngredient(name); }
    public Ingredient getNewIngredient(String name) { return this.factoryIngredient.getNewIngredient(name); }
    public Order getOrder(String alias) { return this.factoryOrder.getOrder(alias); }
}
