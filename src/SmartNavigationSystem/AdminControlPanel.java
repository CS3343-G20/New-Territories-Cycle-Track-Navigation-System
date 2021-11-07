package SmartNavigationSystem;

import java.io.IOException;
import java.util.Scanner;

public class AdminControlPanel extends ControlPanel {

    private static AdminControlPanel instance = new AdminControlPanel();

    private AdminControlPanel() {
        this.controlPanel.put(0, "Exit");
        this.controlPanel.put(1, "Print Member List");
        this.controlPanel.put(2, "Print Schedule List");
        this.controlPanel.put(3, "Print Bookmark List");
    }

    public static AdminControlPanel getInstance() {
        return instance;
    }


    @Override
    public int makeDecision() throws IOException {

        Scanner userInput = new Scanner(System.in);

        String line = "";

        System.out.println("Please input a num:[select from ControlPanel]");
        int nav = 0;

        line = userInput.next();

        if (line.length() > 1) {
            System.out.println("Input format error! Please try again.");
            nav = 1000;
        }
        nav = line.charAt(0) - 48;
        if (nav < 0 || nav > 5) {
            System.out.println("Input error! Please try again.");
            nav = 1000;
        }

        switch (nav) {
            case 0:
                break;
            case 1:
                Admin.printMemberList();
                break;
            case 2:
                Admin.printScheduleList();
                break;
            case 3:
                Admin.printBookmarkList();
                break;
        }

        return nav;

    }

}
