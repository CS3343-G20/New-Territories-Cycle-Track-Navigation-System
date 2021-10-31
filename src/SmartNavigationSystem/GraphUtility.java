package SmartNavigationSystem;

import java.util.ArrayList;

public interface GraphUtility {
    public void dijkstra(String priority, int s);
    public void dfs(int s, int d, ArrayList<Integer> temp);
    public int getDistance(int d);
    public int getRoutesNumber();
    public ArrayList<Integer> getRoute(int i);
    public void clearRoutes();
}
