package SmartNavigationSystem;

public class ExWrongNumberFormat extends Exception {
    private static final long serialVersionUID = 1L;

    public ExWrongNumberFormat() {
        super("The input should be an integer. Please try again:");
    }
}
