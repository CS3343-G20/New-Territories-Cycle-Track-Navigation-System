package SmartNavigationSystem;

import java.io.IOException;
import java.util.*;

public class UserControlPanel extends ControlPanel {

    private UserControlPanel() {
        super();
        this.controlPanel.put(0, "exist");
        this.controlPanel.put(1, "Login");
        this.controlPanel.put(2, "Register");
        this.controlPanel.put(3,"Mode");
        this.controlPanel.put(4, "Login As Admin");
    }

    private static UserControlPanel instance = new UserControlPanel();

    public static UserControlPanel getInstance() {
        return instance;
    }

    public int makeDecision(Scanner userInput) {

        String line = "";

        System.out.println("Please input a num:[select from ControlPanel]");
        int nav = 0;

        line = userInput.next();

        if (line.length() > 1) {
            System.out.println("Input format error! Please try again.");
            this.makeDecision(userInput);
        }
        nav = line.charAt(0) - 48;
        if (nav < 0 || nav > 4) {
            System.out.println("Input error! Please try again.");
            this.makeDecision(userInput);
        }

        switch (nav) {
            case 0:
                break;
            case 1:
                Member m = new Member();
                // if (m.login())
                //     this.user = m;
                // else
                //     nav = 6;
                try {
                    m.Login();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                break;
            case 2:
                Register r = new Register();
            try {
                r.register();
            } catch (IOException e) {
                e.printStackTrace();
            }
                break;
            case 3:
                User u=new User();
                System.out.println("Please choose a mode:[CyclingMode/ClimbingMode]");
                String mode=userInput.next();
                u.chooseMode(mode);
            case 4:
                Admin admin = Admin.getInstance();
                if (!admin.login())
                    nav = 6;
                break;
            default:
                System.out.println("Input error! Please try again.");
                this.makeDecision(userInput);
        }

        return nav;
    }
}
