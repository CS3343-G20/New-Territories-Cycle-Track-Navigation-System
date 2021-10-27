package SmartNavigationSystem;

import java.util.Iterator;
import java.util.TreeSet;

/*
Singleton class
 */
public class ClimbingTrailRepository {
    private TreeSet<ClimbingTrail> climbingTrails;
    private static ClimbingTrailRepository ctr;

    public static ClimbingTrailRepository getInstance() {
        return ctr;
    }

    private ClimbingTrailRepository() {
        climbingTrails = new TreeSet<>();
    }

    public String sort(int order) {
        String resStr = list();
        if (order == 0) {
            return resStr;
        } else if (order == 1) {
            StringBuilder strC = new StringBuilder();
            Iterator it = climbingTrails.descendingIterator();
            while (it.hasNext()) {
                strC.append(((ClimbingTrail) it).displayInformation()).append("\n");
                it.next();
            }
            return String.valueOf(strC);
        }
        return null;
    }

    public void addClimbingTrail(ClimbingTrail ct) {
        climbingTrails.add(ct);
    }

    public void deleteClimbingTrail(ClimbingTrail ct) {
        climbingTrails.remove(ct);
    }

    public String list() {
        StringBuilder strB = new StringBuilder();
        Iterator it = climbingTrails.iterator();
        while (it.hasNext()) {
            strB.append(((ClimbingTrail) it).displayInformation()).append("\n");
            it.next();
        }
        return String.valueOf(strB);
    }

    public String filterByDifficulty(int difficulty) {
        StringBuilder strB = new StringBuilder();
        Iterator it = climbingTrails.iterator();
        while (it.hasNext()) {
            ClimbingTrail tmp = (ClimbingTrail) it;
            if (tmp.getDifficulty() == difficulty) {
                strB.append(tmp.displayInformation()).append("\n");
            }
            it.next();
        }
        return String.valueOf(strB);
    }

    public String filterTrailByDest(String destName) {
        StringBuilder strB = new StringBuilder();
        Iterator it = climbingTrails.iterator();
        while (it.hasNext()) {
            ClimbingTrail tmp = (ClimbingTrail) it;
            if (tmp.getDestinationName().equals(destName)) {
                strB.append(tmp.displayInformation()).append("\n");
            }
            it.next();
        }
        return String.valueOf(strB);
    }

    public String filterTrailByDeparture(String departureName) {
        StringBuilder strB = new StringBuilder();
        Iterator it = climbingTrails.iterator();
        while (it.hasNext()) {
            ClimbingTrail tmp = (ClimbingTrail) it;
            if (tmp.getDepartureName().equals(departureName)) {
                strB.append(tmp.displayInformation()).append("\n");
            }
            it.next();
        }
        return String.valueOf(strB);
    }

    public String findTrailByID(String id) {
        Iterator it = climbingTrails.iterator();
        while (it.hasNext()) {
            ClimbingTrail tmp = (ClimbingTrail) it;
            if (tmp.getID().equals(id)) {
                return tmp.displayInformation();
            }
        }
        return null;
    }


}
