 package test;

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

 import SmartNavigationSystem.AdminControlPanel;
 import SmartNavigationSystem.JsonOperation;

 public class lst_AdminControlPanelTest {

 	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
 	private final PrintStream originalOut = System.out;
 	private final InputStream originalIn = System.in;

 	@Before
 	public void setUpStreams() {
 	    System.setOut(new PrintStream(outContent));
 	}  
	
 	@Test
 	public void makeDecision_case1() throws IOException {
		
 		int res = AdminControlPanel.getInstance().makeDecision(new Scanner("7"));
 		assertEquals(1000, res);
		
 	}
	
 	@Test
 	public void makeDecision_case2() throws IOException {
		
 		int res = AdminControlPanel.getInstance().makeDecision(new Scanner("-7"));
 		assertEquals(1000, res);
		
 	}
	
 	@Test
 	public void makeDecision_case3() throws IOException {
		
 		int res = AdminControlPanel.getInstance().makeDecision(new Scanner("0"));
 		assertEquals(0, res);
		
 	}
	
 	@Test
 	public void makeDecision_case4() throws IOException {
		
 		new JsonOperation();
 		int res = AdminControlPanel.getInstance().makeDecision(new Scanner("1"));
 		assertEquals(1, res);
		
 	}

 	@Test
 	public void makeDecision_case5() throws IOException {
		
 		new JsonOperation();
		int res = AdminControlPanel.getInstance().makeDecision(new Scanner("2"));
 		assertEquals(2, res);
		
 	}

 	@Test
 	public void makeDecision_case6() throws IOException {
		
 		new JsonOperation();
 		int res = AdminControlPanel.getInstance().makeDecision(new Scanner("3"));
 		assertEquals(3, res);
		
 	}
	
	@After
 	public void restoreStreams() {
 	    System.setOut(originalOut);
 	    System.setIn(originalIn);
 	}
	
 }
