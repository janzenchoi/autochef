package backend.classes.appliances;
import java.util.ArrayList;

import backend.classes.Action;
import backend.classes.Ingredient;

/**
 * An appliance that only outputs
 */
public class Supplier extends Appliance {
    
    // Constructor
    public Supplier(String name, String alias, int priceMultiplier, int qualityMultiplier, int capacity, ArrayList<Ingredient> outputs) {
        super(name, alias, priceMultiplier, qualityMultiplier, false, capacity, 0, new ArrayList<Action>());
        for (Ingredient output : outputs) {
            super.addOutput(output);
        }
    }

    // Override to not remove anything (since it has an infinite supply)
    @Override
    public void removeOutput(Ingredient ingredient) {
        return;
    }
}
