package test;

import SmartNavigationSystem.JsonOperation;

import SmartNavigationSystem.Tourist;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @Auther: Archer
 */
public class TouristTest {

    /**
     * Success tourist
     * Confirm that the password is entered correct at the first time
     */
    @Test
    public void testtourist_case1() throws IOException {
        new JsonOperation();
        String email="testtourist1";
        String password="testtouristpwd1";
        String input=email+"\n"+password+"\n"+password+"\n0";
        Tourist tourist=new Tourist();
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        tourist.register();

        //check
        boolean res=JsonOperation.checkMemberPwd(email,password);
        assertTrue(res);
    }

    /**
     * Success tourist
     * Confirm that the password is entered correct at the second time
     */
    @Test
    public void testtourist_case2() throws IOException {
        new JsonOperation();
        String email="testtourist2";
        String password="testtouristpwd2";
        String password1="testtouristpwd643";
        String input=email+"\n"+password+"\n"+password1+"\n"+password+"\n0";
        Tourist tourist=new Tourist();
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        tourist.register();

        //check
        boolean res=JsonOperation.checkMemberPwd(email,password);
        assertTrue(res);
    }

    /**
     * Success tourist
     * Confirm that the password is entered correct at the third time
     */
    @Test
    public void testtourist_case3() throws IOException {
        new JsonOperation();
        String email="testtourist3";
        String password="testtouristpwd3";
        String password1="testtouristpwd643";
        String input=email+"\n"+password+"\n"+password1+"\n"+password1+"\n"+password+"\n0";
        Tourist tourist=new Tourist();
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        tourist.register();

        //check
        boolean res=JsonOperation.checkMemberPwd(email,password);
        assertTrue(res);
    }

    /**
     * Fail tourist
     * Confirm that the password is entered correct at the fourth time
     */
    @Test
    public void testtourist_case4() throws IOException {
        new JsonOperation();
        String email="testtouristfail";
        String password="testtouristfailpwd";
        String password1="testtouristpwd643";
        String input=email+"\n"+password+"\n"+password1+"\n"+password1+"\n"+password1+"\n"+"\n0";
        Tourist tourist=new Tourist();
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        tourist.register();

        //check
        String res=JsonOperation.getMemberPassword(email);
        assertNull(res);
    }

}
