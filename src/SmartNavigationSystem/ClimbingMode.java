package code;

/*
This is a singleton class
 */
public class ClimbingMode {
    private static ClimbingMode cm = new ClimbingMode();
    private ClimbingTrailRepository ctr;

    public static ClimbingMode getInstance() {
        return cm;
    }

    private ClimbingMode() {
        ctr = ClimbingTrailRepository.getInstance();
    }

    public void listTrails() {
        ctr.list();
    }


    public void findTrailsByDifficulty(int difficulty){
        ctr.filterByDifficulty(difficulty);
    }

    public void cycleToStartingPoint(String trail_id){





    }

    public void findTrailsByStartingPoint(String name){
        System.out.println(ctr.filterTrailByDeparture(name));


    }
    public void findTrailsByDestination(String name){
        System.out.println(ctr.filterTrailByDest(name));
    }
    public void chooseClimbingPath() {


    }


}
