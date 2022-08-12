package classes;

/**
 * Stores information about listings
 */
public class Listing {
    private Ingredient ingredient;
    private int price;
    private int quality;

    // Constructor
    public Listing(Ingredient ingredient, int price, int quality) {
        this.ingredient = ingredient;
        this.price      = price;
        this.quality    = quality;
    }

    // Clone Constructor
    public Listing(Listing clone) {
        this.ingredient = new Ingredient(clone.getIngredient());
        this.price = clone.getPrice();
        this.quality = clone.getQuality();
    }

    // Member Getters
    public int getPrice() { return this.price; }
    public int getQuality() { return this.quality; }
    public Ingredient getIngredient() {return this.ingredient; }

    // Is Equal
    public Boolean isEqual(Listing toCheck) {
        if (!this.ingredient.isEqual(toCheck.getIngredient())
        || this.price != toCheck.getPrice()
        || this.quality != toCheck.getQuality()) {
            return false;
        }
        return true;
    }

    // To String
    @Override
    public String toString() {
        return "{" +
            "ingredient: " + this.ingredient + ", " +
            "price: " + this.price + ", " +
            "quality: " + this.quality +
        "}";
    }
}
