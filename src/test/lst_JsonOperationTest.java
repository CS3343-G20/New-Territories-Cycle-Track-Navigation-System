package test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import SmartNavigationSystem.JsonOperation;
import SmartNavigationSystem.Member;

public class lst_JsonOperationTest {

        private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final InputStream originalIn = System.in;

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	} 

        @Test
        public void getMemberInfo_case1() throws FileNotFoundException {
                PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
                pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
                pw.close();

                String email = "cs3343g20system@gmail.com";

                new JsonOperation();
                JSONObject res = JsonOperation.getMemberInfo(email);

                JSONObject orig = new JSONObject();
                orig.put("bookmarks", new String[] {});
                orig.put("password", "pwd");
                orig.put("schedules", new String[] {});
                orig.put("email", "cs3343g20system@gmail.com");

                assertEquals(orig.toJSONString(), res.toJSONString());

        }

        @Test
        public void getMemberInfo_case2() throws FileNotFoundException {
                PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
                pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
                pw.close();

                String email = "system@gmail.com";

                new JsonOperation();
                JSONObject res = JsonOperation.getMemberInfo(email);

                assertEquals(null, res);

        }

        @Test
        public void getWholeObjectString_case1() throws FileNotFoundException {
                PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
                pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
                pw.close();

                new JsonOperation();

                String expected = "{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}";
                String actual = JsonOperation.getWholeObjectString();
                assertEquals(expected, actual);
        }

        @Test
        public void getMemberScheArray_case1() throws FileNotFoundException {
                PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
                pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[{\"key\":\"value\"}],\"email\":\"cs3343g20system@gmail.com\"}]}");
                pw.close();

                String email = "cs3343g20system@gmail.com";

                new JsonOperation();
                String res = JsonOperation.getMemberScheArray(email).toJSONString();

                assertEquals("[{\"key\":\"value\"}]", res);
        }

        @Test
        public void getMemberBookmArray_case1() throws FileNotFoundException {
                PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
                pw.write("{\"memberInfo\":[{\"bookmarks\":[{\"key\":\"value\"}],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
                pw.close();

                String email = "cs3343g20system@gmail.com";

                new JsonOperation();
                String res = JsonOperation.getMemberBookmArray(email).toJSONString();

                assertEquals("[{\"key\":\"value\"}]", res);
        }

        @Test
        public void getMemberPassword_case1() throws FileNotFoundException {
                PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
                pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
                pw.close();

                String email = "cs3343g20system@gmail.com";

                new JsonOperation();
                String res = JsonOperation.getMemberPassword(email);

                assertEquals("pwd", res);
        }

        @Test
        public void getResetPassword_case1() throws IOException {
                PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
                pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
                pw.close();

                String email = "cs3343g20system@gmail.com";
                String newPwd = "newPwd";

                new JsonOperation();
                JsonOperation.resetPwd(email, newPwd);
                String res = JsonOperation.getMemberPassword(email);

                assertEquals("newPwd", res);
        }

        @Test
        public void clearJsonFile_case1() throws IOException {

                PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
                pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
                pw.close();
                new JsonOperation();
                JsonOperation.clearJsonFile();
                File f = new File("docs\\MemberInfo.json");
                assertEquals(0, f.length());

        }

        @Test
        public void addNewMember_case1() throws IOException {
                PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
                pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
                pw.close();
                new JsonOperation();
                JsonOperation.addNewMember("test email", "test password");
                String expected = "{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"},{\"bookmarks\":[],\"password\":\"test password\",\"schedules\":[],\"email\":\"test email\"}]}";
                String actual = JsonOperation.getWholeObjectString();
                assertEquals(expected, actual);
        }

        @Test
        public void checkMemberExist_case1 () throws FileNotFoundException {
                PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
                pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
                pw.close();
                new JsonOperation();
                boolean actual = JsonOperation.checkMemberExist("cs3343g20system@gmail.com");

                assertEquals(true, actual);                
        }

        @Test
        public void checkMemberExist_case2 () throws FileNotFoundException {
                PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
                pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
                pw.close();
                new JsonOperation();
                boolean actual = JsonOperation.checkMemberExist("system@gmail.com");

                assertEquals(false, actual);                
        }

        @Test
        public void checkMemberPwd_case1() throws FileNotFoundException {
                PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
                pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
                pw.close();
                new JsonOperation();
                boolean actual = JsonOperation.checkMemberExist("cs3343g20system@gmail.com");
                assertEquals(true, actual);
        }

        @Test
        public void checkMemberPwd_case2() throws FileNotFoundException {
                PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
                pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
                pw.close();
                new JsonOperation();
                boolean actual = JsonOperation.checkMemberExist("system@gmail.com");
                assertEquals(false, actual);
        }

        @Test
        public void addNewSchedule_case1() throws IOException {
		
                PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
                pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
                pw.close();
                new JsonOperation();

		class Stub_Member extends Member {

			@Override
			public String getEmail() {
				return "cs3343g20system@gmail.com";
			}
		}
		
		Stub_Member m = new Stub_Member();

                new JsonOperation();
		JsonOperation.addNewSchedule(m, "2021/11/6", "mode");

                String actual = JsonOperation.getWholeObjectString();
                assertEquals("expected", actual);

        }

        @Test
        public void deleteMemberSchedule_case1() throws IOException {
                
                PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
                pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
                pw.close();
                new JsonOperation();

		class Stub_Member extends Member {

			@Override
			public String getEmail() {
				return "cs3343g20system@gmail.com";
			}
		}
		
		Stub_Member m = new Stub_Member();

                new JsonOperation();
                JsonOperation.deleteMemberSchedule(m, 0);

                String actual = JsonOperation.getWholeObjectString();
                assertEquals("expected", actual);
        }

        @Test
        public void getAdminToken_case() throws FileNotFoundException {
                String actual = JsonOperation.getAdminToken();
                assertEquals("CS3343G20", actual);
        }

        @Test
        public void printMemberList() throws FileNotFoundException {

                PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
                pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
                pw.close();
                new JsonOperation();

                JsonOperation.printMemberList();
                assertEquals("expected", outContent);

        }

        @Test
        public void sendEmailToAllMembers() {
                //
        }

        
	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setIn(originalIn);
	}
}