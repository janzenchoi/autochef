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
    public Instruction(String action) {
        this.action = action;
        this.subjects = new ArrayList<String>();
    }

    // Member getters
    public String getAction() { return this.action; }
    public ArrayList<String> getSubjects() { return this.subjects; }

    // Adds a subject
    public void addSubject(String subject) {
        this.subjects.add(subject);
    }

    // To String
    public String toString() {
        String subjectString = General.arrayToString(this.subjects);
        return "{" +
            "action: " + this.action + ", " +
            "subjects: " + subjectString +
        "}";
    }
}
