package Java;

public class Vertex {
    private int id;
    private String name;

    public Vertex(String id, String name) {
        this.id = Integer.parseInt(id);
        this.name = name;
    }

    @Override
    public String toString(){
        return String.format("[%d] %s", id, name);
    }

    public String getName() {
        return name;
    }
    
}
