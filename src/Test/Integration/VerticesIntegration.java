package Test.Integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import SmartNavigationSystem.*;

public class VerticesIntegration {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputContent = new ByteArrayOutputStream();

    @Before
    public void setupStreams() {
        System.setOut(new PrintStream(outputContent));
    }

    @Test
    public void getVertexNameByID_TestCase1() throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        Vertices vertices = Vertices.getInstance();
        int id = 1;
        Method method = Vertices.class.getMethod("getVertexNameByID", int.class);
        String result = (String) method.invoke(vertices, id);
        assertEquals("Kwok Tak Seng Catholic Secondary School", result);
    }

    @Test
    public void getVertexNamesByID_TestCase1() throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        Vertices vertices = Vertices.getInstance();
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        String result = vertices.getVertexNamesByID(ids);
        assertEquals("Kwok Tak Seng Catholic Secondary School, Hong Kong Heritage Museum, Lei Uk Tsuen", result);
	}
	
    
    @Test 
    public void listAllVertices_TestCase1() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {  
    	Vertices vertices = Vertices.getInstance();
    	vertices.listAllVertices();	
    	assertEquals(true, outputContent.toString().contains("[0] Sha Tin Che Kung Temple"));
    }
    
    
    @Test 
    public void listAllVertices_TestCase2() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {  
    	Vertices vertices = Vertices.getInstance();
    	vertices.listAllVertices();	
    	assertEquals(true, outputContent.toString().contains("[1] Kwok Tak Seng Catholic Secondary School"));
    }
    
    @Test 
    public void listAllVertices_TestCase3() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {  
    	Vertices vertices = Vertices.getInstance();
    	vertices.listAllVertices();	
    	assertEquals(true, outputContent.toString().contains("[2] Hong Kong Heritage Museum"));
    }
    
    @Test 
    public void listAllVertices_TestCase4() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {  
    	Vertices vertices = Vertices.getInstance();
    	vertices.listAllVertices();	
    	assertEquals(true, outputContent.toString().contains("[3] Lei Uk Tsuen"));
    }
    
    @Test 
    public void listAllVertices_TestCase5() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {  
    	Vertices vertices = Vertices.getInstance();
    	vertices.listAllVertices();	
    	assertEquals(true, outputContent.toString().contains("[4] Chui Tin Street Soccer Pitch"));
    }
    
    @Test 
    public void listAllVertices_TestCase6() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {  
    	Vertices vertices = Vertices.getInstance();
    	vertices.listAllVertices();	
    	assertEquals(true, outputContent.toString().contains("[5] Tsang Tai Uk"));
    }
    
    @Test 
    public void listAllVertices_TestCase7() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {  
    	Vertices vertices = Vertices.getInstance();
    	vertices.listAllVertices();	
    	assertEquals(true, outputContent.toString().contains("[6] Badminton court"));
    }
    @Test 
    public void listAllVertices_TestCase8() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {  
    	Vertices vertices = Vertices.getInstance();
    	vertices.listAllVertices();	
    	assertEquals(false, outputContent.toString().contains("[9] xxxx"));
    }
    
    
    @Test 
    public void listAttractions_TestCase1() {  
    	Vertices vertices = Vertices.getInstance();
    	vertices.listAttractions();
    	assertEquals(false, outputContent.toString().contains("[1] Kwok Tak Seng Catholic Secondary School\n"
//											    			"[3] Lei Uk Tsuen" 
//											    			"[4] Chui Tin Street Soccer Pitch" 
//											    			"[6] Badminton court"
    	));
	}
    
    @Test 
    public void listAttractions_TestCase2() {  
    	Vertices vertices = Vertices.getInstance();
    	vertices.listAttractions();
    	assertEquals(false, outputContent.toString().contains("[3] Lei Uk Tsuen"));
	}
    
    @Test 
    public void listAttractions_TestCase3() {  
    	Vertices vertices = Vertices.getInstance();
    	vertices.listAttractions();
    	assertEquals(false, outputContent.toString().contains("[4] Chui Tin Street Soccer Pitch"));
	}
    
    @Test 
    public void listAttractions_TestCase4() {  
    	Vertices vertices = Vertices.getInstance();
    	vertices.listAttractions();
    	assertEquals(false, outputContent.toString().contains("[6] Badminton court"));
	}
    
    
    @Test 
    public void listAttractions_TestCase5() {  
    	Vertices vertices = Vertices.getInstance();
    	vertices.listAttractions();
    	assertEquals(true, outputContent.toString().contains("[5] Tsang Tai Uk"));    	
	}
    
    @Test 
    public void listAttractions_TestCase6() {  
    	Vertices vertices = Vertices.getInstance();
    	vertices.listAttractions();
    	assertEquals(true, outputContent.toString().contains("[0] Sha Tin Che Kung Temple"));    	
	}
    
    @Test 
    public void listAttractions_TestCase7() {  
    	Vertices vertices = Vertices.getInstance();
    	vertices.listAttractions();
    	assertEquals(true, outputContent.toString().contains("[2] Hong Kong Heritage Museum"));    	
	}
 
    
    @Test 
    public void listAttractions_TestCase8() {  
    	Vertices vertices = Vertices.getInstance();
    	vertices.listAttractions();
    	assertEquals(false, outputContent.toString().contains("xxxxx"));
	}
    
    @Test 
    public void getRouteString_TestCase1() {  
    	Vertices vertices = Vertices.getInstance();
    	ArrayList<Integer> route= new ArrayList<>();
    	route.add(5);
    	route.add(2);
    	route.add(0);
    	String res = vertices.getRouteString(route);
    	assertEquals("Tsang Tai Uk -> Hong Kong Heritage Museum -> Sha Tin Che Kung Temple", res);
	}

    
    @Test //vertex id is valid
    public void checkVertexIdValidity_TestCase1() throws ExInvalidIndex {  
    	Vertices vertices = Vertices.getInstance();
    	int id = 4;
    	int result = vertices.checkVertexIdValidity(id);
        assertEquals(4,result);
	}
    
  
    @Test//vertex id is invalid (not contain id)
    public void checkVertexIdValidity_TestCase2() throws ExInvalidIndex {  
    	Vertices vertices = Vertices.getInstance();
    	ExInvalidIndex thrown = assertThrows(
				ExInvalidIndex.class,
    			()->vertices.checkVertexIdValidity(20)
    	);
    	
    	//assertion on the thrown exception
    	assertEquals("The input should be chosen from the listed indices. Please try again:",thrown.getMessage());
	}
    
    @Test //attraction id is valid
    public void checkAttractionIdValidity_TestCase1() throws ExInvalidIndex {  
    	Vertices vertices = Vertices.getInstance();
    	int id = 0;
    	int result = vertices.checkAttractionIdValidity(id);
        assertEquals(0, result);
    }

    @Test // attraction id is not valid (contain id, but not a attraction)
    public void checkAttractionIdValidity_TestCase2() throws ExInvalidIndex {
        Vertices vertices = Vertices.getInstance();
        ExInvalidIndex thrown = assertThrows(ExInvalidIndex.class, () -> vertices.checkAttractionIdValidity(3));

        // assertion on the thrown exception
        assertEquals("The input should be chosen from the listed indices. Please try again:", thrown.getMessage());
    }

    @Test // attraction id is not valid (not contain id)
    public void checkAttractionIdValidity_TestCase3() throws ExInvalidIndex {
        Vertices vertices = Vertices.getInstance();
        ExInvalidIndex thrown = assertThrows(ExInvalidIndex.class, () -> vertices.checkAttractionIdValidity(20));

        // assertion on the thrown exception
        assertEquals("The input should be chosen from the listed indices. Please try again:", thrown.getMessage());
    }

    @After
    public void cleanUpStreams() {
        System.setOut(standardOut);
    }
}