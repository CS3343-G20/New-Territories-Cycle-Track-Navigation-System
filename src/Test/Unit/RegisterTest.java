package Test.Unit;

import SmartNavigationSystem.JsonOperation;
import SmartNavigationSystem.Register;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

import static org.junit.Assert.*; 

public class RegisterTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
 	private final PrintStream originalOut = System.out;
 	private final InputStream originalIn = System.in;
 	
	private String fileString;
	
	private String email = "cs3343g20system@gmail.com";
	private String wrongEmail = "cs3343g20system";
	private String pwd = "pwd";
	private String wrongPwd = "pwdxxx";
	
	@Before
	public void setUp() throws FileNotFoundException {
		
 	    System.setOut(new PrintStream(outContent));
 	    
        File f = new File("docs/MemberInfo.json");
        Scanner fin = new Scanner(f);
        fileString = fin.useDelimiter("\\Z").next();
        fin.close();

	}

	@Test
	public void register_case1() throws IOException {
		
        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[]}");
        pw.close();

        new JsonOperation();

		Register register = new Register();
		register.register(new Scanner(email +"\n" + pwd + "\n" + pwd));
		assertEquals(true, outContent.toString().contains("Register successfully."));
	}

	@Test
	public void register_case2() throws IOException {
		
        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
        pw.close();

        new JsonOperation();

		Register register = new Register();
		register.register(new Scanner(email));
		assertEquals(true, outContent.toString().contains("Registration failed!"));
	}
	
	@Test
	public void register_case3() throws IOException {
		
        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[]}");
        pw.close();

        new JsonOperation();

		Register register = new Register();
		register.register(new Scanner(wrongEmail + "\n" + email +"\n" + pwd + "\n" + pwd));
		assertEquals(true, outContent.toString().contains("Register successfully."));
	}

	@Test
	public void register_case4() throws IOException {
		
        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[]}");
        pw.close();

        new JsonOperation();

		Register register = new Register();
		register.register(new Scanner(wrongEmail + "\n" + wrongEmail + "\n" + wrongEmail));
		assertEquals(true, outContent.toString().contains("Registration failed!"));
	}

	@Test
	public void register_case5() throws IOException {
		
        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[]}");
        pw.close();

        new JsonOperation();

		Register register = new Register();
		register.register(new Scanner(email + "\n" + pwd + "\n" + wrongPwd + "\n" + wrongPwd + "\n" + wrongPwd));
		assertEquals(true, outContent.toString().contains("Registration failed!"));
	}

	@Test
	public void writeinFile_case1() throws IOException {
		
        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[]}");
        pw.close();

        new JsonOperation();
        
		Register register = new Register();
		register.writeinFile(email, pwd);
		
		JSONObject obj = JsonOperation.getMemberInfo(email);
		
		assertNotNull(obj);
	}

	@After
	public void restore() throws FileNotFoundException {
 		
 	    System.setOut(originalOut);
 	    System.setIn(originalIn);
 	    
        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write(fileString);
        pw.close();

	}
}