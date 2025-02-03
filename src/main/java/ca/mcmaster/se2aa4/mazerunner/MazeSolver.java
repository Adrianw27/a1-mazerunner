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
                path.add('F');
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
                path.add('F');
                steps++;
                continue;
            }

            // If none of the above are free, turn around
            direction = (direction + 2) % 4;
            path.add('R');
            path.add('R');
            steps++;
        }

        return path;
    }

    public static boolean validatePath(char[][] maze, int entry, int exit, String instructions) {
        // 0 = Up, 1 = Right, 2 = Down, 3 = Left
        int[] dRow = {-1, 0, 1, 0};
        int[] dCol = {0, 1, 0, -1};

        int direction = 1;
        int currentRow = entry;
        int currentCol = 0;

        for (char c : instructions.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            if (c == 'F') {
                int newRow = currentRow + dRow[direction];
                int newCol = currentCol + dCol[direction];
                // If the move is out of bounds or hits a wall, path not valid
                if (newRow < 0 || newRow >= maze.length ||
                    newCol < 0 || newCol >= maze[0].length ||
                    maze[newRow][newCol] == '#') {
                    return false;
                }
                currentRow = newRow;
                currentCol = newCol;
            } else if (c == 'R') {
                direction = (direction + 1) % 4;
            } else if (c == 'L') {
                direction = (direction + 3) % 4;
            } else {
                return false;
            }
        }
        return (currentRow == exit && currentCol == maze[0].length - 1);
    }

    private static boolean isFree(char[][] maze, int row, int col) {
        return row >= 0 && row < maze.length &&
               col >= 0 && col < maze[0].length &&
               maze[row][col] != '#';
    }

    public static String factorizePath(List<Character> path) {
        if (path == null || path.isEmpty()) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        int count = 1;
        char previous = path.get(0);
        
        for (int i = 1; i < path.size(); i++) {
            char current = path.get(i);
            if (current == previous) {
                count++;
            } else {
                sb.append(count).append(previous).append(" ");
                previous = current;
                count = 1;
            }
        }
        sb.append(count).append(previous);
        
        return sb.toString();
    }
}
