package factories;
import classes.Action;
import classes.Ingredient;
import classes.Listing;
import classes.Recipe;

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
    private FactoryRecipe factoryRecipe;
    private FactoryListing factoryListing;

    // Single Constructor
    private Factory() {
        this.factoryAction = new FactoryAction();
        this.factoryIngredient = new FactoryIngredient();
        this.factoryRecipe = new FactoryRecipe(factoryAction, factoryIngredient);
        this.factoryListing = new FactoryListing(factoryIngredient);
    }

    // Passes function call to appropriate factory
    public Action getAction(String name) { return this.factoryAction.getAction(name); }
    public Ingredient getIngredient(String name) { return this.factoryIngredient.getIngredient(name); }
    public Ingredient getIngredientCopy(String name) { return this.factoryIngredient.getIngredientCopy(name); }
    public Recipe getRecipe(String name) { return this.factoryRecipe.getRecipe(name); }
    public Recipe getRecipeCopy(String name) { return this.factoryRecipe.getRecipeCopy(name); }
    public Listing getListing(String alias) { return this.factoryListing.getListing(alias); }
    public Listing getListingCopy(String alias) { return this.factoryListing.getListingCopy(alias); }
}
