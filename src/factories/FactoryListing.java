package factories;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import classes.Ingredient;
import classes.Listing;
import helpers.Constants;

/**
 * Provides listing objects
 */
public class FactoryListing {
    private FactoryIngredient factoryIngredient;
    private HashMap<String, Listing> listingMap;

    // Constructor
    public FactoryListing(FactoryIngredient factoryIngredient) {
        this.factoryIngredient = factoryIngredient;
        try {
            this.listingMap = new HashMap<String, Listing>();
            setListings();
        } catch (IOException e) {}
    }

    // Set the listings
    private void setListings() throws IOException {

        // Initialisation
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.LISTING_DATA_PATH));
        String line = bufferedReader.readLine();

        // Read row by row (| alias | ingredientName | price | quality |)
        while ((line = bufferedReader.readLine()) != null) {
            String[] columns        = line.split(",");
            String alias            = columns[0];
            String ingredientName   = columns[1];
            int price               = Integer.parseInt(columns[2]);
            int quality             = Integer.parseInt(columns[3]);
            
            // Create ingredient
            Ingredient ingredient = this.factoryIngredient.getIngredient(ingredientName);
            Listing listing = new Listing(alias, ingredient, price, quality);
            listingMap.put(alias, listing);
        }

        // Close and return
        bufferedReader.close();
    }

    // Gets a listing
    public Listing getListing(String alias) {
        return this.listingMap.get(alias);
    }

    // Gets a copy of a listing
    public Listing getListingCopy(String alias) {
        return new Listing(this.listingMap.get(alias));
    }
}
