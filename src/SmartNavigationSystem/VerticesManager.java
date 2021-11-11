package SmartNavigationSystem;

import java.util.ArrayList;

public interface VerticesManager {
    public String getVertexNameByID(int id);

    public String getVertexNamesByID(ArrayList<Integer> ids);

    public void listAttractions();

    public void listAllVertices();

    public String getRouteString(ArrayList<Integer> route);

    public void listRoute(ArrayList<Integer> route);

    public int checkVertexIdValidity(int id) throws ExInvalidIndex;

    public int checkAttractionIdValidity(int id) throws ExInvalidIndex;
}