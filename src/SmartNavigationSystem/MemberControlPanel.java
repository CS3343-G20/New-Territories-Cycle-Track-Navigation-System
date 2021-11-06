package SmartNavigationSystem;

import java.io.IOException;
import java.util.Scanner;

public class MemberControlPanel extends ControlPanel {

    private Member member;

    protected MemberControlPanel() {
        this.member = null;
        controlPanel.put(0, "Exit");
        controlPanel.put(1, "Reset Password");
        controlPanel.put(2, "Choose Mode");
        controlPanel.put(3, "Check Information");
        controlPanel.put(4, "Delete schedule");
        controlPanel.put(5, "Delete bookmark");
        controlPanel.put(6, "Make schedule");
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
        if (nav < 0 || nav >= 7) {
            System.out.println("Input error! Please try again.");
            nav = 1000;
        }

        switch (nav) {
        case 0:
            break;
        case 1:
            ((Member) (this.member)).resetPwd();
            break;
        case 2:
            System.out.println("Please choose a mode:[CyclingMode/ClimbingMode]");
            String mode = userInput.next();
            this.member.chooseMode(mode);
            break;
        case 3:
            ((Member) (this.member)).CheckInfo();
            break;
        case 4:
            boolean hasSche = JsonOperation.printMemberSchedule(this.member.getEmail());
            if (!hasSche)
                break;
            System.out.println("Please input the index of schedule that you want to delete:");
            int scheIndex = userInput.nextInt();
            ((Member) (this.member)).deleteSchedule(scheIndex);
            break;
        case 5:
            boolean hasBookm = JsonOperation.printMemberBookmark(this.member.getEmail());
            if (!hasBookm)
                break;
            System.out.println("Please input the index of bookmark that you want to delete:");
            int bookmIndex = userInput.nextInt();
            ((Member) (this.member)).deleteBookmark(bookmIndex);
            break;
        case 6:
            System.out.println("Please input the schedule date: [yyyy/mm/dd]");
            String date = userInput.next();
            System.out.println("Please choose a mode that you want to make schedule:");
            System.out.println("1: Cycling Mode\n2: Climbing Mode");
            int scheModeNum = userInput.nextInt();
            if (scheModeNum == 1) {
                this.member.chooseMode("CyclingMode");
                ((Member) (this.member)).makeSchedule("Cycling Mode: " + this.member.getRoute(), date);
            } else if (scheModeNum == 2) {
                this.member.chooseMode("ClimbingMode");
                ((Member) (this.member)).makeSchedule("Climbing Mode: " + this.member.getRoute(), date);
            } else {
                System.out.println("Mode input error!");
            }
            break;
        }

        return nav;
    }

}
