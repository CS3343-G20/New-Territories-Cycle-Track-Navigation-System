package SmartNavigationSystem;

import java.io.InputStream;
import java.util.Scanner;

/*
This is a singleton class
 */
public class ClimbingMode implements Mode {
	private static ClimbingTrailRepoManager ctrManager;
	private static Scanner scan;
	private BookmarkManager bmManager;
	private Member member = null;
	private int pathID;

	// public ClimbingMode(ClimbingTrailRepoManager ctrm, InputStream is) {
	// 	ctrManager = ctrm;
	// 	scan = new Scanner(is);
	// }

	// public ClimbingMode(ClimbingTrailRepoManager ctrm) {
	// 	ctrManager = ctrm;
	// }

	public ClimbingMode() {
		ctrManager = ClimbingTrailRepository.getInstance();
		scan = new Scanner(System.in);
		this.bmManager = Bookmark.getInstance();
	}

	public String listTrails() {
		String result = ctrManager.list();
		System.out.println(result);
		return result;
	}

	public String findTrailsByDifficulty(int difficulty) {
		boolean inFilteredTrails1 = false;
		String result = ctrManager.filterByDifficulty(difficulty);
		for (ClimbingTrail c : ctrManager.getFilteredClimbingTrails()) {
			if (c.getDifficulty() == difficulty) {
				inFilteredTrails1 = true;
			}
		}
		if (inFilteredTrails1) {
			System.out.println("The following are the filtered trails:");
			System.out.print(result);
			return result;
		} else {
			System.out.println("Invalid id, please reenter.");
			chooseSelectionCriteria();
		}
		return null;
	}

	public String listDifficulties() {
		String result = ctrManager.listDifficulties();
		System.out.println(result);
		return result;
	}

	public String listDepartures() {
		String result = ctrManager.listDepartures();
		System.out.println(result);
		return result;
	}

	public String listDestinations() {
		String result = ctrManager.listDestinations();
		System.out.println(result);
		return result;
	}

	public String chooseSelectionCriteria() {
		System.out.printf(
				"Please choose the selection criteria: \n" + "1. Difficulty\n" + "2. Departure\n" + "3. Destination\n");
		int selection = scan.nextInt();
		switch (selection) {
		case 1:
			System.out.printf("Please choose the difficulty from following numbers:\n");
			listDifficulties();
			int difficulty = scan.nextInt();
			return findTrailsByDifficulty(difficulty);

		case 2:
			System.out.printf("Please choose the departure from following departures:");
			listDepartures();
			scan.nextLine();
			String departure = scan.nextLine();
			return findTrailsByDeparture(departure);

		case 3:
			System.out.printf("Please choose the destination from following destinations:");
			listDestinations();
			scan.nextLine();
			String destination = scan.nextLine();
			return findTrailsByDestination(destination);

		default:
			System.out.println("ERROR: Invalid selection criteria");
			chooseSelectionCriteria();
			return null;
		}
	}

	public void addCycling(int PathID) {
		System.out.println("Do you want to cycle to the point? [Y/N]");
		boolean isChosen = false;
		while (!isChosen) {
			try{
				String isCycling = scan.next();
				if (isCycling.equals("Y")) {
					CyclingMode cm = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), scan, Bookmark.getInstance());
					cm.modeSwitch(PathID);
				}else if(!isCycling.equals("N")){
					throw new ExInvalidCommand();
				}
				isChosen = true;
			}catch(ExInvalidCommand e){
					System.out.println(e.getMessage());
				}
		}
	}

	public String findTrailsByDeparture(String name) {
		String result = ctrManager.filterTrailByDeparture(name);
		boolean inFilteredTrails2 = false;
		for (ClimbingTrail c : ctrManager.getFilteredClimbingTrails()) {
			if (c.getDepartureName().equals(name)) {
				inFilteredTrails2 = true;
			}
		}
		if (inFilteredTrails2) {
			System.out.println("The following are the filtered trails:");
			System.out.print(result);
			return result;
		} else {
			System.out.println("Invalid departure name, please reenter.");
			chooseSelectionCriteria();
		}
		return null;
	}

	public String findTrailsByDestination(String name) {
		String result = ctrManager.filterTrailByDest(name);
		boolean inFilteredTrails3 = false;
		for (ClimbingTrail c : ctrManager.getFilteredClimbingTrails()) {
			if (c.getDestinationName().equals(name)) {
				inFilteredTrails3 = true;
			}
		}
		if (inFilteredTrails3) {
			System.out.println("The following are the filtered trails:");
			System.out.print(result);
			return result;
		} else {
			System.out.println("Invalid destination name, please reenter.");
			chooseSelectionCriteria();
		}
		return null;
	}

	public int chooseClimbingPath() {
		System.out.println("Please enter the id of the climbing path that you would like to choose :");
		int pathID = Integer.parseInt(scan.next());
		String trail = ctrManager.findTrailByID(pathID);
		boolean inFilteredTrails4 = false;
		for (ClimbingTrail c : ctrManager.getFilteredClimbingTrails()) {
			if (Integer.parseInt(c.getID())==pathID) {
				inFilteredTrails4 = true;
			}
		}
		if (trail != null&&inFilteredTrails4) {
			System.out.println("This is the climbing trail chosen:");
			System.out.println(trail);
		} else {
			System.out.println("The climbing path doesn't exist, please enter a valid climbing path id");
			chooseClimbingPath();
		}
		return pathID;
	}

	@Override
	public void execute() {
		System.out.println("Here are all the trails:");
		listTrails();
		chooseSelectionCriteria();
		pathID = chooseClimbingPath();
		if (this.member == null){
			addCycling(pathID);
		}
	}

	@Override
	public void memberExecute(Member member) {
		this.member = member;
		execute();
		addBookmark();
		pathID = chooseClimbingPath();
		addCycling(pathID);
		this.member.setRoute("Climbing Mode: " + listTrails());
	}

	public void addBookmark() {
        System.out.println("Do you want to add the selected route as bookmark? [Y/N]");
        boolean isChosen = false;
        while (!isChosen) {
            try {
                String choice = scan.next();
                if (choice.equals("Y")) {
					bmManager.addBookmark("Climbing Mode: " + ctrManager.findTrailByID(pathID), this.member);
                } else if (!choice.equals("N")) {
                    throw new ExInvalidCommand();
                }
                isChosen = true;
            } catch (ExInvalidCommand e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
