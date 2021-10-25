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
    private boolean forClimbing = false;
    private Scanner input = null;
    private Graph map;
    private VerticesManager vManager;
    
    public CyclingMode(Graph map, VerticesManager vManager, Scanner in) {
    	this.map = map;
    	this.vManager = vManager;
    	this.input = in;
    }

    @Override
    public void execute(){

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

            try {
                int cmd = Integer.parseInt(input.nextLine());
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
                    default:
                        throw new ExInvalidCommand();
                }
            }
            catch (NumberFormatException e) {
                System.out.println(new ExWrongNumberFormat().getMessage());
            } catch (ExInvalidCommand e) {
                System.out.println(e.getMessage());
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

        vManager.listAllVertices();

        boolean isSet = false;
        while (!isSet) {
            try {
                System.out.println("Please input a departure ID:");
                this.departure = vManager.checkVertexIdValidity(Integer.parseInt(input.nextLine()));
                isSet = true;
            }
            catch (NumberFormatException e) {
                System.out.println(new ExWrongNumberFormat().getMessage());
            }
            catch (ExInvalidID e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void setDestination() {
        separator();

        if (!forClimbing) {
            vManager.listAllVertices();

            boolean isSet = false;
            while (!isSet) {
                try {
                    System.out.println("Please input a destination ID:");
                    this.departure = vManager.checkVertexIdValidity(Integer.parseInt(input.nextLine()));
                    isSet = true;
                }
                catch (NumberFormatException e) {
                    System.out.println(new ExWrongNumberFormat().getMessage());
                }
                catch (ExInvalidID e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        else {
            System.out.printf("The destination has been set to be the start of your intended climbing trail (%s) by default.\n", vManager.getVertexNameByID(destination));
            System.out.println("1 - Go on");
            System.out.println("2 - Force to change the destination");
            boolean isChosen = false;
            while (!isChosen) {
                try {
                    int choice = Integer.parseInt(input.nextLine());
                    if (choice == 2) {
                        forClimbing = false;
                        setDestination();
                    }
                    else if (choice != 1) {
                        throw new ExInvalidCommand();
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println(new ExWrongNumberFormat().getMessage());
                } catch (ExInvalidCommand e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void addAttractions() {
        separator();

        System.out.println("Do you want to add attractions?[Y/N]");
        try {
            if(input.nextLine().equals("Y")){
                vManager.listAttractions();

                boolean end = false;
                while (!end) {
                    System.out.println("Please choose attractions:");
                    String attractionIds = input.nextLine();
    
                    attractions.clear();
                    String[] attractionIdParts = attractionIds.split(" ");
                     
                    for(String attractionId:attractionIdParts) {
                        int id = Integer.parseInt(attractionId);
                        attractions.add(vManager.checkAttractionIdValidity(id));   
                    }
                    end = true;
                }
            }
            else if (!input.nextLine().equals("N")) {
                throw new ExInvalidCommand();
            }
        }
        catch (ExInvalidCommand e) {
            System.out.println(e.getMessage());
        }
        catch (NumberFormatException e) {
            System.out.println(new ExWrongNumberFormat().getMessage());
        }
        catch (ExInvalidID e) {
            System.out.println(e.getMessage());
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
        System.out.println("Departure - " + vManager.getVertexNameByID(departure));
        System.out.println("Destination - " + vManager.getVertexNameByID(destination));
        System.out.println("Attractions - " + vManager.getVertexNamesByID(attractions));
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
            System.out.println("From " + vManager.getVertexNameByID(s) + " to " + vManager.getVertexNameByID(d) + ":");
            map.dfs(s, d, new ArrayList<Integer>());
            totalCost += map.getDistance(d);

            if (map.getRoutesNumber() > 1) {
                boolean end = false;
                while (!end) {
                    try { 
                        System.out.println("Please select one route:");
                        route.addAll(map.getRoute(Integer.parseInt(input.nextLine())-1));
                        end = true;
                    }
                    catch (NumberFormatException e) {
                        System.out.println(new ExWrongNumberFormat().getMessage());
                    }
                    catch (IndexOutOfBoundsException exception){
                        System.out.println(new ExInvalidCommand().getMessage());
                    }
                    catch (InputMismatchException exception){
                        System.out.println(new ExInvalidCommand().getMessage());
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
        vManager.listRoute(route);
    }

//     // call bookmark()--->lst
//     public void makeBookmark(){

// =======
        
//         // call bookmark()--->lst
        
// >>>>>>> Stashed changes
//     }

    // just for readability
    public void separator() {
        System.out.println("---------------------------------------------------");
    }
}
