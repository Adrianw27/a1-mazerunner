package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class PathFormatter {

    // Takes in the canonical path and factorizes it.
    public static String factorizePath(List<Character> path) {
        if (path == null || path.isEmpty()) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder(); // Use stringbuilder to easily edit the path
        int count = 1; // Counter for repititions of current direction
        char previous = path.get(0); 
        
        for (int i = 1; i < path.size(); i++) { // Loop through path
            char current = path.get(i); 
            if (current == previous) { // If the current direction is the same as the previous one, increment counter
                count++;
            } else { // As soon as current direction differs from the previous one: 
                sb.append(count).append(previous).append(" "); // Append the count storing the repititions of the previous direction and the previous direction
                previous = current; // Set the previous direction to the new current one
                count = 1; // Reset the count
            }
        }
        sb.append(count).append(previous); // Append the final count and previous direction
        
        return sb.toString();
    }
}