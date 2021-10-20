package SmartNavigationSystem;

public class Member extends User{
    public void Login(){
        Login login = new Login();
        login.login();
    }
    public void Cycle(){ //enter into cycling mode
        CyclingMode cyclingMode = new CyclingMode();
        cyclingMode.execute();
    }
}
