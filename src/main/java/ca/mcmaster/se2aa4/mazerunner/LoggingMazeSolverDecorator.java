package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

// Concrete Decorator
public class LoggingMazeSolverDecorator extends MazeSolverDecorator {

    public LoggingMazeSolverDecorator(MazeSolverAlgorithm decoratedSolver) {
        super(decoratedSolver);
    }

    @Override
    public List<Character> findAnyPath(Maze maze) {
        System.out.println("Decorator: Starting maze solving...");
        List<Character> path = decoratedSolver.findAnyPath(maze);
        System.out.println("Decorator: Maze solved. Path: " + path);
        return path;
    }

    @Override
    public boolean validatePath(Maze maze, String instructions) {
        System.out.println("Decorator: Validating path: " + instructions);
        boolean valid = decoratedSolver.validatePath(maze, instructions);
        System.out.println("Decorator: Validation result: " + valid);
        return valid;
    }
}
