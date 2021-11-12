package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import code.ClimbingTrail;
import code.ClimbingTrailRepository;

class ClimbingRepoTest {

	@Test
	void testTreeSet() {
		ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
		ctr.clearClimbingTrails();

		ClimbingTrail ct1 = new ClimbingTrail("001", 2, "Kowloon Tong", "Tai Po");

		ClimbingTrail ct2 = new ClimbingTrail("002", 1, "Shatin", "Tai Po");

		ClimbingTrail ct3 = new ClimbingTrail("003", 5, "Sheung Shui", "Tuen Muen");

		ctr.addClimbingTrail(ct1);
		ctr.addClimbingTrail(ct2);
		ctr.addClimbingTrail(ct3);

		String resultString = ctr.list();
		String expectedString = "The climbing trail with id 002, difficulty 1, destination Shatin, departure_name Tai Po\nThe climbing trail with id 001, difficulty 2, destination Kowloon Tong, departure_name Tai Po\nThe climbing trail with id 003, difficulty 5, destination Sheung Shui, departure_name Tuen Muen\n";
		assertEquals(expectedString, resultString);
	}

	@Test
	void testFilterDifficulty() {
		ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
		ctr.clearClimbingTrails();

		ClimbingTrail ct1 = new ClimbingTrail("001", 8, "Shatin", "Kowloon Tong");

		ClimbingTrail ct2 = new ClimbingTrail("002", 10, "Sheung Shui", "Tai Po");

		ClimbingTrail ct3 = new ClimbingTrail("003", 8, "Olympics", "Tai Po");
		ctr.addClimbingTrail(ct1);
		ctr.addClimbingTrail(ct2);
		ctr.addClimbingTrail(ct3);
		String resultString = ctr.filterByDifficulty(8);
		String expectedString = "The climbing trail with id 001, difficulty 8, destination Shatin, departure_name Kowloon Tong\nThe climbing trail with id 003, difficulty 8, destination Olympics, departure_name Tai Po\n";
		assertEquals(expectedString, resultString);

	}

	@Test
	void testFilterTrailByDest() {
		ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
		ctr.clearClimbingTrails();

		ClimbingTrail ct1 = new ClimbingTrail("001", 8, "Shatin", "Kowloon Tong");

		ClimbingTrail ct2 = new ClimbingTrail("002", 10, "Sheung Shui", "Tai Po");

		ClimbingTrail ct3 = new ClimbingTrail("003", 8, "Olympics", "Tai Po");
		ctr.addClimbingTrail(ct1);
		ctr.addClimbingTrail(ct2);
		ctr.addClimbingTrail(ct3);
		String resultString = ctr.filterTrailByDest("Shatin");
		String expectedString = "The climbing trail with id 001, difficulty 8, destination Shatin, departure_name Kowloon Tong\n";
		assertEquals(expectedString, resultString);
	}

	@Test
	void testFilterTrailByID_FoundCase() {
		ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
		ctr.clearClimbingTrails();

		ClimbingTrail ct1 = new ClimbingTrail("001", 8, "Shatin", "Kowloon Tong");

		ClimbingTrail ct2 = new ClimbingTrail("002", 10, "Sheung Shui", "Tai Po");

		ClimbingTrail ct3 = new ClimbingTrail("003", 8, "Olympics", "Tai Po");
		ctr.addClimbingTrail(ct1);
		ctr.addClimbingTrail(ct2);
		ctr.addClimbingTrail(ct3);
		String resultString = ctr.findTrailByID("002");
		String expectedString = "The climbing trail with id 002, difficulty 10, destination Sheung Shui, departure_name Tai Po";
		assertEquals(expectedString, resultString);
	}

	@Test
	void testFilterTrailByID_NotFoundCase() {
		ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
		ctr.clearClimbingTrails();

		ClimbingTrail ct1 = new ClimbingTrail("001", 8, "Shatin", "Kowloon Tong");

		ClimbingTrail ct2 = new ClimbingTrail("002", 10, "Sheung Shui", "Tai Po");

		ClimbingTrail ct3 = new ClimbingTrail("003", 8, "Olympics", "Tai Po");
		ctr.addClimbingTrail(ct1);
		ctr.addClimbingTrail(ct2);
		ctr.addClimbingTrail(ct3);
		String resultString = ctr.findTrailByID("005");
		String expectedString = null;
		assertEquals(expectedString, resultString);
	}

