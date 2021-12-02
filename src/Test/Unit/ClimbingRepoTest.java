package Test.Unit;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import SmartNavigationSystem.*;

public class ClimbingRepoTest {

    @Test
    public void testTreeSet() {
        ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
        ctr.clearClimbingTrails();

        ClimbingTrail ct1 = new ClimbingTrail(1, 2, 1, "Kowloon Tong", "Tai Po");

        ClimbingTrail ct2 = new ClimbingTrail(2, 1, 2, "Shatin", "Tai Po");

        ClimbingTrail ct3 = new ClimbingTrail(3, 5, 3, "Sheung Shui", "Tuen Mun");

        ctr.addClimbingTrail(ct1);
        ctr.addClimbingTrail(ct2);
        ctr.addClimbingTrail(ct3);

        String resultString = ctr.list();
        String expectedString = String.format("%-10s%-12s%-14s%s", "id", "difficulty", "departureName", "destinationName") + "\n" + String.format("%-10s%-12s%-14s%s", "[" + 2 + "]", 1, "Shatin", "Tai Po") + "\n" + String.format("%-10s%-12s%-14s%s", "[" + 1 + "]", 2, "Kowloon Tong", "Tai Po") + "\n" + String.format("%-10s%-12s%-14s%s", "[" + 3 + "]", 5, "Sheung Shui", "Tuen Mun") +"\n";
        assertEquals(expectedString, resultString);
    }

    @Test
    public void testFilterDifficulty() {
        ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
        ctr.clearClimbingTrails();

        ClimbingTrail ct1 = new ClimbingTrail(1, 8, 1, "Shatin", "Kowloon Tong");

        ClimbingTrail ct2 = new ClimbingTrail(2, 10, 2, "Sheung Shui", "Tai Po");

        ClimbingTrail ct3 = new ClimbingTrail(3, 8, 3, "Olympics", "Tai Po");
        ctr.addClimbingTrail(ct1);
        ctr.addClimbingTrail(ct2);
        ctr.addClimbingTrail(ct3);
        String resultString = ctr.filterByDifficulty(8);
        String expectedString = String.format("%-10s%-12s%-14s%s", "id", "difficulty", "departureName", "destinationName") + "\n"+String.format("%-10s%-12s%-14s%s", "[" + 1 + "]", 8, "Shatin", "Kowloon Tong") + "\n" + String.format("%-10s%-12s%-14s%s", "[" + 3 + "]", 8, "Olympics", "Tai Po")+"\n";
        assertEquals(expectedString, resultString);

    }
    @Test
    public void testFilterDifficulty_NotFound() {
        ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
        ctr.clearClimbingTrails();

        ClimbingTrail ct1 = new ClimbingTrail(1, 8, 1, "Shatin", "Kowloon Tong");

        ClimbingTrail ct2 = new ClimbingTrail(2, 10, 2, "Sheung Shui", "Tai Po");

        ClimbingTrail ct3 = new ClimbingTrail(3, 8, 3, "Olympics", "Tai Po");
        ctr.addClimbingTrail(ct1);
        ctr.addClimbingTrail(ct2);
        ctr.addClimbingTrail(ct3);
        String resultString = ctr.filterByDifficulty(12);
        String expectedString = "";
        assertEquals(expectedString, resultString);

    }

    @Test
    public void testFilterTrailByDest_NotFoundCase() {
        ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
        ctr.clearClimbingTrails();

        ClimbingTrail ct1 = new ClimbingTrail(1, 8, 1, "Shatin", "Kowloon Tong");

        ClimbingTrail ct2 = new ClimbingTrail(2, 10, 2, "Sheung Shui", "Tai Po");

        ClimbingTrail ct3 = new ClimbingTrail(3, 8, 3, "Olympics", "Tai Po");
        ctr.addClimbingTrail(ct1);
        ctr.addClimbingTrail(ct2);
        ctr.addClimbingTrail(ct3);
        String resultString = ctr.filterTrailByDest("Shatin");
        String expectedString ="";
        assertEquals(expectedString, resultString);
    }
    @Test
    public void testFilterTrailByDest_FoundCase() {
        ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
        ctr.clearClimbingTrails();

        ClimbingTrail ct1 = new ClimbingTrail(1, 8, 1, "Shatin", "Kowloon Tong");

        ClimbingTrail ct2 = new ClimbingTrail(2, 10, 2, "Sheung Shui", "Tai Po");

        ClimbingTrail ct3 = new ClimbingTrail(3, 8, 3, "Olympics", "Tai Po");
        ctr.addClimbingTrail(ct1);
        ctr.addClimbingTrail(ct2);
        ctr.addClimbingTrail(ct3);
        String resultString = ctr.filterTrailByDest("Kowloon Tong");
        String expectedString =String.format("%-10s%-12s%-14s%s", "id", "difficulty", "departureName", "destinationName") + "\n"+String.format("%-10s%-12s%-14s%s", "[" + 1 + "]", 8, "Shatin", "Kowloon Tong")+"\n";
        assertEquals(expectedString, resultString);
    }
    @Test
    public void testFilterTrailByID_FoundCase() {
        ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
        ctr.clearClimbingTrails();

        ClimbingTrail ct1 = new ClimbingTrail(1, 8, 1, "Shatin", "Kowloon Tong");

        ClimbingTrail ct2 = new ClimbingTrail(2, 10, 2, "Sheung Shui", "Tai Po");

        ClimbingTrail ct3 = new ClimbingTrail(3, 8, 3, "Olympics", "Tai Po");
        ctr.addClimbingTrail(ct1);
        ctr.addClimbingTrail(ct2);
        ctr.addClimbingTrail(ct3);
        String resultString = ctr.findTrailByID(2);
        String expectedString = String.format("%s (difficulty %s) %s -> %s", "["+2+"]", 10, "Sheung Shui", "Tai Po");
        assertEquals(expectedString, resultString);
    }

