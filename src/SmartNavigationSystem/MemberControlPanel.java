package SmartNavigationSystem;

import java.io.IOException;
import java.util.Scanner;

public class MemberControlPanel extends ControlPanel {

    private Member member;

    protected MemberControlPanel() {
        this.member = null;
        controlPanel.put(0, "Exit");
        controlPanel.put(1, "Reset Password");
        controlPanel.put(2, "Check Information");
        controlPanel.put(3, "Choose Mode");
        controlPanel.put(4, "Delete bookmark");
        controlPanel.put(5, "Make schedule");
        controlPanel.put(6, "Delete schedule");
    }

    private static MemberControlPanel instance = new MemberControlPanel();

    public static MemberControlPanel getInstance() {
        return instance;
    }

    public Member getMember() {
        return this.member;
    }

    public void setMember(Member m) {
        this.member = m;
    }

    @Override
    public int makeDecision(Scanner userInput) throws IOException, ExInvalidIndex {

        String line = "";

        System.out.println("Please input a num:[select from ControlPanel]");
        int nav = 0;

        line = userInput.nextLine().trim();
        while (line.length() == 0 || line.length() > 1) {
            System.out.println("Input format error! Please try again.");
            line = userInput.nextLine().trim();
        }

        nav = line.charAt(0) - 48;
        if (nav < 0 || nav >= 7) {
            System.out.println("Input error! Please try again.");
            nav = 1000;
            return nav;
        }

        switch (nav) {
        case 0:
            break;
        case 1:
            member.resetPwd(userInput);
            break;
        case 2:
        	member.CheckInfo();
            break;
        case 3:
            member.chooseMode(userInput);
            break;
        case 4:
            member.deleteBookmark(userInput);
            break;
        case 5:
            member.makeSchedule(userInput);
            break;
        case 6:
            member.deleteSchedule(userInput);
            break;
        }

        return nav;
    }

}
