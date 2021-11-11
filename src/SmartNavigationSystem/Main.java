package SmartNavigationSystem;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        new JsonOperation();
        try {
            Schedule.sendEmail();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scanner in = new Scanner(System.in);
        ControlPanel cp = UserControlPanel.getInstance();
        launch(cp);
        in.close();
    }

    public static void launch(ControlPanel controlPanel) throws IOException {

        ControlPanel cp = controlPanel;
        try {
            cp.showControlPanel();
            int nav = cp.makeDecision();
            if (nav == 0) {
                System.out.println("========Exit========");
                return;
            } else if (nav == 1 && cp.getClass().equals(UserControlPanel.class)) {
                Member tmp_m = (Member) ((UserControlPanel) cp).getMember();
                cp = MemberControlPanel.getInstance();
                ((MemberControlPanel) cp).setMember(tmp_m);
                launch(cp);
            } else if (nav == 4 && cp.getClass().equals(UserControlPanel.class)) {
                cp = AdminControlPanel.getInstance();
                launch(cp);
            } else if (nav != 0) {
                launch(cp);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}