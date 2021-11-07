package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.mail.MessagingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import SmartNavigationSystem.SendEmail;

public class lst_SendEmailTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream err = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(err));
	}  
	
	@Test
	public void sendEmail_case1() throws MessagingException {
		String to = "cs3343g20system@gmail.com";
		String subject = "Subject line";
		String msg = "Msg line";
		SendEmail.sendEmail(to, subject, msg);
		assertEquals("Sent message successfully....\n", outContent.toString());
	}
	
	@Test
	public void sendEmail_case2() {
		String to = "";
		String subject = "Subject line";
		String msg = "Msg line";
		assertThrows(MessagingException.class, () -> {
			SendEmail.sendEmail(to, subject, msg);
		});
	}

	
	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	}
	
}
