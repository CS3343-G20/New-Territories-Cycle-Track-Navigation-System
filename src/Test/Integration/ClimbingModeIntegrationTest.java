package Test.Integration;

import SmartNavigationSystem.*;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class ClimbingModeIntegrationTest {


    @Test
    public void testfindTrailsByDifficulty_FoundCase() {
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scan = new Scanner(System.in);
        ClimbingMode cm = new ClimbingMode(null, scan, null, null);
        String actualResult = cm.findTrailsByDifficulty();
        assertEquals("id        difficulty  departureName destinationName\n" +
                "[1]       1           Sheung Shui   Tai Po\n", actualResult);

    }

    //
    @Test
    public void testfindTrailsByDifficulty_NotFoundCase() {
        String input = "5\n1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scan = new Scanner(System.in);
        ClimbingMode cm = new ClimbingMode(null, scan, null, null);
        String actualResult = cm.findTrailsByDifficulty();
        assertEquals("id        difficulty  departureName destinationName\n" +
                "[1]       1           Sheung Shui   Tai Po\n", actualResult);
    }

    @Test
    public void testfindTrailsByDifficulty_WrongFormatCase() {
        String input = "test\n1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Scanner scan = new Scanner(System.in);
        ClimbingMode cm = new ClimbingMode(null, scan, null, null);
        String actualResult = cm.findTrailsByDifficulty();
        assertEquals("id        difficulty  departureName destinationName\n" +
                "[1]       1           Sheung Shui   Tai Po\n", actualResult);
    }

    @Test
    public void testfindTrailsByDifficulty_InvalidInputCase() {
        String input = "c\n1\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scan = new Scanner(System.in);
        ClimbingMode cm = new ClimbingMode(null, scan, null, null);
        String result = cm.findTrailsByDifficulty();
        assertEquals("id        difficulty  departureName destinationName\n" +
                "[1]       1           Sheung Shui   Tai Po\n", result);
    }

    @Test
    public void testfindTrailsByDeparture_FoundCase() {
        String input = "Sheung Shui\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scan = new Scanner(System.in);
        ClimbingMode cm = new ClimbingMode(null, scan, null, null);
        String actualResult = cm.findTrailsByDeparture();
        assertEquals("id        difficulty  departureName destinationName\n" +
                "[1]       1           Sheung Shui   Tai Po\n", actualResult);
    }

    //
//
    @Test
    public void testaddCycling_PositiveCase() {
        String input = "1\n1\n1\n1\nY\n1\n1\nN\nN\n0\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scan = new Scanner(System.in);
        ClimbingMode clm = new ClimbingMode(null, scan, null, null);
        clm.run();
        clm.addCycling(1);
        assertEquals(true, outputStreamCaptor.toString().contains("Climbing Route: Sheung Shui -> Tai Po"));
}





    @Test
    public void testaddCycling_NegativeCase() {
        CyclingMode cm = new CyclingMode(null, null, null, null, null);
        String input = "N\nY\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scan = new Scanner(System.in);
        ClimbingMode clm = new ClimbingMode(null, scan, null, cm);
        clm.addCycling(1);
        assertEquals("Do you want to cycle to the point? [Y/N]", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testaddCycling_InvalidCase() {
        String input = "U\nN\nY\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scan = new Scanner(System.in);
        CyclingMode cm = new CyclingMode(null, null, null, null, null);
        ClimbingMode clm = new ClimbingMode(null, scan, null, null);
        clm.addCycling(1);
        assertEquals("Do you want to cycle to the point? [Y/N]\r\n"+
                "The input should be chosen from the listed commands above. Please try again:", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testfindTrailsByDeparture_NotFoundCase() {
        String input = "Shatin\nSheung Shui\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scan = new Scanner(System.in);
        ClimbingMode cm = new ClimbingMode(null, scan, null, null);
        String actualResult = cm.findTrailsByDeparture();
        assertEquals("id        difficulty  departureName destinationName\n" +
                "[2]       3           Shatin        Sheung Shui\n", actualResult);
    }

    //
    @Test
    public void testfindTrailsByDestination_FoundCase() {
        String input = "Tuen Muen\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scan = new Scanner(System.in);
        ClimbingMode cm = new ClimbingMode(null, scan, null, null);
        String actualResult = cm.findTrailsByDestination();
        assertEquals("id        difficulty  departureName destinationName\n" +
                "[3]       3           Yuen Long     Tuen Muen\n", actualResult);
    }

    //
    @Test
    public void testfindTrailsByDestination_NotFoundCase() {
        String input = "Tsuen Wan\nTuen Muen\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scan = new Scanner(System.in);
        ClimbingMode cm = new ClimbingMode(null, scan, null, null);
        String actualResult = cm.findTrailsByDestination();
        assertEquals("id        difficulty  departureName destinationName\n" +
                "[3]       3           Yuen Long     Tuen Muen\n", actualResult);
    }

    @Test
    public void testChooseSelectionCriteria_Case1() {
        String input = "1\n1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scan = new Scanner(System.in);
        ClimbingMode cm = new ClimbingMode(null, scan, null, null);
        String actualResult = cm.chooseSelectionCriteria();
        assertEquals("id        difficulty  departureName destinationName\n" +
                "[1]       1           Sheung Shui   Tai Po\n", actualResult);

    }

    @Test
    public void testChooseSelectionCriteria_Case2() {
        String input = "2\nSheung Shui\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scan = new Scanner(System.in);
        ClimbingMode cm = new ClimbingMode(null, scan, null, null);
        String actualResult = cm.chooseSelectionCriteria();
        assertEquals("id        difficulty  departureName destinationName\n" +
                "[1]       1           Sheung Shui   Tai Po\n", actualResult);

    }

    //
    @Test
    public void testChooseSelectionCriteria_Case3() {
        String input = "3\nTuen Muen\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scan = new Scanner(System.in);
        ClimbingMode cm = new ClimbingMode(null, scan, null, null);
        String actualResult = cm.chooseSelectionCriteria();
        assertEquals("id        difficulty  departureName destinationName\n" +
                "[3]       3           Yuen Long     Tuen Muen\n", actualResult);

    }

    @Test
    public void testChooseSelectionCriteria_Exception() {
        String input = "test\n1\n1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scan = new Scanner(System.in);
        ClimbingMode cm = new ClimbingMode(null, scan, null, null);
        String actualResult = cm.chooseSelectionCriteria();
        assertEquals("id        difficulty  departureName destinationName\n" +
                "[1]       1           Sheung Shui   Tai Po\n", actualResult);

    }

    @Test
    public void testchooseClimbingPath() {
        String input = "1\n2\n3\n4\n5\n6\n7\n8\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scan = new Scanner(System.in);
        ClimbingMode cm = new ClimbingMode(null, scan, null, null);
        ClimbingTrailRepoManager ctri = ClimbingTrailRepository.getInstance();
        ctri.filterByDifficulty(1);
        cm.chooseClimbingPath();
        assertEquals("Please enter the id of the climbing path that you would like to choose :\r\n" +
                "This is the climbing trail chosen:\r\n" +
                "[1] (difficulty 1) Sheung Shui -> Tai Po", outputStreamCaptor.toString().trim());

    }

    @Test
    public void testchooseClimbingPath_NotExist() {
        String input = "5\n1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Scanner scan = new Scanner(System.in);
        ClimbingMode cm = new ClimbingMode(null, scan, null, null);
        ClimbingTrailRepoManager ctri = ClimbingTrailRepository.getInstance();
        ctri.filterByDifficulty(1);
        cm.chooseClimbingPath();
        assertEquals("Please enter the id of the climbing path that you would like to choose :\r\n" +
                "The climbing path doesn't exist, please enter a valid climbing path id\r\n" +
                "This is the climbing trail chosen:\r\n" +
                "[1] (difficulty 1) Sheung Shui -> Tai Po", outputStreamCaptor.toString().trim());

    }

    @Test
    public void testchooseClimbingPath_WrongFormat() {
        String input = "test\n2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Scanner scan = new Scanner(System.in);
        ClimbingMode cm = new ClimbingMode(null, scan, null, null);
        ClimbingTrailRepoManager ctri = ClimbingTrailRepository.getInstance();
        ctri.filterByDifficulty(3);
        cm.chooseClimbingPath();
        assertEquals("Please enter the id of the climbing path that you would like to choose :\r\n" +
                "The input should be an integer. Please try again:\r\n" +
                "This is the climbing trail chosen:\r\n" +
                "[2] (difficulty 3) Shatin -> Sheung Shui", outputStreamCaptor.toString().trim());

    }


    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
        System.setIn(System.in);
    }

    @Test
    public void testaddBookmark_PositiveCase() throws IOException {
        String input = "cs3343g20system@gmail.com\npwd\n1\n1\n1\n1\nN\nN\nY\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        System.setOut(new PrintStream(outputStreamCaptor));
        Scanner scan = new Scanner(System.in);
        new JsonOperation();
        Member m = new Member();
        m.Login(scan);
        ClimbingMode cm = new ClimbingMode(null, scan, null, null);
        cm.memberExecute(m);
        assertEquals(true, outputStreamCaptor.toString().trim().contains("[1] (difficulty 1) Sheung Shui -> Tai Po"));
    }

    @Test
    public void testaddBookmark_InvalidCase() {
        String input = "T\nN\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        System.setOut(new PrintStream(outputStreamCaptor));
        Scanner scan = new Scanner(System.in);
        ClimbingMode cm = new ClimbingMode(null, scan, null, null);
        cm.addBookmark();
        assertEquals("Do you want to add the selected route as bookmark? [Y/N]\r\nThe input should be chosen from the listed commands above. Please try again:", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testChooseSelectionCriteria_Case4() {
        String input = "5\n1\n1\n";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        System.setOut(new PrintStream(outputStreamCaptor));
        Scanner scan = new Scanner(System.in);
        ClimbingMode cm = new ClimbingMode(null, scan, null, null);
        String actualResult = cm.chooseSelectionCriteria();
        assertEquals("id        difficulty  departureName destinationName\n" +
                "[1]       1           Sheung Shui   Tai Po\n", actualResult);

    }
//
//

}
