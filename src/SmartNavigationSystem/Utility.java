package SmartNavigationSystem;

import java.util.Scanner;

public class Utility {
    public static int getIntegerInput(Scanner input){
        try{
            return Integer.parseInt(input.nextLine());
        }catch(NumberFormatException exception){
            System.out.println("Please input an integer");
            return -1;
        }
    }
}
