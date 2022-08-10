package classes.appliances;
import classes.Action;

/**
 * Chopping board
 */
public class Board extends Appliance {
    
    // Constructor
    public Board() {
        super("Board");
        String name = "chop";
        Integer price = 0;
        Integer quality = 0;
        Action action = new Action(name, price, quality);
        setAction(action);
    }
}
