import classes.instructions.Instructor;
import factories.Factory;

/**
 * For simulating the natural process of everything
 */
public class Simulate {

    // Main function
    public static void main(String args[]) {
        Factory factory = Factory.getFactory();
        System.out.println(factory.getListing("burger_chicken"));
        Instructor instructor = new Instructor();
        System.out.println(instructor.instructDisplay("burger_chicken"));
    }
}