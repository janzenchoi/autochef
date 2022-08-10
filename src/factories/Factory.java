package factories;
import classes.Action;
import classes.Ingredient;

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
    private FactoryDish factoryDish;

    // Single Constructor
    private Factory() {
        this.factoryAction = new FactoryAction();
        this.factoryDish = new FactoryDish();
        this.factoryIngredient = new FactoryIngredient(factoryAction);
    }

    // Passes function call to appropriate factory
    public Action getAction(String name) { return this.factoryAction.getAction(name); }
    public Action getNewAction(String name) { return this.factoryAction.getNewAction(name); }
    public Ingredient getIngredient(String name) { return this.factoryIngredient.getIngredient(name); }
    public Ingredient getNewIngredient(String name) { return this.factoryIngredient.getNewIngredient(name); }
    public String getIngredientAlias(String name) { return this.factoryDish.getIngredientAlias(name); }
    public String getIngredientName(String alias) { return this.factoryDish.getIngredientName(alias); }
}
