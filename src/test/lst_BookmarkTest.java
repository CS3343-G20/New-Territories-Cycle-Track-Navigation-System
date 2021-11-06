package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import SmartNavigationSystem.Bookmark;
import SmartNavigationSystem.Login;
import SmartNavigationSystem.Member;

public class lst_BookmarkTest {

	@Test
	public void getMember_case1 () {
		
		class Stub_Login extends Login {
			@Override
			public boolean login() {
				return true;
			}
		}
		
		class Stub_Member extends Member {
			private Stub_Login login;
			@Override
			public Stub_Member Login() {
				this.login = new Stub_Login();
		        login.login();
				return this;
			} 
		}
		
		Stub_Member m = new Stub_Member();
		m.Login();
		
		Bookmark bookm = new Bookmark(m);
		
		assertEquals(m, bookm.getMember());

	}

	
}
