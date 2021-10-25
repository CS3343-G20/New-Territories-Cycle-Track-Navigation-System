package SmartNavigationSystem;

import java.io.IOException;
import java.util.Scanner;

public class Member extends User{
    public void Login() throws IOException{
        Login login = new Login();
        login.login();
    }
    public void Cycle(){ 
        CyclingMode cyclingMode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in));
        cyclingMode.execute();
    }
    public void CheckInfo() {
        //
    }
}
