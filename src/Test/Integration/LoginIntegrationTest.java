package Test.Integration;

import SmartNavigationSystem.JsonOperation;
import SmartNavigationSystem.Login;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import static org.junit.Assert.*;

public class LoginIntegrationTest {

    private final String email = "cs3343g20system@gmail.com";
    private final String wrongEmail = "wrong" + email;
    private final String pwd = "pwd";
    private final String wrongPwd = "wrong" + pwd;
    private final String newPwd = "new" + pwd;
    private final String unmatchedEmail = "cs3343g20system@163.com";
    
    private String fileString;

    @Before
    public void setUp() throws IOException {
        File f = new File("docs/MemberInfo.json");
        Scanner fin = new Scanner(f);
        fileString = fin.useDelimiter("\\Z").next();
        fin.close();
    }

    @Test
    public void testLogin_case1() throws IOException {

        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
        pw.close();

        new JsonOperation();

        String input = email + "\n" + pwd;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Login login = new Login();
        int res = login.login(new Scanner(System.in));
        assertEquals(1, res);
        
    }

    @Test
    public void testLogin_case2() throws IOException {

        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
        pw.close();

        new JsonOperation();

        String input = unmatchedEmail + "\n" + email + "\n" + pwd;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Login login = new Login();
        int res = login.login(new Scanner(System.in));
        assertEquals(1, res);
        
    }

    @Test
    public void testLogin_case3() throws IOException {

        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
        pw.close();

        new JsonOperation();

        String input = unmatchedEmail + "\n" + unmatchedEmail + "\n" + unmatchedEmail + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Login login = new Login();
        int res = login.login(new Scanner(System.in));
        assertEquals(0, res);
        
    }

    @Test
    public void testLogin_case4() throws IOException {
    	
        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[]}");
        pw.close();
        
        new JsonOperation();

        String input = wrongEmail + "\nN";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Login login = new Login();
        int res = login.login(new Scanner(System.in));
        assertEquals(0, res);
        
    }

    @Test
    public void testLogin_case5() throws IOException {
    	
        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[]}");
        pw.close();
        
        new JsonOperation();

        String input = email + "\nNxxx\n" + email + "\nN";
        //System.setIn(new ByteArrayInputStream(input.getBytes()));

        Login login = new Login();
        int res = login.login(new Scanner(input));
        assertEquals(0, res);
        
    }

    @Test
    public void testLogin_case6() throws IOException {

        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
        pw.close();

        new JsonOperation();

        String input = email + "\n" + wrongPwd + "\n" + pwd;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Login login = new Login();
        int res = login.login(new Scanner(System.in));
        assertEquals(1, res);
        
    }

    @Test
    public void testLogin_case7() throws IOException {

        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
        pw.close();

        new JsonOperation();

        String input = email + "\n" + wrongPwd + "\n" + wrongPwd;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Login login = new Login();
        int res = login.login(new Scanner(System.in));
        assertEquals(0, res);
        
    }

    @Test
    public void testLogin_case8() throws IOException {

        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[]}");
        pw.close();

        new JsonOperation();

        String input = email + "\nY\n" + email + "\n" + pwd + "\n" + pwd;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Login login = new Login();
        int res = login.login(new Scanner(System.in));
        assertEquals(2, res);
        
    }

    @Test
    public void verifyPwd_case1() throws FileNotFoundException {
    	
        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
        pw.close();

        new JsonOperation();

        Login login = new Login();

        assertTrue(login.verifyPwd(email, pwd));
    }

    @Test
    public void verifyPwd_case2() throws FileNotFoundException {
    	
        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
        pw.close();

        new JsonOperation();

        Login login = new Login();

        assertFalse(login.verifyPwd(email, wrongPwd));
    }

    @Test
    public void resetPwd_case1() throws IOException {
    	
        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
        pw.close();

        new JsonOperation();

        Login login = new Login();
        login.login(new Scanner(email + "\n" + pwd));

        login.resetPwd(new Scanner("yes\n" + newPwd + "\n" + newPwd));

        assertEquals(newPwd, JsonOperation.getMemberPassword(email));
    }

    @Test
    public void resetPwd_case2() throws IOException {
    	
        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
        pw.close();

        new JsonOperation();

        Login login = new Login();
        login.login(new Scanner(email + "\n" + pwd));

        login.resetPwd(new Scanner("no"));

        assertEquals(pwd, JsonOperation.getMemberPassword(email));
    }
    
    @Test
    public void resetPwd_case3() throws IOException {
    	
        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
        pw.close();

        new JsonOperation();

        Login login = new Login();
        login.login(new Scanner(email + "\n" + pwd));

        login.resetPwd(new Scanner("yes\n" + newPwd + "\n" + pwd));

        assertEquals(pwd, JsonOperation.getMemberPassword(email));
    }
    
    @Test
    public void resetPwd_case4() throws IOException {
    	
        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
        pw.close();

        new JsonOperation();

        Login login = new Login();
        login.login(new Scanner(email + "\n" + pwd));

        login.resetPwd(new Scanner("xxx"));

        assertEquals(pwd, JsonOperation.getMemberPassword(email));
    }

    @Test
    public void getEmail_case1() throws IOException {
    	
        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
        pw.close();

        new JsonOperation();

        Login login = new Login();
        login.login(new Scanner(email + "\n" + pwd));

        assertEquals(email, login.getEmail());
    }

    @After
    public void restore() throws FileNotFoundException {
    	
        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write(fileString);
        pw.close();

    }
}
