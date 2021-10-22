package SmartNavigationSystem;

import java.io.IOException;

public class Member extends User{
    public void Login() throws IOException{
        Login login = new Login();
        login.login();
    }
    public void Cycle(){ 
        CyclingMode cyclingMode = new CyclingMode();
        cyclingMode.execute();
    }
    public void CheckInfo() {
        //
    }
}
