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
        File f=new File("docs\\MemberList");
        Boolean exist=isExist(f, this.inpEmail);
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

    public boolean isExist(File f, String Email) {
        Scanner scan = null;
        try {
            scan = new Scanner(f);
            while(true) {
                if(scan.hasNext()==false) break;
                if(scan.nextLine().contains(Email)) {
                    return true;
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if (scan != null) {
                scan.close();
            }
        }
        return false;
    }

    public boolean isPwdCorrect(File f){
        Scanner scan = null;
        try {
            scan = new Scanner(f);
            while (true) {
                if(scan.hasNext()==false) break;
                String[] memberInfo = scan.nextLine().split("\\s+");
                String email = memberInfo[0];
                String password = memberInfo[1];
                if(email.equals(this.inpEmail) && password.equals(this.inpPwd)) {
                    return true;
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if (scan != null) {
                scan.close();
            }
        }
        return false;
    }

    public void modifyPwd(String newPwd, String inpEmail, File f){
        try {
            Scanner scan = new Scanner(f);
            while (true) {
                if(scan.hasNext()==false) break;
                String[] memberInfo = scan.nextLine().split("\\s+");
                String email = memberInfo[0];
                String password = memberInfo[1];
                if(email.equals(this.inpEmail)){
                    memberInfo[1].replace(password, newPwd);
                }
            } 
            scan.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void verifyPwd(Scanner userInput) {
        File f = new File("docs\\MemberList");
        Boolean pwd = isPwdCorrect(f);
        if (pwd) {
            System.out.println("Log in successfully");
            // cp.removeControlPanel(1);
            // cp.removeControlPanel(2);
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
            } else {
                resetPwd();
                memberCP.showControlPanel();
                try {
                    memberCP.makeDecision(userInput);
                } catch (IOException e) {
                    e.printStackTrace();
                }            }
        }

    }

    // reset password
    public void resetPwd() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Would you like to reset your password?[yes/no]");
        String ans = userInput.nextLine();
        if (ans.equals("yes")) {
            System.out.println("Please input new password: ");
            this.inpPwd = userInput.next();
            File f=new File("docs\\MemberList");
            modifyPwd(this.inpPwd,this.inpEmail,f);
            System.out.println("Reset successfully!");
        }
        userInput.close();
    }
}
