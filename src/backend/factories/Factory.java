package backend.factories;

import backend.classes.Action;
import backend.classes.Ingredient;
import backend.classes.Listing;
import backend.classes.Recipe;
import backend.classes.appliances.Appliance;

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
    private FactoryAppliance factoryAppliance;

    // Single Constructor
    private Factory() {
        this.factoryAction      = new FactoryAction();
        this.factoryIngredient  = new FactoryIngredient();
        this.factoryRecipe      = new FactoryRecipe(factoryAction, factoryIngredient);
        this.factoryListing     = new FactoryListing(factoryIngredient);
        this.factoryAppliance   = new FactoryAppliance(factoryAction);
    }

    // Passes function call to appropriate factory
    public Action getAction(String name) { return this.factoryAction.getAction(name); }
    public Ingredient getIngredient(String name) { return this.factoryIngredient.getIngredient(name); }
    public Recipe getRecipe(String name) { return this.factoryRecipe.getRecipe(name); }
    public Recipe getRecipeCopy(String name) { return this.factoryRecipe.getRecipeCopy(name); }
    public Listing getListing(String name) { return this.factoryListing.getListing(name); }
    public Listing getListingCopy(String name) { return this.factoryListing.getListingCopy(name); }
    public Appliance getAppliance(String name) { return this.factoryAppliance.getAppliance(name); }
}
