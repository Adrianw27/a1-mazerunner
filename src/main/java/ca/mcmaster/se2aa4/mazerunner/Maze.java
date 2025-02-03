package ca.mcmaster.se2aa4.mazerunner;

public class Maze {
    // Encapsulate maze data
    private char[][] grid;
    private int entry;
    private int exit;

    // Initialize maze
    public Maze(char[][] grid) {
        this.grid = grid;
        this.entry = findEntry();
        this.exit = findExit();
    }

    // Function to find entry - first open space in the first column
    private int findEntry() {
        for (int row = 0; row < grid.length; row++) {
            if (grid[row].length > 0 && grid[row][0] == ' ') {
                return row;
            }
        }
        return 0;
    }

    // Function to find exit - first open space in the last column
    private int findExit() {
        int lastCol = grid[0].length - 1;
        for (int row = 0; row < grid.length; row++) {
            if (grid[row][lastCol] == ' ') {
                return row;
            }
        }
        return 0;
    }

    // Takes in maze, row, and column. Checks if a cell is free or a wall.
    public static boolean isEmpty(char[][] maze, int row, int col) {
        if(row >= 0 && row < maze.length && col >= 0 && col < maze[0].length && maze[row][col] != '#'){
                return true;
               }
        else {
            return false;
        }
    }

    // Public getter to access grid
    public char[][] getGrid() {
        return grid;
    }

    // Public getter to access entry
    public int getEntry() {
        return entry;
    }

    // Public getter to access exit
    public int getExit() {
        return exit;
    }
}
