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

    @Override
    public void chooseMode(String mode){
        if (mode.equals(Mode.ClimbingMode.toString())) {
            //
        }
        else if (mode.equals(Mode.CyclingMode.toString())) {
            CyclingMode cyclingMode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), new Scanner(System.in));
            cyclingMode.member_execute(this);
        }
    }
    
    public void deleteBookmark(int bookmarkIndex) throws IOException {
    	Bookmark.deleteBookmark(this, bookmarkIndex);
    }
    
    public void deleteSchedule(int scheduleIndex) throws IOException {
    	Schedule.deleteSchedule(this, scheduleIndex);
    }

    public void makeSchedule(String mode, String date) throws IOException {
    	Schedule.makeSchedule(mode, date, this);
    }

}
