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

    // Member Getters
    public String getName() { return this.name; }
    public int getPrice() { return this.price; }
    public int getQuality() { return this.quality; }

    // Execute the action
    public void act(Ingredient ingredient) {
        ingredient.addAction(this);
    }

    // To String
    public String toString() {
        return "{" +
            "name: " + name + ", " +
            "price: " + price + ", " +
            "quality: " + quality +
        "}";
    }
}
