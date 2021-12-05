package Test.System;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import SmartNavigationSystem.*;

public class MemberSystemTest {
	
    private static ByteArrayOutputStream output = new ByteArrayOutputStream();
	
	@BeforeClass
	public static void setUp() throws IOException {
		String input = "1\n"
				+ "cs3343g20system@gmail.com\n"
				+ "error\n"
				+ "error\n"
				+ "1\n"
				+ "cs3343g20system@gmail.com\n"
				+ "pwd\n"
				+ "7\n"
				+ "5\n"
				+ "2\n"
				+ "1\n"
				+ "1\n"
				+ "1\n"
				+ "Y\n"
				+ "Y\n"
				+ "0\n"
				+ "1\n"
				+ "Y\n"
				+ "2 5\n"
				+ "N\n"
				+ "0\n"
				+ "Y\n"
				+ "2021/12/6\n"
				+ "2\n"
				+ "4\n"
				+ "1\n"
				+ "6\n"
				+ "1\n"
				+ "0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        System.setOut(new PrintStream(output));
        Main.main(null);
	}
    
    @Test // login
    public void login_test1() {
    	assertEquals(true, output.toString().contains("Login successfully!"));
    }
    
    @Test // login fail
    public void login_test2() {
    	assertEquals(true, output.toString().contains("Login failed. Exiting...\n"));
    }
    
    @Test // wrong input
    public void input_test() {
    	assertEquals(true, output.toString().contains("Input error! Please try again."));
    }
    
    @Test // make schedule
    public void makeSchedule_test() {
    	assertEquals(true, output.toString().contains("Make schedule successfully!"));
    }
    
    @Test // check info
    public void checkInfo_test() {
    	assertEquals(true, output.toString().contains("You have 1 schedules"));
    }
    
    @Test // delete bookmark
    public void deleteBookmark_test() {
    	assertEquals(true, output.toString().contains("Delete successfully!"));
    }
    
    @Test // delete schedule
    public void deleteSchedule_test() {
    	assertEquals(true, output.toString().contains("Delete successfully!"));
    }
    
    @Test // exit
    public void exit_test() {
    	assertEquals(true, output.toString().contains("========Exit========"));
    }
}