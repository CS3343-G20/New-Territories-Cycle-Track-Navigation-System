package SmartNavigationSystem;

public class ExInvalidIndex extends Exception {

    private static final long serialVersionUID = 1L;

    public ExInvalidIndex() {
        super("The input should be chosen from the listed IDs. Please try again:");
    }
}