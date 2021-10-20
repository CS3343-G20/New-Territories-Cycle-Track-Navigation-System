package SmartNavigationSystem;

import java.util.*;

public class ControlPanel {
    public static HashMap<Integer, String> controlPanel= new HashMap<Integer, String>();

    ControlPanel(){ 
        controlPanel.put(1,"Login");
        controlPanel.put(2,"Register");
        controlPanel.put(3,"Mode");
        // controlPanel.put(4,"Cycle Trails");// cycling mode
        controlPanel.put(0,"Exit");
    }
    
    public void removeControlPanel(Integer i){
        controlPanel.remove(i);
    }

    public void setControlPanel(Integer i, String function){
        // add new function
        controlPanel.put(i,function);
    }

    public void showControlPanel(){
        for(int nav: controlPanel.keySet()){
            System.out.printf("%s: %s \n",nav, controlPanel.get(nav));
        }
    }

    public void makeDecision(){
        Scanner userInput=new Scanner(System.in);
        
        int nav;
        do{
            System.out.println("Please input a num:[select from ControlPanel]");
            nav=Utility.getIntegerInput(userInput);
        }while(!controlPanel.keySet().contains(nav));

        switch(nav){
            case 0:
                 break;
            case 1:
                Member m=new Member();
                m.Login();
                break;
            case 2:    
                Register r=new Register();
                r.register();
                break;
            case 3:
                User u=new User();
                System.out.println("Please choose a mode:[CyclingMode/ClimbingMode]");
                String mode=userInput.next();
                u.chooseMode(mode);
            //case 4:
               
            default:
               //
        };
        
        userInput.close();
    }
}
