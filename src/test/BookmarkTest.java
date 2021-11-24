package test;

import static org.mockito.Mockito.verify;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.junit.Before;
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
public class BookmarkTest {
	
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
	public void addBookmark_case1() throws FileNotFoundException {
		Bookmark bookm = Bookmark.getInstance();
		bookm.addBookmark("route", m);
		verify(json);
	}
	
	@Test
	public void deleteBookmark_case1() throws FileNotFoundException, ExInvalidIndex {
        JsonOperation.addNewBookmark("route", m);
		Bookmark bookm = Bookmark.getInstance();
		bookm.deleteBookmark(m, 1);
		verify(json);
	}
	
}
