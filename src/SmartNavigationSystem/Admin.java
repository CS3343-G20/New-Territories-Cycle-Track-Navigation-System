package SmartNavigationSystem;

public class Admin extends User {

    private static Admin admin = new Admin();

    public static Admin getInstance() {
        return admin;
    }

    public boolean login() {
        return AdminLogin.login();
    }
 
    public static void printMemberList() {
        JsonOperation.printMemberList();
    }

    public static void printScheduleList() {
        JsonOperation.printScheduleList();
    }

    public static void printBookmarkList() {
        JsonOperation.printBookmarkList();
    }

}