package SmartNavigationSystem;

import java.io.*;
import java.util.Scanner;

public class AdminLogin {

    private int flag = 0;

    public boolean login() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please input the admin token:");
        String inpToken = in.nextLine();
        try {
            boolean valid = inpToken.equals(JsonOperation.getAdminToken());

            while (!valid) {
                if (flag < 3) {
                    System.out.println("Wrong Token input!");
                    flag++;
                    System.out.println("Please input the admin token again:");
                    inpToken = in.nextLine();
                    valid = inpToken.equals(JsonOperation.getAdminToken());
                } else {
                    System.out.print("Login failed too many times.\nExiting...\n");
                    return false;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            // in.close();
        }
        return true;
    }
}
