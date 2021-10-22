package Java;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    // public static void main(String args[])
    // {
    //     // ControlPanel cp = new ControlPanel();
    //     // cp.showControlPanel();
    //     // cp.makeDecision();
    // }

    private static final UserControlPanel AdminControlPanel = null;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        ControlPanel cp = UserControlPanel.getInstance();
        launch(in, cp);
        in.close();

        Admin.getInstance().printMemberList();
    }

    public static void launch(Scanner in, ControlPanel controlPanel) {
        ControlPanel cp = controlPanel;
        try {
            cp.showControlPanel();
            int nav = cp.makeDecision(in);
            if (nav == 0) {
                System.out.println("========Exit========");
                return;
            } else if (nav == 1 && cp.getClass().equals(UserControlPanel.class)) {
                Member tmp_m = ((Member)cp.getMember());
                cp = MemberControlPanel.getInstance();
                cp.setMember(tmp_m);
                launch(in, cp);
            } else if (nav == 5 && cp.getClass().equals(UserControlPanel.class)) {
                cp = AdminControlPanel.getInstance();
                launch(in, cp);
            } else if (nav != 0) {
                launch(in, cp);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}