package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class RightHandMazeSolverTest {

    private final RightHandMazeSolver solver = new RightHandMazeSolver();

    private final char[][] simpleMaze = {
        {' ', '#', '#', '#'},
        {' ', ' ', ' ', '#'},
        {'#', '#', ' ', ' '}
    };

    private final int entry = 0;
    private final int exit = 2;

    @Test
    public void testValidateCorrectPath() {
        String path = "F R F F L F";
        assertTrue(solver.validatePath(simpleMaze, entry, exit, path));
    }

    @Test
    public void testValidateIncorrectPathWall() {
        String path = "F F F"; // Hits a wall
        assertFalse(solver.validatePath(simpleMaze, entry, exit, path));
    }

    @Test
    public void testValidatePathOutOfBounds() {
        String path = "L F"; // Goes off-grid left
        assertFalse(solver.validatePath(simpleMaze, entry, exit, path));
    }

    @Test
    public void testValidatePathInvalidCharacter() {
        String path = "F X F"; // 'X' is not valid
        assertFalse(solver.validatePath(simpleMaze, entry, exit, path));
    }

    @Test
    public void testValidateEmptyPath() {
        String path = "";
        assertFalse(solver.validatePath(simpleMaze, entry, exit, path));
    }

    @Test
    public void testValidateIncompletePath() {
        String path = "F R"; // Incomplete
        assertFalse(solver.validatePath(simpleMaze, entry, exit, path));
    }

    @Test
    public void testValidatePathWithSpaces() {
        String path = "F   R   F   F   L  F";
        assertTrue(solver.validatePath(simpleMaze, entry, exit, path));
    }

    @Test
    public void testFindAnyPathReturnsPath() {
        List<Character> path = solver.findAnyPath(simpleMaze, entry, exit);
        assertNotNull(path);
        assertFalse(path.isEmpty());
    }

    @Test
    public void testFindAnyPathIsValid() {
        List<Character> path = solver.findAnyPath(simpleMaze, entry, exit);
        String canonical = path.stream().map(String::valueOf).reduce("", String::concat);
        assertTrue(solver.validatePath(simpleMaze, entry, exit, canonical));
    }

    @Test
    public void testFindPathInUnsolvableMaze() {
        char[][] blockedMaze = {
            {' ', '#', '#'},
            {'#', '#', '#'},
            {' ', '#', ' '}
        };
        List<Character> path = solver.findAnyPath(blockedMaze, 0, 2);
        String canonical = path.stream().map(String::valueOf).reduce("", String::concat);
        assertFalse(solver.validatePath(blockedMaze, 0, 2, canonical));
    }
}
