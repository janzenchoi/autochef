package factories;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import classes.Action;
import classes.Ingredient;
import helpers.Constants;

/**
 * Provides recipe objects
 */
public class FactoryIngredient {
    private FactoryAction factoryAction;
    private HashMap<String, Ingredient> ingredientMap;

    // Constructor
    public FactoryIngredient(FactoryAction factoryAction) {
        this.factoryAction = factoryAction;
        try {
            this.ingredientMap = new HashMap<String, Ingredient>();
            setIngredients();
        } catch (IOException e) {}
    }

    // Get the recipes
    public void setIngredients() throws IOException {

        // Initialisation
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.INGREDIENT_DATA_PATH));
        String line = bufferedReader.readLine();

        // Read row by row (name | actions | ingredient_1 | actions_1 | ... | ingredient_N | actions_N)
        while ((line = bufferedReader.readLine()) != null) {
            String[] columns = line.split(",");
            if (columns.length == 0) {
                continue;
            }
            
            // Extract basic information
            String name = columns[0];
            int price = Integer.parseInt(columns[1]);
            int quality = Integer.parseInt(columns[2]);
            int numComponents = (columns.length - 3) / 2;
            
            // If recipe has no subingredients
            Ingredient ingredient = new Ingredient(name, price, quality);
            this.ingredientMap.put(name, ingredient);
            
            // If ingredient has subingredients
            if (numComponents > 0) {
                for (int i = 3; i < columns.length; i += 2) {
                    
                    // Get subingredient
                    String subName = columns[i];
                    String[] subActions = columns[i + 1].split("&");
                    Ingredient subIngredient = this.ingredientMap.get(subName);
                    Ingredient clone = new Ingredient(subIngredient);

                    // Add new actions
                    for (int j = 0; j < subActions.length; j++) {
                        if (!subActions[j].equals(Constants.NONE_ACTION)) {
                            Action action = this.factoryAction.getNewAction(subActions[j]);
                            clone.addAction(action);
                        }
                    }

                    // Add subingredient
                    ingredient.addIngredient(clone);
                }
            }
        }

        // Close and return
        bufferedReader.close();
    }

    // Gets an ingredient
    public Ingredient getIngredient(String name) {
        return this.ingredientMap.get(name);
    }

    // Gets a copy of the ingredient
    public Ingredient getNewIngredient(String name) {
        return new Ingredient(getIngredient(name));
    }
}