	@Test
	void testFilterTrailByDeparture() {
		ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
		ctr.clearClimbingTrails();

		ClimbingTrail ct1 = new ClimbingTrail("001", 8, "Shatin", "Kowloon Tong");

		ClimbingTrail ct2 = new ClimbingTrail("002", 10, "Sheung Shui", "Tai Po");

		ClimbingTrail ct3 = new ClimbingTrail("003", 8, "Olympics", "Tai Po");
		ctr.addClimbingTrail(ct1);
		ctr.addClimbingTrail(ct2);
		ctr.addClimbingTrail(ct3);
		String resultString = ctr.filterTrailByDeparture("Kowloon Tong");
		String expectedString = "The climbing trail with id 001, difficulty 8, destination Shatin, departure_name Kowloon Tong\n";
		assertEquals(expectedString, resultString);
	}

	@Test
	void testSortFunction_ValidInput() {
		ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
		ctr.clearClimbingTrails();

		ClimbingTrail ct1 = new ClimbingTrail("001", 8, "Shatin", "Kowloon Tong");

		ClimbingTrail ct2 = new ClimbingTrail("002", 10, "Sheung Shui", "Tai Po");

		ClimbingTrail ct3 = new ClimbingTrail("003", 8, "Olympics", "Tai Po");
		ctr.addClimbingTrail(ct1);
		ctr.addClimbingTrail(ct2);
		ctr.addClimbingTrail(ct3);

		String resultString1 = ctr.sort(0);
		String expectedString1 = "The climbing trail with id 001, difficulty 8, destination Shatin, departure_name Kowloon Tong\nThe climbing trail with id 003, difficulty 8, destination Olympics, departure_name Tai Po\nThe climbing trail with id 002, difficulty 10, destination Sheung Shui, departure_name Tai Po\n";
		assertEquals(expectedString1, resultString1);

		String resultString2 = ctr.sort(1);
		String expectedString2 = "The climbing trail with id 002, difficulty 10, destination Sheung Shui, departure_name Tai Po\nThe climbing trail with id 003, difficulty 8, destination Olympics, departure_name Tai Po\nThe climbing trail with id 001, difficulty 8, destination Shatin, departure_name Kowloon Tong\n";
		assertEquals(expectedString2, resultString2);
	}

	@Test
	void testSortFunction_InvalidInput() {
		ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
		ctr.clearClimbingTrails();

		ClimbingTrail ct1 = new ClimbingTrail("001", 8, "Shatin", "Kowloon Tong");

		ClimbingTrail ct2 = new ClimbingTrail("002", 10, "Sheung Shui", "Tai Po");

		ClimbingTrail ct3 = new ClimbingTrail("003", 8, "Olympics", "Tai Po");
		ctr.addClimbingTrail(ct1);
		ctr.addClimbingTrail(ct2);
		ctr.addClimbingTrail(ct3);
		String resultString = ctr.sort(3);
		String expectedString = null;
		assertEquals(expectedString, resultString);
	}

	@Test
	void testDeleteFunction() {
		ClimbingTrailRepository ctr = ClimbingTrailRepository.getInstance();
		ctr.clearClimbingTrails();

		ClimbingTrail ct1 = new ClimbingTrail("001", 8, "Shatin", "Kowloon Tong");

		ClimbingTrail ct2 = new ClimbingTrail("002", 10, "Sheung Shui", "Tai Po");

		ClimbingTrail ct3 = new ClimbingTrail("003", 8, "Olympics", "Tai Po");
		ctr.addClimbingTrail(ct1);
		ctr.addClimbingTrail(ct2);
		ctr.addClimbingTrail(ct3);

		ctr.deleteClimbingTrail(ct3);
		String resultString = ctr.list();
		String expectedString = "The climbing trail with id 001, difficulty 8, destination Shatin, departure_name Kowloon Tong\nThe climbing trail with id 002, difficulty 10, destination Sheung Shui, departure_name Tai Po\n";
		assertEquals(expectedString, resultString);
	}
}
