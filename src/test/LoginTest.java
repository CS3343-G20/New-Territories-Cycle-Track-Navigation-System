package test;

import SmartNavigationSystem.JsonOperation;
import SmartNavigationSystem.Login;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * 1. Test login
 * 2. Test verify password
 * 3. Test reset password
 */
public class LoginTest {


    /**
     * (A)
     * Test login success.
     *      Email exist and password is correct.
     */
    @Test
    public void testLogin_case1() throws IOException {
        new JsonOperation();
        //2.Test
        String inputEmail="cs3343g20system@gmail.com";
        String inputPassword="pwd";

        String input=inputEmail+"\n"+inputPassword;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Login login=new Login();
        int res=login.login(new Scanner(System.in));
        assertEquals(1,res);
    }


    /**
     * (B)
     * Test login fail:
     * 1.email not exist
     *      (2)chose not to register.
     */
    @Test
    public void testLogin_case3() throws IOException {
        new JsonOperation();
        String inputEmail="false_email";
        String no="N";
        String inputEmail2="cs3343g20system@gmail.com";
        String inputPassword="pwd";
        String input=inputEmail+"\n"+no+"\n"+inputEmail2+"\n"+inputPassword;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Login login=new Login();
        int res=login.login(new Scanner(System.in));
        assertEquals(0,res);
    }

    /**
     * (B)
     * Test login fail:
     * 2.email exist
     *      (1)password is incorrect.
     */
    @Test
    public void testLogin_case4() throws IOException {
        new JsonOperation();
        String inputEmail="cs3343g20system@gmail.com";
        String inputPassword="error_password";
        String inputEmail2="cs3343g20system@gmail.com";
        String inputPassword2="pwd";
        String input=inputEmail+"\n"+inputPassword+"\n"+inputEmail2+"\n"+inputPassword2;;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Login login=new Login();
        int res=login.login(new Scanner(System.in));
        assertEquals(0,res);

    }

    /**
     * Success
     * Email  exist and password is correct.
     */
    @Test
    public void testVerifyPwd_case1() throws IOException {
        new JsonOperation();

        String inputEmail="testVerifyPwd@gmail.com";
        String inputPassword=JsonOperation.getMemberPassword(inputEmail);
        Login login=new Login();
        String input=inputEmail+"\n"+inputPassword;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        int res=login.login(new Scanner(System.in));
        assertEquals(1,res);
        login.verifyPwd(inputEmail,inputPassword);
    }


    /**
     * Fail
     * password is incorrect.
     */
    @Test
    public void testVerifyPwd_case2() throws FileNotFoundException {
        new JsonOperation();
        String inputEmail="testVerifyPwd@gmail.com";
        String inputPassword="jeffpwdetw53";

        Login login=new Login();
        boolean res=login.verifyPwd(inputEmail,inputPassword);
        //3. Check the answer
        // return false
        assertFalse(res);
    }




    /**
     * Cancel reset password.
     */
    @Test
    public void testResetPwd_case1() throws IOException {
        new JsonOperation();
        String inputEmail="testVerifyPwd@gmail.com";
        String inputPassword=JsonOperation.getMemberPassword(inputEmail);
        String input=inputEmail+"\n"+inputPassword+"\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Login login=new Login();
        int loginSuccess=login.login(new Scanner(System.in));
        assertEquals(1,loginSuccess);
        System.setIn(new ByteArrayInputStream("no".getBytes()));
        login.resetPwd(new Scanner(System.in));

        //check:The password has not changed
        new JsonOperation();
        boolean res=JsonOperation.checkMemberPwd(inputEmail,inputPassword);
        assertTrue(res);
    }

    /**
     *  Reset password.
     *   1.The passwords entered are the same.
     *
     */
    @Test
    public void testResetPwd_case2() throws IOException {
        new JsonOperation();
        String inputEmail="testVerifyPwd@gmail.com";

        String inputPassword=JsonOperation.getMemberPassword(inputEmail);

        //login
        String input=inputEmail+"\n"+inputPassword+"\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Login login=new Login();
        int loginSuccess=login.login(new Scanner(System.in));
        assertEquals(1,loginSuccess);

        //reset
        String newPassword=inputPassword+"1";
        String resetInput="yes"+"\n"+newPassword+"\n"+newPassword;
        System.setIn(new ByteArrayInputStream(resetInput.getBytes()));
        login.resetPwd(new Scanner(System.in));

        //check:The password has not changed
        boolean resFalse=JsonOperation.checkMemberPwd(inputEmail,inputPassword);
        boolean resTrue=JsonOperation.checkMemberPwd(inputEmail,newPassword);
        assertFalse(resFalse);
        assertTrue(resTrue);
    }

    /**
     *  Reset password.
     *  2.The entered passwords are inconsistent.
     *
     */
    @Test
    public void testResetPwd_case3() throws IOException {
        new JsonOperation();
        String inputEmail="testVerifyPwd@gmail.com";

        String inputPassword=JsonOperation.getMemberPassword(inputEmail);
        //login
        String input=inputEmail+"\n"+inputPassword+"\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Login login=new Login();
        int loginSuccess=login.login(new Scanner(System.in));
        assertEquals(1,loginSuccess);

        //reset
        String newPassword=inputPassword+"1";
        //not same
        String resetInput="yes"+"\n"+newPassword+"\n"+inputPassword;
        System.setIn(new ByteArrayInputStream(resetInput.getBytes()));
        login.resetPwd(new Scanner(System.in));

        //check:The password has not changed
        boolean resTrue=JsonOperation.checkMemberPwd(inputEmail,inputPassword);
        boolean resFalse=JsonOperation.checkMemberPwd(inputEmail,newPassword);
        assertFalse(resFalse);
        assertTrue(resTrue);
    }

}