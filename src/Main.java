// -------------------------------------------------------
// Assignment (include number)
// Written by: (include your name and student id)
// For “Programming 2” Section (include number) – Winter 2025
// --------------------------------------------------------
/*
 * This is the main class that provides the user with a menu and perform the actions that the user
 * wishes to do.
 *
 * Firstly, in the main method, I directly called the displayAllSessions() method from FitnessManager class
 * in the switch case since the method is void, and it only displays all workout sessions registered by the user.
 *
 * Secondly, displaySelectedWorkout() method filters the workout sessions by using the duration of the workout
 * specified by the user. It prompts the user to enter a duration, and the program finds all sessions that more
 * than what the time declared by the user and displays them.
 *
 * Thirdly, we have an addWorkout() that allows the user to register one of their workouts. This method uses
 * another method called verifyDate() that takes a scanner as a parameter and returns a LocaleDate as to prevent
 * the user from entering a false date (e.g february 29, 2023. Not a leap year so there aren't 29 days in february).
 * addWorkout() then type cast LocaleDate to a String to work with ISO date format yyyy-mm-dd.
 *
 * Fourthly, we have a removeWorkout() method that deletes a workout session from our list of workout based on the
 * date specified by the usr
 *
 * Fifthly, we have a displayLongestWorkout() method that only prints the details about the workout that has the longest
 * duration.
 *
 * Lastly, since we are only saving the data to a text file, I directly called the saveToFile() with the parameter
 * workoutData.txt and added a print that confirms the data has been successfully saved.
 * */
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    //CLASS VARIABLES
    private static FitnessManager manager = new FitnessManager();
    private static Scanner input = new Scanner(System.in);


    //METHODS
    public static void displaySelectedWorkout(){
        System.out.println("Please enter the minimum workout duration: ");
        int duration;
        while(true){
            try {
                duration = input.nextInt();
                if(duration < 0){
                    System.out.println("Cannot enter a negative number");
                    continue;
                }
                break;
            }catch (InputMismatchException iME){
                System.out.println("Invalid answer. Must be a number");
                input.nextLine();
            }
        }
        System.out.println("YOUR WORKOUTS:");
        manager.displayByDurationThreshold(duration);
    }

    private static LocalDate verifyDate(Scanner input){
        while(true){
            try{
                String dateString = input.nextLine().trim();
                LocalDate date = LocalDate.parse(dateString);
                if(date.getYear() <= 0){
                    throw new DateTimeException("Cannot add a negative year");
                }
                return date;
            }catch (DateTimeException dTE){
                System.out.println("Error, " + dTE.getMessage());
                System.out.println("Invalid date. Please enter yyyy-mm-dd");
            }
        }
    }

    public static void addWorkout(){
        System.out.println("Please enter your date: (yyyy-mm-dd)");
        String date = verifyDate(input).toString();

        System.out.println("Please enter your activity type: ");
        String activity;
        while(true){
            activity = input.nextLine().trim();// Trim whitespace from both ends
            if(activity.isEmpty()) {
                System.out.println("Input cannot be empty. Please enter letters only");
            } else if(!activity.matches("[a-zA-Z]+")) {  // Checks for one or more letters
                System.out.println("Invalid answer. Please enter only letters");
            } else {
                break;
            }
        }

        System.out.println("Please enter duration of your workout in minutes:");
        int duration;
        while (true){
            try{
                duration = input.nextInt();
                if (duration < 0){
                    throw new InputMismatchException();
                }
                break;
            }catch (InputMismatchException iME){
                System.out.println("Invalid answer. Please try again");
                input.nextLine();
            }
        }

        System.out.println("Please enter calories burned: ");
        int burnedCalories;
        while (true){
            try{
                burnedCalories = input.nextInt();
                if(burnedCalories < 0){
                    throw new InputMismatchException();
                }
                break;
            }catch (InputMismatchException iME){
                System.out.println("Cannot enter letters or characters. Please try again");
                input.nextLine();
            }
        }
        WorkoutSession workoutSession = new WorkoutSession(date, activity, duration, burnedCalories);
        manager.addWorkoutSession(workoutSession);
    }

    public static void removeWorkout(){
        System.out.println("Please enter your date: (yyyy-mm-dd)");
        String date = verifyDate(input).toString();

        System.out.println("Are you sure you want to delete the workout on " + date + "? (yes/no)");
        String confirmation;
        while(true){
            confirmation = input.nextLine().trim().toLowerCase();  // Trim whitespace from both ends
            if(confirmation.isEmpty()) {
                System.out.println("Input cannot be empty. Please enter letters only");
                continue;
            }
            if (confirmation.equalsIgnoreCase("yes")) {
                boolean wasRemoved = manager.removeWorkoutByDate(date);
                if (wasRemoved) {
                    System.out.println("Workout on " + date + " was successfully deleted.");
                } else {
                    System.out.println("No workout found for " + date + ". Nothing was deleted.");
                }
                return;
            }else if (confirmation.equalsIgnoreCase("no")) {
                System.out.println("Workout deletion cancelled.");
                return;
            }else{
                System.out.println("Invalid. Please enter yes/no");
            }
        }
    }

    public static void displayLongestWorkout(){
        WorkoutSession longest = manager.findLongestWorkout();
        System.out.println(longest);
    }

    public static void main(String[] args) {
        manager.loadFromFile("workoutData.txt");
        boolean continueLoop = true;
        while(continueLoop) {
            System.out.println("""
                    \n===== Health Tracker Menu =====
                    1. Display All Workout Sessions
                    2. Display Workouts Longer than X Minutes
                    3. Add New Workout Session
                    4. Remove Workout by Date
                    5. Show Longest Workout
                    6. Save and Exit
                    """);
            System.out.println("please enter your choice:");
            try {
                int commandChoice = input.nextInt();
                input.nextLine();//clears the buffer
                switch (commandChoice) {
                    case 1:
                        manager.displayAllSessions();
                        break;//exit the switch statement
                    case 2:
                        displaySelectedWorkout();
                        break;
                    case 3:
                        addWorkout();
                        break;
                    case 4:
                        removeWorkout();
                        break;
                    case 5:
                        displayLongestWorkout();
                        break;
                    case 6:
                        manager.saveToFile("workoutData.txt");
                        System.out.println("Your workout data has been saved. Goodbye!");
                        continueLoop = false;//stops the while loop
                        break;
                    default:
                        System.out.println("Invalid command");
                }
            }catch (InputMismatchException iME){
                System.out.println("Invalid command. PLease try again");
                input.next();
            }
        }
    }
}
