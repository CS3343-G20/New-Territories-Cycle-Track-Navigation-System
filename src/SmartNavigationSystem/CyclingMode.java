package SmartNavigationSystem;

import static org.mockito.Answers.values;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CyclingMode implements Mode {
    private List<String> priorityList = Arrays.asList(new String[] { "SP", "ST" });

    private int departure;
    private int destination;
    private String priority = priorityList.get(0);
    private ArrayList<Integer> attractions = new ArrayList<>();
    private ArrayList<Integer> route = new ArrayList<Integer>();
    private boolean forClimbing = false;
    private Scanner input = null;
    private GraphUtility map;
    private VerticesManager vManager;
    private Member member = null;
    private BookmarkManager mark;
    private ClimbingTrailsQuerier tQuerier;

    public CyclingMode(GraphUtility map, VerticesManager vManager, Scanner in, BookmarkManager mark, ClimbingTrailsQuerier querier) {
        this.map = map;
        this.vManager = vManager;
        this.input = in;
        this.mark = mark;
        this.tQuerier = querier;
    }

    @Override
    public void execute() {

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
                switch (cmd) {
                case 0:
                    isConfirmed = true;
                    routePlanning();
                    addBookmark();
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
            } catch (NumberFormatException e) {
                System.out.println(new ExWrongNumberFormat().getMessage());
            } catch (ExInvalidCommand e) {
                System.out.println(e.getMessage());
            }
        }

        // input.close();
    }

    public void memberExecute(Member member) {
        this.member = member;
        execute();
        this.member.setRoute("Cycling Mode: " + vManager.getRouteString(route));
    }

    public void modeSwitch(int trail_id) {
        separator();

        this.destination = tQuerier.getTrailDepartureID(trail_id);
        forClimbing = true;
        execute();
        if (forClimbing) {
            System.out.printf("Climbing Route: %s -> %s\n", vManager.getVertexNameByID(this.destination), tQuerier.getTrailDestinationName(trail_id));
        }
    }

    public void setDeparture() {
        separator();

        vManager.listAllVertices();
        System.out.println("Please input a departure ID:");

        boolean isSet = false;
        while (!isSet) {
            try {
                this.departure = vManager.checkVertexIdValidity(Integer.parseInt(input.nextLine()));
                isSet = true;
            } catch (NumberFormatException e) {
                System.out.println(new ExWrongNumberFormat().getMessage());
            } catch (ExInvalidIndex e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void setDestination() {
        separator();

        if (!forClimbing) {
            vManager.listAllVertices();
            System.out.println("Please input a destination ID:");

            boolean isSet = false;
            while (!isSet) {
                try {
                    this.destination = vManager.checkVertexIdValidity(Integer.parseInt(input.nextLine()));
                    isSet = true;
                } catch (NumberFormatException e) {
                    System.out.println(new ExWrongNumberFormat().getMessage());
                } catch (ExInvalidIndex e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            System.out.printf(
                    "The destination has been set to be the start of your intended climbing trail (%s) by default.\n",
                    vManager.getVertexNameByID(destination));
            System.out.println("1 - Go on");
            System.out.println("2 - Force to change the destination");
            boolean isChosen = false;
            while (!isChosen) {
                try {
                    int choice = Integer.parseInt(input.nextLine());
                    if (choice == 2) {
                        forClimbing = false;
                        setDestination();
                    } else if (choice != 1) {
                        throw new ExInvalidCommand();
                    }
                    isChosen = true;
                } catch (NumberFormatException e) {
                    System.out.println(new ExWrongNumberFormat().getMessage());
                } catch (ExInvalidCommand e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void addAttractions() {
        separator();

        System.out.println("Do you want to set attractions?[Y/N]");
        boolean end = false;
        while (!end) {
            try {
                String in = input.nextLine();
                if (in.equals("Y")) {
                    vManager.listAttractions();
                    System.out.println("Please choose attractions:");

                    boolean isSet = false;
                    while (!isSet) {
                        try {
                            String attractionIds = input.nextLine();

                            String[] attractionIdParts = attractionIds.split(" ");
                            attractions.clear();
                            for (String attractionId : attractionIdParts) {
                                int id = Integer.parseInt(attractionId);
                                attractions.add(vManager.checkAttractionIdValidity(id));
                            }
                            isSet = true;
                        } catch (NumberFormatException e) {
                            System.out.println(new ExWrongNumberFormat().getMessage());
                        } catch (ExInvalidIndex e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    end = true;
                } else if (in.equals("N")) {
                    attractions.clear();
                    end = true;
                } else {
                    throw new ExInvalidCommand();
                }
            } catch (ExInvalidCommand e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void setPriority() {
        separator();

        System.out.println(
                "Default priority setting is shortest path, do you want to set the priority to shortest time?[Y/N]");
        boolean isSet = false;
        while (!isSet) {
            try {
                String in = input.nextLine();
                if (in.equals("Y")) {
                    this.priority = priorityList.get(1);
                } else if (!in.equals("N")) {
                    throw new ExInvalidCommand();
                }
                isSet = true;
            } catch (ExInvalidCommand e) {
                System.out.println(e.getMessage());
            }
        }
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

        for (int i = 0; i < places.size() - 1; i++) {
            int s = places.get(i).intValue();
            int d = places.get(i + 1).intValue();
            map.dijkstra(priority, s);
            System.out.println("From " + vManager.getVertexNameByID(s) + " to " + vManager.getVertexNameByID(d) + ":");
            map.dfs(s, d, new ArrayList<Integer>());
            totalCost += map.getDistance(d);

            if (map.getRoutesNumber() > 1) {
                boolean end = false;
                while (!end) {
                    try {
                        System.out.println("Please select one route:");
                        route.addAll(map.getRoute(Integer.parseInt(input.nextLine()) - 1));
                        end = true;
                    } catch (NumberFormatException e) {
                        System.out.println(new ExWrongNumberFormat().getMessage());
                    } catch (IndexOutOfBoundsException exception) {
                        System.out.println(new ExInvalidIndex().getMessage());
                    }
                }
            } else {
                route.addAll(map.getRoute(0));
            }
            route.remove(route.size() - 1);
            map.clearRoutes();
        }
        route.add(destination);

        System.out.printf("Total cost: %d\nCycling Route: ", totalCost);
        vManager.listRoute(route);
    }

    public void addBookmark() {
        if (this.member == null)
            return;
        System.out.println("Do you want to add this route as a bookmark? [Y/N]");
        boolean isChosen = false;
        while (!isChosen) {
            try {
                String in = input.nextLine();
                if (in.equals("Y")) {
                    mark.addBookmark("Cycling Mode: " + vManager.getRouteString(route), this.member);
                } else if (!in.equals("N")) {
                    throw new ExInvalidCommand();
                }
                isChosen = true;
            } catch (ExInvalidCommand e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // just for readability
    public void separator() {
        System.out.println("---------------------------------------------------");
    }

}