package SmartNavigationSystem;

import java.util.*;

public class Admin {
    // create new key pair for control panel
    public void addFunction(){
        Scanner adminInput=new Scanner(System.in);
        ControlPanel cp=new ControlPanel();

        System.out.println("Please input a pair function: ");
        int symbol=Integer.parseInt(adminInput.next()) ;
        String function=adminInput.next();
        cp.setControlPanel(symbol, function);

        adminInput.close();
    }
}
