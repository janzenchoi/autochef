package factories;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import classes.Action;
import classes.Ingredient;
import classes.Recipe;
import helpers.Constants;

/**
 * Provides recipe objects
 */
public class FactoryRecipe {
    private FactoryAction factoryAction;
    private FactoryIngredient factoryIngredient;
    private HashMap<String, Recipe> recipeMap;

    // Constructor
    public FactoryRecipe(FactoryAction factoryAction, FactoryIngredient factoryIngredient) {
        this.factoryAction = factoryAction;
        this.factoryIngredient = factoryIngredient;
        try {
            this.recipeMap = new HashMap<String, Recipe>();
            setRecipes();
        } catch (IOException e) {}
    }

    // Set the recipes
    private void setRecipes() throws IOException {

        // Initialisation
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.RECIPE_DATA_PATH));
        String line = bufferedReader.readLine();

        // Read row by row (| outputName | action | ingredient_1 | ingredient_2 | ingredient_3 |)
        while ((line = bufferedReader.readLine()) != null) {
            String[] columns = line.split(",");

            // Create output ingredient
            String outputName = columns[0];
            Ingredient output = this.factoryIngredient.getIngredient(outputName);

            // Create action
            String actionName = columns[1];
            Action action = this.factoryAction.getAction(actionName);

            // Create list of ingredients
            ArrayList<Ingredient> inputs = new ArrayList<Ingredient>();
            for (int i = 2; i < columns.length; i++) {
                String inputName = columns[i];
                Ingredient input = this.factoryIngredient.getIngredient(inputName);
                inputs.add(input);
            }

            // Create recipe
            Recipe recipe = new Recipe(output, action, inputs);
            this.recipeMap.put(outputName, recipe);
        }

        // Close and return
        bufferedReader.close();
    }

    // Gets a recipe
    public Recipe getRecipe(String name) {
        return this.recipeMap.get(name);
    }

    // Gets a copy of a recipe
    public Recipe getRecipeCopy(String name) {
        return new Recipe(this.recipeMap.get(name));
    }
}
