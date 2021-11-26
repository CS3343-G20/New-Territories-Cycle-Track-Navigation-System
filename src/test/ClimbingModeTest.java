package test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import SmartNavigationSystem.BookmarkManager;
import SmartNavigationSystem.ClimbingMode;
import SmartNavigationSystem.ClimbingTrail;
import SmartNavigationSystem.ClimbingTrailRepoManager;
import SmartNavigationSystem.ClimbingTrailsQuerier;
import SmartNavigationSystem.CyclingMode;
import SmartNavigationSystem.GraphUtility;
import SmartNavigationSystem.Member;
import SmartNavigationSystem.VerticesManager;

public class ClimbingModeTest {

	class BookmarkStub implements BookmarkManager {

		@Override
		public void addBookmark(String route, Member member) {
			System.out.println("success");

		}

	}
    class CyclingModeStub extends CyclingMode {
    	public CyclingModeStub(GraphUtility map, VerticesManager vManager, Scanner in, BookmarkManager mark,
				ClimbingTrailsQuerier querier) {
			super(null,null,null,null,null);
		}

		@Override 
    	public void modeSwitch(int trail_id, Member member){
    		System.out.println("mode switch successful");
    	}
    }
    
	class ClimbingTrailRepoStub implements ClimbingTrailRepoManager {

		@Override
		public String filterByDifficulty(int difficulty) {
			if (difficulty == 1) {
				return "trail1";
			} else {
				return "";
			}
		}

		@Override
		public String filterTrailByDest(String destName) {
			if (destName.equals("Tuen Mun")) {
				return "trail20";
			}
			return "";
		}

		@Override
		public String filterTrailByDeparture(String departureName) {
			if (departureName.equals("Sheung Shui")) {
				return "trail10";
			}
			return "";
		}

		@Override
		public String findTrailByID(int pathID) {
			if (pathID == 1) {
				return "trail30";
			} else {
				return "";
			}
		}

		@Override
		public String sort(int order) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String list() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String listDestinations() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String listDepartures() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String listDifficulties() {
			return " 1,2";
		}

		@Override
		public ArrayList<ClimbingTrail> getFilteredClimbingTrails() {
			ArrayList<ClimbingTrail> act = new ArrayList<ClimbingTrail>();
			act.add(new ClimbingTrail(1, 1, 1, "Yuen Long", "Tuen Mun"));
			return act;
		}

	}

	@Test
	public void testfindTrailsByDifficulty_FoundCase() {
		String input = "1\n";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		Scanner scan = new Scanner(System.in);
		ClimbingMode cm = new ClimbingMode(new ClimbingTrailRepoStub(), scan, new BookmarkStub(),null);
		String actualResult = cm.findTrailsByDifficulty();
		assertEquals("trail1", actualResult);

	}

	@Test
	public void testfindTrailsByDifficulty_NotFoundCase() {
		String input = "5\n1\n";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		Scanner scan = new Scanner(System.in);
		ClimbingMode cm = new ClimbingMode(new ClimbingTrailRepoStub(), scan, new BookmarkStub(),null);
		String actualResult = cm.findTrailsByDifficulty();
		assertEquals("trail1", actualResult);
	}

	@Test
	public void testfindTrailsByDifficulty_WrongFormatCase() {
		String input = "test\n1\n";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor));
		Scanner scan = new Scanner(System.in);
		ClimbingMode cm = new ClimbingMode(new ClimbingTrailRepoStub(), scan, new BookmarkStub(),null);
		String actualResult = cm.findTrailsByDifficulty();
		//assertEquals("Please choose the difficulty from following numbers:\n 1,2\nThe input should be an integer. Please try again:\nThe following are the filtered trails:\ntrail1",outputStreamCaptor.toString().trim());
		assertEquals("trail1", actualResult);
	}
