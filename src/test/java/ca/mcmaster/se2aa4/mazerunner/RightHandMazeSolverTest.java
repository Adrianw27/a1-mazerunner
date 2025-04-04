package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Arrays;

public class RightHandMazeSolverTest {

    private final RightHandMazeSolver solver = new RightHandMazeSolver();
    private final PathFormatter formatter = new PathFormatter();

    private final char[][] mazeData = new char[][] {
        {'#', '#', '#', '#', '#', '#', '#'},
        {'#', '#', '#', ' ', ' ', ' ', '#'},
        {'#', '#', '#', ' ', '#', ' ', '#'},
        {' ', ' ', ' ', ' ', '#', ' ', ' '},
        {'#', '#', '#', '#', '#', '#', '#'},
        {'#', '#', '#', '#', '#', '#', '#'},
        {'#', '#', '#', '#', '#', '#', '#'}
    };

    private final Maze maze = new Maze(mazeData);

    // 1. Validate the correct path
    @Test
    public void testValidateCorrectPath() {
        String path = "F F F L F F R F F R F F L F";
        path = path.replace(" ", "");
        assertTrue(solver.validatePath(maze, path));
    }

    // 2. Validate a path that travels through walls fails
    @Test
    public void testValidatePathThroughWalls() {
        String path = "F F F F F F";
        path = path.replace(" ", "");
        assertFalse(solver.validatePath(maze, path));
    }

    // 3. Validate a path too short fails
    @Test
    public void testValidateIncorrectPath() {
        String path = "F F F";
        path = path.replace(" ", "");
        assertFalse(solver.validatePath(maze, path));
    }

    // 4. Validate that a path with invalid characters fails
    @Test
    public void testValidatePathInvalidCharacters() {
        String path = "F X F F L F F R F F R F F L F";
        path = path.replace(" ", "");
        assertFalse(solver.validatePath(maze, path));
    }

    // 5. Validate an empty path failure
    @Test
    public void testValidateEmptyPath() {
        String path = "";
        assertFalse(solver.validatePath(maze, path));
    }

    // 6. Validate that findAnyPath provides a non-empty path
    @Test
    public void testFindAnyPathNotEmpty() {
        List<Character> path = solver.findAnyPath(maze);
        assertFalse(path.isEmpty());
    }

    // 7. Validate that the path generated by findAnyPath is valid
    @Test
    public void testFindAnyPathIsValid() {
        List<Character> path = solver.findAnyPath(maze);
        String canonical = path.stream().map(String::valueOf).reduce("", String::concat);
        assertTrue(solver.validatePath(maze, canonical));
    }

    // 8. Test that the PathFormatter correctly factorizes a canonical path
    @Test
    public void testFactorizePath() {
        List<Character> path = Arrays.asList('F','F','L','F','F','R','F','F','R','F','F','L','F');
        assertEquals("2F 1L 2F 1R 2F 1R 2F 1L 1F", formatter.factorizePath(path));
    }

    // 8. Test that the PathFormatter does not fail on empty input
    @Test
    public void testFactorizeEmptyPath() {
        List<Character> path = Arrays.asList();
        assertEquals("", formatter.factorizePath(path));
    }

    // 9. Validate that a path going one square past exit fails
    @Test
    public void testValidatePathTooLong() {
        String path = "F F L F F R F F R F F L F F";
        path = path.replace(" ", "");
        assertFalse(solver.validatePath(maze, path));
    }
}
