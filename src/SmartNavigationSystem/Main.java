package SmartNavigationSystem;

import java.io.IOException;
import java.util.Scanner;

import org.junit.After;

import com.alibaba.fastjson.JSONObject;

public class Main {

    public static void main(String[] args) throws IOException {
        new JsonOperation();
        
        String email = "cs3343g20system@gmail.com";
        String pwd = "pwd";
        
 		JSONObject obj = JsonOperation.getMemberInfo(email);
 		if (obj == null) {
 			JsonOperation.addNewMember(email, pwd);
 		} 
 		
        try {
            Schedule.sendEmail();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scanner in = new Scanner(System.in);
        ControlPanel cp = UserControlPanel.getInstance();
        launch(cp, in);
        in.close();
    }

    public static void launch(ControlPanel controlPanel, Scanner in) throws IOException {

        ControlPanel cp = controlPanel;
        try {
            cp.showControlPanel();
            int nav = cp.makeDecision(in);
            if (nav == 0) {
                System.out.println("=========Exit========");
                return;
            } else if (nav == 1 && cp.getClass().equals(UserControlPanel.class)) {
                Member tmp_m = (Member) ((UserControlPanel) cp).getMember();
                cp = MemberControlPanel.getInstance();
                ((MemberControlPanel) cp).setMember(tmp_m);
                launch(cp, in);
            } else if (nav == 4 && cp.getClass().equals(UserControlPanel.class)) {
                cp = AdminControlPanel.getInstance();
                launch(cp, in);
            } else if (nav != 0) {
                launch(cp, in);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    } 
}