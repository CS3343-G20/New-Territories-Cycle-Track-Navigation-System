package SmartNavigationSystem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class Register {
    private String inpEmail, inpPwd;
    private int tryTimes = 0;
    //memberList<email, password>
    public static HashMap<String,String> memberMap=new HashMap<String,String>();
    ControlPanel cp=new ControlPanel();

    public void register(){
        // request user input
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please input email: ");
        this.inpEmail = userInput.next();
        System.out.println("Please input password: ");
        this.inpPwd = userInput.next();
        // confirm password
        confirmPwd(userInput);
        cp.showControlPanel();
        cp.makeDecision();
        userInput.close();
    }

    // confirm password
    public void confirmPwd(Scanner userInput){
        System.out.println("Please input password again: ");
        String inpPwd2 = userInput.next();
        if(isSame(inpPwd2)){
            memberMap.put(this.inpEmail, this.inpPwd);
            writeinFile(this.inpEmail, this.inpPwd);
            System.out.println("Register successfully.");
        }else{
            if(tryTimes<2){
                tryTimes += 1;
                confirmPwd(userInput);
            }else{
                System.out.println("Registration failed!");
            }   
        }
    }

    public void writeinFile(String Email,String Pwd){
        try{
            File memberListFile=new File("docs\\MemberList");
            FileOutputStream fos=null;
            if(!memberListFile.exists()){
                memberListFile.createNewFile();
                fos=new FileOutputStream(memberListFile); //1st write in
            }else{
                fos=new FileOutputStream(memberListFile, true);
            }
            OutputStreamWriter osw=new OutputStreamWriter(fos,"UTF-8");
            osw.write(Email+"  "+Pwd+"\r\n");
            osw.close();
        } catch (IOException e) {
                e.printStackTrace();
        }
    }

    // check if two input passwords are same 
    public boolean isSame(String inpPwd2){
        return this.inpPwd.equals(inpPwd2);
    }
}
