package SmartNavigationSystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MemberControlPanel extends ControlPanel {

    private MemberControlPanel() {
        this.user = new Member();
        controlPanel.put(0, "exit");
        controlPanel.put(1, "Reset Password");
        controlPanel.put(2, "Mode");
        controlPanel.put(3, "Check Information");
    }

    private static MemberControlPanel instance = new MemberControlPanel();

    public static MemberControlPanel getInstance() {
        return instance;
    }

    @Override
    public int makeDecision(Scanner userInput) throws IOException {
        String line = "";

        System.out.println("Please input a num:[select from ControlPanel]");
        int nav = 0;

        line = userInput.next();

        if (line.length() > 1) {
            System.out.println("Input format error! Please try again.");
            this.makeDecision(userInput);
        }
        nav = line.charAt(0) - 48;
        if (nav < 0 || nav > 5) {
            System.out.println("Input error! Please try again.");
            this.makeDecision(userInput);
        }

        switch (nav) {
        case 0:
            break;
        case 1:
            ((Member) (this.user)).resetPwd();
            break;
        case 2:
            System.out.println("Please choose a mode:[CyclingMode/ClimbingMode]");
            String mode = userInput.next();
            this.user.chooseMode(mode);
        case 3:
            ((Member) (this.user)).CheckInfo();
            break;
        }

        return nav;

    }

}
