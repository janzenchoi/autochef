import java.io.IOException;
import helpers.Progressor;

/**
 * For simulating the natural process of everything
 */
public class Simulate {

    // Main function
    public static void main(String args[]) throws IOException {
        
        // Initialisation
        Progressor progressor = new Progressor();
        Database database = new Database();
        
        // Load ingredients
        progressor.start("Loading in ingredients");
        database.getIngredients();
        progressor.end();
        
        // Load actions
        progressor.start("Loading in actions");
        database.getActions();
        progressor.end();

        // End start
        progressor.endAll();
    }
}