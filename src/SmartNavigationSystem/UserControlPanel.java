package SmartNavigationSystem;

import java.io.IOException;
import java.util.*;

public class UserControlPanel extends ControlPanel {

    private Member user;

    protected UserControlPanel() {
        this.user = null; 
        this.controlPanel.put(0, "Exit");
        this.controlPanel.put(1, "Login");
        this.controlPanel.put(2, "Register");
        this.controlPanel.put(3, "Choose Mode");
        this.controlPanel.put(4, "Login As Admin");
    }

    private static UserControlPanel instance = new UserControlPanel();

    public static UserControlPanel getInstance() {
        return instance;
    }

    public Member getMember() {
        return this.user;
    }

    public void setMember(Member m) {
        this.user = m;
    }

    @Override
    public int makeDecision(Scanner userInput) {

        System.out.println("Please input a num:[select from ControlPanel]");
        int nav = 0;

        String line = userInput.nextLine();

        if (line.length() > 1) {
            System.out.println("Input format error! Please try again.");
            nav = 1000;
        }
        nav = line.charAt(0) - 48;
        if (nav < 0 || nav > 4) {
            System.out.println("Input error! Please try again.");
            nav = 1000;
        }

        switch (nav) {
        case 0:
            break;
        case 1:
            try {
                this.user = new Member().Login(userInput);
                if (this.user == null)
                    nav = 1000;
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        case 2:
            try {
                new Register().register(userInput);
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        case 3: 
            new User().chooseMode(userInput);
            break;
        case 4:
            if (!Admin.getInstance().login(userInput))
                nav = 6;
            break;
        }

        return nav;
    }
}