    @Test
    public void testFilterTrailByID_NotFoundCase() {
        ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
        ctr.clearClimbingTrails();

        ClimbingTrail ct1 = new ClimbingTrail(1, 8, 1, "Shatin", "Kowloon Tong");

        ClimbingTrail ct2 = new ClimbingTrail(2, 10, 2, "Sheung Shui", "Tai Po");

        ClimbingTrail ct3 = new ClimbingTrail(3, 8, 3, "Olympics", "Tai Po");
        ctr.addClimbingTrail(ct1);
        ctr.addClimbingTrail(ct2);
        ctr.addClimbingTrail(ct3);
        String resultString = ctr.findTrailByID(5);
        String expectedString = null;
        assertEquals(expectedString, resultString);
    }

    @Test
    public void testFilterTrailByDeparture_FoundCase() {
        ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
        ctr.clearClimbingTrails();

        ClimbingTrail ct1 = new ClimbingTrail(1, 8, 1, "Shatin", "Kowloon Tong");

        ClimbingTrail ct2 = new ClimbingTrail(2, 10, 2, "Sheung Shui", "Tai Po");

        ClimbingTrail ct3 = new ClimbingTrail(3, 8, 3, "Olympics", "Tai Po");
        ctr.addClimbingTrail(ct1);
        ctr.addClimbingTrail(ct2);
        ctr.addClimbingTrail(ct3);
        String resultString = ctr.filterTrailByDeparture("Shatin");
        String expectedString = String.format("%-10s%-12s%-14s%s", "id", "difficulty", "departureName", "destinationName") + "\n"+String.format("%-10s%-12s%-14s%s", "[" + 1 + "]", 8, "Shatin", "Kowloon Tong")+"\n";
        assertEquals(expectedString, resultString);
    }

    @Test
    public void testFilterTrailByDeparture_NotFoundCase() {
        ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
        ctr.clearClimbingTrails();

        ClimbingTrail ct1 = new ClimbingTrail(1, 8, 1, "Shatin", "Kowloon Tong");

        ClimbingTrail ct2 = new ClimbingTrail(2, 10, 2, "Sheung Shui", "Tai Po");

        ClimbingTrail ct3 = new ClimbingTrail(3, 8, 3, "Olympics", "Tai Po");
        ctr.addClimbingTrail(ct1);
        ctr.addClimbingTrail(ct2);
        ctr.addClimbingTrail(ct3);
        String resultString = ctr.filterTrailByDeparture("Tuen Mun");
        String expectedString =  "";
        assertEquals(expectedString, resultString);
    }
    @Test
    public void testSortFunction_ValidInput() {
        ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
        ctr.clearClimbingTrails();

        ClimbingTrail ct1 = new ClimbingTrail(1, 8, 1, "Shatin", "Kowloon Tong");

        ClimbingTrail ct2 = new ClimbingTrail(2, 10, 2, "Sheung Shui", "Tai Po");

        ClimbingTrail ct3 = new ClimbingTrail(3, 8, 3, "Olympics", "Tai Po");
        ctr.addClimbingTrail(ct1);
        ctr.addClimbingTrail(ct2);
        ctr.addClimbingTrail(ct3);

        String resultString1 = ctr.sort(0);
        String expectedString1 = String.format("%-10s%-12s%-14s%s", "id", "difficulty", "departureName", "destinationName") + "\n"+String.format("%-10s%-12s%-14s%s", "[" + 1 + "]", 8, "Shatin", "Kowloon Tong")+"\n"+String.format("%-10s%-12s%-14s%s", "[" + 3 + "]", 8, "Olympics", "Tai Po")+"\n"+String.format("%-10s%-12s%-14s%s", "[" + 2 + "]", 10, "Sheung Shui", "Tai Po")+"\n";
        assertEquals(expectedString1, resultString1);

        String resultString2 = ctr.sort(1);
        String expectedString2 = String.format("%-10s%-12s%-14s%s", "id", "difficulty", "departureName", "destinationName") + "\n"+String.format("%-10s%-12s%-14s%s", "[" + 2 + "]", 10, "Sheung Shui", "Tai Po")+"\n"+String.format("%-10s%-12s%-14s%s", "[" + 3 + "]", 8, "Olympics", "Tai Po")+"\n"+String.format("%-10s%-12s%-14s%s", "[" + 1 + "]", 8, "Shatin", "Kowloon Tong")+"\n";
        assertEquals(expectedString2, resultString2);
    }

