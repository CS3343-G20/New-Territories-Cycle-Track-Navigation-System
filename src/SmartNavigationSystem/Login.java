package SmartNavigationSystem;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Login {
    protected String inpEmail, inpPwd;
    protected Register register = new Register();

    public int login(Scanner userInput) throws IOException { // 0 for login failing, 1 for login successfully, 2 for register successfully but need to re-login
        System.out.println("Please input email: ");
        this.inpEmail = userInput.nextLine();

        String pattern = "[a-zA-Z0-9_-]+@gmail.com$";
        int flag = 1;
        while (!Pattern.matches(pattern, inpEmail) && flag <3) {
            System.out.println("Please input a correct email address!");
            System.out.println("Please input email: ");
            this.inpEmail = userInput.nextLine();
            flag ++;
        }
        if (flag == 3){
            return 0;
        }

        Boolean emailExist = JsonOperation.checkMemberExist(this.inpEmail);
        if (!emailExist) {
            System.out.println("Account doesn't exist. Would you like to register one?[Y/N]");
            String ans = userInput.nextLine();
            if (ans.equals("Y")) {
                register.register(userInput);
                return 2;
            } else if (!ans.equals("N")) {
                System.out.println("Input error. Please login again.");
                login(userInput);
            }
        } else {
            System.out.println("Please input password: ");
            this.inpPwd = userInput.nextLine();
            boolean verification = verifyPwd(this.inpEmail, this.inpPwd);
            if (!verification) {
                System.out.println("Password error. Please try again.");
            	this.inpPwd = userInput.nextLine();
            	verification = verifyPwd(this.inpEmail, this.inpPwd);
            	if (verification) {
            		System.out.println("Login successfully!");
            		return 1;
            	} else {
            		return 0;
            	}
            } else {
            	System.out.println("Login successfully!");
            	return 1;
            }
        }
        // userInput.close();
        return 0;
    }
    

    public boolean verifyPwd(String email, String pwd) throws FileNotFoundException {
        return JsonOperation.checkMemberPwd(email, pwd);
    }

    // reset password
    public void resetPwd(Scanner userInput) throws IOException {
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
        } else if (!ans.equals("no")) {
            System.out.println("Input error. Exiting...");
        }
    }

    public String getEmail() {
        return this.inpEmail;
    }
}