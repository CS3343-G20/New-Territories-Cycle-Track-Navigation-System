package SmartNavigationSystem;

import java.io.IOException;
import java.util.Scanner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Main {

    public static void main(String[] args) throws IOException {

        new JsonOperation();
        // JSONObject var1 = JsonOperation.getWholeJsonObject();
        // String var2 = JsonOperation.getWholeJsonObjectString();
        // JSONArray var3 = JsonOperation.getWholeMemberInfoArray();
        // JSONObject var4 = JsonOperation.getMemberInfo("liusitong327@gmail.com");
        // JSONArray var5 = JsonOperation.getMemberScheArray("liusitong327@gmail.com");
        // JSONArray var6 = JsonOperation.getMemberBookmArray("liusitong327@gmail.com");
        // String var7 = JsonOperation.getMemberPassword("liusitong327@gmail.com");
        // JsonOperation.resetPwd("liusitong327@gmail.com", "newPwd");
        // JsonOperation.clearJsonFile();
        // JsonOperation.addNewMember("liusitong327@gmail.com", "pwd");
        // boolean var8 = JsonOperation.checkMemberExist("liusitong327@gmail.com");
        // boolean var9 = JsonOperation.checkMemberPwd("liusitong327@gmail.com",
        // "lstPwd");

        //Member m = new Member();

        // JsonOperation.addNewSchedule(new Schedule(m, "2021/10/29", new
        // CyclingMode(Graph.getInstance(), Vertices.getInstance(), new
        // Scanner(System.in))));
        // if (m.Login())
        // JsonOperation.addNewBookMark(new Bookmark(new
        // CyclingMode(Graph.getInstance(), Vertices.getInstance(), new
        // Scanner(System.in)), m));

        // JsonOperation.deleteMemberSchedule(m, 1);
        // JsonOperation.deleteMemberBookmark(m, 2);
        // JsonOperation.updateJsonFile();

        //// Schedule.sendEmail();

        Scanner in = new Scanner(System.in);
        ControlPanel cp = UserControlPanel.getInstance();
        launch(in, cp);
        in.close();

        // JsonOperation.updateJsonFile();

        // Admin.getInstance().printMemberList();

    }

    public static void launch(Scanner in, ControlPanel controlPanel) throws IOException {
        ControlPanel cp = controlPanel;
        //try {
            cp.showControlPanel();
            int nav = cp.makeDecision(in);
            if (nav == 0) {
                System.out.println("========Exit========");
                return;
            } else if (nav == 1 && cp.getClass().equals(UserControlPanel.class)) {
                User tmp_m = cp.getMember();
                cp = MemberControlPanel.getInstance();
                cp.setMember(tmp_m);
                launch(in, cp);
            } else if (nav == 4 && cp.getClass().equals(UserControlPanel.class)) {
                cp = AdminControlPanel.getInstance();
                launch(in, cp);
            } else if (nav != 0) {
                launch(in, cp);
            }
        //} catch (Exception e) {
            //System.out.println(e);
        //}
    }
}
