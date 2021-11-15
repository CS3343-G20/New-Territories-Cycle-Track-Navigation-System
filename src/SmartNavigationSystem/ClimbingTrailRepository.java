package SmartNavigationSystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

/*
Singleton class
 */
public class ClimbingTrailRepository implements ClimbingTrailRepoManager, ClimbingTrailsQuerier {
    private TreeSet<ClimbingTrail> climbingTrails;
    private static ClimbingTrailRepository ctr = new ClimbingTrailRepository();
    private static ArrayList<ClimbingTrail> filteredClimbingTrails;

    public static ClimbingTrailRepository getInstance() {
        return ctr;
    }

    private ClimbingTrailRepository() {
        climbingTrails = new TreeSet<>();
        ClimbingTrail[] cts = Constants.ct;
        filteredClimbingTrails = new ArrayList<>();
        for (ClimbingTrail c : cts) {
            climbingTrails.add(c);
        }
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
        climbingTrails = new TreeSet<>();
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
            strB.append(cttmp.displayInformation());
            if (it.hasNext()){
                strB.append("\n");
            }
        }
        return String.valueOf(strB);
    }

    public String filterByDifficulty(int difficulty) {
        filteredClimbingTrails.clear();
        StringBuilder strB = new StringBuilder();
        Iterator<ClimbingTrail> it = climbingTrails.iterator();
        while (it.hasNext()) {
            ClimbingTrail tmp = it.next();
            if (tmp.getDifficulty() == difficulty) {
                strB.append(tmp.displayInformation()).append("\n");
                filteredClimbingTrails.add(tmp);
            }
        }
        return String.valueOf(strB);
    }

    public String filterTrailByDest(String destName) {
        filteredClimbingTrails.clear();
        StringBuilder strB = new StringBuilder();
        Iterator<ClimbingTrail> it = climbingTrails.iterator();
        while (it.hasNext()) {
            ClimbingTrail tmp = it.next();
            if (tmp.getDestinationName().equals(destName)) {
                strB.append(tmp.displayInformation()).append("\n");
                filteredClimbingTrails.add(tmp);
            }
        }
        return String.valueOf(strB);
    }

    public String filterTrailByDeparture(String departureName) {
        filteredClimbingTrails.clear();
        StringBuilder strB = new StringBuilder();
        Iterator<ClimbingTrail> it = climbingTrails.iterator();
        while (it.hasNext()) {
            ClimbingTrail tmp = it.next();
            if (tmp.getDepartureName().equals(departureName)) {
                strB.append(tmp.displayInformation()).append("\n");
                filteredClimbingTrails.add(tmp);
            }
        }
        return String.valueOf(strB);
    }

    @Override
    public String findTrailByID(int pathID) {
        Iterator<ClimbingTrail> it = climbingTrails.iterator();
        while (it.hasNext()) {
            ClimbingTrail tmp = it.next();
            if (Integer.parseInt(tmp.getID()) == pathID) {
                return tmp.displayInformation();
            }
        }
        return null;
    }

    @Override
    public String listDifficulties() {
        String difficulties = "";
        Iterator<ClimbingTrail> it = climbingTrails.iterator();
        while (it.hasNext()) {
            ClimbingTrail tmp = it.next();
            difficulties += tmp.getDifficulty();
            if (it.hasNext() ==true) {
                difficulties += ", ";
            }
        }
        return difficulties;
    }

    @Override
    public String listDepartures() {
        String departures = "";
        Iterator<ClimbingTrail> it = climbingTrails.iterator();
        while (it.hasNext()) {
            ClimbingTrail tmp = it.next();
            departures += tmp.getDepartureName();
            if (it.hasNext() ==true) {
                departures += ", ";
            }
        }
        return departures;
    }

    @Override
    public String listDestinations() {
        String destinations = "";
        Iterator<ClimbingTrail> it = climbingTrails.iterator();
        while (it.hasNext()) {
            ClimbingTrail tmp = it.next();
            destinations += tmp.getDestinationName();
            if (it.hasNext() ==true) {
                destinations += ", ";
            }
        }
        return destinations;
    }

    @Override
    public ArrayList<ClimbingTrail> getFilteredClimbingTrails() {
        return filteredClimbingTrails;
    }

    @Override
    public int getTrailDepartureID(int id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getTrailDestinationName(int id) {
        // TODO Auto-generated method stub
        return null;
    }
}
