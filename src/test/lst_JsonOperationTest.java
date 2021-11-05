package test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
                //
        }


}