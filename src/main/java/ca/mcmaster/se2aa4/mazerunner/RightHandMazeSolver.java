package ca.mcmaster.se2aa4.mazerunner;

import java.util.*;

public class RightHandMazeSolver extends MazeSolverTemplate {

    // Finds path from entry to exit using right-hand exploration
    @Override
    protected List<Character> solveMaze(Maze maze) {
        char[][] grid = maze.getGrid();
        int entry = maze.getEntry();
        int exit = maze.getExit();

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
        int maxSteps = grid.length * grid[0].length * 4;
        int steps = 0;

        while (steps < maxSteps) {

            // Break if exit square is reached
            if (currentRow == exit && currentCol == grid[0].length - 1) {
                break;
            }

            // Check a right turn first
            int rightDir = (direction + 1) % 4; // Find new direction if the turn was right
            int rightRow = currentRow + dRow[rightDir]; // Find new row and column if turning right then stepping forward
            int rightCol = currentCol + dCol[rightDir];
            if (Maze.isEmpty(grid, rightRow, rightCol)) { // If the cell to the right is empty, carry-out the turn and step
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
            if (Maze.isEmpty(grid, forwardRow, forwardCol)) { // If the cell ahead is empty, step forward
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
            if (Maze.isEmpty(grid, leftRow, leftCol)) { // If the cell to the left is empty, turn left and step forward
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
}
