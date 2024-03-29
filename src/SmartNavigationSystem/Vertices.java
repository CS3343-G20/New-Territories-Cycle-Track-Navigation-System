package SmartNavigationSystem;

import java.util.ArrayList;
import java.util.TreeMap;

public class Vertices implements VerticesManager, VerticesFinder {

    private TreeMap<Integer, Vertex> allVertices = new TreeMap<>();
    private static Vertices instance = new Vertices();

    private Vertices() {
        for (String[] info : Constants.vertices_info) {
            if (info.length == 4) {
                allVertices.put(Integer.valueOf(info[0]), new Attraction(info[0], info[1], info[2], info[3]));
            } else {
                allVertices.put(Integer.valueOf(info[0]), new Vertex(info[0], info[1]));
            }

        }
    }

    public static Vertices getInstance() {
        return instance;
    }

    @Override
    public String getVertexNameByID(int id) {
        return allVertices.get(id).getName();
    }

    @Override
    public String getVertexNamesByID(ArrayList<Integer> ids) {
        String result = "";
        for (int i = 0; i < ids.size(); i++) {
            if (i > 0) {
                result += ", ";
            }
            result += getVertexNameByID(ids.get(i));
        }
        return result;
    }

    @Override
    public void listAllVertices() {
        for (Vertex v : allVertices.values()) {
            System.out.println(v);
        }
    }

    @Override
    public void listAttractions() {
        for (Vertex v : allVertices.values()) {
            if (v instanceof Attraction)
                System.out.println(((Attraction) v).getInfo());
        }
    }

    @Override
    public String getRouteString(ArrayList<Integer> route) {
        String res = "";
        for (int i = 0; i < route.size(); i++) {
            res += getVertexNameByID(route.get(i));
            if (i < route.size() - 1) {
                res += " -> ";
            }
        }
        return res;
    }

    @Override
    public int checkVertexIdValidity(int id) throws ExInvalidIndex {
        if (!allVertices.keySet().contains(id)) {
            throw new ExInvalidIndex();
        }
        return id;
    }

    @Override
    public int checkAttractionIdValidity(int id) throws ExInvalidIndex {
        if (!(allVertices.keySet().contains(id) && allVertices.get(id) instanceof Attraction)) {
            throw new ExInvalidIndex();
        }
        return id;
    }
}