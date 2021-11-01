package SmartNavigationSystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Member extends User {

    protected Login login;

    public Member() {
        this.login = null;
    }

    public Member Login() throws IOException {
        this.login = new Login();
        boolean loginSuccess = login.login();
        if (loginSuccess)
            return this;
        else
            return null;
    }

    public void Cycle() {
        CyclingMode cyclingMode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in));
        cyclingMode.member_execute(this);
    }

    public void CheckInfo() throws FileNotFoundException {
        JsonOperation.printMemberInfo(getEmail());
    }

    public String getEmail() {
        return this.login.getEmail();
    }

    public void resetPwd() throws IOException {
        this.login.resetPwd();
    }
}
