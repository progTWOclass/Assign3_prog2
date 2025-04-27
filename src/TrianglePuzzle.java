// -------------------------------------------------------
// Assignment 2
// Written by: Steve Banh 1971537
// For “Programming 2” Section 02 – Winter 2025
// --------------------------------------------------------
/*
 * This class is using recursive to solve the triangle path puzzle.
 *
 * The findMaxPath method recursively calculates the maximum-sum path from the top to the bottom of a number triangle
 * in which we represent it as a 2d ArrayList called ArrayList<ArrayList<Integer>> triangle. When going downwards, the program
 * builds paths by copying ArrayList<Integer> pathTaken to ArrayList<Integer> currentPath. It then adds elements to the
 * current path (ArrayList<Integer> currentPath) at each step, ensuring left and right branches have independent routes
 * to avoid overlapping modifications. When the program reaches the base case (the bottom row), it completes the path
 * by adding the last element and returns the result as a trackNum object which contains the sum and the path.
 * During the recursion's backtracking, it is comparing left and right child elements to select the biggest value,
 * and then it includes that value to the current path's total.
 * */
import java.util.ArrayList;
import java.util.Scanner;
public class TrianglePuzzle {

    public static trackNum findMaxPath(ArrayList<ArrayList<Integer>> triangle, int row, int col, ArrayList<Integer> pathTaken) {
        //Base case: we're at the bottom row
        if (row == triangle.size() - 1) {
            //find the current element of the last row.
            int value = triangle.get(row).get(col);
            //creates a copy of pathTaken arraylist called "completePath" to finalize the arraylist
            //by adding the last element and return this trackNum
            ArrayList<Integer> completePath = new ArrayList<>(pathTaken);
            completePath.add(value);
            return new trackNum(value, completePath);
        }

        // Get the current value of the upper row
        int currentValue = triangle.get(row).get(col);
        //creates different copies of pathTaken arraylist to store the left path and the right path
        //to avoid both overlapping
        ArrayList<Integer> currentPath = new ArrayList<>(pathTaken);
        currentPath.add(currentValue);

        //split a left and right children path
        trackNum left = findMaxPath(triangle, row + 1, col, currentPath);
        trackNum right = findMaxPath(triangle, row + 1, col + 1, currentPath);

        // Return the path with a higher sum
        if (left.sum > right.sum) {
            return new trackNum(currentValue + left.sum, left.path);
        } else {
            return new trackNum(currentValue + right.sum, right.path);
        }
    }

    public static void main(String[] args) {
        System.out.println("""
                Please enter numbers, separated by a space, to form a triangle
                Each row must have the same amount of numbers as the row number.
                (type 'done' to finish):""");

        Scanner input = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> triangle = new ArrayList<>();
        int rowNum = 0;
        while (true) {
            System.out.print("Row " + (rowNum + 1) + ": ");
            String line = input.nextLine().trim();
            if (line.equalsIgnoreCase("done")) {
                break;
            }

            String[] parts = line.split("\\s+");
            ArrayList<Integer> row = new ArrayList<>();
            try {
                for (String part : parts) {
                    row.add(Integer.parseInt(part));
                }

                //Validate triangle shape (each row should have 1 more element than the previous row)
                if (row.size() != rowNum + 1) {
                    System.out.println("Error: Row " + (rowNum + 1) + " should have " + (rowNum + 1) + " numbers.");
                    continue;
                }

                triangle.add(row);
                rowNum++;
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter only integers separated by spaces.");
            }
        }

        for(ArrayList<Integer> row : triangle){
            for (Integer col : row) {
                System.out.print(" " + col);
            }
            System.out.println();
        }

        trackNum result = findMaxPath(triangle, 0, 0, new ArrayList<>());
        System.out.println("Max Path Sum: " + result.sum);
        System.out.println("Path Taken: " + result.path);
    }
}

