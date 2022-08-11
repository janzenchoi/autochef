package classes;
import java.util.ArrayList;
import helpers.General;

/**
 * Stores a customer's order
 */
public class Order {
    private String alias;
    private String name;
    private ArrayList<Instruction> instructions;

    // Constructor
    public Order(String alias, String name) {
        this.alias = alias;
        this.name = name;
        this.instructions = new ArrayList<Instruction>();
    }

    // Member getters
    public String getAlias() { return this.alias; }
    public String getName() { return this.name; }
    public ArrayList<Instruction> getInstructions() {return this.instructions; }

    // Adds an instruction
    public void addInstruction(Instruction instruction) {
        this.instructions.add(instruction);
    }

    // To String
    @Override
    public String toString() {
        String instructionString = General.arrayToString(this.instructions);
        return "{" +
            "alias: " + this.alias + ", " +
            "name: " + this.name + ", " +
            "instructions: " + instructionString +
        "}";
    }

    // To String (with newline formatting)
    public String toStringFormatted() {
        String formatted = "";
        for (Instruction instruction : this.instructions) {
            formatted += instruction.toString() + "\n";
        }
        return formatted;
    }
}
