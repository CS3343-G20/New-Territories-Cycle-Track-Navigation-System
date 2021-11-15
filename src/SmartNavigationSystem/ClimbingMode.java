package SmartNavigationSystem;

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

	public ClimbingMode(Scanner scan) {
		ctrManager = ClimbingTrailRepository.getInstance();
		this.bmManager = Bookmark.getInstance();
	}

	@Override
	public void execute() {
		System.out.println("Here are all the trails:");
		System.out.println(ctrManager.list());
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
		this.member.setRoute("Climbing Mode: " + ctrManager.list());
	}

	public String chooseSelectionCriteria() {
		System.out.printf(
				"Please choose the selection criteria: \n" + "1. Difficulty\n" + "2. Departure\n" + "3. Destination\n");
		int selection = Integer.parseInt(scan.nextLine());
		switch (selection) {
		case 1:
			System.out.printf("Please choose the difficulty from following numbers:\n");
			System.out.println(ctrManager.listDifficulties());
			int difficulty = Integer.parseInt(scan.nextLine());
			return findTrailsByDifficulty(difficulty);

		case 2:
			System.out.printf("Please choose the departure from following departures:");
			System.out.println(ctrManager.listDepartures());
			String departure = scan.nextLine();
			return findTrailsByDeparture(departure);

		case 3:
			System.out.printf("Please choose the destination from following destinations:");
			System.out.println(ctrManager.listDestinations());
			String destination = scan.nextLine();
			return findTrailsByDestination(destination);

		default:
			System.out.println("ERROR: Invalid selection criteria");
			chooseSelectionCriteria();
			return null;
		}
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
		int pathID = Integer.parseInt(scan.nextLine());
		String trail = ctrManager.findTrailByID(pathID);
		boolean inFilteredTrails4 = false;
		for (ClimbingTrail c : ctrManager.getFilteredClimbingTrails()) {
			if (c.getID()==pathID) {
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

	public void addCycling(int PathID) {
		System.out.println("Do you want to cycle to the point? [Y/N]");
		boolean isChosen = false;
		while (!isChosen) {
			try{
				String isCycling = scan.nextLine();
				if (isCycling.equals("Y")) {
					CyclingMode cm = new CyclingMode(Graph.getInstance(), Vertices.getInstance(),
                    scan, Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
					cm.modeSwitch(PathID, this.member);
				}else if(!isCycling.equals("N")){
					throw new ExInvalidCommand();
				}
				isChosen = true;
			}catch(ExInvalidCommand e){
					System.out.println(e.getMessage());
				}
		}
	}

	public void addBookmark() {
        System.out.println("Do you want to add the selected route as bookmark? [Y/N]");
        boolean isChosen = false;
        while (!isChosen) {
            try {
                String choice = scan.nextLine();
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