    @Test
    public void testSortFunction_InvalidInput() {
        ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
        ctr.clearClimbingTrails();

        ClimbingTrail ct1 = new ClimbingTrail(1, 8, 1, "Shatin", "Kowloon Tong");

        ClimbingTrail ct2 = new ClimbingTrail(2, 10, 2, "Sheung Shui", "Tai Po");

        ClimbingTrail ct3 = new ClimbingTrail(3, 8, 3, "Olympics", "Tai Po");
        ctr.addClimbingTrail(ct1);
        ctr.addClimbingTrail(ct2);
        ctr.addClimbingTrail(ct3);
        String resultString = ctr.sort(3);
        String expectedString = null;
        assertEquals(expectedString, resultString);
    }

    @Test
    public void testSortFunction_NoContent() {
        ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
        ctr.clearClimbingTrails();
        String resultString = ctr.sort(1);
        String expectedString = "";
        assertEquals(expectedString, resultString);
    }
    @Test
    public void testDeleteFunction() {
        ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
        ctr.clearClimbingTrails();

        ClimbingTrail ct1 = new ClimbingTrail(1, 8, 1, "Shatin", "Kowloon Tong");

        ClimbingTrail ct2 = new ClimbingTrail(2, 10, 2, "Sheung Shui", "Tai Po");

        ClimbingTrail ct3 = new ClimbingTrail(3, 8, 3, "Olympics", "Tai Po");
        ctr.addClimbingTrail(ct1);
        ctr.addClimbingTrail(ct2);
        ctr.addClimbingTrail(ct3);
        ctr.deleteClimbingTrail(ct3);
        String resultString = ctr.list();
        String expectedString = String.format("%-10s%-12s%-14s%s", "id", "difficulty", "departureName", "destinationName") + "\n"+String.format("%-10s%-12s%-14s%s", "[" + 1 + "]", 8, "Shatin", "Kowloon Tong")+"\n"+String.format("%-10s%-12s%-14s%s", "[" + 2 + "]", 10, "Sheung Shui", "Tai Po")+"\n";
        assertEquals(expectedString, resultString);
    }
    
    @Test 
    public void testListDifficulties() {
    	 ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
         ctr.clearClimbingTrails();

         ClimbingTrail ct1 = new ClimbingTrail(1, 8, 1, "Shatin", "Kowloon Tong");

         ClimbingTrail ct2 = new ClimbingTrail(2, 10, 2, "Sheung Shui", "Tai Po");

         ClimbingTrail ct3 = new ClimbingTrail(3, 8, 3, "Olympics", "Tai Po");
         ctr.addClimbingTrail(ct1);
         ctr.addClimbingTrail(ct2);
         ctr.addClimbingTrail(ct3);
         String resultString = ctr.listDifficulties();
         String expectedString = " 8, 10";
         assertEquals(expectedString,resultString);
    	
    }
    @Test 
    public void testListDepartures() {
    	 ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
         ctr.clearClimbingTrails();

         ClimbingTrail ct1 = new ClimbingTrail(1, 8, 1, "Shatin", "Kowloon Tong");

         ClimbingTrail ct2 = new ClimbingTrail(2, 10, 2, "Sheung Shui", "Tai Po");

         ClimbingTrail ct3 = new ClimbingTrail(3, 8, 3, "Olympics", "Tai Po");
         ctr.addClimbingTrail(ct1);
         ctr.addClimbingTrail(ct2);
         ctr.addClimbingTrail(ct3);
         String resultString = ctr.listDepartures();
         String expectedString = " Shatin, Olympics, Sheung Shui";
         assertEquals(expectedString,resultString);
    	
    }
    @Test 
    public void testListDestinations() {
    	 ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
         ctr.clearClimbingTrails();

         ClimbingTrail ct1 = new ClimbingTrail(1, 8, 1, "Shatin", "Kowloon Tong");

         ClimbingTrail ct2 = new ClimbingTrail(2, 10, 2, "Sheung Shui", "Tai Po");

         ClimbingTrail ct3 = new ClimbingTrail(3, 8, 3, "Olympics", "Tai Po");
         ctr.addClimbingTrail(ct1);
         ctr.addClimbingTrail(ct2);
         ctr.addClimbingTrail(ct3);
         String resultString = ctr.listDestinations();
         String expectedString = " Kowloon Tong, Tai Po";
         assertEquals(expectedString,resultString);
    	
    }
    
