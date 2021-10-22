package Java;

import java.util.*;

public class Graph {

    private static String priority = "SP";
    private List<String> prioriList=Arrays.asList(new String[]{"SP","ST"});
    private static Graph map = new Graph();

    private int V;
    private List<List<Path> > adj_list = new ArrayList<List<Path>>();
    private int src;
    private int dist[];
    private List<List<Integer>> parent=new ArrayList<List<Integer>>();
    private Set<Integer> visited; 
    private PriorityQueue<Path> pqueue;
    private ArrayList<ArrayList<Integer>> routes=new ArrayList<ArrayList<Integer>> ();

    private class Path implements Comparator<Path> {
    
        private int from;
        private int to;
        private int SP_weight; 
        private int ST_weight;
        private int weight;
    
        public Path() {};
       
        public Path(int from, int to, int sp, int st) { 
            this.from = from; 
            this.to = to;
            this.SP_weight = sp; //sp:shortest path
            this.ST_weight = st; //st: shortest time
        }

        public Path(int from, int to, int weight){
            this.from = from; 
            this.to = to;
            this.weight=weight;
        }

        public int weight(){
            if (priority.equals(prioriList.get(0))) {
                return SP_weight;
            }else {
                return ST_weight;
            }
        }
        
        @Override
        public int compare(Path p1, Path p2) {
            return Integer.compare(p1.weight, p2.weight);
        }
    }

    private Graph() { 
        this.V = Constants.vertexNumber;

        for (int i = 0; i < V; i++) {
            this.adj_list.add(new ArrayList<Path>()); 
        }
        for (int[] adj: Constants.weight) {
            this.adj_list.get(adj[0]).add(new Path(adj[0], adj[1], adj[2], adj[3]));
            this.adj_list.get(adj[1]).add(new Path(adj[1], adj[0], adj[2], adj[3]));
        } 
    }

    public static Graph getInstance() {
        return map;
    }
   
    public void dijkstra(String p, int source, int dest) {
        priority = p;
        this.src = source;
        this.dist = new int[V]; 
        this.visited = new HashSet<Integer>();
        this.pqueue = new PriorityQueue<Path>(V, new Path());
        this.routes=new ArrayList<ArrayList<Integer>> ();

        for (int i = 0; i < V; i++) {
            this.dist[i] = Integer.MAX_VALUE;
            this.parent.add(new ArrayList<Integer>());
        }
   
        pqueue.add(new Path(src, src, 0, 0)); 
        this.dist[src] = 0;

        while (pqueue.peek().to != dest) { 
            int u = pqueue.remove().to;
            if (!visited.contains(u)) {
                visited.add(u);
                process_adjacentVertices(u);
            }
        }
    }

    private void process_adjacentVertices(int u)   { 
        for (int i = 0; i < adj_list.get(u).size(); i++) { 
            Path p = adj_list.get(u).get(i); 

            int newDistance = dist[u] + p.weight(); 

            if(this.dist[p.to] >= newDistance){
                if(this.dist[p.to] > newDistance){
                    this.dist[p.to] = newDistance;
                    this.parent.get(p.to).clear();
                }
                this.parent.get(p.to).add(u);
                if(!visited.contains(p.to)){
                    pqueue.add(new Path(src, p.to, dist[p.to]));
                }
            }
        }
    }

    public void dfs(int src, int dest, ArrayList<Integer> temp){
        if (src == dest) {
            temp.add(dest);
            ArrayList<Integer> route = new ArrayList<Integer>();
            System.out.printf("[%d] ", routes.size()+1);
            for(int i=temp.size()-1; i>=0; i--) {
                System.out.print(Vertices.getInstance().getVertexNameByID(temp.get(i)));
                if(i != 0){ System.out.print(" -> "); }
                route.add(temp.get(i));
            }
            System.out.println();
            this.routes.add(route);
            temp.remove(temp.size()-1);
            return;
        }
        temp.add(dest);
        for(int i=0; i<this.parent.get(dest).size(); i++) {
            dfs(src,this.parent.get(dest).get(i),temp);
        }
        temp.remove(temp.size()-1);
    }

    public ArrayList<Integer> getRoute(int i) {
        return this.routes.get(i);
    }

    public int getRoutesNumber() {
        return this.routes.size();
    }

    public void clearRoutes() {
        this.routes.clear();
    }

    public int getDistance(int index) {
        return this.dist[index];
    }
}
