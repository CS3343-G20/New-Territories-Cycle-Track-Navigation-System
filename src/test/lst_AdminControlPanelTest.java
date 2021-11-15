// package test;

// import static org.junit.Assert.assertEquals;

// import java.io.ByteArrayInputStream;
// import java.io.ByteArrayOutputStream;
// import java.io.IOException;
// import java.io.InputStream;
// import java.io.PrintStream;

// import org.junit.After;
// import org.junit.Before;
// import org.junit.Test;

// import SmartNavigationSystem.AdminControlPanel;
// import SmartNavigationSystem.JsonOperation;

// public class lst_AdminControlPanelTest {

// 	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
// 	private final PrintStream originalOut = System.out;
// 	private final InputStream originalIn = System.in;

// 	@Before
// 	public void setUpStreams() {
// 	    System.setOut(new PrintStream(outContent));
// 	}  
	
// 	@Test
// 	public void makeDecision_case1() throws IOException {
		
// 		String input = "7";
// 		System.setIn(new ByteArrayInputStream(input.getBytes()));
// 		int res = AdminControlPanel.getInstance().makeDecision();
// 		assertEquals(1000, res);
		
// 	}
	
// 	@Test
// 	public void makeDecision_case2() throws IOException {
		
// 		String input = "-7";
// 		System.setIn(new ByteArrayInputStream(input.getBytes()));
// 		int res = AdminControlPanel.getInstance().makeDecision();
// 		assertEquals(1000, res);
		
// 	}
	
// 	@Test
// 	public void makeDecision_case3() throws IOException {
		
// 		String input = "0";
// 		System.setIn(new ByteArrayInputStream(input.getBytes()));
// 		int res = AdminControlPanel.getInstance().makeDecision();
// 		assertEquals(0, res);
		
// 	}
	
// 	@Test
// 	public void makeDecision_case4() throws IOException {
		
// 		String input = "1";
// 		System.setIn(new ByteArrayInputStream(input.getBytes()));
// 		new JsonOperation();
// 		int res = AdminControlPanel.getInstance().makeDecision();
// 		assertEquals(1, res);
		
// 	}

// 	@Test
// 	public void makeDecision_case5() throws IOException {
		
// 		String input = "2";
// 		System.setIn(new ByteArrayInputStream(input.getBytes()));
// 		new JsonOperation();
// 		int res = AdminControlPanel.getInstance().makeDecision();
// 		assertEquals(2, res);
		
// 	}

// 	@Test
// 	public void makeDecision_case6() throws IOException {
		
// 		String input = "3";
// 		System.setIn(new ByteArrayInputStream(input.getBytes()));
// 		new JsonOperation();
// 		int res = AdminControlPanel.getInstance().makeDecision();
// 		assertEquals(3, res);
		
// 	}
	
// 	@After
// 	public void restoreStreams() {
// 	    System.setOut(originalOut);
// 	    System.setIn(originalIn);
// 	}
	
// }
