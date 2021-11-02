package test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
		
		String input = "100";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		int res = MemberControlPanel.getInstance().makeDecision();
		assertEquals(1000, res);
		
	}
	
	@Test
	public void makeDecision_case2() throws IOException {
		
		String input = "0";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		int res = MemberControlPanel.getInstance().makeDecision();
		assertEquals(0, res);
		
	}
	
	@Test
	public void makeDecision_case3() throws IOException {
		
		class Stub_MemberControlPanel extends MemberControlPanel {
			
			public Stub_MemberControlPanel () {
				//super();
			}
			
			
		}
		
		String input = "1";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		int res = MemberControlPanel.getInstance().makeDecision();
		assertEquals(1, res);
		
	}
	
	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setIn(originalIn);
	}
	
}