package ca.mcmaster.se2aa4.mazerunner;

public class Maze {
    private char[][] grid;
    private int entry;
    private int exit;

    public Maze(char[][] grid) {
        this.grid = grid;
        this.entry = findEntry();
        this.exit = findExit();
    }

    private int findEntry() {
        // Look for the first open space in the first column
        for (int row = 0; row < grid.length; row++) {
            if (grid[row].length > 0 && grid[row][0] == ' ') {
                return row;
            }
        }
        return 0;
    }

    private int findExit() {
        // Look for the first open space in the last column
        int lastCol = grid[0].length - 1;
        for (int row = 0; row < grid.length; row++) {
            if (grid[row][lastCol] == ' ') {
                return row;
            }
        }
        return 0;
    }

    public char[][] getGrid() {
        return grid;
    }

    public int getEntry() {
        return entry;
    }

    public int getExit() {
        return exit;
    }
}
