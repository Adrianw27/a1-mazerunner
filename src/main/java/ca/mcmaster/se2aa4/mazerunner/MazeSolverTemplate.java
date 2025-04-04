package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public abstract class MazeSolverTemplate implements MazeSolverAlgorithm {

    @Override
    public final List<Character> findAnyPath(Maze maze) {
        // Delegate solving to the concrete subclass
        return solveMaze(maze);
    }

    // Concrete solvers override this method to implement their own search algorithm to solve maze
    protected abstract List<Character> solveMaze(Maze maze);

    /*
    * Validate path logic is the same no matter what concrete solver is used
    * Validate a path provided by the user by attempting to traverse the maze from entry to exit
    * Params: Maze - grid, entry row, exit row, instructions - provided by user in canonical form
    */
   @Override
    public boolean validatePath(Maze maze, String instructions) {
        char[][] grid = maze.getGrid();
        int entry = maze.getEntry();
        int exit = maze.getExit();

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
                if (newRow < 0 || newRow >= grid.length || newCol < 0 || newCol >= grid[0].length || grid[newRow][newCol] == '#') {
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
        return (currentRow == exit && currentCol == grid[0].length - 1); // If instructions are fully looped through, return true only if current position is the exit
    }
}
