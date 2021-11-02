package SmartNavigationSystem;

import java.io.IOException;
import java.util.Scanner;

import com.alibaba.fastjson.JSONObject;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        ControlPanel cp = UserControlPanel.getInstance();
<<<<<<< HEAD
        launch(in, cp);
=======
        launch(cp);
        in.close();

        // JsonOperation.updateJsonFile();

        // Admin.getInstance().printMemberList();

>>>>>>> lst
    }

    public static void launch(ControlPanel controlPanel) throws IOException {

        ControlPanel cp = controlPanel;
        //try {
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
        //} catch (Exception e) {
            //System.out.println(e);
        //}
    }
}