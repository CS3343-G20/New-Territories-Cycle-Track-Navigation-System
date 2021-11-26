package SmartNavigationSystem;

import java.io.*;
import java.util.Scanner;

public class AdminLogin {

    private static int flag = 0;

    public static boolean login(Scanner in) {
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
                    flag = 0;
                    return false;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            // in.close();
        }
        flag = 0;
        return true;
    }
}
