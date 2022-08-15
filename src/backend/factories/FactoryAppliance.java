package backend.factories;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import backend.classes.Action;
import backend.classes.appliances.Appliance;
import backend.helpers.Constants;

/**
 * Provides recipe objects
 */
public class FactoryAppliance {
    private FactoryAction factoryAction;
    private HashMap<String, Appliance> applianceMap;

    // Constructor
    public FactoryAppliance(FactoryAction factoryAction) {
        this.factoryAction = factoryAction;
        try {
            this.applianceMap = new HashMap<String, Appliance>();
            setAppliances();
        } catch (IOException e) {}
    }

    // Sets the appliances
    public void setAppliances() throws IOException {
        // Initialisation
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.INFO_APPLIANCE_PATH));
        String line = bufferedReader.readLine();

        // Read row by row (| name | alias | priceMultiplier | qualityMultiplier | remote | capacity | time | action_1 | action_2 | action_3 |)
        while ((line = bufferedReader.readLine()) != null) {
            String[] columns = line.split(",");

            // Extract name and alias
            String name = columns[0];
            String alias = columns[1];
            
            // Extract price and quality
            int priceMultiplier = Integer.parseInt(columns[2]);
            int qualityMultiplier = Integer.parseInt(columns[3]);

            // Extract remote, capacity, and time
            Boolean remote = Boolean.parseBoolean(columns[4]);
            int capacity = Integer.parseInt(columns[5]);
            int time = Integer.parseInt(columns[6]);

            // Extract actions
            ArrayList<Action> actions = new ArrayList<Action>();
            for (int i = 7; i < columns.length; i++) {
                Action action = this.factoryAction.getAction(columns[i]);
                actions.add(action);
            }

            // Create appliance object
            Appliance appliance = new Appliance(name, alias, priceMultiplier, qualityMultiplier, remote, capacity, time, actions);
            this.applianceMap.put(name, appliance);
        }

        // Close and return
        bufferedReader.close();
    }

    // Gets an appliance
    Appliance getAppliance(String name) {
        return this.applianceMap.get(name);
    }
}