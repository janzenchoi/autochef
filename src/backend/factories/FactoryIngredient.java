package backend.factories;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import backend.classes.Ingredient;
import backend.helpers.Constants;

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
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.INFO_INGREDIENT_PATH));
        String line = bufferedReader.readLine();

        // Read row by row (| name | alias |)
        while ((line = bufferedReader.readLine()) != null) {
            String[] columns        = line.split(",");
            String name             = columns[0];
            String alias            = columns[1];
            Ingredient ingredient   = new Ingredient(name, alias);
            this.ingredientMap.put(name, ingredient);
        }

        // Close and return
        bufferedReader.close();
    }

    // Gets an ingredient
    public Ingredient getIngredient(String name) {
        return this.ingredientMap.get(name);
    }
}
