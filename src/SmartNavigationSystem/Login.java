package SmartNavigationSystem;

import java.io.*;
import java.util.*; 
import org.json.*;

public class Login {
    private String inpEmail, inpPwd;
    private int tryTimes = 1;
    private ControlPanel memberCP = MemberControlPanel.getInstance();
    private Register register = new Register();
    

    public void login() throws IOException {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please input email: ");
        this.inpEmail = userInput.nextLine();
        Boolean exist=isExist(this.inpEmail);
        if (!exist) {
            System.out.println("Would you like to register one?[Y/N]");
            String ans = userInput.nextLine();
            if (ans.equals("Y")) {
                register.register();
            }
        }
        else {
            System.out.println("Please input password: ");
            this.inpPwd = userInput.nextLine();
            verifyPwd(userInput);
        }
        userInput.close();
    }
    

    public JSONArray JsonParser() throws IOException{

        char cbuf[] = new char[10000];
        InputStreamReader input =new InputStreamReader(new FileInputStream(new File("docs//MemberInfo.json")),"UTF-8");
        int len =input.read(cbuf);
        String text =new String(cbuf,0,len);
        //1.create a json object
        JSONObject obj = new JSONObject(text.substring(text.indexOf("{")));
        //2.get json array
        JSONArray arr= obj.getJSONArray("memberInfo");

        // System.out.println(arr.toString());
        return arr;
    }

    public boolean isExist(String Email) throws IOException {
        JSONArray arr= JsonParser();
        for(int i=0;i<arr.length();i++){
            JSONObject subObj = arr.getJSONObject(i);
            if(subObj.getString("email").equals(Email)){
                return true;
            }
        }
        return false;
    }

    public boolean isPwdCorrect() throws IOException{
        JSONArray arr= JsonParser();
        for(int i=0;i<arr.length();i++){
            JSONObject subObj = arr.getJSONObject(i);
            if(subObj.getString("email").equals(this.inpEmail) && subObj.getString("password").equals(this.inpPwd)){
                return true;
            }
        }
        return false;
    }

    public void verifyPwd(Scanner userInput) throws IOException {
        Boolean pwd = isPwdCorrect();
        if (pwd) {
            System.out.println("Log in successfully");
            memberCP.showControlPanel(); 
            try {
                memberCP.makeDecision(userInput);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            // can try three times
            if (tryTimes < 3) {
                tryTimes += 1;
                System.out.println("Wrong email/password!");
                try {
                    login();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                resetPwd();
                memberCP.showControlPanel();
                try {
                    memberCP.makeDecision(userInput);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // reset password
    public void resetPwd() throws IOException {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Would you like to reset your password?[yes/no]");
        String ans = userInput.nextLine();
        if (ans.equals("yes")) {
            System.out.println("Please input new password: ");
            this.inpPwd = userInput.next();
            modifyPwd(this.inpPwd,this.inpEmail);
            System.out.println("Reset successfully!");
        }
        userInput.close();
    }

    public void modifyPwd(String newPwd, String inpEmail) throws IOException{
        JSONArray arr= JsonParser();
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("docs\\MemberInfo.json"),"UTF-8");
        for(int i=0;i<arr.length();i++){
            JSONObject subObj = arr.getJSONObject(i);
            if(subObj.getString("email").equals(this.inpEmail)){
                subObj.put("password", newPwd);
                break;
            }
        }

        String newJsonData = "{\n"+"\"memberInfo\""+":\n"+arr.toString()+"\n}";
        osw.write(newJsonData);
        osw.flush();//clear buffer
        osw.close();
    }
}
