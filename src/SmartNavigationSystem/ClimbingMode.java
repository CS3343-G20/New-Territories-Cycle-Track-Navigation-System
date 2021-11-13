package SmartNavigationSystem;

import java.io.InputStream;
import java.util.Scanner;

/*
This is a singleton class
 */
public class ClimbingMode implements Mode {
	private static ClimbingTrailRepoManager ctrManager;
	private static Scanner scan ;
	private Member member = null;

	public ClimbingMode(ClimbingTrailRepoManager ctrm,InputStream is) {
		ctrManager = ctrm;
		scan= new Scanner(is);
	}

	
	public ClimbingMode (ClimbingTrailRepoManager ctrm) {
		ctrManager= ctrm;
	}
	public ClimbingMode() {
		ctrManager = ClimbingTrailRepository.getInstance();
		scan= new Scanner(System.in);
	}

	public String listTrails() {
		String result = ctrManager.list();
		System.out.println(result);
		return result;
	}

	public String findTrailsByDifficulty(int difficulty) {
		String result = ctrManager.filterByDifficulty(difficulty);
		System.out.println(result);
		return result;
	}

	public String chooseSelectionCriteria() {
		System.out.printf(
				"Please choose the selection criteria: \n" + "1. Difficulty\n" + "2. Departure\n" + "3. Destination");
		int selection = scan.nextInt();
		switch (selection) {
		case 1:
			int difficulty = scan.nextInt();
			return findTrailsByDifficulty(difficulty);
		case 2:
			String departure = scan.next();
			return findTrailsByDeparture(departure);

		case 3:
			String destination = scan.next();
			return findTrailsByDestination(destination);

		default:
			System.out.println("ERROR: Invalid selection criteria");
			chooseSelectionCriteria();
			return null;
		}
	}

	public void addCycling(int PathID) {
		System.out.println("Do you want to cycle to the point?");
		boolean cycling = scan.nextBoolean();
		if (cycling) {
			CyclingMode cm = new CyclingMode(Graph.getInstance(), Vertices.getInstance(), scan, Bookmark.getInstance());
			cm.modeSwitch(PathID);
		}
	}

	public String findTrailsByDeparture(String name) {
		String result = ctrManager.filterTrailByDeparture(name);
		System.out.println(result);
		return result;
	}

	public String findTrailsByDestination(String name) {
		String result = ctrManager.filterTrailByDest(name);
		System.out.println(result);
		return result;
	}

	public int chooseClimbingPath() {

		System.out.println("Please enter the id of the climbing path that you would like to choose :");
		int pathID = Integer.parseInt(scan.next()) ;
		String trail = ctrManager.findTrailByID(pathID);
		if (trail != null) {
			System.out.println(trail);
		} else {
			System.out.println("The climbing path doesn't exist, please enter a valid climbing path id");
			chooseClimbingPath();
		}
		return pathID;
	}

	@Override
	public void execute() {
		listTrails();
		chooseSelectionCriteria();
		int PathID = chooseClimbingPath();
		addCycling(PathID);
	}


	@Override
	public void memberExecute(Member member) {
		this.member = member;
        execute();
        this.member.setRoute("Climbing Mode: " + listTrails());
    }
	

}
