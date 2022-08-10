package classes.instructions;
import java.util.ArrayList;
import helpers.General;

/**
 * Stores an instruction to do something
 */
public class Instruction {
    public String action;
    public ArrayList<String> subjects;

    // Constructor
    public Instruction(String action, ArrayList<String> subjects) {
        this.action = action;
        this.subjects = subjects;
    }

    // Member getters
    public String getAction() { return this.action; }
    public ArrayList<String> getSubjects() { return this.subjects; }

    // To String
    public String toString() {
        String subjectString = General.arrayToString(this.subjects);
        return "{" +
            "action: " + this.action + ", " +
            "subject: " + subjectString +
        "}";
    }
}
