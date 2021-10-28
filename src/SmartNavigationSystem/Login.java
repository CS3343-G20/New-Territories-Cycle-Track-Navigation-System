package SmartNavigationSystem;

import java.io.*;
import java.util.*;

public class Login {
    private String inpEmail, inpPwd;
    private int tryTimes = 0;
    private ControlPanel memberCP = MemberControlPanel.getInstance();
    private Register register = new Register();

    public boolean login() throws IOException {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please input email: ");
        this.inpEmail = userInput.nextLine();

        Boolean emailExist = JsonOperation.checkMemberExist(this.inpEmail);
        if (!emailExist) {
            System.out.println("Would you like to register one?[Y/N]");
            String ans = userInput.nextLine();
            if (ans.equals("Y")) {
                register.register();
            }
        } else {
            System.out.println("Please input password: ");
            this.inpPwd = userInput.nextLine();
            return verifyPwd(this.inpEmail, this.inpPwd);
        }
        // userInput.close();
        return false;
    }

    public boolean verifyPwd(String email, String pwd) throws FileNotFoundException {
        return JsonOperation.checkMemberPwd(email, pwd);
    }

    // reset password
    public void resetPwd() throws IOException {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Would you like to reset your password?[yes/no]");
        String ans = userInput.nextLine();
        if (ans.equals("yes")) {
            System.out.println("Please input new password: ");
            this.inpPwd = userInput.next();
            System.out.println("Please input your new password again: ");
            String inPwd2 = userInput.next();
            if (inpPwd.equals(inPwd2)) {
                JsonOperation.resetPwd(this.inpEmail, this.inpPwd);
                System.out.println("Reset successfully!");
            } else {
                System.out.println("Resetting failed!");
            }
        }
        // userInput.close();
    }

    public String getEmail() {
        return this.inpEmail;
    }
}
