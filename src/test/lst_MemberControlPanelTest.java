package test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import SmartNavigationSystem.JsonOperation;
import SmartNavigationSystem.Login;
import SmartNavigationSystem.Member;
import SmartNavigationSystem.MemberControlPanel;

public class lst_MemberControlPanelTest { 
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final InputStream originalIn = System.in;

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}  

	@Test
	public void makeDecision_case1() throws IOException {
		
		String input = "7";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		int res = MemberControlPanel.getInstance().makeDecision();
		assertEquals(1000, res);
		
	}
	
	@Test
	public void makeDecision_case2() throws IOException {
		
		String input = "-7";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		int res = MemberControlPanel.getInstance().makeDecision();
		assertEquals(1000, res);
		
	}

	
	@Test
	public void makeDecision_case3() throws IOException {
		
		String input = "0";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		int res = MemberControlPanel.getInstance().makeDecision();
		assertEquals(0, res);
		
	}
	
	@Test
	public void makeDecision_case4() throws IOException {
		
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
			@Override
			public void resetPwd() {
				//pass
			}
		}
		
		Stub_Member m = new Stub_Member();
		m.Login();
		  
		MemberControlPanel.getInstance().setMember(m);
		
		String input = "1";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		int res = MemberControlPanel.getInstance().makeDecision();
		assertEquals(1, res);
		
	}

	@Test
	public void makeDecision_case5() throws IOException {
		
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
			@Override
			public void chooseMode(String mode) {
				//pass
			}
		}
		
		Stub_Member m = new Stub_Member();
		m.Login();
		  
		MemberControlPanel.getInstance().setMember(m);
		
		String input = "2\nCyclingMode";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		int res = MemberControlPanel.getInstance().makeDecision();
		assertEquals(2, res);
		
	}

	@Test
	public void makeDecision_case6() throws IOException {
		
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
			@Override
			public void CheckInfo() {
				//pass
			}
		}
		
		Stub_Member m = new Stub_Member();
		m.Login();
		  
		MemberControlPanel.getInstance().setMember(m);
		
		String input = "3";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		int res = MemberControlPanel.getInstance().makeDecision();
		assertEquals(3, res);
		
	}
	
	
	@Test
	public void makeDecision_case7() throws IOException {
		
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
			@Override
			public void deleteSchedule(int scheduleIndex) {
				//pass
			}
			@Override
			public String getEmail() {
				return "cs3343g20system@gmail.com";
			}
		}
		
		Stub_Member m = new Stub_Member();
		m.Login();
		  
		MemberControlPanel.getInstance().setMember(m);
		
        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[{\"scheduleIndex\":1,\"scheduleDate\":\"2021/11/6\",\"state\":\"true\",\"event\":\"Cycling Mode: null\"}],\"email\":\"cs3343g20system@gmail.com\"}]}");
        pw.close();

		new JsonOperation();
		
		String input = "4\n1";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		int res = MemberControlPanel.getInstance().makeDecision();
		assertEquals(4, res);
		
	}
	
	@Test
	public void makeDecision_case8() throws IOException {
		
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
			@Override
			public void deleteSchedule(int scheduleIndex) {
				//pass
			}
			@Override
			public String getEmail() {
				return "cs3343g20system@gmail.com";
			}
		}
		
		Stub_Member m = new Stub_Member();
		m.Login();
		  
		MemberControlPanel.getInstance().setMember(m);
		
        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
        pw.close();

		new JsonOperation();
		
		String input = "4\n1";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		int res = MemberControlPanel.getInstance().makeDecision();
		assertEquals(4, res);
		
	}

	
	@Test
	public void makeDecision_case9() throws IOException {
		
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
			@Override
			public void deleteBookmark(int bookmarkIndex) {
				//pass
			}
			@Override
			public String getEmail() {
				return "cs3343g20system@gmail.com";
			}
		}
		
		Stub_Member m = new Stub_Member();
		m.Login();
		  
		MemberControlPanel.getInstance().setMember(m);
		
        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
        pw.close();

		new JsonOperation();

		String input = "5\n1";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		int res = MemberControlPanel.getInstance().makeDecision();
		assertEquals(5, res);
		
	}

	@Test
	public void makeDecision_case10() throws IOException {
		
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
			@Override
			public void deleteBookmark(int bookmarkIndex) {
				//pass
			}
			@Override
			public String getEmail() {
				return "cs3343g20system@gmail.com";
			}
		}
		
		Stub_Member m = new Stub_Member();
		m.Login();
		  
		MemberControlPanel.getInstance().setMember(m);
		
        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[{\"bookmarks\":[{\"bookmarkIndex\":1,\"bookmarkType\":\"Cycling Mode: Sha Tin Che Kung Temple -> Hong Kong Heritage Museum\\n\"}],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
        pw.close();

		new JsonOperation();

		String input = "5\n1";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		int res = MemberControlPanel.getInstance().makeDecision();
		assertEquals(5, res);
		
	}

	@Test
	public void makeDecision_case11() throws IOException {
		
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
			@Override
			public void makeSchedule(String mode, String date) {
				//pass
			}
			@Override
			public void chooseMode(String mode) {
				//pass
			}
		}
		
		Stub_Member m = new Stub_Member();
		m.Login();
		  
		MemberControlPanel.getInstance().setMember(m);
		new JsonOperation();

		String input = "6\n2021/11/03\n1";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		int res = MemberControlPanel.getInstance().makeDecision();
		assertEquals(6, res);
		
	}

	@Test
	public void makeDecision_case12() throws IOException {
		
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
			@Override
			public void makeSchedule(String mode, String date) {
				//pass
			}
		}
		
		Stub_Member m = new Stub_Member();
		m.Login();
		  
		MemberControlPanel.getInstance().setMember(m);
		
		String input = "6\n2021/11/03\n2";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		int res = MemberControlPanel.getInstance().makeDecision();
		assertEquals(6, res);
		
	}
	@Test
	public void makeDecision_case13() throws IOException {
		
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
		  
		MemberControlPanel.getInstance().setMember(m);
		
		String input = "6\n2021/11/03\n3";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		int res = MemberControlPanel.getInstance().makeDecision();
		assertEquals(6, res);
		
	}

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
		  
		MemberControlPanel.getInstance().setMember(m);
		Member res = MemberControlPanel.getInstance().getMember();
		assertEquals(m, res);
	}


	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setIn(originalIn);
	}
	
}