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
			public int checkVertexIdValidity(int id) throws ExInvalidID {return id;}
			// useless below
			public String getVertexNameByID(int id) {return null;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public void listRoute(ArrayList<Integer> route) {}
			public int checkAttractionIdValidity(int id) throws ExInvalidID {return id;}
		}
		String input = "1";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		
		CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in));
		mode.setDeparture();
		
		Field departure = CyclingMode.class.getDeclaredField("departure");
		departure.setAccessible(true);
		assertEquals(1, departure.get(mode));
	}
	
	@Test //wrong input (invalid format)
	public void setDeparture_case2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {  
		class stubVertices implements VerticesManager {
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidID {return id;}
			// useless below
			public String getVertexNameByID(int id) {return null;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public void listRoute(ArrayList<Integer> route) {}
			public int checkAttractionIdValidity(int id) throws ExInvalidID {return id;}
		}
		String input = "a\n1";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		
		CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in));
		mode.setDeparture();
		
		Field departure = CyclingMode.class.getDeclaredField("departure");
		departure.setAccessible(true);
		assertEquals(1, departure.get(mode));
	}

	@Test //wrong input (invalid ID)
	public void setDeparture_case3() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {  
		class stubVertices implements VerticesManager {
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidID {
				if (id != 1) {
		        	throw new ExInvalidID();
		        }
		        return id;
			}
			// useless below
			public String getVertexNameByID(int id) {return null;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public void listRoute(ArrayList<Integer> route) {}
			public int checkAttractionIdValidity(int id) throws ExInvalidID {return id;}
		}
		String input = "6\n1";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		
		CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in));
		mode.setDeparture();
		
		Field departure = CyclingMode.class.getDeclaredField("departure");
		departure.setAccessible(true);
		assertEquals(1, departure.get(mode));
	}

	@Test //forCLimbing = false, correct input
    public void setDestination_case1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {  
		class stubVertices implements VerticesManager {
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidID {return id;}
			// useless below
			public String getVertexNameByID(int id) {return null;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public void listRoute(ArrayList<Integer> route) {}
			public int checkAttractionIdValidity(int id) throws ExInvalidID {return id;}
		}
		String input = "1";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		
		CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in));
		mode.setDestination();
		
		Field destination = CyclingMode.class.getDeclaredField("destination");
		destination.setAccessible(true);
		assertEquals(1, destination.get(mode));
	}
	
	@Test //forCLimbing = false, wrong input (invalid format)
    public void setDestination_case2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {  
		class stubVertices implements VerticesManager {
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidID {return id;}
			// useless below
			public String getVertexNameByID(int id) {return null;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public void listRoute(ArrayList<Integer> route) {}
			public int checkAttractionIdValidity(int id) throws ExInvalidID {return id;}
		}
		String input = "a\n1";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		
		CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in));
		mode.setDestination();
		
		Field destination = CyclingMode.class.getDeclaredField("destination");
		destination.setAccessible(true);
		assertEquals(1, destination.get(mode));
	}

	@Test //forCLimbing = false, wrong input (invalid ID)
    public void setDestination_case3() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {  
		class stubVertices implements VerticesManager {
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidID {
				if (id != 1) {
		        	throw new ExInvalidID();
		        }
		        return id;
			}
			// useless below
			public String getVertexNameByID(int id) {return null;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public void listRoute(ArrayList<Integer> route) {}
			public int checkAttractionIdValidity(int id) throws ExInvalidID {return id;}
		}
		String input = "6\n1";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		
		CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in));
		mode.setDestination();
		
		Field destination = CyclingMode.class.getDeclaredField("destination");
		destination.setAccessible(true);
		assertEquals(1, destination.get(mode));
	}

	@Test //forCLimbing = true, correct input to skip over
    public void setDestination_case4() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		class stubVertices implements VerticesManager {
			public String getVertexNameByID(int id) {return null;}
			// useless below
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidID {return id;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public void listRoute(ArrayList<Integer> route) {}
			public int checkAttractionIdValidity(int id) throws ExInvalidID {return id;}
		}
		
		String input = "1";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		
		CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in));
		Field forClimbing = CyclingMode.class.getDeclaredField("forClimbing");
		forClimbing.setAccessible(true);
		forClimbing.set(mode, true);
		Field destination = CyclingMode.class.getDeclaredField("destination");
		destination.setAccessible(true);
		destination.set(mode, 1);
		
		mode.setDestination();

		assertEquals(1, destination.get(mode));
	}

	@Test //forCLimbing = true, correct input for mandatory setting
    public void setDestination_case5() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		class stubVertices implements VerticesManager {
			public String getVertexNameByID(int id) {return null;}
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidID {return id;}
			// useless below
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public void listRoute(ArrayList<Integer> route) {}
			public int checkAttractionIdValidity(int id) throws ExInvalidID {return id;}
		}
		
		String input = "2\n1";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		
		CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in));
		Field forClimbing = CyclingMode.class.getDeclaredField("forClimbing");
		forClimbing.setAccessible(true);
		forClimbing.set(mode, true);
		Field destination = CyclingMode.class.getDeclaredField("destination");
		destination.setAccessible(true);
		destination.set(mode, 1);
		
		mode.setDestination();

		assertEquals(1, destination.get(mode));
	}

	@Test //forCLimbing = true, wrong input (invalid cmd)
    public void setDestination_case6() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		class stubVertices implements VerticesManager {
			public String getVertexNameByID(int id) {return null;}
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidID {return id;}
			// useless below
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public void listRoute(ArrayList<Integer> route) {}
			public int checkAttractionIdValidity(int id) throws ExInvalidID {return id;}
		}
		
		String input = "3\n2\n2";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		
		CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in));
		Field forClimbing = CyclingMode.class.getDeclaredField("forClimbing");
		forClimbing.setAccessible(true);
		forClimbing.set(mode, true);
		Field destination = CyclingMode.class.getDeclaredField("destination");
		destination.setAccessible(true);
		destination.set(mode, 1);
		
		mode.setDestination();

		assertEquals(2, destination.get(mode));
	}
	
	@Test //forCLimbing = true, wrong input (invalid format)
    public void setDestination_case7() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		class stubVertices implements VerticesManager {
			public String getVertexNameByID(int id) {return null;}
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidID {return id;}
			// useless below
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public void listRoute(ArrayList<Integer> route) {}
			public int checkAttractionIdValidity(int id) throws ExInvalidID {return id;}
		}
		
		String input = "a\n2\n2";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		
		CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in));
		Field forClimbing = CyclingMode.class.getDeclaredField("forClimbing");
		forClimbing.setAccessible(true);
		forClimbing.set(mode, true);
		Field destination = CyclingMode.class.getDeclaredField("destination");
		destination.setAccessible(true);
		destination.set(mode, 1);
		
		mode.setDestination();

		assertEquals(2, destination.get(mode));
	}

	@Test //correct command to add, correct input
    public void addAttractions_case1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		class stubVertices implements VerticesManager {
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidID {return id;}
			// useless below
			public String getVertexNameByID(int id) {return null;}
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidID {return id;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listRoute(ArrayList<Integer> route) {}
		}
		
		String input = "Y\n2 5";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		
		CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in));
		mode.addAttractions();
		
		Field attractions = CyclingMode.class.getDeclaredField("attractions");
		attractions.setAccessible(true);
		assertEquals(2, ((ArrayList<Integer>)(attractions.get(mode))).size());
	}
	
	@Test //correct command to add, wrong input (Invalid format)
    public void addAttractions_case2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		class stubVertices implements VerticesManager {
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidID {return id;}
			// useless below
			public String getVertexNameByID(int id) {return null;}
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidID {return id;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listRoute(ArrayList<Integer> route) {}
		}
		
		String input = "Y\na\n2";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		
		CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in));
		mode.addAttractions();
		
		Field attractions = CyclingMode.class.getDeclaredField("attractions");
		attractions.setAccessible(true);
		assertEquals(1, ((ArrayList<Integer>)(attractions.get(mode))).size());
	}
	
	@Test //correct command to add, wrong input (Invalid ID)
    public void addAttractions_case3() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		class stubVertices implements VerticesManager {
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidID {
				if (id != 2 && id !=5) {
					throw new ExInvalidID();
				}
				return id;
			}
			// useless below
			public String getVertexNameByID(int id) {return null;}
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidID {return id;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listRoute(ArrayList<Integer> route) {}
		}
		
		String input = "Y\n1\n2 5";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		
		CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in));
		mode.addAttractions();
		
		Field attractions = CyclingMode.class.getDeclaredField("attractions");
		attractions.setAccessible(true);
		assertEquals(2, ((ArrayList<Integer>)(attractions.get(mode))).size());
	}

	@Test //Correct command not to add
    public void addAttractions_case4() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		String input = "N";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		
		CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in));
		mode.addAttractions();
		
		Field attractions = CyclingMode.class.getDeclaredField("attractions");
		attractions.setAccessible(true);
		assertEquals(0, ((ArrayList<Integer>)(attractions.get(mode))).size());
	}
	
	@Test //Wrong command
    public void addAttractions_case5() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		class stubVertices implements VerticesManager {
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) {return id;}
			// useless below
			public String getVertexNameByID(int id) {return null;}
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id){return id;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listRoute(ArrayList<Integer> route) {}
		}
		
		String input = "a\nY\n2 5";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		
		CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in));
		mode.addAttractions();
		
		Field attractions = CyclingMode.class.getDeclaredField("attractions");
		attractions.setAccessible(true);
		assertEquals(2, ((ArrayList<Integer>)(attractions.get(mode))).size());
	}

	@Test //correct input (SP)
    public void setPriority_case1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {  
		String input = "N";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		
		CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in));
		mode.setPriority();
		
		Field priority = CyclingMode.class.getDeclaredField("priority");
		priority.setAccessible(true);
		assertEquals("SP", priority.get(mode));
	}

	@Test //correct input (ST)
    public void setPriority_case2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {  
		String input = "Y";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		
		CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in));
		mode.setPriority();
		
		Field priority = CyclingMode.class.getDeclaredField("priority");
		priority.setAccessible(true);
		assertEquals("ST", priority.get(mode));
	}

	@Test //wrong input
    public void setPriority_case3() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {  
		String input = "a\nY";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		
		CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in));
		mode.setPriority();
		
		Field priority = CyclingMode.class.getDeclaredField("priority");
		priority.setAccessible(true);
		assertEquals("ST", priority.get(mode));
	}
	
	@Test
	public void routePlanning_case1() {
		
	}

}