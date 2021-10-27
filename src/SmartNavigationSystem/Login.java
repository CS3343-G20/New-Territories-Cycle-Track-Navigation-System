package SmartNavigationSystem;

import java.io.*;
import java.util.*;

public class Login {
    private String inpEmail, inpPwd;
    private int tryTimes = 0;
    private ControlPanel memberCP = MemberControlPanel.getInstance();
    private Register register = new Register();
    

    public void login() throws IOException {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please input email: ");
        this.inpEmail = userInput.nextLine();

        Boolean emailExist=JsonOperation.checkMemberExist(this.inpEmail);
        if (!emailExist) {
            System.out.println("Would you like to register one?[Y/N]");
            String ans = userInput.nextLine();
            if (ans.equals("Y")) {
                register.register();
            }
        }
        else {
            System.out.println("Please input password: ");
            this.inpPwd = userInput.nextLine();
            verifyPwd(this.inpEmail, this.inpPwd);
        }
        //userInput.close();
    }

    public boolean verifyPwd(String email, String pwd) throws FileNotFoundException {
        return JsonOperation.checkMemberPwd(email, pwd);
    }


    // reset password
    public void resetPwd() throws FileNotFoundException {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Would you like to reset your password?[yes/no]");
        String ans = userInput.nextLine();
        if (ans.equals("yes")) {
            System.out.println("Please input new password: ");
            this.inpPwd = userInput.next();
            JsonOperation.resetPwd(this.inpEmail, this.inpPwd);
            System.out.println("Reset successfully!");
        }
        //userInput.close();
    }

    public String getEmail() {
        return this.inpEmail;
    }
}
