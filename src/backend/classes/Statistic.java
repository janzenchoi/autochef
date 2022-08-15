package backend.classes;

/**
 * For representing an integer statistic
 */
public class Statistic {
    private int base;
    private int multiplier;

    // Constructor
    public Statistic(int base) {
        this.base = base;
        this.multiplier = 100; // in %
    }

    // Member getters
    public int getBase() { return this.base; }
    public int getMultiplier() { return this.multiplier; }

    // Increase multiplier
    public void increaseMultiplier(int increaseAmount) {
        this.multiplier += increaseAmount;
    }

    // Gets the statistic value
    public int getFinal() {
        int multiplied = Math.round(this.base * this.multiplier / 100);
        return (multiplied < 0) ? 0 : multiplied;
    }

    // Is Equal
    public Boolean isEqual(Statistic toCheck) {
        if (this.base != toCheck.getBase()
        || this.multiplier != toCheck.getMultiplier()) {
            return false;
        }
        return true;
    }

    // To String
    @Override
    public String toString() {
        return "{" +
            "base: " + this.base + ", " +
            "multiplier: " + this.multiplier +
        "}";
    }
}