package test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import SmartNavigationSystem.Admin;
import SmartNavigationSystem.JsonOperation;

public class AdminTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
 	private final PrintStream originalOut = System.out;
 	private final InputStream originalIn = System.in;
 	
	private String fileString;
	
 	@Before
 	public void setUpStreams() throws FileNotFoundException {
 		
 	    System.setOut(new PrintStream(outContent));
 	    
        File f = new File("docs/MemberInfo.json");
        Scanner fin = new Scanner(f);
        fileString = fin.useDelimiter("\\Z").next();
        fin.close();
        
        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
        pw.close();
        
        new JsonOperation();

	}  
 	
	@Test
	public void login_case1() {
		Admin admin = Admin.getInstance();
 		String input = "CS3343G20\n";
 		System.setIn(new ByteArrayInputStream(input.getBytes()));
		boolean res = admin.login(new Scanner(input));
		assertEquals(true, res);
	}
	
	@Test
	public void login_case2() {
		Admin admin = Admin.getInstance();
 		String input = "CS3343G20xxx\nCS3343G20";
 		System.setIn(new ByteArrayInputStream(input.getBytes()));
		boolean res = admin.login(new Scanner(input));
		assertEquals(true, res);
	}

	@Test
	public void login_case3() {
		Admin admin = Admin.getInstance();
 		String input = "CS3343G20xxx\nCS3343G20xxx\nCS3343G20xxx\nCS3343G20xxx";
 		System.setIn(new ByteArrayInputStream(input.getBytes()));
		boolean res = admin.login(new Scanner(input));
		assertEquals(false, res);
	}

	
	@Test
	public void printMemberList_case1() throws FileNotFoundException {
		new JsonOperation();
		Admin.printMemberList();
		assertEquals("Member list:\ncs3343g20system@gmail.com\n", outContent.toString());
	}

	@Test
	public void printScheduleList_case1() throws FileNotFoundException {
		new JsonOperation();
		Admin.printScheduleList();
		assertEquals("Schedule list:\nEmail                         ScheduleIndex     Date           Sent     Event\n", outContent.toString());
	}

	@Test
	public void printBookmarkList_case1() throws FileNotFoundException {
		new JsonOperation();
		Admin.printBookmarkList();
		assertEquals("Bookmark list:\nEmail                         BookmarkIndex     Type\n", outContent.toString());
	}

 	@After
 	public void restoreStreams() throws FileNotFoundException {
 		
 	    System.setOut(originalOut);
 	    System.setIn(originalIn);
 	    
        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write(fileString);
        pw.close();

 	}

}
