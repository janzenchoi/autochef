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
            this.actionMap = getActions();
        } catch (IOException e) {}
    }

    // Get all the Actions
    private HashMap<String, Action> getActions() throws IOException {

        // Initialisation
        HashMap<String, Action> actionMap = new HashMap<String, Action>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.ACTIONS_DATA_PATH));
        String line = bufferedReader.readLine();

        // Read row by row (name | price | quality)
        while ((line = bufferedReader.readLine()) != null) {
            String[] columns = line.split(",");
            String name = columns[0];
            int price = Integer.parseInt(columns[1]);
            int quality = Integer.parseInt(columns[2]);
            Action action = new Action(name, price, quality);
            actionMap.put(name, action);
        }

        // Close buffer and return
        bufferedReader.close();
        return actionMap;
    }

    // Gets an action
    public Action getAction(String name) {
        return actionMap.get(name);
    }

    // Gets a new action
    public Action getNewAction(String name) {
        Action action = actionMap.get(name);
        int price = action.getPrice();
        int quality = action.getQuality();
        return new Action(name, price, quality);
    }
}