    @Test
    public void testgetTrailDepartureID() {
    	 ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
         ctr.clearClimbingTrails();

         ClimbingTrail ct1 = new ClimbingTrail(1, 8, 1, "Shatin", "Kowloon Tong");

         ClimbingTrail ct2 = new ClimbingTrail(2, 10, 2, "Sheung Shui", "Tai Po");

         ClimbingTrail ct3 = new ClimbingTrail(3, 8, 3, "Olympics", "Tai Po");
         ctr.addClimbingTrail(ct1);
         ctr.addClimbingTrail(ct2);
         ctr.addClimbingTrail(ct3);
         int resultID = ctr.getTrailDepartureID(2);
         int expectedID=2;
         assertEquals(expectedID,resultID);
    	
    }
    @Test
    public void testgetTrailDepartureID_NotFound() {
    	 ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
         ctr.clearClimbingTrails();

         ClimbingTrail ct1 = new ClimbingTrail(1, 8, 1, "Shatin", "Kowloon Tong");

         ClimbingTrail ct2 = new ClimbingTrail(2, 10, 2, "Sheung Shui", "Tai Po");

         ClimbingTrail ct3 = new ClimbingTrail(3, 8, 3, "Olympics", "Tai Po");
         ctr.addClimbingTrail(ct1);
         ctr.addClimbingTrail(ct2);
         ctr.addClimbingTrail(ct3);
         int resultID = ctr.getTrailDepartureID(4);
         int expectedID=-1;
         assertEquals(expectedID,resultID);
    	
    }
    
    @Test
    public void testgetTrailDestinationName() {
    	 ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
         ctr.clearClimbingTrails();

         ClimbingTrail ct1 = new ClimbingTrail(1, 8, 1, "Shatin", "Kowloon Tong");

         ClimbingTrail ct2 = new ClimbingTrail(2, 10, 2, "Sheung Shui", "Tai Po");

         ClimbingTrail ct3 = new ClimbingTrail(3, 8, 3, "Olympics", "Tai Po");
         ctr.addClimbingTrail(ct1);
         ctr.addClimbingTrail(ct2);
         ctr.addClimbingTrail(ct3);
         String resultDestName = ctr.getTrailDestinationName(3);
         String expectedDestName = "Tai Po";
         assertEquals(expectedDestName,resultDestName);
    	
    }
    @Test
    public void testgetTrailDestinationName_NotFound() {
    	 ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
         ctr.clearClimbingTrails();

         ClimbingTrail ct1 = new ClimbingTrail(1, 8, 1, "Shatin", "Kowloon Tong");

         ClimbingTrail ct2 = new ClimbingTrail(2, 10, 2, "Sheung Shui", "Tai Po");

         ClimbingTrail ct3 = new ClimbingTrail(3, 8, 3, "Olympics", "Tai Po");
         ctr.addClimbingTrail(ct1);
         ctr.addClimbingTrail(ct2);
         ctr.addClimbingTrail(ct3);
         String resultDestName = ctr.getTrailDestinationName(5);
         String expectedDestName = null;
         assertEquals(expectedDestName,resultDestName);
    	
    }
    
    @Test
    public void testgetFilteredClimbingTrails() {
    	 ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
         ctr.clearClimbingTrails();

         ClimbingTrail ct1 = new ClimbingTrail(1, 8, 1, "Shatin", "Kowloon Tong");

         ClimbingTrail ct2 = new ClimbingTrail(2, 10, 2, "Sheung Shui", "Tai Po");

         ClimbingTrail ct3 = new ClimbingTrail(3, 8, 3, "Olympics", "Tai Po");
         ctr.addClimbingTrail(ct1);
         ctr.addClimbingTrail(ct2);
         ctr.addClimbingTrail(ct3);
         ctr.filterByDifficulty(10);
         ArrayList<ClimbingTrail> cts= ctr.getFilteredClimbingTrails();
         assertEquals(2,cts.get(0).getID());
    }
}
