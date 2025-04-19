// -------------------------------------------------------
// Assignment (include number)
// Written by: (include your name and student id)
// For “Programming 2” Section (include number) – Winter 2025
// --------------------------------------------------------
/*
 * This class is implementing FitnessOperations class. This class must have all the methods
 * in the interface class with implementations/logic.
 * */
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
public class FitnessManager implements FitnessOperations{

    //CLASS ATTRIBUTES
    private ArrayList<WorkoutSession> workoutSessionsList;


    //CONSTRUCTOR
    public FitnessManager(){
        this.workoutSessionsList = new ArrayList<>();
    }


    //GETTERS AND SETTERS
    public ArrayList<WorkoutSession> getWorkoutSessionsList(){
        return workoutSessionsList;
    }
    public void setWorkoutSessionsList(ArrayList<WorkoutSession> workoutSessionsList) {
        this.workoutSessionsList = workoutSessionsList;
    }


    //METHODS
    //in charge of displaying all workout session registered by the user
    @Override
    public void displayAllSessions() {
        if(workoutSessionsList.isEmpty()){
            System.out.println("No workout to display. Please register one first");
            return;
        }

        System.out.println("YOUR WORKOUTS: ");
        Collections.sort(workoutSessionsList);//using the compareTo() in WorkoutSession for sorting
        for(WorkoutSession display : workoutSessionsList){
            System.out.println(display);
        }
    }

    //displaying all workout sessions that have a duration more than the time specified by the user
    @Override
    public void displayByDurationThreshold(int threshold) {
        if(workoutSessionsList.isEmpty()){
            System.out.println("No workout registered. Please register one first");
        }

        boolean foundTreshold = false;
        for(WorkoutSession displayThreshold : workoutSessionsList){
            if(threshold < displayThreshold.getDuration()){
                System.out.println(displayThreshold);
                foundTreshold = true;
            }
        }

        if(!foundTreshold){
            System.out.println("No workout found that exceeds " + threshold + " minutes");
        }
    }

    //allow the user to add a workout session
    @Override
    public void addWorkoutSession(WorkoutSession session) {
        for(WorkoutSession workout : workoutSessionsList){
            if(workout.getDate().equals(session.getDate())){
                System.out.println("Date already registered. Please enter a new date");
                return;
            }
        }
        workoutSessionsList.add(session);
        System.out.println("Workout session registered successfully");
    }

    //allow the user to delete a workout session
    @Override
    public boolean removeWorkoutByDate(String date) {
        if(workoutSessionsList.isEmpty()){
            System.out.println("Cannot remove workout. List is empty. Please register one workout");
            return false;
        }

        for(int i = 0; i < workoutSessionsList.size(); i++){
            if(workoutSessionsList.get(i).getDate().equals(date)){
                workoutSessionsList.remove(i);
                return true;
            }
        }
        return false;
    }

    //allow the user to find their longest workout session based on the duration
    @Override
    public WorkoutSession findLongestWorkout() {
        if(workoutSessionsList.isEmpty()){
            System.out.println("No workout registered. Please register one first");
            return null;
        }

        System.out.println("Your longest workout: ");
        WorkoutSession maxDuration = workoutSessionsList.get(0);//assume that the first one is the maximum duration
        for(int i = 0; i < workoutSessionsList.size(); i++){
            if(workoutSessionsList.get(i).getDuration() > maxDuration.getDuration()){
                maxDuration = workoutSessionsList.get(i);
            }
        }
        return maxDuration;
    }

    //allow the user to save their workout sessions to a text file
    @Override
    public void saveToFile(String filename) {
        try{
            PrintWriter write = new PrintWriter(new FileOutputStream(filename));

            //save in comma separated values
            for(WorkoutSession sessions : workoutSessionsList){
                    String formatData = String.format("%s,%s,%d,%d",
                            sessions.getDate(),
                            sessions.getActivityType(),
                            sessions.getDuration(),
                            sessions.getCaloriesBurned()
                );
                write.println(formatData);
            }
            write.close();
        }catch (IOException ioE){
            System.out.println("something went wrong. File not created");
        }
    }

    //allow the user to load their workout sessions text file
    public void loadFromFile(String filename) {
        //find the file path
        File file = new File("C:\\Users\\steve\\OneDrive\\Desktop\\prog2\\Assign3_prog2\\" + filename);
        if (!file.exists()) {
            System.out.println("Error: File '" + filename + "' not found at: " + file.getAbsolutePath());
            return;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            //workoutSessionsList.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                //ensure we have exactly 4 parts (date, activity type, duration, calories burned)
                if (parts.length == 4) {
                    WorkoutSession session = new WorkoutSession(
                            parts[0].trim(),//remove beginning and end whitespaces
                            parts[1].trim(),
                            Integer.parseInt(parts[2].trim()),
                            Integer.parseInt(parts[3].trim())
                    );
                    workoutSessionsList.add(session);
                }
            }
            System.out.println(workoutSessionsList.size() + " workouts loaded from: " + file.getAbsolutePath());
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}