package classes;

/**
 * For storing listing orders
 */
public class Order {
    private Listing listing;
    private int priceAdd;
    private int qualityAdd;
    private float priceMultiplier;
    private float qualityMultiplier;

    // Constructor
    public Order(Listing listing) {
        this.listing = listing;
        this.priceAdd = 0;
        this.qualityAdd = 0;
        this.priceMultiplier = 1;
        this.qualityMultiplier = 1;
    }

    // Member getters
    public Listing getListing() { return this.listing; }
    public int getPriceAdd() { return this.priceAdd; }
    public int getQualityAdd() { return this.qualityAdd; }
    public float getPriceMultipler() { return this.priceMultiplier; }
    public float getQualityMultipler() { return this.qualityMultiplier; }

    // Increase functions
    public void increasePriceAdd(int increaseAmount) { this.priceAdd += increaseAmount; }
    public void increaseQualityAdd(int increaseAmount) { this.qualityAdd += increaseAmount; }
    public void increasePriceMultiplier(float increaseAmount) { this.priceMultiplier += increaseAmount; }
    public void increaseQualityMultiplier(float increaseAmount) { this.qualityMultiplier += increaseAmount; }

    // Gets the rounded price after applying modifiers
    public int getPrice() {
        int originalPrice = this.listing.getPrice();
        originalPrice = Math.round(originalPrice * this.priceMultiplier);
        originalPrice += this.priceAdd;
        return originalPrice;
    }

    // Gets the rounded quality after applying modifiers
    public int getQuality() {
        int originalQuality = this.listing.getQuality();
        originalQuality = Math.round(originalQuality * this.qualityMultiplier);
        originalQuality += this.qualityAdd;
        return originalQuality;
    }
}
