package Test.Integration;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import SmartNavigationSystem.JsonOperation;
import SmartNavigationSystem.Register;

public class RegisterIntegrationTest {
	   @Test
	    public void testRegister_case1() throws IOException {
	        new JsonOperation();
	        String email="testregister1@gmail.com";
	        String password="testregisterpwd1";
	        String input=email+"\n"+password+"\n"+password+"\n0";
	        Register register=new Register();
	        System.setIn(new ByteArrayInputStream(input.getBytes()));
	        register.register(new Scanner(System.in));

	        //check
	        boolean res=JsonOperation.checkMemberPwd(email,password);
	        assertTrue(res);
	    }

	    @Test
	    public void testRegister_case2() throws IOException {
	        new JsonOperation();
	        String email="testregister2@gmail.com";
	        String password="testregisterpwd2";
	        String password1="testregisterpwd643";
	        String input=email+"\n"+password+"\n"+password1+"\n"+password+"\n0";
	        Register register=new Register();
	        System.setIn(new ByteArrayInputStream(input.getBytes()));
	        register.register(new Scanner(System.in));

	        //check
	        Object res=JsonOperation.getMemberInfo(email);
	        assertNotNull(res);
	    }

	    @Test
	    public void testRegister_case3() throws IOException {
	        new JsonOperation();
	        String email="testregister2@gmail.com";
	        String password="testregisterpwd3";
	        String password1="testregisterpwd643";
	        String input=email+"\n"+password+"\n"+password1+"\n"+password1+"\n"+password+"\n0";
	        Register register=new Register();
	        System.setIn(new ByteArrayInputStream(input.getBytes()));
	        register.register(new Scanner(System.in));

	        //check
	        Object res=JsonOperation.getMemberInfo(email);
	        assertNotNull(res);
	    }

	    @Test
	    public void testRegister_case4() throws IOException {
	        new JsonOperation();
	        String email="testregister@gmail.com";
	        String password="testregisterfailpwd";
	        String password1="testregisterpwd643";
	        String input=email+"\n"+password+"\n"+password1+"\n"+password1+"\n"+password1+"\n"+"\n0";
	        Register r=new Register();
	        System.setIn(new ByteArrayInputStream(input.getBytes()));
	        r.register(new Scanner(System.in));

	        //check
	        Object res=JsonOperation.getMemberInfo(email);
	        assertNull(res);
	    }
	    
	    
}
