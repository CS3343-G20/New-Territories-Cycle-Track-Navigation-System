package SmartNavigationSystem;

public class ClimbingTrail implements Comparable<ClimbingTrail> {
    private int id;
    private int difficulty;
    private int departure_id;
    private String destination_name;
    private String departure_name;

    public ClimbingTrail(int id, int difficulty, int departure_id, String departure_name, String destination_name) {
    	this.id = id;
    	this.difficulty = difficulty;
        this.departure_id = departure_id;
        this.departure_name = departure_name;
    	this.destination_name = destination_name;
    }
    public String displayInformation() {
        return String.format("%-10s%-12s%-14s%s", "["+id+"]", difficulty, departure_name, destination_name);
    }

    public String fullInformation() {
        return String.format("%s (difficulty %s) %s -> %s", "["+id+"]", difficulty, departure_name, destination_name);
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getDestinationName() {
        return destination_name;
    }

    public String getDepartureName() {
        return departure_name;
    }

    public int getDepartureID(){return departure_id;}
    @Override
    public int compareTo(ClimbingTrail ct2) {
    	if (this.getDifficulty()!=ct2.getDifficulty()) {
            return this.getDifficulty() - ct2.getDifficulty();
    	}
    	else {
    		return this.id-ct2.id;
    	}
    }

    public int getID() {
        return id;
    }
}
