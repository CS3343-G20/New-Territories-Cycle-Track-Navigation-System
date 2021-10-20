package SmartNavigationSystem;
public class User {
    enum Mode{
        CyclingMode,
        ClimbingMode
    }
    public void chooseMode(String mode){
        if (mode.equals(Mode.ClimbingMode.toString())) {
            //
        }
        else if (mode.equals(Mode.CyclingMode.toString())) {
            CyclingMode cyclingMode = new CyclingMode();
            cyclingMode.execute();
        }
    }
}
