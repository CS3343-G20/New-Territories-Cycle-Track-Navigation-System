package SmartNavigationSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonOperation {

    private static JSONObject wholeJsonObject;
    // private static String wholeJsonObjectString;

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

        JSONArray memberInfo_arr = wholeJsonObject.getJSONArray("memberInfo");

        for (int i = 0; i < memberInfo_arr.size(); i++) {
            JSONObject memberInfo = memberInfo_arr.getJSONObject(i);
            String memberEmail = memberInfo.getString("email");
            if (memberEmail.equals(email)) {
                return memberInfo.getJSONArray("bookmarks");
            }
        }
        return null;

    }

    public static String getMemberPassword(String email) throws FileNotFoundException {

        JSONArray memberInfo_arr = wholeJsonObject.getJSONArray("memberInfo");

        for (int i = 0; i < memberInfo_arr.size(); i++) {
            JSONObject memberInfo = memberInfo_arr.getJSONObject(i);
            String memberEmail = memberInfo.getString("email");
            if (memberEmail.equals(email)) {
                return memberInfo.getString("password");
            }
        }
        return null;

    }

    public static void resetPwd(String email, String pwd) throws IOException {

        JSONArray memberInfo_arr = wholeJsonObject.getJSONArray("memberInfo");

        for (int i = 0; i < memberInfo_arr.size(); i++) {
            JSONObject memberInfo = memberInfo_arr.getJSONObject(i);
            String memberEmail = memberInfo.getString("email");
            if (memberEmail.equals(email)) {
                memberInfo.replace("password", pwd);
                break;
            }
        }

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

        JSONArray memberInfo_arr = wholeJsonObject.getJSONArray("memberInfo");

        for (int i = 0; i < memberInfo_arr.size(); i++) {
            JSONObject memberInfo = memberInfo_arr.getJSONObject(i);
            String memberEmail = memberInfo.getString("email");
            if (memberEmail.equals(email)) {
                return true;
            }
        }
        return false;

    }

    public static boolean checkMemberPwd(String email, String pwd) throws FileNotFoundException {
        return getMemberPassword(email).equals(pwd);
    }

    public static void addNewSchedule(Member member, String date, String mode) throws IOException {

        JSONArray arr = getMemberScheArray(member.getEmail());

        int index = arr.size() + 1;
        // Here is to modify schedule event
        JSONObject obj = JSON.parseObject("{\"scheduleIndex\": " + index + ",\"scheduleDate\": \"" + date
                + "\",\"state\": \"" + "true" + "\",\"event\": \"" + mode + "\"}");

        arr.add(obj);

        updateJsonFile();

    }

    public static void addNewBookMark(String mode, Member member) throws IOException {

        JSONArray arr = getMemberBookmArray(member.getEmail());

        int index = arr.size() + 1;

        // Here is to add more bookmark info
        JSONObject obj = JSON.parseObject("{\"bookmarkIndex\": " + index + ",\"bookmarkType\": \"" + mode + "\"}");
        arr.add(obj);

        updateJsonFile();

    }

    public static void deleteMemberSchedule(Member member, int index) throws IOException {

        JSONArray memberScheduleArray = getMemberScheArray(member.getEmail());

        boolean flag = false;

        for (int i = 0; i < memberScheduleArray.size(); i++) {
            JSONObject obj = memberScheduleArray.getJSONObject(i);
            if (obj.getIntValue("scheduleIndex") == index) {
                memberScheduleArray.remove(obj);
                flag = true;
                break;
            }
        }

        if (flag) {
            updateJsonFile();
            System.out.println("Delete successfully!");
        } else {
            System.out.println("Schedule index input error!");
        }

    }

    public static void deleteMemberBookmark(Member member, int index) throws IOException {

        JSONArray memberBookmarkArray = getMemberBookmArray(member.getEmail());

        boolean flag = false;

        for (int i = 0; i < memberBookmarkArray.size(); i++) {
            JSONObject obj = memberBookmarkArray.getJSONObject(i);
            if (obj.getIntValue("bookmarkIndex") == index) {
                memberBookmarkArray.remove(obj);
                flag = true;
                break;
            }
        }

        if (flag) {
            updateJsonFile();
            System.out.println("Delete successfully!");
        } else {
            System.out.println("Bookmark index input error!");
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

        System.out.println("Email: " + email);
        JSONObject memberOnj = getMemberInfo(email);
        String password = memberOnj.getString("password");
        System.out.println("Password: " + password + "\n");

        JSONArray memberScheArray = getMemberScheArray(email);
        int scheNum = memberScheArray.size();
        if (scheNum == 0) {
            System.out.print("You haven't make schedules.\n");
        } else {
            System.out.println("You have " + scheNum + " schedules:\n");
            for (int i = 0; i < scheNum; i++) {
                JSONObject memberSche = memberScheArray.getJSONObject(i);
                System.out.println("Schedule index: " + memberSche.getIntValue("scheduleIndex") + "\nEvent: "
                        + memberSche.getString("event") + "\nSchedule date: " + memberSche.getString("scheduleDate"));
                System.out.println();
            }
        }

        JSONArray memberBookmArray = getMemberBookmArray(email);
        int bookmNum = memberBookmArray.size();
        if (bookmNum == 0) {
            System.out.print("You haven't add bookmarks.\n");
        } else {
            System.out.println("You have " + bookmNum + " bookmarks:\n");
            for (int i = 0; i < bookmNum; i++) {
                JSONObject memberBookm = memberBookmArray.getJSONObject(i);
                System.out.println("Bookmark index: " + memberBookm.getIntValue("bookmarkIndex") + "\nType: "
                        + memberBookm.getString("bookmarkType"));
                System.out.println();
            }
        }

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
        System.out.println("Member list:");
        for (int i = 0; i < wholeMemberInfoArray.size(); i++) {
            JSONObject membObject = wholeMemberInfoArray.getJSONObject(i);
            System.out.println(membObject.getString("email"));
        }

    }

    public static void printScheduleList() {

        JSONArray wholeMemberInfoArray = getWholeMemberInfoArray();
        System.out.println("Schedule list:");
        for (int i = 0; i < wholeMemberInfoArray.size(); i++) {
            JSONObject membObject = wholeMemberInfoArray.getJSONObject(i);
            JSONArray scheArr = membObject.getJSONArray("schedules");
            System.out.println("Email\t\tScheduleIndex\tDate\tSent\tEvent");
            for (int j = 0; j < scheArr.size(); j++) {
                System.out.print(membObject.getString("email") + "\t");
                JSONObject scheObj = scheArr.getJSONObject(j);
                System.out.print(scheObj.getIntValue("scheduleIndex") + "\t" + scheObj.getString("scheduleDate") + "\t"
                        + scheObj.getBoolean("state") + "\t" + scheObj.getString("event"));
                System.out.println();
            }
        }

    }

    public static void printBookmarkList() {

        JSONArray wholeMemberInfoArray = getWholeMemberInfoArray();
        System.out.println("Bookmark list:");
        for (int i = 0; i < wholeMemberInfoArray.size(); i++) {
            JSONObject membObject = wholeMemberInfoArray.getJSONObject(i);
            JSONArray bookmArr = membObject.getJSONArray("bookmarks");
            System.out.println("Email\t\tBookmarkIndex\tType");
            for (int j = 0; j < bookmArr.size(); j++) {
                System.out.print(membObject.getString("email") + "\t");
                JSONObject scheObj = bookmArr.getJSONObject(j);
                System.out.print(scheObj.getIntValue("bookmarkIndex") + "\t" + scheObj.getString("bookmarkType"));
                System.out.println();
            }
        }

    }

}
