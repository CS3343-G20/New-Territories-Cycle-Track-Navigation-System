package SmartNavigationSystem;

import java.io.IOException;
import java.util.Scanner;

public class AdminControlPanel extends ControlPanel {

    private static AdminControlPanel instance = new AdminControlPanel();
    private User user;

    private AdminControlPanel() {
        this.user = new Admin();
        this.controlPanel.put(0, "Exit");
        this.controlPanel.put(1, "Print Member List");
        this.controlPanel.put(2, "Print Schedule List");
        this.controlPanel.put(3, "Print Bookmark List");
        //this.controlPanel.put(4, "Mountain Climbing Trails");
        //this.controlPanel.put(5, "Cycle Trails");
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
            this.makeDecision();
        }
        nav = line.charAt(0) - 48;
        if (nav < 0 || nav > 5) {
            System.out.println("Input error! Please try again.");
            this.makeDecision();
        }

        switch (nav) {
            case 0:
                break;
            case 1:
                ((Admin) (this.user)).printMemberList();
                break;
            case 2:
                ((Admin) (this.user)).printScheduleList();
                break;
            case 3:
                ((Admin) (this.user)).printBookmarkList();
                break;
            /*
            case 4:
                this.user.MountainClimbingTrails();
                break;
            case 5:
                this.user.CycleTrails();
                break;
            */
        }

        return nav;

    }

}
