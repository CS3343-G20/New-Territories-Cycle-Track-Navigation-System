package SmartNavigationSystem;

import java.io.IOException;
import java.util.*;

public abstract class ControlPanel {
    public HashMap<Integer, String> controlPanel= new HashMap<Integer, String>();

    public void showControlPanel(){
        System.out.println("------------------------------");
        for(int nav: controlPanel.keySet()){
            System.out.printf("%s: %s \n",nav, controlPanel.get(nav));
        }
        System.out.println("-------------------------------");
    }

    public abstract int makeDecision() throws IOException;

}
