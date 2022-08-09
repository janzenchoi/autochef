package helpers;
import java.time.Duration;
import java.time.Instant;

// For visualising progress
public class Progressor {
    Instant initialStart;
    Instant previousStart;
    int processIndex;
    int maxMessageSize;
    String separator;

    // Constructor
    public Progressor() {

        // Initialising
        this.initialStart = Instant.now();
        this.previousStart = this.initialStart;
        this.maxMessageSize = 30;

        // Initial visualisation
        this.separator = new String(new char[this.maxMessageSize + 20]).replace("\0", "=");
        String clearScreen = new String(new char[20]).replace("\0", "\n");
        System.out.println(clearScreen);
        System.out.println(this.separator);
    }

    // Indicates the start of a process
    public void start(String message) {
        int paddingSize = maxMessageSize - message.length();
        String padding = new String(new char[paddingSize]).replace("\0", ".");
        System.out.print("  " + message + " " + padding + " ");
    }

    // Indicates the end of a process
    public void end() {
        Duration duration = Duration.between(this.previousStart, Instant.now());
        this.previousStart = Instant.now();
        String durationString = String.valueOf(Math.round(duration.toMillis()));
        System.out.println("Done! (" + durationString + "ms)");
    }

    // Finishes all the processes
    public void endAll() {
        Duration duration = Duration.between(this.initialStart, Instant.now());
        double durationSeconds = Math.round(duration.toMillis() / 10) / 100;
        String durationString = String.valueOf(durationSeconds);
        System.out.println(this.separator);
        System.out.println("  Finished everything in " + durationString + " seconds!");
        System.out.println(this.separator + "\n");
    }
}
