package test;

import SmartNavigationSystem.JsonOperation;
import SmartNavigationSystem.Member;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 *
 */
public class MemberTest {
    /**
     * Test email.
     */
    @Test
    public void testEmail_case1() throws IOException {
        new JsonOperation();
<<<<<<< HEAD
        if (JsonOperation.getMemberInfo("testEmail@gmail.com")==null){
            JsonOperation.addNewMember("testEmail@gmail.com","pwd");
        }
        String inputEmail="testEmail@gmail.com";
        String inputPassword=JsonOperation.getMemberPassword(inputEmail);
=======
        String inputEmail="cs3343g20system@gmail.com";
        String inputPassword="pwd";
>>>>>>> master

        String input=inputEmail+"\n"+inputPassword;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Member member=new Member();
        member.Login(new Scanner(System.in));

        String email=member.getEmail();
        assertEquals(inputEmail,email);
    }

    @Test
    public void testCheckInfo_case1() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        new JsonOperation();
<<<<<<< HEAD
        if (JsonOperation.getMemberInfo("testEmail@gmail.com")==null){
            JsonOperation.addNewMember("testEmail@gmail.com","pwd");
        }
        String inputEmail="testEmail@gmail.com";
        String inputPassword=JsonOperation.getMemberPassword(inputEmail);
=======
        String inputEmail="cs3343g20system@gmail.com";
        String inputPassword="pwd";
>>>>>>> master

        String input=inputEmail+"\n"+inputPassword;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Member member=new Member();
        member.Login(new Scanner(System.in));


        member.CheckInfo();

    }




    /**
     *  Reset password.
     *   1.The passwords entered are the same.
     *
     */
    @Test
    public void testResetPwd_case2() throws IOException {
        new JsonOperation();
<<<<<<< HEAD
        if (JsonOperation.getMemberInfo("testEmail@gmail.com")==null){
            JsonOperation.addNewMember("testEmail@gmail.com","pwd");
        }
        String inputEmail="testEmail@gmail.com";
=======
        new JsonOperation();
        String inputEmail="testVerifyPwd@gmail.com";
>>>>>>> master
        String inputPassword=JsonOperation.getMemberPassword(inputEmail);

        String input=inputEmail+"\n"+inputPassword+"\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Member member=new Member();
        Member loginSuccess=member.Login(new Scanner(System.in));
        assertNotNull(loginSuccess);
        String newPassword=inputPassword+"1";
        String resetInput="yes"+"\n"+newPassword+"\n"+newPassword;
        System.setIn(new ByteArrayInputStream(resetInput.getBytes()));
        member.resetPwd(new Scanner(System.in));

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
<<<<<<< HEAD
        if (JsonOperation.getMemberInfo("testEmail@gmail.com")==null){
            JsonOperation.addNewMember("testEmail@gmail.com","pwd");
        }
        String inputEmail="testEmail@gmail.com";
=======
        new JsonOperation();
        String inputEmail="testVerifyPwd@gmail.com";
>>>>>>> master
        String inputPassword=JsonOperation.getMemberPassword(inputEmail);

        String input=inputEmail+"\n"+inputPassword+"\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Member member=new Member();
        Member loginSuccess=member.Login(new Scanner(System.in));
        assertNotNull(loginSuccess);
        String newPassword=inputPassword+"1";
        //not same
        String resetInput="yes"+"\n"+newPassword+"\n"+inputPassword;
        System.setIn(new ByteArrayInputStream(resetInput.getBytes()));
        member.resetPwd(new Scanner(System.in));

        //check:The password has not changed
        boolean resTrue=JsonOperation.checkMemberPwd(inputEmail,inputPassword);
        boolean resFalse=JsonOperation.checkMemberPwd(inputEmail,newPassword);
        assertFalse(resFalse);
        assertTrue(resTrue);
    }

<<<<<<< HEAD
}
=======
}
>>>>>>> master
