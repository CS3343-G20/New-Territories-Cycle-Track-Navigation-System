package SmartNavigationSystem;

import java.io.*;
import java.util.*;

public class Register {
    private String inpEmail, inpPwd;
    private int tryTimes = 0;
    ControlPanel cp = UserControlPanel.getInstance();

    public void register() throws IOException {
        // request user input
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please input email: ");
        this.inpEmail = userInput.next();
        System.out.println("Please input password: ");
        this.inpPwd = userInput.next();
        // confirm password
        confirmPwd(userInput);
        cp.showControlPanel();
        cp.makeDecision(userInput);
        //userInput.close();
    }

    // confirm password
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
