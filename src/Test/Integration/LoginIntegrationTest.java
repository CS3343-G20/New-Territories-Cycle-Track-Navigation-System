package Test.Integration;

import SmartNavigationSystem.JsonOperation;
import SmartNavigationSystem.Login;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class LoginIntegrationTest {

private final String email = "cs3343g20system@gmail.com";
private final String wrongEmail = "wrong" + email;
private final String pwd = "pwd";
private final String wrongPwd = "wrong" + pwd;
private final String newPwd = "new" + pwd;
private final String unexistEmail = "fakeEmail@gmail.com";
private final String unmatchedEmail ="fakeEmail@163.com";

@Before
public void setUp() throws IOException {
    new JsonOperation();
		JSONObject obj = JsonOperation.getMemberInfo(email);
		if (obj == null) {
			JsonOperation.addNewMember(email, pwd);
		} else {
			JsonOperation.resetPwd(email, pwd);
		}
}

@Test
public void testLogin_case1() throws IOException {

    String input=email+"\n"+pwd;
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    Login login=new Login();
    int res=login.login(new Scanner(System.in));
    assertEquals(1,res);
}

@Test
public void testLogin_case3() throws IOException {
    String no="N";
    String input=wrongEmail+"\n"+no+"\n"+email+"\n"+pwd;
    System.setIn(new ByteArrayInputStream(input.getBytes()));

    Login login=new Login();
    int res=login.login(new Scanner(System.in));
    assertEquals(0,res);
}

@Test
public void testLogin_case4() throws IOException {
    String input=email+"\n"+wrongPwd+"\n"+email+"\n"+pwd;;
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    Login login=new Login();
    int res=login.login(new Scanner(System.in));
    assertEquals(0,res);

}

// please clear this email from memberInfo first
@Test
public void testLogin_case5() throws IOException {
    String input=unexistEmail+"\nY\n"+unexistEmail+"\n"+pwd+"\n"+pwd;;
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    Login login=new Login();
    int res=login.login(new Scanner(System.in));
    assertEquals(2,res);
}

//please clear this email from memberInfo first
@Test
public void testLogin_case6() throws IOException {
 String input=unmatchedEmail+"\n"+email+"\n"+pwd+"\n"+pwd;;
 System.setIn(new ByteArrayInputStream(input.getBytes()));
 Login login=new Login();
 int res=login.login(new Scanner(System.in));
 assertEquals(1,res);
}


@Test
public void testVerifyPwd_case1() throws IOException {
    Login login=new Login();
    String input=email+"\n"+pwd;
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    int res=login.login(new Scanner(System.in));
    assertEquals(1,res);
    login.verifyPwd(email,pwd);
}

@Test
public void testVerifyPwd_case2() throws IOException {
    Login login=new Login();
    boolean res=login.verifyPwd(email,wrongPwd);
    assertFalse(res);
}

@Test
public void testResetPwd_case1() throws IOException {
    String input=email+"\n"+pwd+"\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    Login login=new Login();
    int loginSuccess=login.login(new Scanner(System.in));
    assertEquals(1,loginSuccess);
    System.setIn(new ByteArrayInputStream("no".getBytes()));
    login.resetPwd(new Scanner(System.in));

    //check:The password has not changed
    new JsonOperation();
    boolean res=JsonOperation.checkMemberPwd(email,pwd);
    assertTrue(res);
}

@Test
public void testResetPwd_case2() throws IOException {
    //login
    String input=email+"\n"+pwd+"\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    Login login=new Login();
    int loginSuccess=login.login(new Scanner(System.in));
    assertEquals(1,loginSuccess);

    //reset
    String resetInput="yes"+"\n"+newPwd+"\n"+newPwd;
    System.setIn(new ByteArrayInputStream(resetInput.getBytes()));
    login.resetPwd(new Scanner(System.in));

    //check:The password has not changed
    boolean resFalse=JsonOperation.checkMemberPwd(email,pwd);
    boolean resTrue=JsonOperation.checkMemberPwd(email,newPwd);
    assertFalse(resFalse);
    assertTrue(resTrue);
}

@Test
public void testResetPwd_case3() throws IOException {
    //login
    String input=email+"\n"+pwd+"\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    Login login=new Login();
    int loginSuccess=login.login(new Scanner(System.in));
    assertEquals(1,loginSuccess);

    //not same
    String resetInput="yes"+"\n"+newPwd+"\n"+pwd;
    System.setIn(new ByteArrayInputStream(resetInput.getBytes()));
    login.resetPwd(new Scanner(System.in));

    //check:The password has not changed
    boolean resTrue=JsonOperation.checkMemberPwd(email,pwd);
    boolean resFalse=JsonOperation.checkMemberPwd(email,newPwd);
    assertFalse(resFalse);
    assertTrue(resTrue);
}

@Test
public void testResetPwd_case4() throws IOException {
    //login
    String input=email+"\n"+pwd+"\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    Login login=new Login();
    int loginSuccess=login.login(new Scanner(System.in));
    assertEquals(1,loginSuccess);

    //not same
    String resetInput="xxx";
    System.setIn(new ByteArrayInputStream(resetInput.getBytes()));
    login.resetPwd(new Scanner(System.in));

    //check:The password has not changed
    boolean resTrue=JsonOperation.checkMemberPwd(email,pwd);
    boolean resFalse=JsonOperation.checkMemberPwd(email,newPwd);
    assertFalse(resFalse);
    assertTrue(resTrue);
}
}

