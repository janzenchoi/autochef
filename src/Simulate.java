import backend.factories.Factory;

/**
 * For simulating the natural process of everything
 */
public class Simulate {

    // Main function
    public static void main(String args[]) {
        Factory factory = Factory.getFactory();
        System.out.println(factory.getAppliance("blender"));
    }
}