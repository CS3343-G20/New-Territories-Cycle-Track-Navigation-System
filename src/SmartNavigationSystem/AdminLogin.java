package SmartNavigationSystem;

import java.io.*;
import java.util.Scanner;

public class AdminLogin {
    private int flag=0;

    public boolean login(){
        Scanner in = new Scanner(System.in);
        System.out.println("Please input the admin token:");
        String inpToken=in.nextLine();
        File f = new File("G20\\docs\\AdminInfo");
        try {
            boolean valid = isValid(f,inpToken);
       
            while(!valid){
                if(flag<3){
                    System.out.println("Wrong Token input!");
                    flag++;
                    System.out.println("Please input the admin token:");
                    inpToken=in.nextLine();
                    valid = isValid(f,inpToken);
                }else{
                    System.out.print("Login Failed");
                    return false;
                }
            } 
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            in.close();
        }
        return true;
    }

    private boolean isValid(File f, String inpToken) throws FileNotFoundException {
        Scanner scan = new Scanner(f);
        while(true){
            if(scan.hasNext()==false) break;
             String[] AdminInfo = scan.nextLine().split("\\s+");
            if(AdminInfo[0].equals(inpToken)){
                scan.close();
                return true;
            }
        }
        scan.close();
        return false;
    }
}
