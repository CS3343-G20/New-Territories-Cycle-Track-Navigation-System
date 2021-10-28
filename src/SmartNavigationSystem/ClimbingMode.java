package code;

import java.util.Scanner;

/*
This is a singleton class
 */
public class ClimbingMode implements mode {
	private static ClimbingMode cm = new ClimbingMode();
	private static ClimbingTrailRepoManager ctrManager = ClimbingTrailRepository.getInstance();
	private static Scanner scan = new Scanner(System.in);

	public static ClimbingMode getInstance() {
		return cm;
	}

	private ClimbingMode() {
		ctrManager = ClimbingTrailRepository.getInstance();
	}

	public void listTrails() {
		ctrManager.list();
	}

	public void findTrailsByDifficulty(int difficulty) {
		ctrManager.filterByDifficulty(difficulty);
	}

	public void chooseSelectionCriteria() {
		System.out.printf(
				"Please choose the selection criteria: \n" + "1. Difficulty\n" + "2. Departure\n" + "3. Destination");
		int selection = scan.nextInt();
		switch (selection) {
		case 1:
			int difficulty = scan.nextInt();
			findTrailsByDifficulty(difficulty);
			break;
		case 2:
			String departure = scan.next();
			findTrailsByDeparture(departure);
			break;

		case 3:
			String destination = scan.next();
			findTrailsByDestination(destination);
			break;

		default:
			System.out.println("ERROR: Invalid selection criteria");
			chooseSelectionCriteria();
			break;
		}
	}

	public void addCycling() {
		System.out.println("Do you want to cycle to the point?");
		boolean cycling = scan.nextBoolean();
	    if(cycling) {
	    	CyclingMode cm = new CyclingMode()
	    	cm.modeswitch();
	    }
	}

	public void findTrailsByDeparture(String name) {
		System.out.println(ctrManager.filterTrailByDeparture(name));

	}

	public void findTrailsByDestination(String name) {
		System.out.println(ctrManager.filterTrailByDest(name));
	}

	public void chooseClimbingPath() {

		System.out.println("Please enter the id of the climbing path that you would like to choose :");
		String pathID = scan.next();
		String trail = ctrManager.findTrailByID(pathID);
		if (trail != null) {
			System.out.println(trail);
		} else {
			System.out.println("The climbing path doesn't exist, please enter a valid climbing path id");
			chooseClimbingPath();
		}
	}

	@Override
	public void execute() {
		listTrails();
		chooseSelectionCriteria();
		chooseClimbingPath();
		addCycling();
	}

}
