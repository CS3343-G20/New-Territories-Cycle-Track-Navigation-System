package SmartNavigationSystem;

public class Admin extends User {

    private static Admin admin = new Admin();

    public static Admin getInstance() {
        return admin;
    }

    public boolean login() {
        return new AdminLogin().login();
    }

    public void printMemberList() {
        // Database.getInstance().printMemberList();
    }

    public void printScheduleList() {
        // Database.getInstance().printScheduleList();
    }

    public void printBookmarkList() {
        // Database.getInstance().printBookmarkList();
    }

}
