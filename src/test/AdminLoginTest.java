package test;

import SmartNavigationSystem.AdminLogin;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.Assert.assertFalse;
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
        AdminLogin adminLogin=new AdminLogin();
        System.setIn(new ByteArrayInputStream(token.getBytes()));
        boolean res=adminLogin.login(new Scanner(System.in));
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
        AdminLogin adminLogin=new AdminLogin();
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        boolean res=adminLogin.login(new Scanner(System.in));
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
        AdminLogin adminLogin=new AdminLogin();
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        boolean res=adminLogin.login(new Scanner(System.in));
        assertTrue(res);
    }

    /**
     * Fail
     * False token at the first time
     * False token at the second time
     * False token at the Third time
     * True token at the fourth  time
     */
    @Test
    public void testLogin_case4(){
        String token1="CS3343G20gferg";
        String token2="CS3343G2063476";
        String token3="CS3343G253464";
        String token4="CS3343G20";
        String input=token1+"\n"+token2+"\n"+token3+"\n"+token4;
        AdminLogin adminLogin=new AdminLogin();
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        boolean res=adminLogin.login(new Scanner(System.in));
        assertFalse(res);
    }

}
