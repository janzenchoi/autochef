package backend.factories;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import backend.classes.Ingredient;
import backend.classes.Listing;
import backend.helpers.Constants;

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
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.INFO_LISTING_PATH));
        String line = bufferedReader.readLine();

        // Read row by row (| ingredient | price | quality |)
        while ((line = bufferedReader.readLine()) != null) {
            String[] columns        = line.split(",");
            String ingredientName   = columns[0];
            int price               = Integer.parseInt(columns[1]);
            int quality             = Integer.parseInt(columns[2]);
            
            // Create ingredient
            Ingredient ingredient = this.factoryIngredient.getIngredient(ingredientName);
            Listing listing = new Listing(ingredient, price, quality);
            listingMap.put(ingredientName, listing);
        }

        // Close and return
        bufferedReader.close();
    }

    // Gets a listing
    public Listing getListing(String name) {
        return this.listingMap.get(name);
    }

    // Gets a copy of a listing
    public Listing getListingCopy(String name) {
        return new Listing(this.listingMap.get(name));
    }
}
