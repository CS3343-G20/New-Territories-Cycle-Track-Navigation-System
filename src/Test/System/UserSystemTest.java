package Test.System;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.BeforeClass;
import org.junit.Test;

import SmartNavigationSystem.Main;

public class UserSystemTest {
    private static ByteArrayOutputStream output = new ByteArrayOutputStream();

	@BeforeClass
	public static void setUp() throws IOException {
		String input = "3\n2\n1\n1\n1\nY\n0\n1\nN\nN\n0\n1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        System.setOut(new PrintStream(output));
        Main.main(null);
	}


	@Test
	public void testlistTrails() {
		assertEquals(true, output.toString().contains("Here are all the trails:"));
		
	}
	@Test
	public void testchooseSelectionCriteria() {
		assertEquals(true,output.toString().contains("Please choose the selection criteria"));
	}
	@Test
	public void testfindTrailsByDifficulty() {
		assertEquals(true,output.toString().contains("Please choose the difficulty from following numbers:"));
		assertEquals(true,output.toString().contains("The following are the filtered trails:"));
	}
	@Test 
	public void testchooseClimbingPath() {
		assertEquals(true,output.toString().contains("This is the climbing trail chosen:"));
	}
	@Test
	public void testaddCycling() {
	    assertEquals(true,output.toString().contains("Do you want to cycle to the point? [Y/N]"));
	}
	@Test
	public void testsetDeparture() {
		assertEquals(true,output.toString().contains("Please input a departure ID:"));
	}
	@Test
	public void testsetDestination() {
		assertEquals(true,output.toString().contains("The destination has been set to be the start of your intended climbing trail (Sheung Shui) by default."));
	}
	@Test
	public void testaddAttractions() {
		assertEquals(true,output.toString().contains("Do you want to set attractions?[Y/N]"));
	}
	@Test
	public void testsetPriority() {
		assertEquals(true,output.toString().contains("Default priority setting is shortest path, do you want to set the priority to shortest time?[Y/N]"));
	}
	@Test
	public void testroutePlanning() {
		assertEquals(true,output.toString().contains("Cycling Planning Result: Sha Tin Che Kung Temple -> Lei Uk Tsuen -> Tsang Tai Uk -> Sheung Shui"));
	}
}
