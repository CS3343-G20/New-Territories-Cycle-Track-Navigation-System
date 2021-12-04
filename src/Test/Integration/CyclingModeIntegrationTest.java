package Test.Integration;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;

import SmartNavigationSystem.*;

public class CyclingModeIntegrationTest {

    @Test // correct input
    public void setDeparture_case1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String input = "1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
        mode.setDeparture(); 

        Field departure = CyclingMode.class.getDeclaredField("departure");
        departure.setAccessible(true);
        assertEquals(1, departure.get(mode));
    }

    @Test // wrong input (invalid format)
    public void setDeparture_case2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String input = "a\n1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
        mode.setDeparture();

        Field departure = CyclingMode.class.getDeclaredField("departure");
        departure.setAccessible(true);
        assertEquals(1, departure.get(mode));
    }

    @Test // wrong input (invalid ID)
    public void setDeparture_case3() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String input = "10\n1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
        mode.setDeparture();

        Field departure = CyclingMode.class.getDeclaredField("departure");
        departure.setAccessible(true);
        System.out.println("--------------------------"+departure.get(mode));
        assertEquals(1, departure.get(mode));
    }

    @Test // forCLimbing = false, correct input
    public void setDestination_case1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String input = "1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
        mode.setDestination();

        Field destination = CyclingMode.class.getDeclaredField("destination");
        destination.setAccessible(true);
        assertEquals(1, destination.get(mode));
    }

    @Test // forCLimbing = false, wrong input (invalid format)
    public void setDestination_case2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {  
        String input = "a\n1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
        mode.setDestination();

        Field destination = CyclingMode.class.getDeclaredField("destination");
        destination.setAccessible(true);
        assertEquals(1, destination.get(mode));
    }

    @Test // forCLimbing = false, wrong input (invalid ID)
    public void setDestination_case3() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {  
        String input = "10\n1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
        mode.setDestination();

        Field destination = CyclingMode.class.getDeclaredField("destination");
        destination.setAccessible(true);
        assertEquals(1, destination.get(mode));
    }

    @Test // forCLimbing = true, correct input to skip over
    public void setDestination_case4() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String input = "1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
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
        String input = "2\n1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
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
    	String input = "3\n2\n2";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
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
    	String input = "a\n2\n2";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
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
        String input = "Y\n2 5";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
        mode.addAttractions();

        Field attractions = CyclingMode.class.getDeclaredField("attractions");
        attractions.setAccessible(true);
        assertEquals(2, ((ArrayList<Integer>) (attractions.get(mode))).size());
    }

    @Test // correct command to add, wrong input (Invalid format)
    public void addAttractions_case2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String input = "Y\na\n2";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
        mode.addAttractions();

        Field attractions = CyclingMode.class.getDeclaredField("attractions");
        attractions.setAccessible(true);
        assertEquals(1, ((ArrayList<Integer>) (attractions.get(mode))).size());
    }

    @Test // correct command to add, wrong input (Invalid ID)
    public void addAttractions_case3() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        String input = "Y\n1\n2 5";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
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
        String input = "a\nY\n2 5";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
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
        String input = "1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
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
        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
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
        String input = "a\n1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
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
        String input = "5\n1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
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
        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
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
        String input = "0\n6\nY\n2 5\nN\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
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
        String input = "1\n6\nY\n2 5\nN\n1\n0\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
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
        String input = "0\n1\nY\n2 5\nN\n2\n6\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
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
        String input = "0\n6\nY\n2\nN\n3\nY\n2 5\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
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
        String input = "0\n6\nY\n2 5\nN\n4\nY\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
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
        String input = "0\n6\nY\n2 5\nN\na\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
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
        String input = "0\n6\nY\n2 5\nN\n5\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
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

    @Test // use trail departure as cycling destination
    public void modeSwitch_case1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {  

        String input = "0\n1\nY\n2 5\nN\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());

        mode.modeSwitch(1, null);

        String expected = "Climbing Route: Sheung Shui -> Tai Po"; 
        assertEquals(true, output.toString().contains(expected));
    }

    @Test // not use trail departure as cycling destination
    public void modeSwitch_case2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException { 
        String input = "0\n2\n6\nY\n2 5\nN\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());

        mode.modeSwitch(1, null);

        String expected = "Climbing Route: "; 
        assertEquals(false, output.toString().contains(expected));
    }
    
    @Test // member, use trail departure as cycling destination
    public void modeSwitch_case3() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {  
        String input = "0\n1\nY\n2 5\nN\n0\nN\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        CyclingMode mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in),
                Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
        
        Member m = new Member();
        mode.modeSwitch(1, m);

        String expected = "Climbing Route: Sheung Shui -> Tai Po"; 
        assertEquals(true, output.toString().contains(expected));
    }
}