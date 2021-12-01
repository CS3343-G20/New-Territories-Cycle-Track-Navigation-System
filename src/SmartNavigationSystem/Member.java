package SmartNavigationSystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Member extends User {

    protected Login login;
    private String routeString;

    public Member() {
        this.login = null;
        this.routeString = null;
    }

    public Member Login(Scanner userInput) throws IOException {
        this.login = new Login();
        int loginStatus = login.login(userInput);
        if (loginStatus == 1)
            return this;
        else if (loginStatus == 2) 
        	System.out.println("Please re-login after registration.");
        else
            System.out.print("Login failed. Exiting...\n\n");
        return null;
    }
 
    public void CheckInfo() throws FileNotFoundException {
        JsonOperation.printMemberInfo(getEmail());
    }

    public String getEmail() {
        return this.login.getEmail();
    }

    public void resetPwd(Scanner userInput) throws IOException {
        this.login.resetPwd(userInput);
    }

    @Override
    public void executeMode() {
        mode.memberExecute(this);
    }

    public void deleteBookmark(Scanner userInput) throws IOException {
        boolean hasBookm = JsonOperation.printMemberBookmark(getEmail());
        if (!hasBookm)
            return;
        System.out.println("Please input the index of bookmark that you want to delete:");
        boolean isChosen = false;
        while (!isChosen) {
            try {
                int bookmarkIndex = Integer.parseInt(userInput.nextLine());
                Bookmark.getInstance().deleteBookmark(this, bookmarkIndex);
                isChosen = true;
            }
            catch (NumberFormatException e) {
                System.out.println(new ExWrongNumberFormat().getMessage());
            }
            catch (ExInvalidIndex e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void deleteSchedule(Scanner userInput) throws IOException {
        boolean hasSche = JsonOperation.printMemberSchedule(getEmail());
        if (!hasSche)
            return;
        System.out.println("Please input the index of schedule that you want to delete:");
        boolean isChosen = false;
        while (!isChosen) {
            try {
                int scheduleIndex = Integer.parseInt(userInput.nextLine());
                Schedule.deleteSchedule(this, scheduleIndex);
                isChosen = true;
            }
            catch (NumberFormatException e) {
                System.out.println(new ExWrongNumberFormat().getMessage());
            }
            catch (ExInvalidIndex e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void makeSchedule(Scanner userInput) throws IOException, ExInvalidIndex {
        chooseMode(userInput);
        System.out.println("Please input the schedule date: [yyyy/mm/dd]");
        boolean isChosen = false;
        while (!isChosen) {
            try{
            String date = userInput.nextLine();
			if (!ScheduleDate.isValidDate(date)) {
                    throw new ExInvalidDate();
			}
			Schedule.makeSchedule(this.routeString, date, this);
			isChosen = true;
        } catch (ExInvalidDate e) {
        	System.out.println(e.getMessage());
        }
        }
    }

    public void setRoute(String routeString) {
        this.routeString = routeString;
    }


}