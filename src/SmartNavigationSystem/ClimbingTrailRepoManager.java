package code;

public interface ClimbingTrailRepoManager {
	String filterByDifficulty(int difficulty);

	String filterTrailByDest(String destName);

	String filterTrailByDeparture(String departureName);

	String findTrailByID(String ID);

	String sort(int order);

	String list();
}
