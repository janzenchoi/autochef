package factories;
import classes.Action;
import classes.Ingredient;
import classes.Recipe;
import java.util.HashMap;
import helpers.Constants;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Provides recipe objects
 */
public class FactoryRecipe {
    private FactoryAction factoryActions;
    private FactoryIngredient factoryIngredients;
    private HashMap<String, Recipe> recipeMap;

    // Constructor
    public FactoryRecipe(FactoryAction factoryActions, FactoryIngredient factoryIngredients) {
        this.factoryActions = factoryActions;
        this.factoryIngredients = factoryIngredients;
        try {
            this.recipeMap = getRecipes();
        } catch (IOException e) {}
    }

    // Get all the recipes
    private HashMap<String, Recipe> getRecipes() throws IOException {

        // Initialisation
        HashMap<String, Recipe> recipeMap = new HashMap<String, Recipe>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.RECIPES_DATA_PATH));
        String line = bufferedReader.readLine();
        
        // Read row by row (name | ingredient_1 | actions_1 | ... | ingredient_N | actions_N)
        while ((line = bufferedReader.readLine()) != null) {

            // Extract row data
            String[] columns = line.split(",");
            int numIngredients = (columns.length - 1) / 2;
            String recipeName = columns[0];

            // Recipe object
            Recipe recipe = new Recipe(recipeName);

            // Go through ingredients
            for (int i = 0; i < numIngredients; i++) {
                String name = columns[2*i + 1];
                String[] actions = columns[2*i + 2].split("&");
                Ingredient ingredient = factoryIngredients.getNewIngredient(name);
                for (int j = 0; j < actions.length; j++) {
                    Action action = factoryActions.getNewAction(actions[j]);
                    ingredient.addAction(action);
                }
                recipe.addIngredient(ingredient);
            }

            // Add recipe
            recipeMap.put(recipeName, recipe);
        }

        // Close buffer and return
        bufferedReader.close();
        return recipeMap;
    }

    // Gets a recipe
    public Recipe getRecipe(String name) {
        return this.recipeMap.get(name);
    }

    // Gets a new recipe
    public Recipe getNewRecipe(String name) {
        Recipe recipe = this.recipeMap.get(name);
        return new Recipe(recipe.getName());
    }
}
