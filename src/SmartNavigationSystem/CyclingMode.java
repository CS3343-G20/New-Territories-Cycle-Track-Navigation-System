package SmartNavigationSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CyclingMode implements Mode {
    private List<String> priorityList = Arrays.asList(new String[]{"SP","ST"});

    private int departure;
    private int destination;
    private String priority;
    private ArrayList<Integer> attractions = new ArrayList<> ();
    private ArrayList<Integer> route = new ArrayList<Integer> ();
    private Graph map = Graph.getInstance();
    private VerticesManager verticesManager = Vertices.getInstance();
    private boolean forClimbing = false;
    private Scanner input = null;

    @Override
    public void execute(){
        input = new Scanner(System.in);

        setDeparture();

        setDestination();

        addAttractions();

        setPriority();

        boolean isConfirmed = false;
        while (!isConfirmed) {
            listInfo();
            System.out.println("0: Confirm the above information and start route planning");
            System.out.println("1: Reset departure");
            System.out.println("2: Reset destination");
            System.out.println("3: Reset attraction");
            System.out.println("4: Reset priority");

            int cmd = Utility.getIntegerInput(input);
            switch(cmd) {
                case 0:
                    isConfirmed = true;
                    routePlanning();
                    break;
                case 1:
                    setDeparture();
                    break;
                case 2:
                    setDestination();
                    break;
                case 3:
                    addAttractions();
                    break;
                case 4:
                    setPriority();
                    break;
            }
        }
        input.close();
    }

    public void changeToCyclingMode(int d) {
        separator();

        this.destination = d;
        forClimbing = true;
        execute();
    }

    public void setDeparture() {
        separator();

        verticesManager.listAllVertices();
        int id = -1;
        do {
            System.out.println("Please input a departure ID:");
            id = Utility.getIntegerInput(input);
            this.departure = id;
        } while (!verticesManager.isValidVertexId(id));

    }

    public void setDestination() {
        separator();

        if (!forClimbing) {
            verticesManager.listAllVertices();
            int id = -1;
            do {
                System.out.println("Please input a destination ID:");
                id = Utility.getIntegerInput(input);
                this.destination = id;
            } while (!verticesManager.isValidVertexId(id));
        }
        else {
            System.out.printf("The destination has been set to be the start of your intended climbing trail (%s) by default.\n", verticesManager.getVertexNameByID(destination));
            System.out.println("1 - Go on");
            System.out.println("2 - Force to change the destination");
            if (Utility.getIntegerInput(input) == 2) {
                forClimbing = false;
                setDestination();
            }
        }
    }

    public void addAttractions(){
        separator();

        System.out.println("Do you want to add attractions?[Y/N]");
        if(input.nextLine().equals("Y")){
            verticesManager.listAttractions();
            System.out.println("Please choose attractions:");
            String attractionIds = input.nextLine();

            attractions.clear();
            String[] attractionIdParts = attractionIds.split(" ");
            for(String attractionId:attractionIdParts) {
                attractions.add(Integer.parseInt(attractionId));
            }
        }
    }

    public void setPriority(){
        separator();

        String p = priorityList.get(0);
        System.out.println("Default priority setting is shortest path, do you want to set the priority to shortest time?[Y/N]");
        if(input.nextLine().equals("Y")){
            p = priorityList.get(1);
        }
        this.priority = p;
    }

    public void listInfo() {
        separator();

        System.out.println("Current choices:");
        System.out.println("Departure - " + verticesManager.getVertexNameByID(departure));
        System.out.println("Destination - " + verticesManager.getVertexNameByID(destination));
        System.out.println("Attractions - " + verticesManager.getVertexNamesByID(attractions));
        System.out.println("Priority - " + priority);
        System.out.println();
    }
    
    public void routePlanning() {
        separator();

        ArrayList<Integer> places = new ArrayList<>();
        places.add(Integer.valueOf(departure));
        places.addAll(attractions);
        places.add(Integer.valueOf(destination));
        int totalCost = 0;

        for (int i=0; i<places.size()-1; i++) {
            int s = places.get(i).intValue();
            int d = places.get(i+1).intValue();
            map.dijkstra(priority, s, d);
            System.out.println("From " + verticesManager.getVertexNameByID(s) + " to " + verticesManager.getVertexNameByID(d) + ":");
            map.dfs(s, d, new ArrayList<Integer>());
            totalCost += map.getDistance(d);

            if (map.getRoutesNumber() > 1) {
                boolean end = false;
                while (!end) {
                    try { 
                        System.out.println("Please select one route:");
                        route.addAll(map.getRoute(Utility.getIntegerInput(input)-1));
                        end = true;
                    } catch(IndexOutOfBoundsException exception){
                        System.out.println("xxxxx");
                    } catch(InputMismatchException exception){
                        System.out.println("xxxxx");
                    }
                }
            }
            else {
                route.addAll(map.getRoute(0));
            }
            route.remove(route.size()-1);
            map.clearRoutes();
        }
        route.add(destination);

        System.out.printf("Total cost: %d\nRoute: ", totalCost);
        verticesManager.listRoute(route);
        
        // call bookmark()--->lst
    }

    // just for readability
    public void separator() {
        System.out.println("---------------------------------------------------");
    }
}
