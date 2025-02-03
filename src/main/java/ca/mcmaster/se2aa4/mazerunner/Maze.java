package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class Maze {
    private char[][] grid;
    private int entry,exit;

    public Maze(char[][] grid){
        this.grid = grid;
        this.entry = findEntry();
        this.exit = findExit();
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

    // Search first column for empty entry tile
    private int findEntry(){
        for(int i = 0; i < grid.length; i++){
            if(this.grid[i][0] != '#'){
                return i;
            }
        }
        return -1;
    }

    // Search last column for empty entry tile
    private int findExit(){
        for(int i = 0; i < grid.length; i++){
            if(this.grid[0][grid[0].length-1] != '#'){
                return i;
            }
        }
        return -1;
    }
}