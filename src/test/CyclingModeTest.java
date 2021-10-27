package test;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;

import SmartNavigationSystem.*;

public class CyclingModeTest {

	@Test //correct input
    public void setDeparture_case1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {  
		class stubVertices implements VerticesManager {
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) {return id;}
			// useless below
			public String getVertexNameByID(int id) {return null;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public void listRoute(ArrayList<Integer> route) {}
			public int checkAttractionIdValidity(int id) {return id;}
		}
		String input = "1";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		
		CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in));
		mode.setDeparture();
		
		Field departure = CyclingMode.class.getDeclaredField("departure");
		departure.setAccessible(true);
		assertEquals(1, departure.get(mode));
	}

	@Test //wrong input
    public void setDeparture_case2() {

	}

	@Test //forCLimbing = false, correct input
    public void setDestination_case1() {  

	}

	@Test //forCLimbing = false, wrong input
    public void setDestination_case2() {

	}

	@Test //forCLimbing = true, correct input to skip over
    public void setDestination_case3() {

	}

	@Test //forCLimbing = true, correct input for mandatory setting
    public void setDestination_case4() {

	}

	@Test //forCLimbing = true, wrong input
    public void setDestination_case5() {

	}

	@Test //correct input
    public void addAttractions_case1() {  

	}

	@Test //wrong input
    public void addAttractions_case2() {  

	}

	@Test //correct input (SP)
    public void setPriority_case1() {  

	}

	@Test //correct input (ST)
    public void setPriority_case2() {  

	}

	@Test //wrong input
    public void setPriority_case3() {  

	}

}