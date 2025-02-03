package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public interface MazeSolverAlgorithm {

    // Finds a path for the given maze
    List<Character> findAnyPath(char[][] maze, int entry, int exit);

    // Validates a user-provided path against the given maze
    boolean validatePath(char[][] maze, int entry, int exit, String instructions);
}
