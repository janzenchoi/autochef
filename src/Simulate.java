import classes.instructions.Instructor;

/**
 * For simulating the natural process of everything
 */
public class Simulate {

    // Main function
    public static void main(String args[]) {
        Instructor instructor = new Instructor();
        String instructionsString = instructor.getInstructionsString("Chicken Burger");
        System.out.println(instructionsString);
    }
}