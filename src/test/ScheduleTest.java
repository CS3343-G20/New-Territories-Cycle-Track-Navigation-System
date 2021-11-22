package test;

import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import SmartNavigationSystem.ExInvalidIndex;
import SmartNavigationSystem.JsonOperation;
import SmartNavigationSystem.Login;
import SmartNavigationSystem.Member;
import SmartNavigationSystem.Schedule;
 
@RunWith(MockitoJUnitRunner.class)
public class ScheduleTest {
 	
 	@Mock JsonOperation json;
 	@Test
 	public void makeSchedule_case1() throws IOException {
 		
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
		
 		Stub_Member m = new Stub_Member();
 		m.Login();
		
        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
        pw.close();

        new JsonOperation();
 		
        Schedule.makeSchedule("route", "2001/01/01", m);
        verify(json);

 	}

 	@Test
 	public void deleteSchedule_case1() throws IOException, ExInvalidIndex {
 		
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
		
 		Stub_Member m = new Stub_Member();
 		m.Login();
		
        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
        pw.close();

        new JsonOperation();
 		
        Schedule.deleteSchedule(m, 1);
        verify(json);

 	}
 	
 	@Test
 	public void sendEmail_case1() throws IOException, MessagingException {
 		
        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
        pw.close();

        new JsonOperation();
 		
        Schedule.sendEmail();
        verify(json);

 	}


 }
	
