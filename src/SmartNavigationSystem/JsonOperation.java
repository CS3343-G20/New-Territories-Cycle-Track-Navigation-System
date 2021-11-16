package SmartNavigationSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import javax.mail.MessagingException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonOperation {

    private static JSONObject wholeJsonObject;

    public JsonOperation() throws FileNotFoundException {
        File f = new File("docs/MemberInfo.json");
        Scanner fin = new Scanner(f);
        String content = fin.useDelimiter("\\Z").next();
        fin.close();
        wholeJsonObject = JSONObject.parseObject(content);
    }

    public static JSONObject getMemberInfo(String email) throws FileNotFoundException {

        JSONArray wholeMemberInfo_arr = wholeJsonObject.getJSONArray("memberInfo");

        for (int i = 0; i < wholeMemberInfo_arr.size(); i++) {
            JSONObject memberInfo = wholeMemberInfo_arr.getJSONObject(i);
            String memberEmail = memberInfo.getString("email");
            if (memberEmail.equals(email)) {
                return memberInfo;
            }
        }
        return null;

    }

    public static String getWholeObjectString() {
        return wholeJsonObject.toJSONString();
    }

    public static JSONArray getMemberScheArray(String email) throws FileNotFoundException {
        JSONObject memberInfo = getMemberInfo(email);
        return memberInfo.getJSONArray("schedules");
    }

    public static JSONArray getMemberBookmArray(String email) throws FileNotFoundException {

        JSONObject memberInfo = getMemberInfo(email);
        return memberInfo.getJSONArray("bookmarks");

    }

    public static String getMemberPassword(String email) throws FileNotFoundException {

        JSONObject memberInfo = getMemberInfo(email);
        return memberInfo.getString("password");

    }

    public static void resetPwd(String email, String pwd) throws IOException {

        JSONObject memberInfo = getMemberInfo(email);
        memberInfo.replace("password", pwd);

        updateJsonFile();

    }

    public static void clearJsonFile() throws IOException {
        FileWriter fout = new FileWriter("docs\\MemberInfo.json");
        fout.close();
    }

    public static void addNewMember(String email, String password) throws IOException {
        JSONObject e = JSON.parseObject(
                "{\"password\":\"" + password + "\",\"email\":\"" + email + "\", \"schedules\": [],\"bookmarks\": []}");
        wholeJsonObject.getJSONArray("memberInfo").add(e);
        updateJsonFile();
    }

    public static boolean checkMemberExist(String email) throws FileNotFoundException {

        JSONObject memberInfo = getMemberInfo(email);
        if (memberInfo == null)
            return false;
        else
            return true;

    }

    public static boolean checkMemberPwd(String email, String pwd) throws FileNotFoundException {
        return getMemberPassword(email).equals(pwd);
    }

    public static void addNewSchedule(Member member, String date, String route) throws IOException {

        JSONArray arr = getMemberScheArray(member.getEmail());

        int index = arr.size() + 1;
        // Here is to modify schedule event
        JSONObject obj = JSON.parseObject("{\"scheduleIndex\": " + index + ",\"scheduleDate\": \"" + date
                + "\",\"state\": \"" + "true" + "\",\"event\": \"" + route + "\"}");

        arr.add(obj);

        updateJsonFile();

    }

    public static void addNewBookmark(String route, Member member) {

        try {
            JSONArray arr = getMemberBookmArray(member.getEmail());
            int index = arr.size() + 1;

            // Here is to add more bookmark info
            JSONObject obj = JSON.parseObject("{\"bookmarkIndex\": " + index + ",\"bookmarkType\": \"" + route + "\"}");
            arr.add(obj);

            updateJsonFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void deleteMemberSchedule(Member member, int index) throws IOException, ExInvalidIndex {

        JSONArray memberScheduleArray = getMemberScheArray(member.getEmail());

        boolean flag = false;
        
        if (index > memberScheduleArray.size()) {
        	System.out.print("Index input error\n");
        	return;
        }

        for (int i = 0; i < memberScheduleArray.size(); i++) {
            JSONObject obj = memberScheduleArray.getJSONObject(i);
            if (obj.getIntValue("scheduleIndex") == index) {
                memberScheduleArray.remove(obj);
                flag = true;
                break;
            }
        }

        for (int i = 0; i < memberScheduleArray.size(); i++) {
            JSONObject obj = memberScheduleArray.getJSONObject(i);
            obj.replace("scheduleIndex", i + 1);
        }

        if (flag) {
            updateJsonFile();
            System.out.println("Delete successfully!");
        } else {
            throw new ExInvalidIndex();
        }

    }

    public static void deleteMemberBookmark(Member member, int index) throws ExInvalidIndex {

        try {
            JSONArray memberBookmarkArray = getMemberBookmArray(member.getEmail());

            boolean flag = false;
            
            if (index > memberBookmarkArray.size()) {
            	System.out.print("Index input error\n");
            	return;
            }


            for (int i = 0; i < memberBookmarkArray.size(); i++) {
                JSONObject obj = memberBookmarkArray.getJSONObject(i);
                if (obj.getIntValue("bookmarkIndex") == index) {
                    memberBookmarkArray.remove(obj);
                    flag = true;
                    break;
                }
            }

            for (int i = 0; i < memberBookmarkArray.size(); i++) {
                JSONObject obj = memberBookmarkArray.getJSONObject(i);
                obj.replace("bookmarkIndex", i + 1);
            }

            if (flag) {
                updateJsonFile();
                System.out.println("Delete successfully!");
            } else {
                throw new ExInvalidIndex();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void updateJsonFile() throws IOException {
        File memberListFile = new File("docs\\MemberInfo.json");
        FileOutputStream fos = new FileOutputStream(memberListFile, true);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        JsonOperation.clearJsonFile();
        osw.write(getWholeObjectString());
        osw.close();
    }

    public static JSONArray getWholeMemberInfoArray() {
        return wholeJsonObject.getJSONArray("memberInfo");
    }

    public static void printMemberInfo(String email) throws FileNotFoundException {

        System.out.print("Email: " + email + "\n");
        System.out.print("Password: " + JsonOperation.getMemberPassword(email) + "\n\n");
        JsonOperation.printMemberSchedule(email);
        JsonOperation.printMemberBookmark(email);

    }

    public static boolean printMemberSchedule(String email) throws FileNotFoundException {

        JSONArray memberScheArray = getMemberScheArray(email);
        int scheNum = memberScheArray.size();
        if (scheNum == 0) {
            System.out.print("You haven't make schedules.\n\n");
            return false;
        } else {
            System.out.print("You have " + scheNum + " schedules:\n\n");
            for (int i = 0; i < scheNum; i++) {
                JSONObject memberSche = memberScheArray.getJSONObject(i);
                System.out.print("Schedule index: " + memberSche.getIntValue("scheduleIndex") + "\nEvent: "
                        + memberSche.getString("event") + "\nSchedule date: " + memberSche.getString("scheduleDate")
                        + "\n\n");
            }
        }
        return true;
    }

    public static boolean printMemberBookmark(String email) throws FileNotFoundException {

        JSONArray memberBookmArray = getMemberBookmArray(email);
        int bookmNum = memberBookmArray.size();
        if (bookmNum == 0) {
            System.out.print("You haven't add bookmarks.\n\n");
            return false;
        } else {
            System.out.print("You have " + bookmNum + " bookmarks:\n\n");
            for (int i = 0; i < bookmNum; i++) {
                JSONObject memberBookm = memberBookmArray.getJSONObject(i);
                System.out.print("Bookmark index: " + memberBookm.getIntValue("bookmarkIndex") + "\nType: "
                        + memberBookm.getString("bookmarkType") + "\n");
            }
        }
        return true;
    }

    public static String getAdminToken() throws FileNotFoundException {

        File f = new File("docs/AdminInfo.json");
        Scanner fin = new Scanner(f);
        String content = fin.useDelimiter("\\Z").next();
        JSONObject adminJsonObject = JSONObject.parseObject(content);
        fin.close();
        return adminJsonObject.getString("token");

    }

    public static void printMemberList() {

        JSONArray wholeMemberInfoArray = getWholeMemberInfoArray();
        System.out.print("Member list:\n");
        for (int i = 0; i < wholeMemberInfoArray.size(); i++) {
            JSONObject membObject = wholeMemberInfoArray.getJSONObject(i);
            System.out.print(membObject.getString("email") + "\n");
        }

    }

    public static void printScheduleList() {

        JSONArray wholeMemberInfoArray = getWholeMemberInfoArray();
        System.out.print("Schedule list:\n");
        System.out.printf("%-30s%-18s%-15s%-9s%s\n", "Email", "ScheduleIndex", "Date", "Sent", "Event");
        for (int i = 0; i < wholeMemberInfoArray.size(); i++) {
            JSONObject membObject = wholeMemberInfoArray.getJSONObject(i);
            JSONArray scheArr = membObject.getJSONArray("schedules");
            for (int j = 0; j < scheArr.size(); j++) {
                String email = membObject.getString("email");
                JSONObject scheObj = scheArr.getJSONObject(j);
                int scheduleIndex = scheObj.getIntValue("scheduleIndex");
                String scheduleDate = scheObj.getString("scheduleDate");
                boolean state = scheObj.getBoolean("state");
                String event = scheObj.getString("event");
                System.out.printf("%-30s%-18s%-15s%-9s%s\n", email, scheduleIndex, scheduleDate, state, event);
            }
        }

    }

    public static void printBookmarkList() {

        JSONArray wholeMemberInfoArray = getWholeMemberInfoArray();
        System.out.print("Bookmark list:\n");
        System.out.printf("%-30s%-18s%s\n", "Email", "BookmarkIndex", "Type");
        for (int i = 0; i < wholeMemberInfoArray.size(); i++) {
            JSONObject membObject = wholeMemberInfoArray.getJSONObject(i);
            JSONArray bookmArr = membObject.getJSONArray("bookmarks");
            for (int j = 0; j < bookmArr.size(); j++) {
                String email = membObject.getString("email");
                JSONObject scheObj = bookmArr.getJSONObject(j);
                int bookmarkIndex = scheObj.getIntValue("bookmarkIndex");
                String bookmarkType = scheObj.getString("bookmarkType");
                System.out.printf("%-30s%-18s%s\n", email, bookmarkIndex, bookmarkType);
            }
        }

    }

    public static void sendEmailToAllMembers() throws MessagingException {
        JSONArray memberInfo_arr = JsonOperation.getWholeMemberInfoArray();

        for (int i = 0; memberInfo_arr != null && i < memberInfo_arr.size(); i++) {

            JSONObject member = memberInfo_arr.getJSONObject(i);
            String email = member.getString("email");

            JSONArray sche_arr = member.getJSONArray("schedules");

            int scheduleIndex = 0;
            String event = null;
            String scheduleDate = null;
            Boolean state = null;
            for (int j = 0; sche_arr != null && j < sche_arr.size(); j++) {
                JSONObject sche = sche_arr.getJSONObject(j);
                scheduleIndex = sche.getIntValue("scheduleIndex");
                event = sche.getString("event");
                scheduleDate = sche.getString("scheduleDate");
                state = sche.getBooleanValue("state");

                String d = ScheduleDate.getTomorrowDate();
                if (state && scheduleDate.equals(d)) {
                    String subject = "Remember your scchedule tomorrow?";
                    String msg = "Schedule: schedule#" + scheduleIndex + "\nEvent: " + event + "\nDate: "
                            + scheduleDate.toString();

                    SendEmail.sendEmail(email, subject, msg);

                    sche.replace("state", "true", "false");

                }
            }
        }

    }

}