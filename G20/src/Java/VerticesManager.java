package Java;

import java.util.ArrayList;

public interface VerticesManager {
    public String getVertexNameByID(int id);
    public String getVertexNamesByID(ArrayList<Integer> ids);
    public void listAttractions();
    public void listAllVertices();
    public void listRoute(ArrayList<Integer> route);
    public boolean isValidVertexId(int id);
    public boolean isValidAttractionId(int id);
}
