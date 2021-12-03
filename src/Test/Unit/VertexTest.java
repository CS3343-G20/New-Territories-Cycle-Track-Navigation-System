package test.Unit;

import org.junit.Test;
import static org.junit.Assert.*;

import SmartNavigationSystem.*;

public class VertexTest {

	Vertex v = new Vertex("1","testVertex");
	
	@Test
	public void toString_TestCase(){
		String expected = "[1] testVertex";
		String result =v.toString();
		assertEquals(expected, result);
	}
	
	@Test
	public void getName_TestCase(){
		String expected = "testVertex";
		String result =v.getName();
		assertEquals(expected, result);
	}
}
