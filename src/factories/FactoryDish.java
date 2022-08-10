package factories;
import java.util.HashMap;
import helpers.Constants;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Provides a mapping between ingredient aliases and names
 */
public class FactoryDish {
    private HashMap<String, String> aliasToNameMap;
    private HashMap<String, String> nameToAliasMap;
    
    // Singleton Constructor
    public FactoryDish() {
        try {
            this.aliasToNameMap = new HashMap<String, String>();
            this.nameToAliasMap = new HashMap<String, String>();
            setDishes();
        } catch (IOException e) {}
    }

    // Get all the Actions
    private void setDishes() throws IOException {

        // Initialisation
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.DISH_DATA_PATH));
        String line = bufferedReader.readLine();

        // Read row by row (alias | name)
        while ((line = bufferedReader.readLine()) != null) {
            String[] columns = line.split(",");
            if (columns.length == 0) {
                continue;
            }
            String alias = columns[0];
            String name = columns[1];
            this.aliasToNameMap.put(alias, name);
            this.nameToAliasMap.put(name, alias);
        }

        // Close buffer
        bufferedReader.close();
    }

    // Gets the name of the ingredient given its alias
    public String getIngredientName(String alias) {
        return this.aliasToNameMap.get(alias);
    }

    // Gets the alias of the ingredient given its name
    public String getIngredientAlias(String name) {
        return this.nameToAliasMap.get(name);
    }
}
