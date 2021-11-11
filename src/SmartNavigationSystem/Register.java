package SmartNavigationSystem;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Register {
    private String inpEmail, inpPwd;
    private int tryTimes = 0;
    ControlPanel cp = UserControlPanel.getInstance();

    public void register() throws IOException {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please input email: ");
        this.inpEmail = userInput.next();

        String pattern = "[a-zA-Z0-9_-]+@gmail.com$";
        int flag = 1;
        while (!Pattern.matches(pattern, inpEmail) && flag <3) {
            System.out.println("Please input a correct email address!");
            System.out.println("Please input email: ");
            this.inpEmail = userInput.nextLine();
            flag ++;
        }
        if (flag == 3){
            System.out.println("Registration failed!");
        } else{
            System.out.println("Please input password: ");
            this.inpPwd = userInput.next();
            confirmPwd(userInput);
        }
    }

    public void confirmPwd(Scanner userInput) throws IOException {
        System.out.println("Please input password again: ");
        String inpPwd2 = userInput.next();
        if (this.inpPwd.equals(inpPwd2)) {
            writeinFile(this.inpEmail, this.inpPwd);
            System.out.println("Register successfully.");
        } else {
            if (tryTimes < 2) {
                tryTimes += 1;
                confirmPwd(userInput);
            } else {
                System.out.println("Registration failed!");
            }
        }
    }

    public void writeinFile(String email, String password) throws IOException {
        JsonOperation.addNewMember(email, password);
    }
}
