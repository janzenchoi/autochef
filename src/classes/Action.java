package classes;

// Action Class
public class Action {
    private int price;
    private int quality;
    private String actionProcess;
    private String actionResult;

    // Constructor
    public Action(String actionProcess, String actionResult, int price, int quality) {
        this.actionProcess = actionProcess;
        this.actionResult = actionResult;
        this.price = price;
        this.quality = quality;
    }

    // Member Getters
    public String getActionProcess() { return this.actionProcess; }
    public String getActionResult() { return this.actionResult; }
    public int getPrice() { return this.price; }
    public int getQuality() { return this.quality; }

    // Execute the action
    public void act(Ingredient ingredient) {
        ingredient.addAction(this);
    }
}
