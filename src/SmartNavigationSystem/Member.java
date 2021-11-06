package SmartNavigationSystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Member extends User{

    private Login login;

    public Member() {
        this.login = null;
    }

    public boolean Login() throws IOException{
        this.login = new Login();
        return login.login();
    }
    public void Cycle(){ 
        CyclingMode cyclingMode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in));
        cyclingMode.execute();
    }
    public String CheckInfo() throws FileNotFoundException {
        return JsonOperation.printMemberInfo(getEmail());
    }
    public String getEmail() {
        return this.login.getEmail();
    }

    public void resetPwd() throws IOException {
        this.login.resetPwd();
    }
}
