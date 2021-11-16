package SmartNavigationSystem;

import java.util.Scanner;

public class ClimbingMode implements Mode {
	private static ClimbingTrailRepoManager ctrManager;
	private Scanner scan;
	private BookmarkManager bmManager;
	private Member member = null;
	private int pathID;

	public ClimbingMode(ClimbingTrailRepoManager ctrm, Scanner scan, BookmarkManager bmManager) {
		if (ctrm == null) {
			ctrManager = ClimbingTrailRepository.getInstance();
		} else {
			ctrManager = ctrm;
		}
		this.scan = scan;
		if (bmManager == null) {
			this.bmManager = Bookmark.getInstance();
		} else {
			this.bmManager = bmManager;
		}
	}

	public void run() {
		System.out.println("Here are all the trails:");
		System.out.println(ctrManager.list());
		chooseSelectionCriteria();
		pathID = chooseClimbingPath();
	}

	@Override
	public void execute() {
		run();
		addCycling(pathID);
	}

	@Override
	public void memberExecute(Member member) {
		this.member = member;
		run();
		addBookmark();
		this.member.setRoute("Climbing Mode: " + ctrManager.findTrailByID(pathID));
		addCycling(pathID);
	}

	public String chooseSelectionCriteria() {
		System.out.printf(
				"Please choose the selection criteria: \n" + "1. Difficulty\n" + "2. Departure\n" + "3. Destination\n");
		while (true) {
			try {
				int selection = Integer.parseInt(scan.nextLine());
				switch (selection) {
				case 1:
					return findTrailsByDifficulty();
				case 2:
					return findTrailsByDeparture();
				case 3:
					return findTrailsByDestination();
				default:
					System.out.println("ERROR: Invalid selection criteria");
				}
			} catch (NumberFormatException e) {
				System.out.println(new ExWrongNumberFormat().getMessage());
			}
		}
	}

	public String findTrailsByDifficulty() {
		System.out.printf("Please choose the difficulty from following numbers:\n");
		System.out.println(ctrManager.listDifficulties());
		while (true) {
			try {
				int difficulty = Integer.parseInt(scan.nextLine());
				String result = ctrManager.filterByDifficulty(difficulty);
				if (result.length() != 0) {
					System.out.println("The following are the filtered trails:");
					System.out.print(result);
					return result;
				} else {
					System.out.println("Invalid difficulty. Please try again.");
				}
			} catch (NumberFormatException e) {
				System.out.println(new ExWrongNumberFormat().getMessage());
			}
		}
	}

	public String findTrailsByDeparture() {
		System.out.printf("Please choose the departure from following departures:");
		System.out.println(ctrManager.listDepartures());
		while (true) {
			String departure = scan.nextLine();
			String result = ctrManager.filterTrailByDeparture(departure);
			if (result.length() != 0) {
				System.out.println("The following are the filtered trails:");
				System.out.print(result);
				return result;
			} else {
				System.out.println("Invalid departure name. Please reenter.");
			}
		}
	}

	public String findTrailsByDestination() {
		System.out.printf("Please choose the destination from following destinations:");
		System.out.println(ctrManager.listDestinations());
		while (true) {
			String destination = scan.nextLine();
			String result = ctrManager.filterTrailByDest(destination);
			if (result.length() != 0) {
				System.out.println("The following are the filtered trails:");
				System.out.print(result);
				return result;
			} else {
				System.out.println("Invalid destination name, please reenter.");
			}
		}
	}

	public int chooseClimbingPath() {
		System.out.println("Please enter the id of the climbing path that you would like to choose :");
		while (true) {
			try {
				int pathID = Integer.parseInt(scan.nextLine());
				String trail = ctrManager.findTrailByID(pathID);
				boolean inFilteredTrails = false;
				for (ClimbingTrail c : ctrManager.getFilteredClimbingTrails()) {
					if (c.getID() == pathID) {
						inFilteredTrails = true;
					}
				}
				if (trail != null && inFilteredTrails) {
					System.out.println("This is the climbing trail chosen:");
					System.out.println(trail);
					return pathID;
				} else {
					System.out.println("The climbing path doesn't exist, please enter a valid climbing path id");
				}
			} catch (NumberFormatException e) {
				System.out.println(new ExWrongNumberFormat().getMessage());
			}
		}
	}

	public void addCycling(int PathID) {
		System.out.println("Do you want to cycle to the point? [Y/N]");
		boolean isChosen = false;
		while (!isChosen) {
			try {
				String isCycling = scan.nextLine();
				if (isCycling.equals("Y")) {
					CyclingMode cm = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), scan,
							Bookmark.getInstance(), ClimbingTrailRepository.getInstance());
					cm.modeSwitch(PathID, this.member);
				} else if (!isCycling.equals("N")) {
					throw new ExInvalidCommand();
				}
				isChosen = true;
			} catch (ExInvalidCommand e) {
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
