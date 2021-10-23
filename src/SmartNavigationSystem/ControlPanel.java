package SmartNavigationSystem;

import java.io.IOException;
import java.util.*;

public abstract class ControlPanel {
    public HashMap<Integer, String> controlPanel= new HashMap<Integer, String>();

    public User user;

    ControlPanel(){ 
        // this.user = new User();
        this.user=new Tourist();
    }
    

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

}
