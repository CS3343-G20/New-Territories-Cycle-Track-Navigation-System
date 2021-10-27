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

    public JsonOperation() throws FileNotFoundException {
        File f = new File("docs/MemberInfo.json");
        Scanner fin = new Scanner(f);
        String content = fin.useDelimiter("\\Z").next();

        wholeJsonObject = JSONObject.parseObject(content);

    }

    public static JSONObject getWholeJsonObject() throws FileNotFoundException  {
        // new JsonOperation();
        return wholeJsonObject;
    }

    public static String getWholeJsonObjectString() throws FileNotFoundException  {
        if (JsonOperation.getWholeJsonObject() == null)
            return "";
        else
            return JsonOperation.getWholeJsonObject().toJSONString();
    }

    public static JSONArray getWholeMemberInfoArray() throws FileNotFoundException {
        if (JsonOperation.getWholeJsonObject() == null)
            return null;
        else
            return JsonOperation.getWholeJsonObject().getJSONArray("memberInfo");
    }

    public static JSONObject getMemberInfo(String email) throws FileNotFoundException {

        JSONArray wholeMemberInfo_arr = JsonOperation.getWholeMemberInfoArray();

        for (int i = 0; i < wholeMemberInfo_arr.size(); i++) {
            JSONObject memberInfo = wholeMemberInfo_arr.getJSONObject(i);
            String memberEmail = memberInfo.getString("email");
            if (memberEmail.equals(email)) {
                return memberInfo;
            }
        }
        return null;

    }

    public static JSONArray getMemberScheArray(String email) throws FileNotFoundException {
        JSONObject memberInfo = getMemberInfo(email);
        return memberInfo.getJSONArray("schedules");
    }

    public static JSONArray getMemberBookmArray(String email) throws FileNotFoundException {

        JSONArray memberInfo_arr = JsonOperation.getWholeMemberInfoArray();

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

        JSONArray memberInfo_arr = JsonOperation.getWholeMemberInfoArray();

        for (int i = 0; i < memberInfo_arr.size(); i++) {
            JSONObject memberInfo = memberInfo_arr.getJSONObject(i);
            String memberEmail = memberInfo.getString("email");
            if (memberEmail.equals(email)) {
                return memberInfo.getString("password");
            }
        }
        return null;

    }

    public static void resetPwd(String email, String pwd) throws FileNotFoundException {

        JSONArray memberInfo_arr = JsonOperation.getWholeMemberInfoArray();

        for (int i = 0; i < memberInfo_arr.size(); i++) {
            JSONObject memberInfo = memberInfo_arr.getJSONObject(i);
            String memberEmail = memberInfo.getString("email");
            if (memberEmail.equals(email)) {
                memberInfo.replace("password", pwd);
            }
        }

    }

    public static void clearJsonFile() throws IOException {
        FileWriter fout = new FileWriter("docs\\MemberInfo.json");
        fout.close();
    }

    public static void addNewMember(String email, String password) throws IOException {
        JSONObject e = JSON.parseObject("{\"password\":\"" + password + "\",\"email\":\"" + email + "\"}");
        getWholeMemberInfoArray().add(e);
        updateJsonFile();
    }

    public static boolean checkMemberExist(String email) throws FileNotFoundException {

        JSONArray memberInfo_arr = JsonOperation.getWholeMemberInfoArray();

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

    public static void addNewSchedule(Schedule sche) throws FileNotFoundException  {

        JSONArray arr = getMemberScheArray(sche.getMember().getEmail());

        if (arr != null) {
            int index = arr.size() + 1;
            sche.setIndex(index);
            // Here is to modify schedule event
            JSONObject obj = JSON.parseObject("{\"scheduleIndex\": " + index + ",\"scheduleDate\": \"" + sche.getDate()
                    + "\",\"state\": \"" + sche.getState() + "\",\"event\": \"" + sche.getMode() + "\"}");

            arr.add(obj);
        } else {
            JSONObject memberInfo = getMemberInfo(sche.getMember().getEmail());
            memberInfo.put("schedules", "[]");
            JSONArray memberInfoArray = memberInfo.getJSONArray("schedules");
            // Here is to modify schedule event
            JSONObject obj = JSON.parseObject("{\"scheduleIndex\": " + 1 + ",\"scheduleDate\": \"" + sche.getDate()
                    + "\",\"state\": \"" + sche.getState() + "\",\"event\": \"" + sche.getMode() + "\"}");
            memberInfoArray.add(obj);

        }

    }

    public static void addNewBookMark(Bookmark bookm) throws FileNotFoundException {

        JSONArray arr = getMemberBookmArray(bookm.getMember().getEmail());

        if (arr != null) {
            int index = arr.size() + 1;
            bookm.setIndex(index);

            // Here is to add more bookmark info
            JSONObject obj = JSON
                    .parseObject("{\"bookmarkIndex\": " + index + ",\"bookmarkType\": \"" + bookm.getMode() + "\"}");
            arr.add(obj);
        } else {
            JSONObject memberInfo = getMemberInfo(bookm.getMember().getEmail());
            memberInfo.put("bookmarks", "[]");
            JSONArray memberInfoArray = memberInfo.getJSONArray("bookmarks");
            // Here is to modify schedule event
            JSONObject obj = JSON
                    .parseObject("{\"bookmarkIndex\": " + 1 + ",\"bookmarkType\": \"" + bookm.getMode() + "\"}");
            arr.add(obj);
            memberInfoArray.add(obj);

        }

    }

    public static void deleteMemberSchedule(Member member, int index) throws FileNotFoundException {

        JSONArray memberScheduleArray = getMemberScheArray(member.getEmail());

        for (int i = 0; i < memberScheduleArray.size(); i++) {
            JSONObject obj = memberScheduleArray.getJSONObject(i);
            if (obj.getIntValue("scheduleIndex") == index) {
                memberScheduleArray.remove(obj);
            }
        }

    }

    public static void deleteMemberBookmark(Member member, int index) throws FileNotFoundException {

        JSONArray memberBookmarkArray = getMemberBookmArray(member.getEmail());

        for (int i = 0; i < memberBookmarkArray.size(); i++) {
            JSONObject obj = memberBookmarkArray.getJSONObject(i);
            if (obj.getIntValue("bookmarkIndex") == index) {
                memberBookmarkArray.remove(obj);
            }
        }

    }

    public static void updateJsonFile() throws IOException {
        File memberListFile = new File("docs\\MemberInfo.json");
        FileOutputStream fos = new FileOutputStream(memberListFile, true);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        JsonOperation.clearJsonFile();
        String str = "{\"memberInfo\": " + JSON.toJSONString(getWholeMemberInfoArray()) + "}";
        osw.write(str);
        osw.close();
    }

}
