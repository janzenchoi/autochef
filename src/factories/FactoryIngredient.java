package factories;
import java.util.ArrayList;
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

    // Adds a raw ingredient to the recipe map
    private void setIngredient(String name, int price, int quality, String[] actions) {
        Ingredient ingredient = new Ingredient(name, price, quality);
        for (int i = 0; i < actions.length; i++) {
            if (!actions[i].equals(Constants.NONE_ACTION)) {
                Action action = this.factoryAction.getAction(actions[i]);
                ingredient.addAction(action);
            }
        }
        this.ingredientMap.put(name, ingredient);
    }

    // Adds an ingredient assembly to the recipe map
    private void setIngredient(String name, String[] actions, ArrayList<String> componentNames, ArrayList<String[]> componentActions) {
        
        // Create base ingredient
        Ingredient ingredient = new Ingredient(name, 0, 0);
        for (int i = 0; i < actions.length; i++) {
            Action clone = this.factoryAction.getNewAction(actions[i]);
            ingredient.addAction(clone);
        }

        // Add sub ingredients
        for (int i = 0; i < componentNames.size(); i++) {

            // Get ingredient
            Ingredient newIngredient = this.ingredientMap.get(componentNames.get(i));
            Ingredient clone = new Ingredient(newIngredient);
            
            // Add new actions
            for (int j = 0; j < componentActions.get(i).length; j++) {
                String actionName = componentActions.get(i)[j];
                if (!actionName.equals(Constants.NONE_ACTION)) {
                    Action action = this.factoryAction.getNewAction(actionName);
                    clone.addAction(action);
                }
            }

            // Add component ingredient
            ingredient.addIngredient(clone);
        }
        this.ingredientMap.put(name, ingredient);
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
            String[] actions = columns[3].split("&");
            int numComponents = (columns.length - 4) / 2;

            // Add recipe based on number of ingredients
            if (numComponents == 0) {
                setIngredient(name, price, quality, actions);
            } else {
                ArrayList<String> componentNames = new ArrayList<String>();
                ArrayList<String[]> componentActions = new ArrayList<String[]>();
                for (int i = 4; i < columns.length; i += 2) {
                    componentNames.add(columns[i]);
                    componentActions.add(columns[i + 1].split("&"));
                }
                setIngredient(name, actions, componentNames, componentActions);
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
