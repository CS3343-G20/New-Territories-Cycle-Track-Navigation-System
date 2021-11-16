package SmartNavigationSystem;

import java.io.IOException;
import java.util.*;

public abstract class ControlPanel {
    public HashMap<Integer, String> controlPanel= new HashMap<Integer,String>();

    public void showControlPanel(){
        System.out.print("------------------------------\n");
        for(int nav: controlPanel.keySet()){
            System.out.printf("%s: %s\n",nav, controlPanel.get(nav));
        }
        System.out.print("------------------------------\n");
    }

    public abstract int makeDecision(Scanner in) throws IOException;

}
