package Test.Integration;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import SmartNavigationSystem.Bookmark;
import SmartNavigationSystem.ExInvalidIndex;
import SmartNavigationSystem.JsonOperation;
import SmartNavigationSystem.Member;

public class BookamarkIntegrationTest {

	private Member m;
	private final String email = "cs3343g20system@gmail.com";
	private final String pwd = "pwd";
	
	@Before
	public void setUp() throws IOException {
		
		m = new Member();
		new JsonOperation();
		String input=email+"\n"+pwd;
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		m.Login(new Scanner(System.in));
	    
	}

	@Test
	public void addBookmark_case1() throws FileNotFoundException {
		Bookmark bookm = Bookmark.getInstance();
		int org = JsonOperation.getMemberBookmArray(email).size();
		bookm.addBookmark("route", m);
		int cur = JsonOperation.getMemberBookmArray(email).size();
		assertEquals(org + 1, cur);
	}
	
	@Test
	public void deleteBookmark_case1() throws FileNotFoundException, ExInvalidIndex {
	    JsonOperation.addNewBookmark("route", m);
		Bookmark bookm = Bookmark.getInstance();
		int org = JsonOperation.getMemberBookmArray(email).size();
		bookm.deleteBookmark(m, 1);
		int cur = JsonOperation.getMemberBookmArray(email).size();
		assertEquals(org - 1, cur);
	}
}
