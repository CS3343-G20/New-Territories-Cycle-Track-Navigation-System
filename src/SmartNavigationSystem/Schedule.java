package SmartNavigationSystem;

import java.io.IOException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Schedule {

    private Member member;
    private String date;
    private boolean state;
    private Mode mode;

    public Schedule(Member member, String date, Mode mode) {
        this.member = member;
        this.date = date; // format: yyyy/mm/dd
        this.state = true; // wait for sending email
        this.mode = mode;
    }

    public Member getMember() {
        return this.member;
    }

    public String getDate() {
        return this.date;
    }

    public boolean getState() {
        return this.state;
    }

    public Mode getMode() {
        return this.mode;
    }

    public static void makeSchedule(String mode, String date, Member member) throws IOException {
        JsonOperation.addNewSchedule(member, date, mode);
    }

    public static void deleteSchedule(Member member, int index) throws IOException {
        JsonOperation.deleteMemberSchedule(member, index);
    }

    public static void sendEmail() throws IOException {

        JSONArray memberInfo_arr = JsonOperation.getWholeMemberInfoArray();
        if (memberInfo_arr != null) {

            for (int i = 0; i < memberInfo_arr.size(); i++) {

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

}
