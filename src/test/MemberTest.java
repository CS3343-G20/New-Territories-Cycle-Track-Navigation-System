package test;

import SmartNavigationSystem.JsonOperation;
import SmartNavigationSystem.Login;
import SmartNavigationSystem.Member;
import SmartNavigationSystem.Vertices;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

/**
 *
 */
public class MemberTest {
    /**
     * Test email.
     */
    @Test
    public void testEmail_case1() throws IOException {
        String inputEmail="jeff";
        String inputPassword="jeffpwd";

        String input=inputEmail+"\n"+inputPassword;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Member member=new Member();
        member.Login();

        String email=member.getEmail();
        assertEquals(inputEmail,email);
    }

    @Test
    public void testCheckInfo_case1() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        new JsonOperation();
        String inputEmail="jeff";
        String inputPassword="jeffpwd";

        String input=inputEmail+"\n"+inputPassword;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Member member=new Member();
        member.Login();


        String res=member.CheckInfo();
        //expected
        String expected="Email: jeff\n" +
                "Password: jeffpwd\n" +
                "\n" +
                "You have 2 schedules:\n" +
                "Schedule index: 1\n" +
                "Event: jeffEVENT1\n" +
                "Schedule date: 20211022\n" +
                "\n" +
                "Schedule index: 2\n" +
                "Event: jeffEVENT2\n" +
                "Schedule date: 20211024\n" +
                "\n" +
                "You have 2 bookmarks:\n" +
                "\n" +
                "Bookmark index: 1\n" +
                "Type: climbing trail\n" +
                "\n" +
                "Bookmark index: 2\n" +
                "Type: cycling trail\n" +
                "\n" ;
        assertEquals(expected,res);
    }


    /**
     * Cancel reset password.
     */
    @Test
    public void testResetPwd_case1() throws IOException {
        String inputEmail="jeff";
        String inputPassword="jeffpwd";

        String input=inputEmail+"\n"+inputPassword+"\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Member member=new Member();
        boolean loginSuccess=member.Login();
        assertTrue(loginSuccess);

        System.setIn(new ByteArrayInputStream("no".getBytes()));
        member.resetPwd();

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
        String inputEmail="testreset";

        String inputPassword=JsonOperation.getMemberPassword(inputEmail);

        String input=inputEmail+"\n"+inputPassword+"\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Member member=new Member();
        boolean loginSuccess=member.Login();
        assertTrue(loginSuccess);
        String newPassword=inputPassword+"1";
        String resetInput="yes"+"\n"+newPassword+"\n"+newPassword;
        System.setIn(new ByteArrayInputStream(resetInput.getBytes()));
        member.resetPwd();

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
        String inputEmail="testreset";

        String inputPassword=JsonOperation.getMemberPassword(inputEmail);

        String input=inputEmail+"\n"+inputPassword+"\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Member member=new Member();
        boolean loginSuccess=member.Login();
        assertTrue(loginSuccess);
        String newPassword=inputPassword+"1";
        //not same
        String resetInput="yes"+"\n"+newPassword+"\n"+inputPassword;
        System.setIn(new ByteArrayInputStream(resetInput.getBytes()));
        member.resetPwd();

        //check:The password has not changed
        boolean resTrue=JsonOperation.checkMemberPwd(inputEmail,inputPassword);
        boolean resFalse=JsonOperation.checkMemberPwd(inputEmail,newPassword);
        assertFalse(resFalse);
        assertTrue(resTrue);
    }

}
