package test;

import SmartNavigationSystem.AdminLogin;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;

/**
 * Test login
 */
public class AdminLoginTest {

    /**
     * Success
     * True token at the first time
     */
    @Test
    public void testLogin_case1(){
        String token="CS3343G20";
        System.setIn(new ByteArrayInputStream(token.getBytes()));
        boolean res=AdminLogin.login(new Scanner(System.in));
        assertTrue(res);
    }

    /**
     * Success
     * False token at the first time
     * True token at the second time
     */
    @Test
    public void testLogin_case2(){
        String token1="CS3343G20gferg";
        String token2="CS3343G20";
        String input=token1+"\n"+token2;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        boolean res=AdminLogin.login(new Scanner(System.in));
        assertTrue(res);
    }

    /**
     * Success
     * False token at the first time
     * False token at the second time
     * True token at the Third time
     */
    @Test
    public void testLogin_case3(){
        String token1="CS3343G20gferg";
        String token2="CS3343G2063476";
        String token3="CS3343G20";
        String input=token1+"\n"+token2+"\n"+token3;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        boolean res=AdminLogin.login(new Scanner(System.in));
        assertTrue(res);
    }

}
