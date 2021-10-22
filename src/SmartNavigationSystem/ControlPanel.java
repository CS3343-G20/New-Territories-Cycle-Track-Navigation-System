package SmartNavigationSystem;

import java.io.IOException;
import java.util.*;

public abstract class ControlPanel {
    public HashMap<Integer, String> controlPanel= new HashMap<Integer, String>();

    public User user;

    ControlPanel(){ 
        this.user=new User();
        // controlPanel.put(1,"Login");
        // controlPanel.put(2,"Register");
        // controlPanel.put(3,"Mode");
        // controlPanel.put(0,"Exit");
    }
    
    // public void removeControlPanel(Integer i){
    //     controlPanel.remove(i);
    // }

    public User getMember() {
        return this.user;
    }

    public void setMember(Member m) {
        this.user = m;
    }


    public void showControlPanel(){
        System.out.println("------------------------------");
        for(int nav: controlPanel.keySet()){
            System.out.printf("%s: %s \n",nav, controlPanel.get(nav));
        }
        System.out.println("-------------------------------");
    }

    public abstract int makeDecision(Scanner in) throws IOException;
    // public void makeDecision(){
    //     Scanner userInput=new Scanner(System.in);
        
    //     int nav;
    //     do{
    //         System.out.println("Please input a num:[select from ControlPanel]");
    //         nav=Utility.getIntegerInput(userInput);
    //     }while(!controlPanel.keySet().contains(nav));

    //     switch(nav){
    //         case 0:
    //              break;
    //         case 1:
    //             Member m=new Member();
    //             m.Login();
    //             break;
    //         case 2:    
    //             Register r=new Register();
    //             r.register();
    //             break;
    //         case 3:
    //             User u=new User();
    //             System.out.println("Please choose a mode:[CyclingMode/ClimbingMode]");
    //             String mode=userInput.next();
    //             u.chooseMode(mode);
    //         //case 4:
               
    //         default:
    //            //
    //     };
        
    //     userInput.close();
    // }

}
