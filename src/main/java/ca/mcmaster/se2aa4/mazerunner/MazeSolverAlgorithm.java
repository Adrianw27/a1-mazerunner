package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public interface MazeSolverAlgorithm {

    // Finds a path for the given maze
    List<Character> findAnyPath(Maze maze);

    // Validates a user-provided path against the given maze
    boolean validatePath(Maze maze, String instructions);
}
