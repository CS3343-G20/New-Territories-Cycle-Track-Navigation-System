package SmartNavigationSystem;

import java.io.IOException;
import java.util.*;

public class UserControlPanel extends ControlPanel {

    private Member user;

    private UserControlPanel() {
        this.user = null;
        this.controlPanel.put(0, "exit");
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
    public int makeDecision() {

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
        if (nav < 0 || nav > 4) {
            System.out.println("Input error! Please try again.");
            nav = 1000;
        }

        switch (nav) {
        case 0:
            break;
        case 1:
            Member m = new Member();
            try {
                this.user = m.Login();
                if (this.user == null)
                    nav = 1000;
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
            // User u=new User();
            User u = new Tourist();
            System.out.println("Please choose a mode:[CyclingMode/ClimbingMode]");
            String mode = userInput.next();
            u.chooseMode(mode);
        case 4:
            Admin admin = Admin.getInstance();
            if (!admin.login())
                nav = 6;
            break;
        }

        return nav;
    }
}