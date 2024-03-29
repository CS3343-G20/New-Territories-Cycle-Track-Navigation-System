package Test.Integration;

import SmartNavigationSystem.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;


public class UserIntegrationTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
 	private final PrintStream originalOut = System.out;
 	private final InputStream originalIn = System.in;

 	@Before
 	public void setUpStreams() throws FileNotFoundException {
 	    System.setOut(new PrintStream(outContent));
	}  
 
    @Test
    public void testChooseMode_case1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        User user=new User();
        String input = "1\n6\nY\n2\nN\n3\nY\n2 5\n0\n1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        user.chooseMode(new Scanner(System.in));
        assertEquals(true, outContent.toString().contains("Please input a departure ID:"));
    }

//    @Test
//    public void testChooseMode_case2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
//        User user=new User();
//        String input = "2\n1\n1\n1\nN";
//        System.setIn(new ByteArrayInputStream(input.getBytes()));
//        user.chooseMode(new Scanner(System.in));
//        assertEquals(true, outContent.toString().contains("[1] (difficulty 1) Sheung Shui -> Tai Po"));
//    }
    
    @Test
    public void testChooseMode_case3() {
        String input = "a\n1\n6\nY\n2\nN\n3\nY\n2 5\n0\n1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        User user = new User();
        user.chooseMode(new Scanner(System.in));
        assertEquals(true, outContent.toString().contains("Please input a departure ID:"));
    }
    
    @Test
    public void testChooseMode_case4() {
        String input = "3\n1\n6\nY\n2\nN\n3\nY\n2 5\n0\n1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        User user = new User();
        user.chooseMode(new Scanner(System.in));
        assertEquals(true, outContent.toString().contains("Please input a departure ID:"));
    }
    
 	@After
 	public void restoreStreams() throws FileNotFoundException {
 	    System.setOut(originalOut);
 	    System.setIn(originalIn);
 	}

}