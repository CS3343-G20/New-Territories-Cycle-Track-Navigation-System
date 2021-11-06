package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Member;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import SmartNavigationSystem.Admin;
import SmartNavigationSystem.Register;
import SmartNavigationSystem.Tourist;
import SmartNavigationSystem.UserControlPanel;

@RunWith(MockitoJUnitRunner.class)

public class lst_UserControlPanelTest {
    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final InputStream originalIn = System.in;

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}  

    
    @Test
    public void makeDecision_case1() {
		String input = "7";
		System.setIn(new ByteArrayInputStream(input.getBytes())); 
		int res = UserControlPanel.getInstance().makeDecision();
		assertEquals(1000, res);
    }

	@Test
    public void makeDecision_case2() {
		String input = "-7";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		int res = UserControlPanel.getInstance().makeDecision();
		assertEquals(1000, res);
    }

	@Test
    public void makeDecision_case3() {
		String input = "0";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		int res = UserControlPanel.getInstance().makeDecision();
		assertEquals(0, res);
    }

	@Mock
	private Member member;
	private Register register;
	private Tourist tourist;
	private Admin admin;
	
	@Test
	public void makeDecision_case4() throws IOException {
		
		String input = "2";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		int res = UserControlPanel.getInstance().makeDecision();
		//assertEquals(1, res);
		verify(register).register();
	}


    @After
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setIn(originalIn);
	}


}
