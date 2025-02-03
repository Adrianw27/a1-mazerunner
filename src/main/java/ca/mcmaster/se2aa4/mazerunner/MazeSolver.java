package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class MazeSolver {

    // Finds a path from the start to the goal position using right-hand exploration.
    public static List<Character> findAnyPath(char[][] maze, int entry, int exit) {

        // 0 = Up, 1 = Right, 2 = Down, 3 = Left.
        int[] dRow = {-1, 0, 1, 0};
        int[] dCol = {0, 1, 0, -1};

        // Start facing right
        int direction = 1;
        int currentRow = entry;
        int currentCol = 0;

        List<Character> path = new ArrayList<>();

        int maxSteps = maze.length * maze[0].length * 4;
        int steps = 0;

        while (steps < maxSteps) {
            if (currentRow == exit && currentCol == maze[0].length - 1) {
                break;
            }

            // Check the cell to the right of the current direction.
            int rightDir = (direction + 1) % 4;
            int rightRow = currentRow + dRow[rightDir];
            int rightCol = currentCol + dCol[rightDir];

            if (isFree(maze, rightRow, rightCol)) {
                direction = rightDir;
                currentRow = rightRow;
                currentCol = rightCol;
                path.add('R');
                steps++;
                continue;
            }

            // Check forward cell
            int forwardRow = currentRow + dRow[direction];
            int forwardCol = currentCol + dCol[direction];
            if (isFree(maze, forwardRow, forwardCol)) {
                currentRow = forwardRow;
                currentCol = forwardCol;
                path.add('F');
                steps++;
                continue;
            }

            // Check left cell
            int leftDir = (direction + 3) % 4; 
            int leftRow = currentRow + dRow[leftDir];
            int leftCol = currentCol + dCol[leftDir];
            if (isFree(maze, leftRow, leftCol)) {
                direction = leftDir;
                currentRow = leftRow;
                currentCol = leftCol;
                path.add('L');
                steps++;
                continue;
            }

            // If none of the above are free, turn around
            direction = (direction + 2) % 4;
            steps++;
        }

        return path;
    }

    private static boolean isFree(char[][] maze, int row, int col) {
        return row >= 0 && row < maze.length &&
               col >= 0 && col < maze[0].length &&
               maze[row][col] != '#';
    }
}
