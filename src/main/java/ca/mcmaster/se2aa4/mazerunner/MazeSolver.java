package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class MazeSolver {

    // Finds path from entry to exit using right-hand exploration
    public static List<Character> findAnyPath(char[][] maze, int entry, int exit) {

        // Change in row or col variable based on the current direction
        // Indexes: 0 = Up, 1 = Right, 2 = Down, 3 = Left.
        int[] dRow = {-1, 0, 1, 0};
        int[] dCol = {0, 1, 0, -1};

        // Start facing right
        int direction = 1;

        // Set current position to maze[entry][0]
        int currentRow = entry;
        int currentCol = 0;

        List<Character> path = new ArrayList<>();

        // Max steps and step counter to prevent infinite looping
        int maxSteps = maze.length * maze[0].length * 4;
        int steps = 0;

        while (steps < maxSteps) {

            // Break if exit square is reached
            if (currentRow == exit && currentCol == maze[0].length - 1) {
                break;
            }

            // Check a right turn first
            int rightDir = (direction + 1) % 4; // Find new direction if the turn was right
            int rightRow = currentRow + dRow[rightDir]; // Find new row and column if turning right then stepping forward
            int rightCol = currentCol + dCol[rightDir];
            if (Maze.isEmpty(maze, rightRow, rightCol)) { // If the cell to the right is empty, carry-out the turn and step
                direction = rightDir; // Update direction and position
                currentRow = rightRow;
                currentCol = rightCol;
                path.add('R'); // Update path
                path.add('F');
                steps++;
                continue;
            }

            // Check forward cell next
            int forwardRow = currentRow + dRow[direction]; // Find new row and column if moving forward (no direction change)
            int forwardCol = currentCol + dCol[direction];
            if (Maze.isEmpty(maze, forwardRow, forwardCol)) { // If the cell ahead is empty, step forward
                currentRow = forwardRow; // Update position
                currentCol = forwardCol;
                path.add('F'); // Update path
                steps++;
                continue;
            }

            // Check left cell last
            int leftDir = (direction + 3) % 4; // Find new direction after turning left using wrap around
            int leftRow = currentRow + dRow[leftDir]; // Find new row and columns if the turn was made
            int leftCol = currentCol + dCol[leftDir];
            if (Maze.isEmpty(maze, leftRow, leftCol)) { // If the cell to the left is empty, turn left and step forward
                direction = leftDir; // Update direction and position
                currentRow = leftRow;
                currentCol = leftCol;
                path.add('L'); // Update path
                path.add('F');
                steps++;
                continue;
            }

            // If none of the above are free, turn around
            direction = (direction + 2) % 4; // Reverse direction
            path.add('R'); // Update path with 180 degree turn
            path.add('R');
            steps++;
        }

        return path;
    }

    /*
    * Validate a path provided by the user by attempting to traverse the maze from entry to exit
    * Params: Maze - grid, entry row, exit row, instructions - provided by user in canonical form
    */
    public static boolean validatePath(char[][] maze, int entry, int exit, String instructions) {
        // 0 = Up, 1 = Right, 2 = Down, 3 = Left
        int[] dRow = {-1, 0, 1, 0};
        int[] dCol = {0, 1, 0, -1};

        int direction = 1;
        int currentRow = entry;
        int currentCol = 0;

        // Loop through all instructions
        for (char c : instructions.toCharArray()) {
            if (c == ' ') { // Skip if space encountered
                continue;
            }
            if (c == 'F') { // Step forward on an F
                int newRow = currentRow + dRow[direction]; // Check what the new position would be after the step
                int newCol = currentCol + dCol[direction];
                if (newRow < 0 || newRow >= maze.length || newCol < 0 || newCol >= maze[0].length || maze[newRow][newCol] == '#') {
                    return false; // If move is out of bounds, or it hits a wall, the path is invalid
                }
                currentRow = newRow; // If path not yet invalidated, update position
                currentCol = newCol;
            } else if (c == 'R') { // On R, change direction one turn to the right
                direction = (direction + 1) % 4;
            } else if (c == 'L') { // On L, change direcition one turn to the left
                direction = (direction + 3) % 4;
            } else { // Any other instruction gets invalidated
                return false;
            }
        }
        return (currentRow == exit && currentCol == maze[0].length - 1); // If instructions are fully looped through, return true only if current position is the exit
    }

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
