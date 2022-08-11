package factories;
import java.util.HashMap;
import classes.Ingredient;
import classes.Order;
import helpers.Constants;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Provides orders and instructions
 */
public class FactoryOrder {
    private FactoryIngredient factoryIngredient;
    private HashMap<String, Order> orderMap;

    // Constructor
    public FactoryOrder(FactoryIngredient factoryIngredient) {
        try {
            this.factoryIngredient = factoryIngredient;
            this.orderMap = new HashMap<String, Order>();
            setOrders();
        } catch (IOException e) {}
    }

    // Get all the Actions
    private void setOrders() throws IOException {

        // Initialisation
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.ORDER_DATA_PATH));
        String line = bufferedReader.readLine();

        // Read row by row (alias | name)
        while ((line = bufferedReader.readLine()) != null) {
            String[] columns = line.split(",");
            if (columns.length == 0) {
                continue;
            }
            
            // Extract information
            String alias = columns[0];
            String name = columns[1];

            // Create order
            Ingredient ingredient = this.factoryIngredient.getIngredient(name);
            Order order = new Order(alias, ingredient);
            this.orderMap.put(alias, order);
        }

        // Close buffer
        bufferedReader.close();
    }

    // Gets the name of the ingredient given its alias
    public Order getOrder(String alias) {
        return this.orderMap.get(alias);
    }
}
