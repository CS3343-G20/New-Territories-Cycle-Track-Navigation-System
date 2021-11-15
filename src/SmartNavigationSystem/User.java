package SmartNavigationSystem;

import java.util.Scanner;

public class User {
    protected Mode mode = null;

    public void chooseMode(Scanner userInput) {
        System.out.println("Please choose a mode:[1-CyclingMode/2-ClimbingMode]");
        boolean isChosen = false;
        while (!isChosen) {
            try {
                int modeIndex = Integer.parseInt(userInput.nextLine());
                if (modeIndex == 1) {
                    mode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(),
                    userInput, Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
                }
                else if (modeIndex == 2) {
                    mode = new ClimbingMode();
                }
                executeMode();
                isChosen = true;
            }
            catch (NumberFormatException e) {
                System.out.println(new ExWrongNumberFormat().getMessage());
            }
            catch (NullPointerException e) {
                System.out.println(new ExInvalidIndex().getMessage());
            }
        }
    }

    public void executeMode() {
        mode.execute();
    }
}
