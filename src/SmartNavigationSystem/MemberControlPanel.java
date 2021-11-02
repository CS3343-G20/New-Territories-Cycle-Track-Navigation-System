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
        //controlPanel.put(7, "Add bookmark");
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
            System.out.println("Input format error!");
            nav = 1000;
        }
        nav = line.charAt(0) - 48;
        if (nav < 0 || nav > 7) {
            System.out.println("Input error!");
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
            System.out.println("Please input the index of schedule that you want to delete:");
            int scheIndex = userInput.nextInt();
            Schedule.deleteSchedule(((Member) (this.member)), scheIndex);
            break;
        case 5:
            System.out.println("Please input the index of bookmark that you want to delete:");
            int bookmIndex = userInput.nextInt();
            Bookmark.deleteBookmark(((Member) (this.member)), bookmIndex);
            break;
        case 6:
            System.out.println("Please input the schedule date: [yyyy/mm/dd]");
            String date = userInput.next();
            System.out.println("Please choose a mode that you want to make schedule:");
            System.out.println("1: Cycling Mode\n2: Climbing Mode");
            int scheModeNum = userInput.nextInt();
            if (scheModeNum == 1) {
                Schedule.makeSchedule("Cycling Mode", date, ((Member) (this.member)));
            } else if (scheModeNum == 2) {
                Schedule.makeSchedule("Climbing Mode", date, ((Member) (this.member)));
            } else {
                System.out.println("Bookmark mode input error!");
            }
            break;
        }

        return nav;
    }

}
