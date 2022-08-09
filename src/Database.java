import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import helpers.Constants;
import classes.Action;
import classes.Ingredient;

// Populating this simulation
public class Database {
    private ArrayList<Ingredient> ingredientList;
    private ArrayList<Action> actionList;
    
    // Constructor
    public Database() {
        ingredientList = new ArrayList<Ingredient>();
        actionList = new ArrayList<Action>();
    }
    
    // Get all the ingredients (via CSV)
    public void getIngredients() throws IOException {

        // Prepare CSV reading
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.INGREDIENTS_DATA_PATH));
        String line = bufferedReader.readLine();

        // Read row by row
        while ((line = bufferedReader.readLine()) != null) {

            // Extract column information (name | price | quality)
            String[] columns = line.split(",");
            String name = columns[0];
            int price   = Integer.parseInt(columns[1]);
            int quality = Integer.parseInt(columns[2]);

            // Create ingredient
            Ingredient ingredient = new Ingredient(name, price, quality);
            this.ingredientList.add(ingredient);
        }
        bufferedReader.close();
    }

    // Get all the actions (via CSV)
    public void getActions() throws IOException {

        // Prepare CSV reading
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.ACTIONS_DATA_PATH));
        String line = bufferedReader.readLine();

        // Read row by row
        while ((line = bufferedReader.readLine()) != null) {

            // Extract column information (actionProcess | actionResult | price | quality)
            String[] columns = line.split(",");
            String actionProcess    = columns[0];
            String actionResult     = columns[1];
            int price               = Integer.parseInt(columns[2]);
            int quality             = Integer.parseInt(columns[2]);

            // Create action
            Action action = new Action(actionProcess, actionResult, price, quality);
            this.actionList.add(action);
        }
        bufferedReader.close();
    }
}
