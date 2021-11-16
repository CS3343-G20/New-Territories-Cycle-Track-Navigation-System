package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import SmartNavigationSystem.ScheduleDate;

public class lst_ScheduleDateTest {

	@Test
	public void isValidDate_case1() {
		boolean res = ScheduleDate.isValidDate("2001/01/01");
		assertEquals(true, res);
	}

	@Test
	public void isValidDate_case2() {
		boolean res = ScheduleDate.isValidDate("2001/101/01");
		assertEquals(false, res);
	}

}
