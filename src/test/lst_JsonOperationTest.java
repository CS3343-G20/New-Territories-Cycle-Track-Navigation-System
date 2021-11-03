package test;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import SmartNavigationSystem.JsonOperation;

public class lst_JsonOperationTest {
	
	@Test
	public void getMemberInfo_case1() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
        pw.close();
        
        String email = "cs3343g20system@gmail.com";
        
        new JsonOperation();
        JSONObject res = JsonOperation.getMemberInfo(email);
        
        JSONObject orig = new JSONObject();
        orig.put("bookmarks", new String[] { });
        orig.put("password", "pwd"); 
        orig.put("schedules", new String[] { });
        orig.put("email", "cs3343g20system@gmail.com");
        
        System.out.println(res);
        System.out.println(orig);
            
        //assertEquals(orig, res);
        //assertEquals(res, orig);
        //assertEquals("str", "str");
	}
	
}