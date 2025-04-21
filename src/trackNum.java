/*
* This is a class that helps store and track the numbers that give the maximum total sum
* */
import java.util.ArrayList;
public class trackNum {
    int sum;
    ArrayList<Integer> path;

    public trackNum(int sum, ArrayList<Integer> path) {
        this.sum = sum;
        this.path = new ArrayList<>(path); // Create a copy to avoid reference issues
    }
}
