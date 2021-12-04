package Test.Unit;

import SmartNavigationSystem.ExInvalidIndex;
import SmartNavigationSystem.JsonOperation;
import SmartNavigationSystem.Member;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class MemberTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
 	private final PrintStream originalOut = System.out;
 	private final InputStream originalIn = System.in;
 	
 	public final String email = "cs3343g20system@gmail.com";
 	public final String pwd = "pwd";
 	public Member m = new Member();


 	@Before 
 	public void setUp() throws IOException, ExInvalidIndex {
 	    
 	    new JsonOperation();
 	     	    
    	String input = email+"\n"+JsonOperation.getMemberPassword(email);
    	m.Login(new Scanner(input));

 		JSONObject obj = JsonOperation.getMemberInfo(email);
 		if (obj == null) {
 			JsonOperation.addNewMember(email, pwd);
 		} else {
 			JsonOperation.resetPwd(email, pwd);
 			int bookmNum = JsonOperation.getMemberBookmArray(email).size();
 			int scheNum = JsonOperation.getMemberScheArray(email).size();
 			for (int i = 1; i <= bookmNum; i++) {
 				JsonOperation.deleteMemberBookmark(m, 1);
 			}
 			for (int i = 1; i <= scheNum; i++) {
 				JsonOperation.deleteMemberSchedule(m, 1);
 			}
 		}
 		
 		System.setOut(new PrintStream(outContent));
 		
 	}
 	
 	@Test
 	public void checkInfo_case1() throws FileNotFoundException {
 		m.CheckInfo();
 		assertEquals("Email: cs3343g20system@gmail.com\nPassword: pwd\n\nYou haven't make schedules.\n\nYou haven't add bookmarks.\n\n", outContent.toString());
 	}
	
 	@Test
 	public void getEmail_case1() {
 		assertEquals(email, m.getEmail());
 	}
 	
 	@Test 
 	public void resetPwd_case1() throws IOException {
 		m.resetPwd(new Scanner("yes\nnewPwd\nnewPwd"));
 		assertEquals("newPwd", JsonOperation.getMemberPassword(email));
 	}    
    
    @Test
    public void deleteBookmark_case1() throws IOException {

    	JsonOperation.addNewBookmark("route", m);
    	int org = JsonOperation.getMemberBookmArray(email).size();
    	m.deleteBookmark(new Scanner("1"));
    	int cur = JsonOperation.getMemberBookmArray(email).size();
    	assertEquals(org - 1, cur);
    }
    
    @Test
    public void deleteBookmark_case2() throws IOException, ExInvalidIndex {
    	m.deleteBookmark(new Scanner("1"));
    	int cur = JsonOperation.getMemberBookmArray(email).size();
    	assertEquals(0, cur);
    }
    
    @Test
    public void deleteSchedule_case1() throws IOException {
    	JsonOperation.addNewSchedule(m, "2001/01/01", "route");
    	int org = JsonOperation.getMemberScheArray(email).size();
    	m.deleteSchedule(new Scanner("1"));
    	int cur = JsonOperation.getMemberScheArray(email).size();
    	assertEquals(org - 1, cur);
    }
    
    @Test
    public void deleteSchedule_case2() throws IOException {
    	m.deleteSchedule(new Scanner("1"));
    	int cur = JsonOperation.getMemberScheArray(email).size();
    	assertEquals(0, cur);
    }

    @Test
    public void makeSchedule_case1() throws IOException{
    	int org = JsonOperation.getMemberScheArray(email).size();
		m.makeSchedule(new Scanner("1\n0\n1\nN\nY\n0\nN\n2021/01/01"));
    	int cur = JsonOperation.getMemberScheArray(email).size();
    	assertEquals(org + 1, cur);
    }
    
    @Test
    public void makeSchedule_case2() throws IOException {
    	m.makeSchedule(new Scanner("1\n0\n1\nN\nY\n0\nN\n2021/50/01\n2021/01/01"));
    	assertEquals(true, outContent.toString().contains("The date is invalid. Please try again:"));
    }

    
    @After
    public void restoreStreams() {
 	    System.setOut(originalOut);
 	    System.setIn(originalIn);
    }

}
