package SmartNavigationSystem;

public interface ClimbingTrailRepoManager {
	String filterByDifficulty(int difficulty);

	String filterTrailByDest(String destName);

	String filterTrailByDeparture(String departureName);

	String findTrailByID(int pathID);

	String sort(int order);

	String list();
}
