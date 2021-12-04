package Test.Unit;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;

import SmartNavigationSystem.*;

public class CyclingModeTest {

    @Test // correct input
    public void setDeparture_case1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        class stubVertices implements VerticesManager {
            public void listAllVertices() {}
            public int checkVertexIdValidity(int id) throws ExInvalidIndex {return id;}
            // useless below
            public String getVertexNameByID(int id) {return null;}
            public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
            public void listAttractions() {}
            public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
            public String getRouteString(ArrayList<Integer> route) {return null;}
        }
        String input = "1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
        mode.setDeparture(); 

        Field departure = CyclingMode.class.getDeclaredField("departure");
        departure.setAccessible(true);
        assertEquals(1, departure.get(mode));
    }

    @Test // wrong input (invalid format)
    public void setDeparture_case2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        class stubVertices implements VerticesManager {
            public void listAllVertices() {}
            public int checkVertexIdValidity(int id) throws ExInvalidIndex {return id;}
            // useless below
            public String getVertexNameByID(int id) {return null;}
            public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
            public void listAttractions() {}
            public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
            public String getRouteString(ArrayList<Integer> route) {return null;}
        }
        String input = "a\n1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
        mode.setDeparture();

        Field departure = CyclingMode.class.getDeclaredField("departure");
        departure.setAccessible(true);
        assertEquals(1, departure.get(mode));
    }

    @Test // wrong input (invalid ID)
    public void setDeparture_case3() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        class stubVertices implements VerticesManager {
        	public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidIndex {
				if (id != 1) {
		        	throw new ExInvalidIndex();
		        }
		        return id;
			}
			// useless below
			public String getVertexNameByID(int id) {return null;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getRouteString(ArrayList<Integer> route) {return null;}
        }
        String input = "10\n1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
        mode.setDeparture();

        Field departure = CyclingMode.class.getDeclaredField("departure");
        departure.setAccessible(true);
        assertEquals(1, departure.get(mode));
    }

    @Test // forCLimbing = false, correct input
    public void setDestination_case1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    	class stubVertices implements VerticesManager {
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidIndex {return id;}
			// useless below
			public String getVertexNameByID(int id) {return null;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getRouteString(ArrayList<Integer> route) {return null;}
		}
        String input = "1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
        mode.setDestination();

        Field destination = CyclingMode.class.getDeclaredField("destination");
        destination.setAccessible(true);
        assertEquals(1, destination.get(mode));
    }

    @Test // forCLimbing = false, wrong input (invalid format)
    public void setDestination_case2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {  
		class stubVertices implements VerticesManager {
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidIndex {return id;}
			// useless below
			public String getVertexNameByID(int id) {return null;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getRouteString(ArrayList<Integer> route) {return null;}
		}
        String input = "a\n1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
        mode.setDestination();

        Field destination = CyclingMode.class.getDeclaredField("destination");
        destination.setAccessible(true);
        assertEquals(1, destination.get(mode));
    }

    @Test // forCLimbing = false, wrong input (invalid ID)
    public void setDestination_case3() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {  
		class stubVertices implements VerticesManager {
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidIndex {
				if (id != 1) {
		        	throw new ExInvalidIndex();
		        }
		        return id;
			}
			// useless below
			public String getVertexNameByID(int id) {return null;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getRouteString(ArrayList<Integer> route) {return null;}
		}
        String input = "10\n1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
        mode.setDestination();

        Field destination = CyclingMode.class.getDeclaredField("destination");
        destination.setAccessible(true);
        assertEquals(1, destination.get(mode));
    }

    @Test // forCLimbing = true, correct input to skip over
    public void setDestination_case4() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		class stubVertices implements VerticesManager {
			public String getVertexNameByID(int id) {return null;}
			// useless below
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getRouteString(ArrayList<Integer> route) {return null;}
		}

        String input = "1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
        Field forClimbing = CyclingMode.class.getDeclaredField("forClimbing");
        forClimbing.setAccessible(true);
        forClimbing.set(mode, true);
        Field destination = CyclingMode.class.getDeclaredField("destination");
        destination.setAccessible(true);
        destination.set(mode, 1);

        mode.setDestination();

        assertEquals(1, destination.get(mode));
    }

    @Test // forCLimbing = true, correct input for mandatory setting
    public void setDestination_case5() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		class stubVertices implements VerticesManager {
			public String getVertexNameByID(int id) {return null;}
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidIndex {return id;}
			// useless below
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getRouteString(ArrayList<Integer> route) {return null;}
		}

        String input = "2\n1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
        Field forClimbing = CyclingMode.class.getDeclaredField("forClimbing");
        forClimbing.setAccessible(true);
        forClimbing.set(mode, true);
        Field destination = CyclingMode.class.getDeclaredField("destination");
        destination.setAccessible(true);
        destination.set(mode, 1);

        mode.setDestination();

        assertEquals(1, destination.get(mode));
    }

    @Test // forCLimbing = true, wrong input (invalid cmd)
    public void setDestination_case6() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		class stubVertices implements VerticesManager {
			public String getVertexNameByID(int id) {return null;}
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidIndex {return id;}
			// useless below
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getRouteString(ArrayList<Integer> route) {return null;}
		}

        String input = "3\n2\n2";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
        Field forClimbing = CyclingMode.class.getDeclaredField("forClimbing");
        forClimbing.setAccessible(true);
        forClimbing.set(mode, true);
        Field destination = CyclingMode.class.getDeclaredField("destination");
        destination.setAccessible(true);
        destination.set(mode, 1);

        mode.setDestination();

        assertEquals(2, destination.get(mode));
    }

    @Test // forCLimbing = true, wrong input (invalid format)
    public void setDestination_case7() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		class stubVertices implements VerticesManager {
			public String getVertexNameByID(int id) {return null;}
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidIndex {return id;}
			// useless below
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getRouteString(ArrayList<Integer> route) {return null;}
		}

        String input = "a\n2\n2";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
        Field forClimbing = CyclingMode.class.getDeclaredField("forClimbing");
        forClimbing.setAccessible(true);
        forClimbing.set(mode, true);
        Field destination = CyclingMode.class.getDeclaredField("destination");
        destination.setAccessible(true);
        destination.set(mode, 1);

        mode.setDestination();

        assertEquals(2, destination.get(mode));
    }

    @Test // correct command to add, correct input
    public void addAttractions_case1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		class stubVertices implements VerticesManager {
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
			// useless below
			public String getVertexNameByID(int id) {return null;}
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public String getRouteString(ArrayList<Integer> route) {return null;}
		}

        String input = "Y\n2 5";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
        mode.addAttractions();

        Field attractions = CyclingMode.class.getDeclaredField("attractions");
        attractions.setAccessible(true);
        assertEquals(2, ((ArrayList<Integer>) (attractions.get(mode))).size());
    }

    @Test // correct command to add, wrong input (Invalid format)
    public void addAttractions_case2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		class stubVertices implements VerticesManager {
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
			// useless below
			public String getVertexNameByID(int id) {return null;}
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public String getRouteString(ArrayList<Integer> route) {return null;}
		}

        String input = "Y\na\n2";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
        mode.addAttractions();

        Field attractions = CyclingMode.class.getDeclaredField("attractions");
        attractions.setAccessible(true);
        assertEquals(1, ((ArrayList<Integer>) (attractions.get(mode))).size());
    }

    @Test // correct command to add, wrong input (Invalid ID)
    public void addAttractions_case3() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		class stubVertices implements VerticesManager {
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidIndex {
				if (id != 2 && id !=5) {
					throw new ExInvalidIndex();
				}
				return id;
			}
			// useless below
			public String getVertexNameByID(int id) {return null;}
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public String getRouteString(ArrayList<Integer> route) {return null;}
		}

        String input = "Y\n1\n2 5";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
        mode.addAttractions();

        Field attractions = CyclingMode.class.getDeclaredField("attractions");
        attractions.setAccessible(true);
        assertEquals(2, ((ArrayList<Integer>) (attractions.get(mode))).size());
    }

    @Test // Correct command not to add
    public void addAttractions_case4() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		String input = "N";
		System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
        mode.addAttractions();

        Field attractions = CyclingMode.class.getDeclaredField("attractions");
        attractions.setAccessible(true);
        assertEquals(0, ((ArrayList<Integer>) (attractions.get(mode))).size());
    }

    @Test // Wrong command
    public void addAttractions_case5() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		class stubVertices implements VerticesManager {
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) {return id;}
			// useless below
			public String getVertexNameByID(int id) {return null;}
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id){return id;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public String getRouteString(ArrayList<Integer> route) {return null;}
		}

        String input = "a\nY\n2 5";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
        mode.addAttractions();

        Field attractions = CyclingMode.class.getDeclaredField("attractions");
        attractions.setAccessible(true);
        assertEquals(2, ((ArrayList<Integer>) (attractions.get(mode))).size());
    }

    @Test // correct input (SP)
    public void setPriority_case1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String input = "N";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
        mode.setPriority();

        Field priority = CyclingMode.class.getDeclaredField("priority");
        priority.setAccessible(true);
        assertEquals("SP", priority.get(mode));
    }

    @Test // correct input (ST)
    public void setPriority_case2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String input = "Y";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
        mode.setPriority();

        Field priority = CyclingMode.class.getDeclaredField("priority");
        priority.setAccessible(true);
        assertEquals("ST", priority.get(mode));
    }

    @Test // wrong input
    public void setPriority_case3() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String input = "a\nY";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
        mode.setPriority();

        Field priority = CyclingMode.class.getDeclaredField("priority");
        priority.setAccessible(true);
        assertEquals("ST", priority.get(mode));
    }

    @Test // no attractions, more than one route, correct input
    public void routePlanning_case1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		class stubGraph implements GraphUtility {
			public void dijkstra(String priority, int s) {}
			public void dfs(int s, int d, ArrayList<Integer> temp) {}
			public int getDistance(int d) {return 3;}
			public int getRoutesNumber() {return 4;}
			public ArrayList<Integer> getRoute(int i) {
				ArrayList<Integer> r = new ArrayList<Integer>();
				r.add(0);r.add(3);r.add(5);r.add(6);
				return r;
			}
			public void clearRoutes() {}
		}
		class stubVertices implements VerticesManager {
			public String getVertexNameByID(int id) {return null;}
			// useless below
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getRouteString(ArrayList<Integer> route) {return null;}
		}

        String input = "1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(new stubGraph(), new stubVertices(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());

        Field departure = CyclingMode.class.getDeclaredField("departure");
        departure.setAccessible(true);
        departure.set(mode, 0);
        Field destination = CyclingMode.class.getDeclaredField("destination");
        destination.setAccessible(true);
        destination.set(mode, 6);

        mode.routePlanning();

        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(0);
        expected.add(3);
        expected.add(5);
        expected.add(6);

        Field route = CyclingMode.class.getDeclaredField("route");
        route.setAccessible(true);

        assertEquals(expected, route.get(mode));
    }

    @Test // no attractions, only one route, no input
    public void routePlanning_case2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		class stubGraph implements GraphUtility {
			public void dijkstra(String priority, int s) {}
			public void dfs(int s, int d, ArrayList<Integer> temp) {}
			public int getDistance(int d) {return 1;}
			public int getRoutesNumber() {return 1;}
			public ArrayList<Integer> getRoute(int i) {
				ArrayList<Integer> r = new ArrayList<Integer>();
				r.add(0);r.add(1);
				return r;
			}
			public void clearRoutes() {}
		}
		class stubVertices implements VerticesManager {
			public String getVertexNameByID(int id) {return null;}
			// useless below
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getRouteString(ArrayList<Integer> route) {return null;}
		}

        CyclingMode mode = new CyclingMode(new stubGraph(), new stubVertices(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());

        Field departure = CyclingMode.class.getDeclaredField("departure");
        departure.setAccessible(true);
        departure.set(mode, 0);
        Field destination = CyclingMode.class.getDeclaredField("destination");
        destination.setAccessible(true);
        destination.set(mode, 1);

        mode.routePlanning();

        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(0);
        expected.add(1);

        Field route = CyclingMode.class.getDeclaredField("route");
        route.setAccessible(true);

        assertEquals(expected, route.get(mode));
    }

    @Test // no attractions, wrong input (invalid format)
    public void routePlanning_case3() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		class stubGraph implements GraphUtility {
			public void dijkstra(String priority, int s) {}
			public void dfs(int s, int d, ArrayList<Integer> temp) {}
			public int getDistance(int d) {return 3;}
			public int getRoutesNumber() {return 4;}
			public ArrayList<Integer> getRoute(int i) {
				ArrayList<Integer> r = new ArrayList<Integer>();
				r.add(0);r.add(3);r.add(5);r.add(6);
				return r;
			}
			public void clearRoutes() {}
		}
		class stubVertices implements VerticesManager {
			public String getVertexNameByID(int id) {return null;}
			// useless below
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getRouteString(ArrayList<Integer> route) {return null;}
		}

        String input = "a\n1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(new stubGraph(), new stubVertices(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());

        Field departure = CyclingMode.class.getDeclaredField("departure");
        departure.setAccessible(true);
        departure.set(mode, 0);
        Field destination = CyclingMode.class.getDeclaredField("destination");
        destination.setAccessible(true);
        destination.set(mode, 6);

        mode.routePlanning();

        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(0);
        expected.add(3);
        expected.add(5);
        expected.add(6);

        Field route = CyclingMode.class.getDeclaredField("route");
        route.setAccessible(true);

        assertEquals(expected, route.get(mode));
    }

    @Test // no attractions, wrong input (invalid index)
    public void routePlanning_case4() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		class stubGraph implements GraphUtility {
			private ArrayList<ArrayList<Integer>> routes = new ArrayList<ArrayList<Integer>>();
			
			stubGraph() {
				ArrayList<Integer> r1 = new ArrayList<Integer>();
				r1.add(0);r1.add(3);r1.add(5);r1.add(6);
				routes.add(r1);
				ArrayList<Integer> r2 = new ArrayList<Integer>();
				routes.add(r2);
				ArrayList<Integer> r3 = new ArrayList<Integer>();
				routes.add(r3);
				ArrayList<Integer> r4 = new ArrayList<Integer>();
				routes.add(r4);
			}
			public void dijkstra(String priority, int s) {}
			public void dfs(int s, int d, ArrayList<Integer> temp) {}
			public int getDistance(int d) {return 3;}
			public int getRoutesNumber() {return 4;}
			public ArrayList<Integer> getRoute(int i) {
				return routes.get(i);
			}
			public void clearRoutes() {}
		}
		class stubVertices implements VerticesManager {
			public String getVertexNameByID(int id) {return null;}
			// useless below
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getRouteString(ArrayList<Integer> route) {return null;}
		}


        String input = "5\n1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(new stubGraph(), new stubVertices(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());

        Field departure = CyclingMode.class.getDeclaredField("departure");
        departure.setAccessible(true);
        departure.set(mode, 0);
        Field destination = CyclingMode.class.getDeclaredField("destination");
        destination.setAccessible(true);
        destination.set(mode, 6);

        mode.routePlanning();

        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(0);
        expected.add(3);
        expected.add(5);
        expected.add(6);

        Field route = CyclingMode.class.getDeclaredField("route");
        route.setAccessible(true);

        assertEquals(expected, route.get(mode));
    }

    @Test // with attractions, only one route, no input
    public void routePlanning_case5() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		class stubGraph implements GraphUtility {
			private ArrayList<ArrayList<Integer>> routes = new ArrayList<ArrayList<Integer>>();
			private int count = -1;
			
			stubGraph() {
				ArrayList<Integer> r1 = new ArrayList<Integer>();
				r1.add(0);r1.add(2);
				routes.add(r1);
				ArrayList<Integer> r2 = new ArrayList<Integer>();
				r2.add(2);r2.add(5);
				routes.add(r2);
				ArrayList<Integer> r3 = new ArrayList<Integer>();
				r3.add(5);r3.add(6);
				routes.add(r3);
			}
			public void dijkstra(String priority, int s) {}
			public void dfs(int s, int d, ArrayList<Integer> temp) {}
			public int getDistance(int d) {return 1;}
			public int getRoutesNumber() {return 1;}
			public ArrayList<Integer> getRoute(int i) {
				count++;
				return routes.get(count);
			}
			public void clearRoutes() {}
		}
		class stubVertices implements VerticesManager {
			public String getVertexNameByID(int id) {return null;}
			// useless below
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getRouteString(ArrayList<Integer> route) {return null;}
		}

        CyclingMode mode = new CyclingMode(new stubGraph(), new stubVertices(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());

        Field departure = CyclingMode.class.getDeclaredField("departure");
        departure.setAccessible(true);
        departure.set(mode, 0);
        Field destination = CyclingMode.class.getDeclaredField("destination");
        destination.setAccessible(true);
        destination.set(mode, 6);
        Field attractions = CyclingMode.class.getDeclaredField("attractions");
        attractions.setAccessible(true);
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(2);
        list.add(5);
        attractions.set(mode, list);

        mode.routePlanning();

        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(0);
        expected.add(2);
        expected.add(5);
        expected.add(6);

        Field route = CyclingMode.class.getDeclaredField("route");
        route.setAccessible(true);

        assertEquals(expected, route.get(mode));
    }

    @Test // 0 confirm
    public void execute_case1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {  
		class stubVertices implements VerticesManager {
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getVertexNameByID(int id) {return null;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
			//useless below
			public String getRouteString(ArrayList<Integer> route) {return null;}
		}
		class stubGraph implements GraphUtility {
			private ArrayList<ArrayList<Integer>> routes = new ArrayList<ArrayList<Integer>>();
			private int count = -1;
			
			stubGraph() {
				ArrayList<Integer> r1 = new ArrayList<Integer>();
				r1.add(0);r1.add(2);
				routes.add(r1);
				ArrayList<Integer> r2 = new ArrayList<Integer>();
				r2.add(2);r2.add(5);
				routes.add(r2);
				ArrayList<Integer> r3 = new ArrayList<Integer>();
				r3.add(5);r3.add(6);
				routes.add(r3);
			}
			public void dijkstra(String priority, int s) {}
			public void dfs(int s, int d, ArrayList<Integer> temp) {}
			public int getDistance(int d) {return 1;}
			public int getRoutesNumber() {return 1;}
			public ArrayList<Integer> getRoute(int i) {
				count++;
				return routes.get(count);
			}
			public void clearRoutes() {}
		}

        String input = "0\n6\nY\n2 5\nN\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(new stubGraph(), new stubVertices(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());

        mode.execute();

        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(0);
        expected.add(2);
        expected.add(5);
        expected.add(6);

        Field route = CyclingMode.class.getDeclaredField("route");
        route.setAccessible(true);

        assertEquals(expected, route.get(mode));
    }

    @Test // 1 change the departure
    public void execute_case2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {  
		class stubVertices implements VerticesManager {
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getVertexNameByID(int id) {return null;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
			//useless below
			public String getRouteString(ArrayList<Integer> route) {return null;}
		}
		class stubGraph implements GraphUtility {
			private ArrayList<ArrayList<Integer>> routes = new ArrayList<ArrayList<Integer>>();
			private int count = -1;
			
			stubGraph() {
				ArrayList<Integer> r1 = new ArrayList<Integer>();
				r1.add(0);r1.add(2);
				routes.add(r1);
				ArrayList<Integer> r2 = new ArrayList<Integer>();
				r2.add(2);r2.add(5);
				routes.add(r2);
				ArrayList<Integer> r3 = new ArrayList<Integer>();
				r3.add(5);r3.add(6);
				routes.add(r3);
			}
			public void dijkstra(String priority, int s) {}
			public void dfs(int s, int d, ArrayList<Integer> temp) {}
			public int getDistance(int d) {return 1;}
			public int getRoutesNumber() {return 1;}
			public ArrayList<Integer> getRoute(int i) {
				count++;
				return routes.get(count);
			}
			public void clearRoutes() {}
		}

        String input = "1\n6\nY\n2 5\nN\n1\n0\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(new stubGraph(), new stubVertices(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());

        mode.execute();

        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(0);
        expected.add(2);
        expected.add(5);
        expected.add(6);

        Field route = CyclingMode.class.getDeclaredField("route");
        route.setAccessible(true);

        assertEquals(expected, route.get(mode));
    }

    @Test // 2 change the destination
    public void execute_case3() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {  
		class stubVertices implements VerticesManager {
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getVertexNameByID(int id) {return null;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
			//useless below
			public String getRouteString(ArrayList<Integer> route) {return null;}
		}
		class stubGraph implements GraphUtility {
			private ArrayList<ArrayList<Integer>> routes = new ArrayList<ArrayList<Integer>>();
			private int count = -1;
			
			stubGraph() {
				ArrayList<Integer> r1 = new ArrayList<Integer>();
				r1.add(0);r1.add(2);
				routes.add(r1);
				ArrayList<Integer> r2 = new ArrayList<Integer>();
				r2.add(2);r2.add(5);
				routes.add(r2);
				ArrayList<Integer> r3 = new ArrayList<Integer>();
				r3.add(5);r3.add(6);
				routes.add(r3);
			}
			public void dijkstra(String priority, int s) {}
			public void dfs(int s, int d, ArrayList<Integer> temp) {}
			public int getDistance(int d) {return 1;}
			public int getRoutesNumber() {return 1;}
			public ArrayList<Integer> getRoute(int i) {
				count++;
				return routes.get(count);
			}
			public void clearRoutes() {}
		}

        String input = "0\n1\nY\n2 5\nN\n2\n6\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(new stubGraph(), new stubVertices(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());

        mode.execute();

        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(0);
        expected.add(2);
        expected.add(5);
        expected.add(6);

        Field route = CyclingMode.class.getDeclaredField("route");
        route.setAccessible(true);

        assertEquals(expected, route.get(mode));
    }

    @Test // 3 change attractions
    public void execute_case4() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {  
		class stubVertices implements VerticesManager {
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getVertexNameByID(int id) {return null;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
			//useless below
			public String getRouteString(ArrayList<Integer> route) {return null;}
		}
		class stubGraph implements GraphUtility {
			private ArrayList<ArrayList<Integer>> routes = new ArrayList<ArrayList<Integer>>();
			private int count = -1;
			
			stubGraph() {
				ArrayList<Integer> r1 = new ArrayList<Integer>();
				r1.add(0);r1.add(2);
				routes.add(r1);
				ArrayList<Integer> r2 = new ArrayList<Integer>();
				r2.add(2);r2.add(5);
				routes.add(r2);
				ArrayList<Integer> r3 = new ArrayList<Integer>();
				r3.add(5);r3.add(6);
				routes.add(r3);
			}
			public void dijkstra(String priority, int s) {}
			public void dfs(int s, int d, ArrayList<Integer> temp) {}
			public int getDistance(int d) {return 1;}
			public int getRoutesNumber() {return 1;}
			public ArrayList<Integer> getRoute(int i) {
				count++;
				return routes.get(count);
			}
			public void clearRoutes() {}
		}

        String input = "0\n6\nY\n2\nN\n3\nY\n2 5\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(new stubGraph(), new stubVertices(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());

        mode.execute();

        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(0);
        expected.add(2);
        expected.add(5);
        expected.add(6);

        Field route = CyclingMode.class.getDeclaredField("route");
        route.setAccessible(true);

        assertEquals(expected, route.get(mode));
    }

    @Test // 4 change priority
    public void execute_case5() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {  
		class stubVertices implements VerticesManager {
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getVertexNameByID(int id) {return null;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
			//useless below
			public String getRouteString(ArrayList<Integer> route) {return null;}
		}
		class stubGraph implements GraphUtility {
			private ArrayList<ArrayList<Integer>> routes = new ArrayList<ArrayList<Integer>>();
			private int count = -1;
			
			stubGraph() {
				ArrayList<Integer> r1 = new ArrayList<Integer>();
				r1.add(0);r1.add(2);
				routes.add(r1);
				ArrayList<Integer> r2 = new ArrayList<Integer>();
				r2.add(2);r2.add(5);
				routes.add(r2);
				ArrayList<Integer> r3 = new ArrayList<Integer>();
				r3.add(5);r3.add(6);
				routes.add(r3);
			}
			public void dijkstra(String priority, int s) {}
			public void dfs(int s, int d, ArrayList<Integer> temp) {}
			public int getDistance(int d) {return 1;}
			public int getRoutesNumber() {return 1;}
			public ArrayList<Integer> getRoute(int i) {
				count++;
				return routes.get(count);
			}
			public void clearRoutes() {}
		}

        String input = "0\n6\nY\n2 5\nN\n4\nY\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(new stubGraph(), new stubVertices(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());

        mode.execute();

        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(0);
        expected.add(2);
        expected.add(5);
        expected.add(6);

        Field route = CyclingMode.class.getDeclaredField("route");
        route.setAccessible(true);

        assertEquals(expected, route.get(mode));
    }

    @Test // invalid format
    public void execute_case6() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {  
		class stubVertices implements VerticesManager {
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getVertexNameByID(int id) {return null;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
			//useless below
			public String getRouteString(ArrayList<Integer> route) {return null;}
		}
		class stubGraph implements GraphUtility {
			private ArrayList<ArrayList<Integer>> routes = new ArrayList<ArrayList<Integer>>();
			private int count = -1;
			
			stubGraph() {
				ArrayList<Integer> r1 = new ArrayList<Integer>();
				r1.add(0);r1.add(2);
				routes.add(r1);
				ArrayList<Integer> r2 = new ArrayList<Integer>();
				r2.add(2);r2.add(5);
				routes.add(r2);
				ArrayList<Integer> r3 = new ArrayList<Integer>();
				r3.add(5);r3.add(6);
				routes.add(r3);
			}
			public void dijkstra(String priority, int s) {}
			public void dfs(int s, int d, ArrayList<Integer> temp) {}
			public int getDistance(int d) {return 1;}
			public int getRoutesNumber() {return 1;}
			public ArrayList<Integer> getRoute(int i) {
				count++;
				return routes.get(count);
			}
			public void clearRoutes() {}
		}

        String input = "0\n6\nY\n2 5\nN\na\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(new stubGraph(), new stubVertices(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());

        mode.execute();

        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(0);
        expected.add(2);
        expected.add(5);
        expected.add(6);

        Field route = CyclingMode.class.getDeclaredField("route");
        route.setAccessible(true);

        assertEquals(expected, route.get(mode));
    }

    @Test // invalid command
    public void execute_case7() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {  
		class stubVertices implements VerticesManager {
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getVertexNameByID(int id) {return null;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
			//useless below
			public String getRouteString(ArrayList<Integer> route) {return null;}
		}
		class stubGraph implements GraphUtility {
			private ArrayList<ArrayList<Integer>> routes = new ArrayList<ArrayList<Integer>>();
			private int count = -1;
			
			stubGraph() {
				ArrayList<Integer> r1 = new ArrayList<Integer>();
				r1.add(0);r1.add(2);
				routes.add(r1);
				ArrayList<Integer> r2 = new ArrayList<Integer>();
				r2.add(2);r2.add(5);
				routes.add(r2);
				ArrayList<Integer> r3 = new ArrayList<Integer>();
				r3.add(5);r3.add(6);
				routes.add(r3);
			}
			public void dijkstra(String priority, int s) {}
			public void dfs(int s, int d, ArrayList<Integer> temp) {}
			public int getDistance(int d) {return 1;}
			public int getRoutesNumber() {return 1;}
			public ArrayList<Integer> getRoute(int i) {
				count++;
				return routes.get(count);
			}
			public void clearRoutes() {}
		}

        String input = "0\n6\nY\n2 5\nN\n5\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(new stubGraph(), new stubVertices(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());

        mode.execute();

        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(0);
        expected.add(2);
        expected.add(5);
        expected.add(6);

        Field route = CyclingMode.class.getDeclaredField("route");
        route.setAccessible(true);

        assertEquals(expected, route.get(mode));
    }

	@Test // not add
    public void addBookmark_case1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		class stubVertices implements VerticesManager {
			// useless below
			public String getVertexNameByID(int id) {return null;}
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getRouteString(ArrayList<Integer> route) {return null;}
		}
		class stubBookmark implements BookmarkManager {
			
			String route = null;
			Member member = null;

			public void addBookmark(String route, Member member) {
				this.route = route;
				this.member = member;
			}
		}

        String input = "N\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        stubBookmark mark = new stubBookmark();
        CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in), mark, ClimbingTrailRepository.getInstance());

        Field member = CyclingMode.class.getDeclaredField("member");
        member.setAccessible(true);
        Member m = new Member();
        member.set(mode, m);

        mode.addBookmark();

        assertEquals(true, (mark.route == null) && (mark.member == null));
    }

    @Test // add
    public void addBookmark_case2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		class stubVertices implements VerticesManager {
			public String getRouteString(ArrayList<Integer> route) {return "a";}
			// useless below
			public String getVertexNameByID(int id) {return null;}
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
		}
		class stubBookmark implements BookmarkManager {
			
			String route = null;
			Member member = null;

			public void addBookmark(String route, Member member) {
				this.route = route;
				this.member = member;
			}
		}

        String input = "Y\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        stubBookmark mark = new stubBookmark();
        CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in), mark, ClimbingTrailRepository.getInstance());

        Field member = CyclingMode.class.getDeclaredField("member");
        member.setAccessible(true);
        Member m = new Member();
        member.set(mode, m);

        mode.addBookmark();

        assertEquals(true, (mark.route.equals("Cycling Mode: a")) && (mark.member == m));
    }

    @Test // wrong input
    public void addBookmark_case3() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		class stubVertices implements VerticesManager {
			public String getRouteString(ArrayList<Integer> route) {return "a";}
			// useless below
			public String getVertexNameByID(int id) {return null;}
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
		}
		class stubBookmark implements BookmarkManager {
			
			String route = null;
			Member member = null;

			public void addBookmark(String route, Member member) {
				this.route = route;
				this.member = member;
			}
		}
		
        String input = "1\nY\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        stubBookmark mark = new stubBookmark();
        CyclingMode mode = new CyclingMode(Graph.getInstance(), new stubVertices(), new Scanner(System.in), mark, ClimbingTrailRepository.getInstance());

        Field member = CyclingMode.class.getDeclaredField("member");
        member.setAccessible(true);
        Member m = new Member();
        member.set(mode, m);

        mode.addBookmark();

        assertEquals(true, (mark.route.equals("Cycling Mode: a")) && (mark.member == m));
    }
    
    @Test
    public void memberExecute_case1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        class stubVertices implements VerticesManager {
            public void listAllVertices() {}
            public int checkVertexIdValidity(int id) throws ExInvalidIndex { return id;}
            public String getVertexNameByID(int id) { return null;}
            public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
            public void listAttractions() { }
            public int checkAttractionIdValidity(int id) throws ExInvalidIndex { return id;}
            public String getRouteString(ArrayList<Integer> route) { return "a";}
        }
        class stubGraph implements GraphUtility {
            private ArrayList<ArrayList<Integer>> routes = new ArrayList<ArrayList<Integer>>();
            private int count = -1;
            stubGraph() {
                ArrayList<Integer> r1 = new ArrayList<Integer>();
                r1.add(0);
                r1.add(2);
                routes.add(r1);
                ArrayList<Integer> r2 = new ArrayList<Integer>();
                r2.add(2);
                r2.add(5);
                routes.add(r2);
                ArrayList<Integer> r3 = new ArrayList<Integer>();
                r3.add(5);
                r3.add(6);
                routes.add(r3);
            }
            public void dijkstra(String priority, int s) {}
            public void dfs(int s, int d, ArrayList<Integer> temp) {}
            public int getDistance(int d) {return 1;}
            public int getRoutesNumber() {return 1;}
            public ArrayList<Integer> getRoute(int i) {
                count++;
                return routes.get(count);
            }
            public void clearRoutes() {}
        }
        class stubBookmark implements BookmarkManager {
            String route = null;
            Member member = null;
            public void addBookmark(String route, Member member) {
                this.route = route;
                this.member = member;
            }
        }

        String input = "0\n6\nY\n2 5\nN\n0\nY\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        stubBookmark mark = new stubBookmark();
        CyclingMode mode = new CyclingMode(new stubGraph(), new stubVertices(), new Scanner(System.in), mark, ClimbingTrailRepository.getInstance());

        Member m = new Member();
        mode.memberExecute(m);

        assertEquals(true, (mark.route.equals("Cycling Mode: a")) && (mark.member == m));
    }

    @Test // use trail departure as cycling destination
    public void modeSwitch_case1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {  
		class stubVertices implements VerticesManager {
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getVertexNameByID(int id) {return "a";}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
			public void listAttractions() {}
			//useless below
			public String getRouteString(ArrayList<Integer> route) {return null;}
		}
		class stubGraph implements GraphUtility {
			private ArrayList<ArrayList<Integer>> routes = new ArrayList<ArrayList<Integer>>();
			private int count = -1;
			
			stubGraph() {
				ArrayList<Integer> r1 = new ArrayList<Integer>();
				r1.add(0);r1.add(2);
				routes.add(r1);
				ArrayList<Integer> r2 = new ArrayList<Integer>();
				r2.add(2);r2.add(5);
				routes.add(r2);
				ArrayList<Integer> r3 = new ArrayList<Integer>();
				r3.add(5);r3.add(6);
				routes.add(r3);
			}
			public void dijkstra(String priority, int s) {}
			public void dfs(int s, int d, ArrayList<Integer> temp) {}
			public int getDistance(int d) {return 1;}
			public int getRoutesNumber() {return 1;}
			public ArrayList<Integer> getRoute(int i) {
				count++;
				return routes.get(count);
			}
			public void clearRoutes() {}
		}
        class stubClimgbingTrailRepo implements ClimbingTrailsQuerier {
            public int getTrailDepartureID(int id) {return 7;}
            public String getTrailDestinationName(int id) {return "b";}
        }

        String input = "0\n1\nY\n2 5\nN\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        CyclingMode mode = new CyclingMode(new stubGraph(), new stubVertices(), new Scanner(System.in),
                Bookmark.getInstance(), new stubClimgbingTrailRepo());

        mode.modeSwitch(1, null);

        String expected = "Climbing Route: a -> b"; 
        assertEquals(true, output.toString().contains(expected));
    }

    @Test // not use trail departure as cycling destination
    public void modeSwitch_case2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {  
		class stubVertices implements VerticesManager {
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getVertexNameByID(int id) {return "a";}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public void listAttractions() {}
			public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
			//useless below
			public String getRouteString(ArrayList<Integer> route) {return null;}
		}
		class stubGraph implements GraphUtility {
			private ArrayList<ArrayList<Integer>> routes = new ArrayList<ArrayList<Integer>>();
			private int count = -1;
			
			stubGraph() {
				ArrayList<Integer> r1 = new ArrayList<Integer>();
				r1.add(0);r1.add(2);
				routes.add(r1);
				ArrayList<Integer> r2 = new ArrayList<Integer>();
				r2.add(2);r2.add(5);
				routes.add(r2);
				ArrayList<Integer> r3 = new ArrayList<Integer>();
				r3.add(5);r3.add(6);
				routes.add(r3);
			}
			public void dijkstra(String priority, int s) {}
			public void dfs(int s, int d, ArrayList<Integer> temp) {}
			public int getDistance(int d) {return 1;}
			public int getRoutesNumber() {return 1;}
			public ArrayList<Integer> getRoute(int i) {
				count++;
				return routes.get(count);
			}
			public void clearRoutes() {}
		}
        class stubClimgbingTrailRepo implements ClimbingTrailsQuerier {
            public int getTrailDepartureID(int id) {return 7;}
            //useless below
            public String getTrailDestinationName(int id) {return "b";}
        }

        String input = "0\n2\n6\nY\n2 5\nN\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        CyclingMode mode = new CyclingMode(new stubGraph(), new stubVertices(), new Scanner(System.in),
                Bookmark.getInstance(), new stubClimgbingTrailRepo());

        mode.modeSwitch(1, null);

        String expected = "Climbing Route: "; 
        assertEquals(false, output.toString().contains(expected));
    }
    
    @Test // member, use trail departure as cycling destination
    public void modeSwitch_case3() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {  
		class stubVertices implements VerticesManager {
			public void listAllVertices() {}
			public int checkVertexIdValidity(int id) throws ExInvalidIndex {return id;}
			public String getVertexNameByID(int id) {return "a";}
			public String getVertexNamesByID(ArrayList<Integer> ids) {return null;}
			public int checkAttractionIdValidity(int id) throws ExInvalidIndex {return id;}
			public void listAttractions() {}
			//useless below
			public String getRouteString(ArrayList<Integer> route) {return null;}
		}
		class stubGraph implements GraphUtility {
			private ArrayList<ArrayList<Integer>> routes = new ArrayList<ArrayList<Integer>>();
			private int count = -1;
			
			stubGraph() {
				ArrayList<Integer> r1 = new ArrayList<Integer>();
				r1.add(0);r1.add(2);
				routes.add(r1);
				ArrayList<Integer> r2 = new ArrayList<Integer>();
				r2.add(2);r2.add(5);
				routes.add(r2);
				ArrayList<Integer> r3 = new ArrayList<Integer>();
				r3.add(5);r3.add(6);
				routes.add(r3);
			}
			public void dijkstra(String priority, int s) {}
			public void dfs(int s, int d, ArrayList<Integer> temp) {}
			public int getDistance(int d) {return 1;}
			public int getRoutesNumber() {return 1;}
			public ArrayList<Integer> getRoute(int i) {
				count++;
				return routes.get(count);
			}
			public void clearRoutes() {}
		}
        class stubClimgbingTrailRepo implements ClimbingTrailsQuerier {
            public int getTrailDepartureID(int id) {return 7;}
            public String getTrailDestinationName(int id) {return "b";}
        }

        String input = "0\n1\nY\n2 5\nN\n0\nN\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        CyclingMode mode = new CyclingMode(new stubGraph(), new stubVertices(), new Scanner(System.in),
                Bookmark.getInstance(), new stubClimgbingTrailRepo());
        
        Member m = new Member();
        mode.modeSwitch(1, m);

        String expected = "Climbing Route: a -> b"; 
        assertEquals(true, output.toString().contains(expected));
    }
}