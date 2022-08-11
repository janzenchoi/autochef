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

        // Read row by row (| outputName | ingredient_1 | actions_1 | ... | ingredient_N | actions_N |)
        while ((line = bufferedReader.readLine()) != null) {
            String[] columns = line.split(",");
            String outputName = columns[0];
            
            // Create list of input ingredients
            ArrayList<Ingredient> inputIngredients = new ArrayList<Ingredient>();
            for (int i = 1; i < columns.length; i += 2) {
                String inputName = columns[i];
                String[] inputActions = columns[i + 1].split("&");
                
                // Create input ingredient
                Ingredient inputIngredient = this.factoryIngredient.getIngredientCopy(inputName);

                // Add actions to input ingredient
                for (int j = 0; j < inputActions.length; j++) {
                    if (!inputActions[j].equals(Constants.NONE_ACTION)) {
                        Action action = this.factoryAction.getAction(inputActions[j]);
                        inputIngredient.addAction(action);
                    }
                }

                // Add to list
                inputIngredients.add(inputIngredient);
            }

            // Create recipe
            Ingredient outputIngredient = this.factoryIngredient.getIngredientCopy(outputName);
            Recipe recipe = new Recipe(inputIngredients, outputIngredient);
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
