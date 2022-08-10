package factories;
import java.util.HashMap;
import classes.Action;
import helpers.Constants;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Provides action objects
 */
public class FactoryAction {
    private HashMap<String, Action> actionMap;
    
    // Singleton Constructor
    public FactoryAction() {
        try {
            this.actionMap = new HashMap<String, Action>();
            setActions();
        } catch (IOException e) {}
    }

    // Get all the Actions
    private void setActions() throws IOException {

        // Initialisation
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.ACTION_DATA_PATH));
        String line = bufferedReader.readLine();

        // Read row by row (name | price | quality)
        while ((line = bufferedReader.readLine()) != null) {
            String[] columns = line.split(",");
            if (columns.length == 0) {
                continue;
            }
            
            // Extract information
            String name = columns[0];
            int price = Integer.parseInt(columns[1]);
            int quality = Integer.parseInt(columns[2]);
            
            // Create action
            Action action = new Action(name, price, quality);
            this.actionMap.put(name, action);
        }

        // Close buffer
        bufferedReader.close();
    }

    // Gets an action
    public Action getAction(String name) {
        return this.actionMap.get(name);
    }

    // Gets a new action
    public Action getNewAction(String name) {
        return new Action(getAction(name));
    }
}
