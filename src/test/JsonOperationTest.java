package test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.mail.MessagingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import SmartNavigationSystem.ExInvalidIndex;
import SmartNavigationSystem.JsonOperation;
import SmartNavigationSystem.Member;
import SmartNavigationSystem.ScheduleDate;

public class JsonOperationTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final InputStream originalIn = System.in;
	
	private String fileString;

	@Before
	public void setUp() throws FileNotFoundException {
		
	    System.setOut(new PrintStream(outContent));
	    
        File f = new File("docs/MemberInfo.json");
        Scanner fin = new Scanner(f);
        fileString = fin.useDelimiter("\\Z").next();
        fin.close();
	    
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
                boolean actual = JsonOperation.checkMemberPwd("cs3343g20system@gmail.com", "pwd");
                assertEquals(true, actual);
        }

        @Test
        public void checkMemberPwd_case2() throws FileNotFoundException {
                PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
                pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
                pw.close();
                new JsonOperation();
                boolean actual = JsonOperation.checkMemberPwd("cs3343g20system@gmail.com", "wrong pwd");
                assertEquals(false, actual);
        }

        @Test
        public void addNewSchedule_case1() throws IOException {
		
                PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
                pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
                pw.close();

		class Stub_Member extends Member {

			@Override
			public String getEmail() {
				return "cs3343g20system@gmail.com";
			}
		}
		
		Stub_Member m = new Stub_Member();

                new JsonOperation();
		JsonOperation.addNewSchedule(m, "2021/11/6", "Cycling Mode: null");

                String actual = JsonOperation.getWholeObjectString();
                String expected = "{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[{\"scheduleIndex\":1,\"scheduleDate\":\"2021/11/6\",\"state\":\"true\",\"event\":\"Cycling Mode: null\"}],\"email\":\"cs3343g20system@gmail.com\"}]}";
                assertEquals(expected, actual);

        }
        
        @Test
        public void addNewBookmark_case1() throws IOException {
        	
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
    		
    		JsonOperation.addNewBookmark("route", m);
    		
            String actual = JsonOperation.getWholeObjectString();
            String expected = "{\"memberInfo\":[{\"bookmarks\":[{\"bookmarkIndex\":1,\"bookmarkType\":\"route\"}],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}";
            assertEquals(expected, actual);
        	
        }

        @Test
        public void deleteMemberSchedule_case1() throws IOException, ExInvalidIndex {
                
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
                JsonOperation.deleteMemberSchedule(m, 1);

                String actual = JsonOperation.getWholeObjectString();
                assertEquals("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}", actual);
        }
        
        @Test
        public void deleteMemberSchedule_case2() throws IOException, ExInvalidIndex {
                
                PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
                pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[{\"scheduleIndex\":1,\"scheduleDate\":\"2021/11/6\",\"state\":\"true\",\"event\":\"Cycling Mode: null\"},{\"scheduleIndex\":2,\"scheduleDate\":\"2021/11/6\",\"state\":\"true\",\"event\":\"Cycling Mode: null\"}],\"email\":\"cs3343g20system@gmail.com\"}]}");
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
                JsonOperation.deleteMemberSchedule(m, 1);

                String actual = JsonOperation.getWholeObjectString();
                assertEquals("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[{\"scheduleIndex\":1,\"scheduleDate\":\"2021/11/6\",\"state\":\"true\",\"event\":\"Cycling Mode: null\"}],\"email\":\"cs3343g20system@gmail.com\"}]}", actual);
        }
        
        @Test
        public void deleteMemberSchedule_case3() throws IOException, ExInvalidIndex {
            
            PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
            pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[{\"scheduleIndex\":1,\"scheduleDate\":\"2021/11/6\",\"state\":\"true\",\"event\":\"Cycling Mode: null\"}],\"email\":\"cs3343g20system@gmail.com\"}]}");
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
            JsonOperation.deleteMemberSchedule(m, 7);

            String actual = JsonOperation.getWholeObjectString();
            assertEquals("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[{\"scheduleIndex\":1,\"scheduleDate\":\"2021/11/6\",\"state\":\"true\",\"event\":\"Cycling Mode: null\"}],\"email\":\"cs3343g20system@gmail.com\"}]}", actual);

        }

        @Test
        public void deleteMemberBookmark_case1() throws IOException, ExInvalidIndex {
                
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
                JsonOperation.deleteMemberBookmark(m, 1);

                String actual = JsonOperation.getWholeObjectString();
                assertEquals("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}", actual);
        }
        
        @Test
        public void deleteMemberBookmark_case2() throws IOException, ExInvalidIndex {
                
                PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
                pw.write("{\"memberInfo\":[{\"bookmarks\":[{\"bookmarkIndex\":1,\"bookmarkType\":\"Cycling Mode: Sha Tin Che Kung Temple -> Hong Kong Heritage Museum\n\"},{\"bookmarkIndex\":2,\"bookmarkType\":\"Cycling Mode: Sha Tin Che Kung Temple -> Hong Kong Heritage Museum\n\"}],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
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
                JsonOperation.deleteMemberBookmark(m, 1);

                String actual = JsonOperation.getWholeObjectString();
                assertEquals("{\"memberInfo\":[{\"bookmarks\":[{\"bookmarkIndex\":1,\"bookmarkType\":\"Cycling Mode: Sha Tin Che Kung Temple -> Hong Kong Heritage Museum\\n\"}],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}", actual);
        }

        @Test
        public void deleteMemberBookmark_case3() throws IOException, ExInvalidIndex {
                
                PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
                pw.write("{\"memberInfo\":[{\"bookmarks\":[{\"bookmarkIndex\":1,\"bookmarkType\":\"Cycling Mode: Sha Tin Che Kung Temple -> Hong Kong Heritage Museum\n\"}],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
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
                JsonOperation.deleteMemberBookmark(m, 7);

                String actual = JsonOperation.getWholeObjectString();
                assertEquals("{\"memberInfo\":[{\"bookmarks\":[{\"bookmarkIndex\":1,\"bookmarkType\":\"Cycling Mode: Sha Tin Che Kung Temple -> Hong Kong Heritage Museum\\n\"}],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}", actual);
        }

        @Test 
        public void printMemberInfo_case1() throws FileNotFoundException {
        	
            PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
            pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
            pw.close();
            new JsonOperation();

            JsonOperation.printMemberInfo("cs3343g20system@gmail.com");
    		String expected = "Email: cs3343g20system@gmail.com\nPassword: pwd\n\nYou haven't make schedules.\n\nYou haven't add bookmarks.\n\n";
    		
    		assertEquals(expected, outContent.toString());

        	
        }
        
        @Test
        public void printMemberSchedule_case1() throws FileNotFoundException {
        	
            PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
            pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[{\"scheduleIndex\":1,\"scheduleDate\":\"2021/11/6\",\"state\":\"true\",\"event\":\"Cycling Mode: null\"}],\"email\":\"cs3343g20system@gmail.com\"}]}");
            pw.close();
            new JsonOperation();

            JsonOperation.printMemberSchedule("cs3343g20system@gmail.com");
    		String expected = "You have 1 schedules:\n\nSchedule index: 1\nEvent: Cycling Mode: null\nSchedule date: 2021/11/6\n\n";
    		
    		assertEquals(expected, outContent.toString());
            
        }

        @Test
        public void printMemberSchedule_case2() throws FileNotFoundException {
        	
            PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
            pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
            pw.close();
            new JsonOperation();

            JsonOperation.printMemberSchedule("cs3343g20system@gmail.com");
    		String expected = "You haven't make schedules.\n\n";
    		
    		assertEquals(expected, outContent.toString());
            
        }

        @Test
        public void printMemberBookmark_case1() throws FileNotFoundException {
        	
            PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
            pw.write("{\"memberInfo\":[{\"bookmarks\":[{\"bookmarkIndex\":1,\"bookmarkType\":\"Cycling Mode: null\n\"}],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
            pw.close();
            new JsonOperation();

            JsonOperation.printMemberBookmark("cs3343g20system@gmail.com");
    		String expected = "You have 1 bookmarks:\n\nBookmark index: 1\nType: Cycling Mode: null\n\n";
    		
    		assertEquals(expected, outContent.toString());
            
        }

        @Test
        public void printMemberBookmark_case2() throws FileNotFoundException {
        	
            PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
            pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[],\"email\":\"cs3343g20system@gmail.com\"}]}");
            pw.close();
            new JsonOperation();

            JsonOperation.printMemberBookmark("cs3343g20system@gmail.com");
    		String expected = "You haven't add bookmarks.\n\n";
    		
    		assertEquals(expected, outContent.toString());
            
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
                assertEquals("Member list:\ncs3343g20system@gmail.com\n", outContent.toString());

        }

        @Test
        public void printScheduleList_case1() throws FileNotFoundException {
        	
            PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
            pw.write("{\"memberInfo\":[{\"bookmarks\":[{\"bookmarkIndex\":1,\"bookmarkType\":\"Cycling Mode: Sha Tin Che Kung Temple -> Hong Kong Heritage Museum -> Chui Tin Street Soccer Pitch\\n\"}],\"password\":\"pwd\",\"schedules\":[{\"scheduleIndex\":1,\"scheduleDate\":\"2021/11/6\",\"state\":\"true\",\"event\":\"Cycling Mode: null\"},{\"scheduleIndex\":2,\"scheduleDate\":\"2021/11/06\",\"state\":\"true\",\"event\":\"Cycling Mode: Cycling Mode: Sha Tin Che Kung Temple -> Hong Kong Heritage Museum -> Chui Tin Street Soccer Pitch\\n\"}],\"email\":\"cs3343g20system@gmail.com\"},{\"bookmarks\":[],\"password\":\"test password\",\"schedules\":[],\"email\":\"test email\"}]}");
            pw.close();
            new JsonOperation();

            JsonOperation.printScheduleList();
            String actual = outContent.toString();
            
            String expected = "Schedule list:\n"
            		+ "Email                         ScheduleIndex     Date           Sent     Event\n"
            		+ "cs3343g20system@gmail.com     1                 2021/11/6      true     Cycling Mode: null\n"
            		+ "cs3343g20system@gmail.com     2                 2021/11/06     true     Cycling Mode: Cycling Mode: Sha Tin Che Kung Temple -> Hong Kong Heritage Museum -> Chui Tin Street Soccer Pitch\n"
            		+ "\n";
            assertEquals(expected, actual);

        	
        }

        @Test
        public void printBookmarkList_case1() throws FileNotFoundException {
        	
            PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
            pw.write("{\"memberInfo\":[{\"bookmarks\":[{\"bookmarkIndex\":1,\"bookmarkType\":\"Cycling Mode: Sha Tin Che Kung Temple -> Hong Kong Heritage Museum -> Chui Tin Street Soccer Pitch\n\"}],\"password\":\"pwd\",\"schedules\":[{\"scheduleIndex\":1,\"scheduleDate\":\"2021/11/6\",\"state\":\"true\",\"event\":\"Cycling Mode: null\"},{\"scheduleIndex\":2,\"scheduleDate\":\"2021/11/06\",\"state\":\"true\",\"event\":\"Cycling Mode: Cycling Mode: Sha Tin Che Kung Temple -> Hong Kong Heritage Museum -> Chui Tin Street Soccer Pitch\n\"}],\"email\":\"cs3343g20system@gmail.com\"},{\"bookmarks\":[],\"password\":\"test password\",\"schedules\":[],\"email\":\"test email\"}]}");
            pw.close();
            new JsonOperation();

            JsonOperation.printBookmarkList();
            
            String expected = "Bookmark list:\n"
            		+ "Email                         BookmarkIndex     Type\n"
            		+ "cs3343g20system@gmail.com     1                 Cycling Mode: Sha Tin Che Kung Temple -> Hong Kong Heritage Museum -> Chui Tin Street Soccer Pitch\n\n";
            
            assertEquals(expected, outContent.toString());

        	
        }

        @Test
        public void sendEmailToAllMembers() throws FileNotFoundException, MessagingException {
                
        	String date = ScheduleDate.getTomorrowDate();
        	
            PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
            pw.write("{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[{\"scheduleIndex\":1,\"scheduleDate\":\"" + date + "\",\"state\":\"true\",\"event\":\"Cycling Mode: null\"},{\"scheduleIndex\":2,\"scheduleDate\":\"" + date + "\",\"state\":\"true\",\"event\":\"Cycling Mode: Cycling Mode: Sha Tin Che Kung Temple -> Hong Kong Heritage Museum -> Chui Tin Street Soccer Pitch\\n\"}],\"email\":\"cs3343g20system@gmail.com\"}]}");
            pw.close();
            new JsonOperation();

        	JsonOperation.sendEmailToAllMembers();
        	
        	String actual = JsonOperation.getWholeObjectString();
        	String expected = "{\"memberInfo\":[{\"bookmarks\":[],\"password\":\"pwd\",\"schedules\":[{\"scheduleIndex\":1,\"scheduleDate\":\"" + date + "\",\"state\":\"false\",\"event\":\"Cycling Mode: null\"},{\"scheduleIndex\":2,\"scheduleDate\":\"" + date + "\",\"state\":\"false\",\"event\":\"Cycling Mode: Cycling Mode: Sha Tin Che Kung Temple -> Hong Kong Heritage Museum -> Chui Tin Street Soccer Pitch\\n\"}],\"email\":\"cs3343g20system@gmail.com\"}]}";
        	
        	assertEquals(expected, actual);
        }
        
	@After
	public void restore() throws FileNotFoundException {
		
	    System.setOut(originalOut);
	    System.setIn(originalIn);
	    
        PrintWriter pw = new PrintWriter("docs/MemberInfo.json");
        pw.write(fileString);
        pw.close();
        

	}
}