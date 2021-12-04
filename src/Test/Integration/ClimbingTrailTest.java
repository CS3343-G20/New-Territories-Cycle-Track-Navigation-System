package test;

import SmartNavigationSystem.ClimbingTrail;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClimbingTrailTest {
    ClimbingTrail ct = new ClimbingTrail(1,1,1,"Kowloon Tong","Shatin");
    @Test
    public void testgetID(){
    int ID = ct.getID();
    int expected = 1;
    assertEquals(expected,ID);
    }
    @Test
    public void testgetDifficulty(){
        int difficulty = ct.getDifficulty();
        int expected = 1;
        assertEquals(expected,difficulty);
    }
    @Test
    public void testgetDestinationName(){
        String destName = ct.getDestinationName();
        String expected = "Shatin";
        assertEquals(expected,destName);
    }
    @Test
    public void testgetDepartureName(){
        String departureName = ct.getDepartureName();
        String expected = "Kowloon Tong";
        assertEquals(expected,departureName);
    }
    @Test
    public void testgetDepartureID(){
        int departureID = ct.getDepartureID();
        int expected = 1;
        assertEquals(expected,departureID);
    }
    @Test
    public void testcompareTo(){
        ClimbingTrail ct2 = new ClimbingTrail(1,2,1,"Sheung Shui","Fan Ling");
        int result = ct.compareTo(ct2);
        int expected = -1;
        assertEquals(expected,result);
    }
    @Test
    public void testdisplayInformation(){
        String info = ct.displayInformation();
        String expected = String.format("%-10s%-12s%-14s%s", "["+1+"]", 1, "Kowloon Tong", "Shatin");
        assertEquals(expected,info);
    }
    @Test
    public void testfullInformation(){
        String info = ct.fullInformation();
        String expected =  String.format("%s (difficulty %s) %s -> %s", "["+1+"]", 1, "Kowloon Tong", "Shatin");
        assertEquals(expected,info);
    }

}
