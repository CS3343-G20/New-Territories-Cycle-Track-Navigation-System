package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;

import code.ClimbingMode;
import code.ClimbingTrailRepoManager;

public class ClimbingModeTest {
	private ClimbingTrailRepoManager ctrm = new ClimbingTrailRepoManagerStub();
	private ClimbingMode cm = new ClimbingMode(ctrm);

	class ClimbingTrailRepoManagerStub implements ClimbingTrailRepoManager {

		private String testStr = "Test trail";
		private String testStr1 = "Test trail 1";
		private String testStr2 = "Test trail 2";
		private String testStr3 = "Test trail 3";

//		ClimbingTrailRepoManagerStub(){
//			
//		}
		@Override
		public String filterByDifficulty(int difficulty) {
			// TODO Auto-generated method stub
			switch (difficulty) {
			case 1:
				return testStr1;
			case 5:
				return testStr2;
			case 8:
				return testStr3;
			default:
				return testStr;
			}
		}

		@Override
		public String filterTrailByDest(String destName) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String filterTrailByDeparture(String departureName) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String findTrailByID(String ID) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String sort(int order) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String list() {
			// TODO Auto-generated method stub
			return testStr;
		}
	}

	@Test
	public void testTrailListing() {
		String actualString = cm.listTrails();
		String expectedString = "Test trail";
		assertEquals(expectedString, actualString);

	}

	@Test
	public void testFilterDifficulty() {
		String actualString = cm.findTrailsByDifficulty(8);
		String expectedString = "Test trail 3";
		assertEquals(expectedString, actualString);

	}

	@Test
	public void testChooseSelectionCriteria() {
		String testData = "15";

		
		String actualString = cm.chooseSelectionCriteria();
		String expectedString = "Test trail 2";
		assertEquals(expectedString, actualString);

	}

}
