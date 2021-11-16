package SmartNavigationSystem;

import java.io.IOException;

import javax.mail.MessagingException;

public class Schedule {

    private Member member;
    private String date;
    private boolean state;

    public Schedule(Member member, String date) {
        this.member = member;
        this.date = date; // format: yyyy/mm/dd
        this.state = true; // wait for sending email
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

    public static void makeSchedule(String route, String date, Member member) throws IOException {
        JsonOperation.addNewSchedule(member, date, route);
    }

    public static void deleteSchedule(Member member, int index) throws IOException, ExInvalidIndex {
        JsonOperation.deleteMemberSchedule(member, index);
    }

    public static void sendEmail() throws IOException, MessagingException {
    	JsonOperation.sendEmailToAllMembers();
    }

}
