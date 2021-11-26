package test;

import SmartNavigationSystem.ControlPanel;
import SmartNavigationSystem.ExInvalidIndex;
import SmartNavigationSystem.JsonOperation;
import SmartNavigationSystem.Main;
import SmartNavigationSystem.UserControlPanel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 */
public class MainTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
 	private final PrintStream originalOut = System.out;
 	private final InputStream originalIn = System.in;
 	
 	private final String email = "cs3343g20system@gmail.com";
 	private final String pwd = "pwd";

 	@Before 	
 	public void setUp() throws IOException, ExInvalidIndex {
 		
 		System.setOut(new PrintStream(outContent));
 	    
 		new JsonOperation();

 		JSONObject obj = JsonOperation.getMemberInfo(email);
 		if (obj == null) {
 			JsonOperation.addNewMember(email, pwd);
 		} else {
 			JsonOperation.resetPwd(email, pwd);
 		}
 		 		
 	}

    @Test
    public void launch_case1() throws IOException {
        String input="0";
        ControlPanel cp = UserControlPanel.getInstance();
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Main.launch(cp,new Scanner(System.in));
        assertEquals(true, outContent.toString().contains("========Exit========"));
    }
    
    @Test
    public void launch_case2() throws IOException {
        String input="1\ncs3343g20system@gmail.com\npwd\n3\n0";
        ControlPanel cp = UserControlPanel.getInstance();
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Main.launch(cp,new Scanner(System.in));
        assertEquals(true, outContent.toString().contains("========Exit========"));
    }
    
    @Test
    public void launch_case3() throws IOException {
        String input="4\nCS3343G20\n0";
        ControlPanel cp = UserControlPanel.getInstance();
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Main.launch(cp,new Scanner(System.in));
        assertEquals(true, outContent.toString().contains("========Exit========"));
    }
    
    @Test
    public void main_case1() throws IOException {
        String input="4\nCS3343G20\n0";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Main.main(null);
        assertEquals(true, outContent.toString().contains("========Exit========"));
    }

 	@After
 	public void restoreStreams() {
 	    System.setOut(originalOut);
 	    System.setIn(originalIn);
 	}


}