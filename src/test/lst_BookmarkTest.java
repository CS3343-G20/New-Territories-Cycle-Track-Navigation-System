package test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import SmartNavigationSystem.Bookmark;
import SmartNavigationSystem.ExInvalidIndex;
import SmartNavigationSystem.JsonOperation;
import SmartNavigationSystem.Login;
import SmartNavigationSystem.Member;

@RunWith(MockitoJUnitRunner.class)
public class lst_BookmarkTest {

	@Mock JsonOperation json;
	@Test
	public void addBookmark_case1() throws FileNotFoundException {
		
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
 		
		Bookmark bookm = Bookmark.getInstance();
		bookm.addBookmark("route", m);
		verify(json);
	}
	
	@Test
	public void deleteBookmark_case1() throws FileNotFoundException, ExInvalidIndex {
		
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
        pw.write("{\"memberInfo\":[{\"bookmarks\":[{\"bookmarkIndex\":1,\"bookmarkType\":\"Cycling Mode: Sha Tin Che Kung Temple -> Hong Kong Heritage Museum\n\"}],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
        pw.close();
        new JsonOperation();

		Bookmark bookm = Bookmark.getInstance();
		bookm.deleteBookmark(m, 1);
		verify(json);

	}
	
}
