package SmartNavigationSystem;

import java.io.*;
import java.util.Scanner;
import org.json.*;

public class AdminLogin {
    private int flag=0;

    public boolean login() throws IOException{
        Scanner in = new Scanner(System.in);
        System.out.println("Please input the admin token:");
        String inpToken=in.nextLine();

        try {
            JSONObject obj = JsonParser();
            boolean valid = isValid(inpToken);
       
            while(!valid){
                if(flag<3){
                    System.out.println("Wrong Token input!");
                    flag++;
                    System.out.println("Please input the admin token:");
                    inpToken=in.nextLine();
                    valid = isValid(inpToken);
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

    public JSONObject JsonParser() throws IOException{

        char cbuf[] = new char[10000];
        InputStreamReader input =new InputStreamReader(new FileInputStream(new File("docs//AdminInfo.json")),"UTF-8");
        int len =input.read(cbuf);
        String text =new String(cbuf,0,len);
        JSONObject obj = new JSONObject(text.substring(text.indexOf("{")));

        return obj;
    }

    private boolean isValid(String inpToken) throws IOException {
        JSONObject obj = JsonParser();
        if(obj.getString("token").equals(inpToken)){
            return true;
        }
        return false;
    }
}
