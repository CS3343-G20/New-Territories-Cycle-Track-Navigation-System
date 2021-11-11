package SmartNavigationSystem;

import java.util.Scanner;

public class User {
    enum Mode {
        CyclingMode, ClimbingMode
    }

    public void chooseMode(String mode) {
        if (mode.equals(Mode.ClimbingMode.toString())) {
           //
        } else if (mode.equals(Mode.CyclingMode.toString())) {
            CyclingMode cyclingMode = new CyclingMode(Graph.getInstance(), Vertices.getInstance(),
                    new Scanner(System.in), Bookmark.getInstance());
            cyclingMode.execute();
        }
    }
}
