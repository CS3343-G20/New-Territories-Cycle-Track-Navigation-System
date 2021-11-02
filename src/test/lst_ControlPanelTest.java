package test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import SmartNavigationSystem.ControlPanel;

public class lst_ControlPanelTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}

	@Test
	public void showControlPanel_case1() {
		
		class Stub_ControlPanel extends ControlPanel {
			public Stub_ControlPanel () {
				controlPanel.put(0, "str");
			}

			@Override
			public int makeDecision() throws IOException {
				return 0;
			}
		}
		
		Stub_ControlPanel cp = new Stub_ControlPanel();
		cp.showControlPanel();
		
	    assertEquals("------------------------------\n0: str\n------------------------------\n", outContent.toString());
	    
	}
	
	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	}
	
	
}