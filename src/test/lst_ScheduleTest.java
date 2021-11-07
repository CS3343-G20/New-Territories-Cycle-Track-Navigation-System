package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import SmartNavigationSystem.Login;
import SmartNavigationSystem.Member;
import SmartNavigationSystem.Schedule;

public class lst_ScheduleTest {

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
		
		Schedule sche = new Schedule(m, "2021/10/01");
		
		assertEquals(m, sche.getMember());

	}

	@Test
	public void getDate_case1 () {
		Schedule sche = new Schedule(null, "2021/10/01");
		assertEquals("2021/10/01", sche.getDate());
	}
	
	@Test
	public void getState_case1() {
		Schedule sche = new Schedule(null, null);
		assertEquals(true, sche.getState());
	}
	
}
