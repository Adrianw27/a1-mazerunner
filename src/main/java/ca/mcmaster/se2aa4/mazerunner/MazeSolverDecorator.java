package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

// Abstract Decorator 
public abstract class MazeSolverDecorator implements MazeSolverAlgorithm {
    protected MazeSolverAlgorithm decoratedSolver;

    public MazeSolverDecorator(MazeSolverAlgorithm decoratedSolver) {
        this.decoratedSolver = decoratedSolver;
    }

    @Override
    public List<Character> findAnyPath(Maze maze) {
        return decoratedSolver.findAnyPath(maze);
    }

    @Override
    public boolean validatePath(Maze maze, String instructions) {
        return decoratedSolver.validatePath(maze, instructions);
    }
}
