package test;

import static org.mockito.Mockito.verify;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.MessagingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import SmartNavigationSystem.ExInvalidIndex;
import SmartNavigationSystem.JsonOperation;
import SmartNavigationSystem.Login;
import SmartNavigationSystem.Member;
import SmartNavigationSystem.Schedule;
import test.BookmarkTest.Stub_Login;
import test.BookmarkTest.Stub_Member;
 
@RunWith(MockitoJUnitRunner.class)
public class ScheduleTest {
	
	private Stub_Member m;
	
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
				return "cs3343g20system@gmail.com";
			}
		}

		@Before
		public void setUp() throws FileNotFoundException {
			m = new Stub_Member();
			m.Login();
			new JsonOperation();
		}
 	
 	@Mock JsonOperation json;
 	@Test
 	public void makeSchedule_case1() throws IOException {
        Schedule.makeSchedule("route", "2001/01/01", m);
        verify(json);
 	}

 	@Test
 	public void deleteSchedule_case1() throws IOException, ExInvalidIndex { 		
        Schedule.deleteSchedule(m, 1);
        verify(json);
 	}
 	
 	@Test
 	public void sendEmail_case1() throws IOException, MessagingException {
        Schedule.sendEmail();
        verify(json);
 	}


 }
	
