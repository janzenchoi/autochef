package classes;

/**
 * Stores information about dish listings
 */
public class Listing {
    private String alias;
    private Ingredient ingredient;
    private int price;
    private int quality;

    // Constructor
    public Listing(String alias, Ingredient ingredient, int price, int quality) {
        this.alias      = alias;
        this.ingredient = ingredient;
        this.price      = price;
        this.quality    = quality;
    }

    // Clone Constructor
    public Listing(Listing clone) {
        this.alias = clone.getAlias();
        this.ingredient = new Ingredient(clone.getIngredient());
        this.price = clone.getPrice();
        this.quality = clone.getQuality();
    }

    // Member Getters
    public String getAlias() { return this.alias; }
    public int getPrice() { return this.price; }
    public int getQuality() { return this.quality; }
    public Ingredient getIngredient() {return this.ingredient; }

    // Is Equal
    public Boolean isEqual(Listing toCheck) {
        if (!this.alias.equals(toCheck.getAlias())
        || !this.ingredient.isEqual(toCheck.getIngredient())
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
            "alias: " + this.alias + ", " +
            "ingredient: " + this.ingredient + ", " +
            "price: " + this.price + ", " +
            "quality: " + this.quality +
        "}";
    }
}
