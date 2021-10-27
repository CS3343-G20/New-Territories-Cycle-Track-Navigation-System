package SmartNavigationSystem;

public class ExInvalidID extends Exception {

    private static final long serialVersionUID = 1L;

    public ExInvalidID() {
        super("The input should be chosen from the listed IDs. Please try again:");
    }
}