package SmartNavigationSystem;

public class ExInvalidDate extends Exception {

    private static final long serialVersionUID = 1L;

    public ExInvalidDate() {
        super("The date is invalid. Please try again:");
    }
}
