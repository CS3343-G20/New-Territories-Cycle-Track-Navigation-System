package code;

public class ClimbingTrail implements Comparable<ClimbingTrail> {
    private String id;
    private int difficulty;
    private String destination_name;
    private String departure_name;

    public ClimbingTrail(String id, int difficulty, String destination_name, String departure_name) {
    	this.id = id;
    	this.difficulty = difficulty;
    	this.destination_name = destination_name;
    	this.departure_name = departure_name;
    }
    public String displayInformation() {
        return "The climbing trail with id " + id + ", difficulty " + difficulty + ", destination " + destination_name + ", departure_name " + departure_name;
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

    @Override
    public int compareTo(ClimbingTrail ct2) {
    	if (this.getDifficulty()!=ct2.getDifficulty()) {
        return this.getDifficulty() - ct2.getDifficulty();
    	}
    	else {
    		return this.getID().compareTo(ct2.getID());
    	}
    }

    public String getID() {
        return id;
    }
}
