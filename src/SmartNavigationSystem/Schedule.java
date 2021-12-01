package SmartNavigationSystem;

import java.io.IOException;

import javax.mail.MessagingException;

public class Schedule {
	
	private Schedule() {
	}

    public static void makeSchedule(String route, String date, Member member) throws IOException{
        JsonOperation.addNewSchedule(member, date, route);
    }

    public static void deleteSchedule(Member member, int index) throws IOException, ExInvalidIndex {
        JsonOperation.deleteMemberSchedule(member, index);
    }

    public static boolean sendEmail() throws IOException, MessagingException {
    	return JsonOperation.sendEmailToAllMembers();
    }

}
