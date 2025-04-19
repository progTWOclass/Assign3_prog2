// -------------------------------------------------------
// Assignment 3
// Written by:
// For “Programming 2” Section 02 – Winter 2025
// --------------------------------------------------------
/*
* This file is an interface. It only contains method signatures.
* It tells other classes that are implementing this type (FitnessOperations)
* what method they must have.
* */
public interface FitnessOperations {

    //METHODS
    public void displayAllSessions();

    public void displayByDurationThreshold(int threshold);

    public void addWorkoutSession(WorkoutSession session);

    public boolean removeWorkoutByDate(String date);

    public WorkoutSession findLongestWorkout();

    public void saveToFile(String filename);
}
