package backend.factories;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import backend.classes.Action;
import backend.helpers.Constants;

/**
 * Provides action objects
 */
public class FactoryAction {
    private HashMap<String, Action> actionMap;
    
    // Constructor
    public FactoryAction() {
        try {
            this.actionMap = new HashMap<String, Action>();
            setActions();
        } catch (IOException e) {}
    }

    // Set all the Actions
    private void setActions() throws IOException {
        
        // Initialisation
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.INFO_ACTION_PATH));
        String line = bufferedReader.readLine();

        // Read row by row (| name | alias |)
        while ((line = bufferedReader.readLine()) != null) {
            String[] columns    = line.split(",");
            String name         = columns[0];
            String alias        = columns[1];
            Action action       = new Action(name, alias);
            this.actionMap.put(name, action);
        }

        // Close buffer
        bufferedReader.close();
    }

    // Gets an action
    public Action getAction(String name) {
        return this.actionMap.get(name);
    }
}
