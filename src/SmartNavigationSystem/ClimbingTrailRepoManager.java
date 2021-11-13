package SmartNavigationSystem;

import java.util.ArrayList;

public interface ClimbingTrailRepoManager {
	String filterByDifficulty(int difficulty);

	String filterTrailByDest(String destName);

	String filterTrailByDeparture(String departureName);

	String findTrailByID(int pathID);

	String sort(int order);

	String list();

	String listDestinations();

	String listDepartures();
	
	String listDifficulties();
	
	ArrayList<ClimbingTrail> getFilteredClimbingTrails();
}
