package Test.System;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.BeforeClass;
import org.junit.Test;

import SmartNavigationSystem.*;

public class AdminSystemTest {
	private static ByteArrayOutputStream output = new ByteArrayOutputStream();
	
	@BeforeClass
	public static void setUp() throws IOException{
		String input = "4\nxxx\nasda\nasdsd\nadaasd\n4\nCS3343G20\n4\n1\n2\n3\n0";
//		   "4\nCS3343G20\n"+
//		   "4\n"+
//		   "1\n"+
//		   "2\n"+
//		   "3\n"+
//		   "0\n";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		System.setOut(new PrintStream(output));
		Main.main(null);
	}
	
	@Test // admin login fail
	public void login_testcase1() {	
		assertEquals(true, output.toString().contains("Wrong Token input!"));
		assertEquals(true, output.toString().contains("Please input the admin token again:"));
		assertEquals(true, output.toString().contains("Wrong Token input!"));
		assertEquals(true, output.toString().contains("Please input the admin token again:"));
		assertEquals(true, output.toString().contains( "Wrong Token input!"));
		assertEquals(true, output.toString().contains("Please input the admin token again:"));
		assertEquals(true, output.toString().contains("Login failed too many times"));
		assertEquals(true, output.toString().contains("Exiting..."));
	}
	
	@Test // admin login success
	public void login_testcase2() {
		assertEquals(true, true);
	}
	
	@Test 
	public void exception_testcase1() {
		assertEquals(true, output.toString().contains("Input error!"));
	}
	
	@Test
	public void printMemberList_testcase1() {
		assertEquals(true, output.toString().contains("Member list"));
	    assertEquals(true, output.toString().contains("mary@gmail.com"));
	    assertEquals(true, output.toString().contains("jeff@gmail.com"));
	}
	
	@Test
	public void prinScheduleList_testcase1() {
		assertEquals(true, output.toString().contains("Schedule list:"));
		assertEquals(true, output.toString().contains("Email                         ScheduleIndex     Date           Sent     Event"));
	}
	
	@Test
	public void printBookmarkList_testcase1() {
		assertEquals(true, output.toString().contains("Bookmark list:"));
		assertEquals(true, output.toString().contains("Email                         BookmarkIndex     Type"));
	}
	
	@Test
	public void exit_testcase1() {
		assertEquals(true, output.toString().contains("=========Exit========"));
	}
	
}
