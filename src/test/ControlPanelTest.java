package test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import SmartNavigationSystem.ControlPanel;

public class ControlPanelTest extends ControlPanel{
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	
	public ControlPanelTest() {
		controlPanel.put(0, "exit");
	}
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}
	
	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	}
	
	private int decisionNum = 0;
	
	@Override
	public int makeDecision() throws IOException {
		return decisionNum;
	}
	
	@Test
	public void showControlPanel_testCase() {
		 ControlPanelTest testCP = new ControlPanelTest();
		 testCP.showControlPanel();
		 assertEquals("------------------------------\n0: exit\n------------------------------\n", outContent.toString());
	}	
}


/*
public abstract class ControlPanelTest {
	ControlPanel controlPanel;
	
	// factory method
	public abstract ControlPanel getControlPanel();
	//all test method needs to make use of the factory method to create the instance of controlPanel
	
	//return expected value
	public abstract int 
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}
	
	@Before
	public void setUp() throws Exception{
		controlPanel = getControlPanel();
	}
	
	@After
	public void tearDown() throws Exception{
	}
	
	@Test
	public int showControlPanel_testCase() {
		int expected = 
	}
	
}
*/