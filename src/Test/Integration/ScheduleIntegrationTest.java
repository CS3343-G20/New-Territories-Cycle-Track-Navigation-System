package Test.Integration;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.mail.MessagingException;

import org.junit.Before;
import org.junit.Test;

import SmartNavigationSystem.ExInvalidIndex;
import SmartNavigationSystem.JsonOperation;
import SmartNavigationSystem.Member;
import SmartNavigationSystem.Schedule;

public class ScheduleIntegrationTest {
	private Member m;
	private String email = "cs3343g20system@gmail.com";
	private String pwd = "pwd";
	

	@Before
	public void setUp() throws IOException {
		m = new Member();
		new JsonOperation();
		String input=email+"\n"+pwd;
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		m.Login(new Scanner(System.in));
	}
 	
 	@Test
 	public void makeSchedule_case1() throws IOException {
 		int org = JsonOperation.getMemberScheArray(email).size();
        Schedule.makeSchedule("route", "2001/01/01", m);
 		int cur = JsonOperation.getMemberScheArray(email).size();
        assertEquals(org + 1, cur);
 	}

 	@Test
 	public void deleteSchedule_case1() throws IOException, ExInvalidIndex { 	
 		JsonOperation.addNewSchedule(m, "2021/01/01", "route");
 		int org = JsonOperation.getMemberScheArray(email).size();
        Schedule.deleteSchedule(m, 1);
 		int cur = JsonOperation.getMemberScheArray(email).size();
        assertEquals(org - 1, cur);
 	}
 	
 	@Test
 	public void sendEmail_case1() throws IOException, MessagingException {
        assertEquals(true, Schedule.sendEmail());
 	}


}
