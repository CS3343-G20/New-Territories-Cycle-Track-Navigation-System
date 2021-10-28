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
        JsonOperation.printMemberList();
    }

    public void printScheduleList() {
        JsonOperation.printScheduleList();
    }

    public void printBookmarkList() {
        JsonOperation.printBookmarkList();
    }

}
