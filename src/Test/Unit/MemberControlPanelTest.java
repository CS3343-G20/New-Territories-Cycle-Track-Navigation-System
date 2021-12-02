package Test.Unit;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import SmartNavigationSystem.ExInvalidIndex;
import SmartNavigationSystem.JsonOperation;
import SmartNavigationSystem.Login;
import SmartNavigationSystem.Member;
import SmartNavigationSystem.MemberControlPanel;

public class MemberControlPanelTest { 
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
 	private final PrintStream originalOut = System.out;
 	private final InputStream originalIn = System.in;
 	
 	private final String email = "cs3343g20system@gmail.com";
 	
 	private Stub_Member m;

	class Stub_Login extends Login {
 		public boolean login() {
 			return true; 
 		}
 	}
		
 	class Stub_Member extends Member {
 		
 		private Stub_Login login;
 		
 		@Override
 		public Stub_Member Login(Scanner scan) {
 			this.login = new Stub_Login();
 	        login.login();
 			return this;
 		} 
 		
 		@Override
		public void resetPwd(Scanner scan) {
 			//pass
 		}
 		
 		@Override
		public void chooseMode(Scanner scan) {
 			//pass
 		}
 		
 		@Override
		public void CheckInfo() {
 			//pass
 		}
 		
 		@Override
		public void deleteSchedule(Scanner scan) {
 			//pass
 		}
 		
 		@Override
		public void deleteBookmark(Scanner scan) {
 			//pass
 		}
 		
 		@Override
		public void makeSchedule(Scanner scan) {
 			//pass
 		}
 		
 		@Override
 		public String getEmail() {
 			return email;
 		}
 	}
 	
 	@Before 	
 	public void setUp() throws IOException, ExInvalidIndex {
		
 	    System.setOut(new PrintStream(outContent));
 	    
 		m = new Stub_Member();
 		m.Login(new Scanner(""));
 		
 		new JsonOperation();

 		JSONObject obj = JsonOperation.getMemberInfo(email);
 		if (obj == null) {
 			JsonOperation.addNewMember(email, "pwd");
 		} else {
 			JsonOperation.resetPwd(email, "pwd");
 			int bookmNum = JsonOperation.getMemberBookmArray(email).size();
 			int scheNum = JsonOperation.getMemberScheArray(email).size();
 			for (int i = 1; i <= bookmNum; i++) {
 				JsonOperation.deleteMemberBookmark(m, 1);
 			}
 			for (int i = 1; i <= scheNum; i++) {
 				JsonOperation.deleteMemberSchedule(m, 1);
 			}
 		}
 		
 		MemberControlPanel.getInstance().setMember(m);
 		 		
 	}
 	
 	@Test
 	public void makeDecision_case1() throws IOException, ExInvalidIndex {
 		int res = MemberControlPanel.getInstance().makeDecision(new Scanner("7"));
 		assertEquals(1000, res);
 	}
	
 	@Test
 	public void makeDecision_case2() throws IOException, ExInvalidIndex {
 		int res = MemberControlPanel.getInstance().makeDecision(new Scanner("\n-7\n7"));
 		assertEquals(1000, res);
 	}

 	@Test
 	public void makeDecision_case3() throws IOException, ExInvalidIndex {
 		int res = MemberControlPanel.getInstance().makeDecision(new Scanner("-"));
 		assertEquals(1000, res);
 	}
	
 	@Test
 	public void makeDecision_case4() throws IOException, ExInvalidIndex {
 		int res = MemberControlPanel.getInstance().makeDecision(new Scanner("0"));
 		assertEquals(0, res);
 	}
	
 	@Test
 	public void makeDecision_case5() throws IOException, ExInvalidIndex {
 		int res = MemberControlPanel.getInstance().makeDecision(new Scanner("1"));
 		assertEquals(1, res);
 	}

 	@Test
 	public void makeDecision_case6() throws IOException, ExInvalidIndex {
 		int res = MemberControlPanel.getInstance().makeDecision(new Scanner("2\nCyclingMode"));
 		assertEquals(2, res);
 	}

 	@Test
 	public void makeDecision_case7() throws IOException, ExInvalidIndex {
 		int res = MemberControlPanel.getInstance().makeDecision(new Scanner("3"));
 		assertEquals(3, res);
 	}
	
	
 	@Test
 	public void makeDecision_case8() throws IOException, ExInvalidIndex {
				  
 		JsonOperation.addNewSchedule(m, "date", "route");
 		
 		String input = "4\n1";
 		System.setIn(new ByteArrayInputStream(input.getBytes()));
 		int res = MemberControlPanel.getInstance().makeDecision(new Scanner("4\n1"));
 		assertEquals(4, res);
		
 	}
	
 	@Test
 	public void makeDecision_case9() throws IOException, ExInvalidIndex {
 		int res = MemberControlPanel.getInstance().makeDecision(new Scanner("4\n1"));
 		assertEquals(4, res);
 	}
	
 	@Test
 	public void makeDecision_case10() throws IOException, ExInvalidIndex {
 		int res = MemberControlPanel.getInstance().makeDecision(new Scanner("5\n1"));
 		assertEquals(5, res);
 	}

 	@Test
 	public void makeDecision_case11() throws IOException, ExInvalidIndex {
 		JsonOperation.addNewBookmark("route", m);
		int res = MemberControlPanel.getInstance().makeDecision(new Scanner("5\n1"));
 		assertEquals(5, res);
 	}

 	@Test
 	public void makeDecision_case12() throws IOException, ExInvalidIndex {
 		int res = MemberControlPanel.getInstance().makeDecision(new Scanner("6\n2021/11/03\n1"));
 		assertEquals(6, res);
 	}

 	@Test
 	public void makeDecision_case13() throws IOException, ExInvalidIndex {
 		int res = MemberControlPanel.getInstance().makeDecision(new Scanner("6\n2021/11/03\n2"));
 		assertEquals(6, res);
 	}
 	@Test
 	public void makeDecision_case14() throws IOException, ExInvalidIndex {
 		int res = MemberControlPanel.getInstance().makeDecision(new Scanner("6\n2021/11/03\n3"));
 		assertEquals(6, res);
 	}

 	@Test
 	public void getMember_case1 () {
 		Member res = MemberControlPanel.getInstance().getMember();
 		assertEquals(m, res);
 	}

 	@After
 	public void restoreStreams() {
 	    System.setOut(originalOut);
 	    System.setIn(originalIn);
 	}
	
 }