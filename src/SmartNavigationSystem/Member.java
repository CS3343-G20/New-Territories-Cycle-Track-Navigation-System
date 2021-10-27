package SmartNavigationSystem;

import java.io.IOException;
import java.util.Scanner;

public class Member extends User{

    private Login login;

    public Member() {
        this.login = null;
    }

    public void Login() throws IOException{
        this.login = new Login();
        login.login();
    }
    public void Cycle(){ 
        CyclingMode cyclingMode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in));
        cyclingMode.execute();
    }
    public void CheckInfo() {
        //
    }
    public String getEmail() {
        return this.login.getEmail();
    }
}
