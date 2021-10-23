package code;

public class ClimbingTrail implements Comparable<ClimbingTrail> {
    private String id;
    private int difficulty;
    private String destination_name;
    private String departure_name;
    private String region;

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
        return this.getDifficulty() - ct2.getDifficulty();
    }

    public String getID() {
        return id;
    }
}
