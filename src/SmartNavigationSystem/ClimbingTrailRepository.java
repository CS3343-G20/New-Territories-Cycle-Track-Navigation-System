package SmartNavigationSystem;

import java.util.Iterator;
import java.util.TreeSet;

/*
Singleton class
 */
public class ClimbingTrailRepository implements ClimbingTrailRepoManager{
    private TreeSet<ClimbingTrail> climbingTrails;
    private static ClimbingTrailRepository ctr = new ClimbingTrailRepository();

    public static ClimbingTrailRepository getInstance() {
        return ctr;
    }

    private  ClimbingTrailRepository() {
    	climbingTrails  =new TreeSet<>();
    }

    public String sort(int order) {
        String resStr = list();
        if (order == 0) {
            return resStr;
        } else if (order == 1) {
            StringBuilder strC = new StringBuilder();
            Iterator<ClimbingTrail> it = climbingTrails.descendingIterator();
            while (it.hasNext()) {
            	ClimbingTrail ct = it.next();
                strC.append(ct.displayInformation()).append("\n");
            }
            return String.valueOf(strC);
        }
        return null;
    }
    public void clearClimbingTrails() {
    	climbingTrails= new TreeSet<>();
    }

    public void addClimbingTrail(ClimbingTrail ct) {
        climbingTrails.add(ct);
    }

    public void deleteClimbingTrail(ClimbingTrail ct) {
        climbingTrails.remove(ct);
    }

    public String list() {
        StringBuilder strB = new StringBuilder();
        Iterator<ClimbingTrail> it = climbingTrails.iterator();
        while (it.hasNext()) {
            ClimbingTrail cttmp = it.next();

            strB.append(cttmp.displayInformation()).append("\n");
        }
        return String.valueOf(strB);
    }

    public String filterByDifficulty(int difficulty) {
        StringBuilder strB = new StringBuilder();
        Iterator<ClimbingTrail> it = climbingTrails.iterator();
        while (it.hasNext()) {
            ClimbingTrail tmp = it.next();
            if (tmp.getDifficulty() == difficulty) {
                strB.append(tmp.displayInformation()).append("\n");
            }
        }
        return String.valueOf(strB);
    }
    
    public String filterTrailByDest(String destName) {
        StringBuilder strB = new StringBuilder();
        Iterator <ClimbingTrail> it = climbingTrails.iterator();
        while (it.hasNext()) {
            ClimbingTrail tmp = it.next();
            if (tmp.getDestinationName().equals(destName)) {
                strB.append(tmp.displayInformation()).append("\n");
            }
        }
        return String.valueOf(strB);
    }

    public String filterTrailByDeparture(String departureName) {
        StringBuilder strB = new StringBuilder();
        Iterator <ClimbingTrail>it = climbingTrails.iterator();
        while (it.hasNext()) {
            ClimbingTrail tmp = it.next();
            if (tmp.getDepartureName().equals(departureName)) {
                strB.append(tmp.displayInformation()).append("\n");
            }
        }
        return String.valueOf(strB);
    }
    @Override
    public String findTrailByID(String id) {
        Iterator<ClimbingTrail> it = climbingTrails.iterator();
        while (it.hasNext()) {
            ClimbingTrail tmp = it.next();
            if (tmp.getID().equals(id)) {
                return tmp.displayInformation();
            }
        }
        return null;
    }

    //-----------new --------------------------------------------------------------------------------
//     @Override
//     public String findTrailByID(int pathID) {
        
//         return null;
//     }


}
