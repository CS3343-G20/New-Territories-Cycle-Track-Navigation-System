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
    public int makeDecision(Scanner userInput) throws IOException {

        String line = "";

        System.out.println("Please input a num:[select from ControlPanel]");
        int nav = 0;

        line = userInput.nextLine().trim();
        while (line.length() == 0 || line.length() > 1) {
            System.out.println("Input format error! Please try again.");
            System.out.println("Please input a num:[select from ControlPanel]");
            line = userInput.nextLine().trim();
        }

        nav = line.charAt(0) - 48;
        if (nav < 0 || nav > 3) {
            System.out.println("Input error!");
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
