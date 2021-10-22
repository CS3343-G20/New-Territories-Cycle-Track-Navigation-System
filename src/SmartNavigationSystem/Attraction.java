package Java;

public class Attraction extends Vertex {
    private String description;
    private String distance;

    public Attraction(String id, String name, String description, String distance) {
        super(id, name);
        this.description = description;
        this.distance = distance;
    }

    public String getInfo(){
        return String.format("%s %s\n%s\n", super.toString(), distance, description);
    }
    
}
