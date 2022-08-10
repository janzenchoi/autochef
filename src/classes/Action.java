package classes;

/**
 * Action conducted by appliances
 */
public class Action {
    private String name;
    private int price;
    private int quality;

    // Constructor
    public Action(String name, int price, int quality) {
        this.name = name;
        this.price = price;
        this.quality = quality;
    }

    // Clone constructor
    public Action(Action clone) {
        this.name = clone.getName();
        this.price = clone.getPrice();
        this.quality = clone.getQuality();
    }

    // Member Getters
    public String getName() { return this.name; }
    public int getPrice() { return this.price; }
    public int getQuality() { return this.quality; }

    // To String
    @Override
    public String toString() {
        return "{" +
            "name: " + this.name + ", " +
            "price: " + this.price + ", " +
            "quality: " + this.quality +
        "}";
    }
}
