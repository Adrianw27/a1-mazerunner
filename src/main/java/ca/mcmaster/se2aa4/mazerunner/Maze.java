package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class Maze {
    private char[][] grid;

    public Maze(char[][] grid){
        this.grid = grid;
    }

    public char[][] getGrid(){
        return grid;
    }

    public int getHeight() {
        return grid.length;
    }

    public int getWidth(){
        return grid[0].length;
    }
}