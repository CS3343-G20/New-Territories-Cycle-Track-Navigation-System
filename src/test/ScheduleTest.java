package test;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.mail.MessagingException;

import org.junit.Before;
import org.junit.Test;

import SmartNavigationSystem.ExInvalidIndex;
import SmartNavigationSystem.JsonOperation;
import SmartNavigationSystem.Login;
import SmartNavigationSystem.Member;
import SmartNavigationSystem.Schedule;
 
public class ScheduleTest {
	
	private Stub_Member m;
	private String email = "cs3343g20system@gmail.com";
	
	class Stub_Login extends Login {
			public boolean login() {
				return true;
			}
		}
	
		class Stub_Member extends Member {
			private Stub_Login login;
			public Stub_Member Login() {
				this.login = new Stub_Login();
		        login.login();
				return this;
			} 
			@Override
			public String getEmail() {
				return email;
			}
		}

		@Before
		public void setUp() throws FileNotFoundException {
			m = new Stub_Member();
			m.Login();
			new JsonOperation();
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
	
