package test;

import static org.mockito.Mockito.verify;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import SmartNavigationSystem.Admin;
import SmartNavigationSystem.AdminLogin;
import SmartNavigationSystem.JsonOperation;

@RunWith(MockitoJUnitRunner.class)
public class lst_AdminTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
 	private final PrintStream originalOut = System.out;
 	private final InputStream originalIn = System.in;
	
 	@Before
 	public void setUpStreams() {
 	    System.setOut(new PrintStream(outContent));
	}  

	@Mock AdminLogin adminLogin;
	@Test
	public void login_case1() {
		Admin admin = Admin.getInstance();
 		String input = "CS3343G20\n";
 		System.setIn(new ByteArrayInputStream(input.getBytes()));
		admin.login(new Scanner(input));
		verify(adminLogin);
	}
	
	@Test
	public void printMemberList_case1() throws FileNotFoundException {
		new JsonOperation();
		Admin.printMemberList();
		verify(adminLogin);
	}

	@Test
	public void printScheduleList_case1() throws FileNotFoundException {
		new JsonOperation();
		Admin.printScheduleList();
		verify(adminLogin);
	}

	@Test
	public void printBookmarkList_case1() throws FileNotFoundException {
		new JsonOperation();
		Admin.printBookmarkList();
		verify(adminLogin);
	}

 	@After
 	public void restoreStreams() {
 	    System.setOut(originalOut);
 	    System.setIn(originalIn);
 	}

}
