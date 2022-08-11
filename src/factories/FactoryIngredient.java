package factories;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import classes.Ingredient;
import helpers.Constants;

/**
 * Provides ingredient objects
 */
public class FactoryIngredient {
    private HashMap<String, Ingredient> ingredientMap;

    // Constructor
    public FactoryIngredient() {
        try {
            this.ingredientMap = new HashMap<String, Ingredient>();
            setIngredients();
        } catch (IOException e) {}
    }

    // Set the ingredients
    private void setIngredients() throws IOException {

        // Initialisation
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.INGREDIENT_DATA_PATH));
        String line = bufferedReader.readLine();

        // Read row by row (| name |)
        while ((line = bufferedReader.readLine()) != null) {
            String[] columns = line.split(",");
            String name = columns[0];
            Ingredient ingredient = new Ingredient(name);
            this.ingredientMap.put(name, ingredient);
        }

        // Close and return
        bufferedReader.close();
    }

    // Gets an ingredient
    public Ingredient getIngredient(String name) {
        return this.ingredientMap.get(name);
    }

    // Gets a copy ingredient
    public Ingredient getIngredientCopy(String name) {
        return new Ingredient(this.ingredientMap.get(name));
    }
}
