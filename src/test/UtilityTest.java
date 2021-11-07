package test;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Scanner;

import SmartNavigationSystem.*;

public class UtilityTest {
	
	@Test // return integer
	public void getIntegerInput_TestCase1() {
		Utility utility= new Utility();
		Scanner input = new Scanner("1");
		int result = utility.getIntegerInput(input);
		assertEquals(1, result);
	}
	
	@Test // exception 
	public void getIntegerInput_TestCase2() {
		Utility utility= new Utility();
		Scanner input = new Scanner("*");
		int result = utility.getIntegerInput(input);
		assertEquals(- 1, result);
	}
}
