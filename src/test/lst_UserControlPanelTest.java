 package test;

 import static org.junit.jupiter.api.Assertions.assertEquals;

 import java.io.ByteArrayOutputStream;
 import java.io.InputStream;
 import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
 import org.junit.Before;
 import org.junit.Test;

 import SmartNavigationSystem.Member;
 import SmartNavigationSystem.Login;
 import SmartNavigationSystem.UserControlPanel;

 public class lst_UserControlPanelTest {
    
     private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
 	private final PrintStream originalOut = System.out;
 	private final InputStream originalIn = System.in;

 	@Before
 	public void setUpStreams() {
 	    System.setOut(new PrintStream(outContent));
 	}  
	
     @Test
     public void getMember_case1() {
    	
 		class Stub_Login extends Login {
 			@Override
 			public int login(Scanner in) {
 				return 1; 
 			}
 		}
		
 		class Stub_Member extends Member {
 			private Stub_Login login;
 			@Override
 			public Stub_Member Login(Scanner in) {
 				this.login = new Stub_Login();
 		        login.login(in);
 				return this;
 			} 
 		}
		
 		Stub_Member m = new Stub_Member();
 		m.Login(new Scanner(""));

 		UserControlPanel.getInstance().setMember(m);
     	assertEquals(m, UserControlPanel.getInstance().getMember());
    	
     }
	
     @Test
     public void makeDecision_case1() {
 		int res = UserControlPanel.getInstance().makeDecision(new Scanner("7"));
 		assertEquals(1000, res);
     }

 	@Test
     public void makeDecision_case2() {
 		int res = UserControlPanel.getInstance().makeDecision(new Scanner("-7"));
 		assertEquals(1000, res);
     }

 	@Test
     public void makeDecision_case3() {
 		int res = UserControlPanel.getInstance().makeDecision(new Scanner("0"));
 		assertEquals(0, res);
     }


     @After
 	public void restoreStreams() {
 	    System.setOut(originalOut);
 	    System.setIn(originalIn);
 	}


 }
