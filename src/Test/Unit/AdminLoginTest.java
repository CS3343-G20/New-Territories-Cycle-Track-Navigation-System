package Test.Unit;

import SmartNavigationSystem.AdminLogin;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;

public class AdminLoginTest {
	
	private String token = "CS3343G20";
	private String wrongToken = "CS3343G20xxx";

    @Test
    public void testLogin_case1(){
        System.setIn(new ByteArrayInputStream(token.getBytes()));
        boolean res=AdminLogin.login(new Scanner(System.in));
        assertTrue(res);
    }

    @Test
    public void testLogin_case2(){
        String input=wrongToken+"\n"+token;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        boolean res=AdminLogin.login(new Scanner(System.in));
        assertTrue(res);
    }

    @Test
    public void testLogin_case3(){
        String input=wrongToken+"\n"+wrongToken+"\n"+token;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        boolean res=AdminLogin.login(new Scanner(System.in));
        assertTrue(res);
    }

}
