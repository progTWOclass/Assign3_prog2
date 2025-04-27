// -------------------------------------------------------
// Assignment 3
// Written by: Steve Banh 1971537
// For “Programming 2” Section 02 – Winter 2025
// --------------------------------------------------------
/*
 * This class is implementing Comparable<...> interface to sort objects in ascending order.
 * In our case, we are sorting WorkoutSessions objects. This class must have and
 * override the CompareTo() method. We are trying to sort WorkoutSessions objects by their dates.
 * */
public class WorkoutSession implements Comparable<WorkoutSession>{

    //CLASS ATTRIBUTES
    private String date;// (e.g., "2025-04-11")
    private String activityType; //(e.g., "Running", "Swimming")
    private int duration;// (minutes)
    private int caloriesBurned;


    //CONSTRUCTOR
    public WorkoutSession(String date, String activityType, int duration, int caloriesBurned){
        this.date = date;
        this.activityType = activityType;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
    }


    //GETTERS AND SETTERS
    public String getDate(){
        return date;
    }
    public String getActivityType(){
        return activityType;
    }
    public int getDuration(){
        return duration;
    }
    public int getCaloriesBurned(){
        return caloriesBurned;
    }

    public void setDate(String date){
        this.date = date;
    }
    public void setActivityType(String activityType){
        this.activityType = activityType;
    }
    public void setDuration(int duration){
        this.duration = duration;
    }
    public void setCaloriesBurned(int caloriesBurned){
        this.caloriesBurned = caloriesBurned;
    }


    //METHODS
    //an important method to sort the dates in ascending order by telling our
    //collection.sort() method in FitnessManager class how to sort the dates
    @Override
    public int compareTo(WorkoutSession otherDate) {
        if(otherDate == null){
            throw new NullPointerException("Cannot compare with null");
        }
        return this.date.compareTo(otherDate.date);
    }

    //for printing and debugging
    public String toString(){
        return "Date: " + getDate() + "\n" +
                "Activity: " + getActivityType() + "\n" +
                "Duration (min): " + getDuration() + "\n" +
                "Calories burned: " + getCaloriesBurned() + "\n";
    }
}
