package Test.Unit;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import SmartNavigationSystem.Bookmark;
import SmartNavigationSystem.ExInvalidIndex;
import SmartNavigationSystem.JsonOperation;
import SmartNavigationSystem.Login;
import SmartNavigationSystem.Member;

public class BookmarkTest {
	
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
	public void addBookmark_case1() throws FileNotFoundException {
		Bookmark bookm = Bookmark.getInstance();
		int org = JsonOperation.getMemberBookmArray(email).size();
		bookm.addBookmark("route", m);
		int cur = JsonOperation.getMemberBookmArray(email).size();
		assertEquals(org + 1, cur);
	}
	
	@Test
	public void deleteBookmark_case1() throws FileNotFoundException, ExInvalidIndex {
        JsonOperation.addNewBookmark("route", m);
		Bookmark bookm = Bookmark.getInstance();
		int org = JsonOperation.getMemberBookmArray(email).size();
		bookm.deleteBookmark(m, 1);
		int cur = JsonOperation.getMemberBookmArray(email).size();
		assertEquals(org - 1, cur);
	}
	
}