//	@Test
//	public void testfindTrailsByDifficulty_InvalidInputCase() {
//		String input = "c\n1\n";
//		ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStreamCaptor));
//		System.setIn(new ByteArrayInputStream(input.getBytes()));
//		Scanner scan = new Scanner(System.in);
//		ClimbingMode cm = new ClimbingMode(new ClimbingTrailRepoStub(), scan, new BookmarkStub(),null);
//		cm.findTrailsByDifficulty();
//		assertEquals("Please choose the difficulty from following numbers:\n 1,2\nThe input should be an integer. Please try again:\nThe following are the filtered trails:\ntrail1", outputStreamCaptor.toString().trim());
//	}
	@Test
	public void testfindTrailsByDeparture_FoundCase() {
		String input = "Sheung Shui\n";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		Scanner scan = new Scanner(System.in);
		ClimbingMode cm = new ClimbingMode(new ClimbingTrailRepoStub(), scan, new BookmarkStub(),null);
		String actualResult = cm.findTrailsByDeparture();
		assertEquals("trail10", actualResult);
	}


	@Test
	public void testaddCycling_PositiveCase() {
		CyclingMode cm = new CyclingModeStub(null,null,null,null,null);
		String input = "Y\n";
		ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor));
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		Scanner scan = new Scanner(System.in);
		ClimbingMode clm = new ClimbingMode(new ClimbingTrailRepoStub(), scan, new BookmarkStub(),cm);
		clm.addCycling(1);
		assertEquals("Do you want to cycle to the point? [Y/N]\r\nmode switch successful",outputStreamCaptor.toString().trim());
	}
	@Test
	public void testaddCycling_NegativeCase() {
		CyclingMode cm = new CyclingModeStub(null,null,null,null,null);
		String input = "N\nY\n";
		ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor));
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		Scanner scan = new Scanner(System.in);
		ClimbingMode clm = new ClimbingMode(new ClimbingTrailRepoStub(), scan, new BookmarkStub(),cm);
		clm.addCycling(1);
		assertEquals("Do you want to cycle to the point? [Y/N]",outputStreamCaptor.toString().trim());
	}
	@Test
	public void testaddCycling_InvalidCase() {
		CyclingMode cm = new CyclingModeStub(null,null,null,null,null);
		String input = "U\nY\n";
		ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor));
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		Scanner scan = new Scanner(System.in);
		ClimbingMode clm = new ClimbingMode(new ClimbingTrailRepoStub(), scan, new BookmarkStub(),cm);
		clm.addCycling(1);
		assertEquals("Do you want to cycle to the point? [Y/N]\r\nThe input should be chosen from the listed commands above. Please try again:\r\nmode switch successful",outputStreamCaptor.toString().trim());
	}
	@Test
	public void testfindTrailsByDeparture_NotFoundCase() {
		String input = "Shatin\nSheung Shui\n";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		Scanner scan = new Scanner(System.in);
		ClimbingMode cm = new ClimbingMode(new ClimbingTrailRepoStub(), scan, new BookmarkStub(),null);
		String actualResult = cm.findTrailsByDeparture();
		assertEquals("trail10", actualResult);
	}

	@Test
	public void testfindTrailsByDestination_FoundCase() {
		String input = "Tuen Mun\n";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		Scanner scan = new Scanner(System.in);
		ClimbingMode cm = new ClimbingMode(new ClimbingTrailRepoStub(), scan, new BookmarkStub(),null);
		String actualResult = cm.findTrailsByDestination();
		assertEquals("trail20", actualResult);
	}

	@Test
	public void testfindTrailsByDestination_NotFoundCase() {
		String input = "Tsuen Wan\nTuen Mun\n";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		Scanner scan = new Scanner(System.in);
		ClimbingMode cm = new ClimbingMode(new ClimbingTrailRepoStub(), scan, new BookmarkStub(),null);
		String actualResult = cm.findTrailsByDestination();
		assertEquals("trail20", actualResult);
	}

	@Test
	public void testChooseSelectionCriteria_Case1() {
		String input = "1\n1\n";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		Scanner scan = new Scanner(System.in);
		ClimbingMode cm = new ClimbingMode(new ClimbingTrailRepoStub(), scan, new BookmarkStub(),null);
		String actualResult = cm.chooseSelectionCriteria();
		assertEquals("trail1", actualResult);

	}

	@Test
	public void testChooseSelectionCriteria_Case2() {
		String input = "2\nSheung Shui\n";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		Scanner scan = new Scanner(System.in);
		ClimbingMode cm = new ClimbingMode(new ClimbingTrailRepoStub(), scan, new BookmarkStub(),null);
		String actualResult = cm.chooseSelectionCriteria();
		assertEquals("trail10", actualResult);

	}

	@Test
	public void testChooseSelectionCriteria_Case3() {
		String input = "3\nTuen Mun\n";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		Scanner scan = new Scanner(System.in);
		ClimbingMode cm = new ClimbingMode(new ClimbingTrailRepoStub(), scan, new BookmarkStub(),null);
		String actualResult = cm.chooseSelectionCriteria();
		assertEquals("trail20", actualResult);

	}
	@Test
	public void testChooseSelectionCriteria_Exception() {
		String input = "test\n1\n1\n";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		Scanner scan = new Scanner(System.in);
		ClimbingMode cm = new ClimbingMode(new ClimbingTrailRepoStub(), scan, new BookmarkStub(),null);
		String actualResult = cm.chooseSelectionCriteria();
		assertEquals("trail1", actualResult);

	}
	@Test
	public void testchooseClimbingPath() {
		String input = "1\n";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor));
		Scanner scan = new Scanner(System.in);
		ClimbingMode cm = new ClimbingMode(new ClimbingTrailRepoStub(), scan, new BookmarkStub(),null);
		cm.chooseClimbingPath();
		assertEquals("Please enter the id of the climbing path that you would like to choose :\r\nThis is the climbing trail chosen:\r\ntrail30", outputStreamCaptor.toString().trim());

	}
	@Test
	public void testchooseClimbingPath_NotExist() {
		String input = "5\n1\n";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor));
		Scanner scan = new Scanner(System.in);
		ClimbingMode cm = new ClimbingMode(new ClimbingTrailRepoStub(), scan, new BookmarkStub(),null);
		cm.chooseClimbingPath();
		assertEquals("Please enter the id of the climbing path that you would like to choose :\r\nThe climbing path doesn't exist, please enter a valid climbing path id\r\nThis is the climbing trail chosen:\r\ntrail30", outputStreamCaptor.toString().trim());

	}
	
	@Test
	public void testchooseClimbingPath_WrongFormat() {
		String input = "test\n1\n";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor));
		Scanner scan = new Scanner(System.in);
		ClimbingMode cm = new ClimbingMode(new ClimbingTrailRepoStub(), scan, new BookmarkStub(),null);
		cm.chooseClimbingPath();
		assertEquals("Please enter the id of the climbing path that you would like to choose :\r\nThe input should be an integer. Please try again:\r\nThis is the climbing trail chosen:\r\ntrail30", outputStreamCaptor.toString().trim());

	}


	@AfterEach
	public void restoreStreams() {
		System.setOut(System.out);
		System.setIn(System.in);
	}

	@Test
	public void testaddBookmark_PositiveCase() {
		String input = "Y\n";
		ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		System.setOut(new PrintStream(outputStreamCaptor));
		Scanner scan = new Scanner(System.in);
		ClimbingMode cm = new ClimbingMode(new ClimbingTrailRepoStub(), scan, new BookmarkStub(),null);
		cm.addBookmark();
		assertEquals("Do you want to add the selected route as bookmark? [Y/N]\r\nsuccess", outputStreamCaptor.toString().trim());
	}
	@Test
	public void testaddBookmark_InvalidCase() {
		String input = "T\nN\n";
		PrintStream standardOut = System.out;
		ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		System.setOut(new PrintStream(outputStreamCaptor));
		Scanner scan = new Scanner(System.in);
		ClimbingMode cm = new ClimbingMode(new ClimbingTrailRepoStub(), scan, new BookmarkStub(),null);
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
		ClimbingMode cm = new ClimbingMode(new ClimbingTrailRepoStub(), scan, new BookmarkStub(),null);
		String actualResult=cm.chooseSelectionCriteria();
		assertEquals("trail1",actualResult);

	}

}