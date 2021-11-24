package SmartNavigationSystem;

public class Attraction extends Vertex {
    private String description;
    private String distanceToCycleTrack;

    public Attraction(String id, String name, String distance, String description) {
        super(id, name);
        this.distanceToCycleTrack = distance;
        this.description = description;
    }

    public String getInfo(){
        return String.format("%s (%s to the cycle track)\n    %s\n", super.toString(), distanceToCycleTrack, description);
    }
    
}
