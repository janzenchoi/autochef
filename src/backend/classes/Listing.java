package backend.classes;

/**
 * Stores information about listings
 */
public class Listing {
    private Ingredient ingredient;
    private int basePrice;
    private int baseQuality;

    // Constructor
    public Listing(Ingredient ingredient, int basePrice, int baseQuality) {
        this.ingredient     = ingredient;
        this.basePrice      = basePrice;
        this.baseQuality    = baseQuality;
    }

    // Clone Constructor
    public Listing(Listing clone) {
        this.ingredient     = clone.getIngredient();
        this.basePrice      = clone.getBasePrice();
        this.baseQuality    = clone.getBaseQuality();
    }

    // Member Getters
    public int getBasePrice() { return this.basePrice; }
    public int getBaseQuality() { return this.baseQuality; }
    public Ingredient getIngredient() {return this.ingredient; }

    // Is Equal
    public Boolean isEqual(Listing toCheck) {
        if (!this.ingredient.isEqual(toCheck.getIngredient())
        || this.basePrice != toCheck.getBasePrice()
        || this.baseQuality != toCheck.getBaseQuality()) {
            return false;
        }
        return true;
    }

    // To String
    @Override
    public String toString() {
        return "{" +
            "ingredient: " + this.ingredient + ", " +
            "basePrice: " + this.basePrice + ", " +
            "baseQuality: " + this.baseQuality +
        "}";
    }
}
