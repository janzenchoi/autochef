package backend.classes;

/**
 * For storing listing orders
 */
public class Order {
    private Listing listing;
    private Statistic price;
    private Statistic quality;

    // Constructor
    public Order(Listing listing) {
        this.listing    = listing;
        this.price      = new Statistic(this.listing.getBasePrice());
        this.quality    = new Statistic(this.listing.getBaseQuality());
    }

    // Member getters
    public Listing getListing() { return this.listing; }
    public Statistic getPrice() { return this.price; }
    public Statistic getQuality() { return this.quality; }

    // To String
    @Override
    public String toString() {
        return "{" +
            "listing: " + this.listing + ", " +
            "price: " + this.price + ", " +
            "quality: " + this.quality +
        "}";
    }
}
