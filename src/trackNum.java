// -------------------------------------------------------
// Assignment 2
// Written by: Steve Banh 1971537
// For “Programming 2” Section 02 – Winter 2025
// --------------------------------------------------------
/*
* This is a class that helps store and track the numbers that give the maximum total sum
* of the triangle puzzle
* */
import java.util.ArrayList;
public class trackNum {
    int sum;
    ArrayList<Integer> path;

    public trackNum(int sum, ArrayList<Integer> path) {
        this.sum = sum;
        //Create a copy of the arraylist (path); this ensures that every trackNum created has its own 'path' arraylist
        //to prevent accidental overwriting during recursion
        this.path = new ArrayList<>(path);
    }
}
