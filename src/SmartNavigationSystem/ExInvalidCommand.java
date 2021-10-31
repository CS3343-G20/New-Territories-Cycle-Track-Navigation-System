package SmartNavigationSystem;

public class ExInvalidCommand extends Exception {

    private static final long serialVersionUID = 1L;

    public ExInvalidCommand() {
        super("The input should be chosen from the listed commands above. Please try again:");
    }
}