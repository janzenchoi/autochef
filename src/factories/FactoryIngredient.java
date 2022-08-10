package factories;
import java.util.HashMap;
import classes.Ingredient;
import helpers.Constants;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Provides ingredient objects
 */
public class FactoryIngredient {
    private HashMap<String, Ingredient> ingredientMap;
    
    // Singleton Constructor
    public FactoryIngredient() {
        try {
            this.ingredientMap = getIngredients();
        } catch (IOException e) {}
    }

    // Get all the ingredients
    private HashMap<String, Ingredient> getIngredients() throws IOException {

        // Initialisation
        HashMap<String, Ingredient> ingredientMap = new HashMap<String, Ingredient>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.INGREDIENTS_DATA_PATH));
        String line = bufferedReader.readLine();

        // Read row by row (name | price | quality)
        while ((line = bufferedReader.readLine()) != null) {
            String[] columns = line.split(",");
            String name = columns[0];
            int price = Integer.parseInt(columns[1]);
            int quality = Integer.parseInt(columns[2]);
            Ingredient ingredient = new Ingredient(name, price, quality);
            ingredientMap.put(name, ingredient);
        }

        // Close buffer and return
        bufferedReader.close();
        return ingredientMap;
    }

    // Gets an ingredient
    public Ingredient getIngredient(String name) {
        return ingredientMap.get(name);
    }

    // Gets a new ingredient
    public Ingredient getNewIngredient(String name) {
        Ingredient ingredient = ingredientMap.get(name);
        int price = ingredient.getPrice();
        int quality = ingredient.getQuality();
        return new Ingredient(name, price, quality);
    }
}
