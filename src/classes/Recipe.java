package classes;
import java.util.ArrayList;
import helpers.General;

/**
 * For parsing recipe information
 */
public class Recipe {
    private String name;
    private int price;
    private int quality;
    private ArrayList<Ingredient> ingredients;
    
    // Constructor
    public Recipe(String name) {
        this.name = name;
        this.price = 0;
        this.quality = 0;
        this.ingredients = new ArrayList<Ingredient>();
    }

    // Member Getters
    public String getName() { return this.name; }
    public int getPrice() { return this.price; }
    public int getQuality() { return this.quality; }
    public ArrayList<Ingredient> getIngredients() { return this.ingredients; };

    // Adds an ingredient
    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
        this.price += ingredient.getPrice();
        this.quality += ingredient.getQuality();
        for (Action action : ingredient.getActions()) {
            this.price += action.getPrice();
            this.quality += action.getQuality();
        }
    }

    // To String
    public String toString() {
        String ingredientsString = General.arrayToString(this.ingredients);
        return "{" +
            "name: " + name + ", " +
            "price: " + price + ", " +
            "quality: " + quality + ", " +
            "ingredients: " + ingredientsString +
        "}";
    }
}
