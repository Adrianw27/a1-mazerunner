import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class MazeSolver {
    
    private static class Position {
        int row, col;
        Position(int r, int c) { this.row = r; this.col = c; }
    }

    public static List<Position> findAnyPath(char[][] grid, int startRow, int startCol, int endRow, int endCol) {
        // 0 = Up, 1 = Right, 2 = Down, 3 = Left.
        int[] dRow = {-1, 0, 1, 0};
        int[] dCol = {0, 1, 0, -1};

        int currentRow = startRow;
        int currentCol = startCol;
        int direction = 1;

        List<Position> path = new ArrayList<>();
        path.add(new Position(currentRow, currentCol));

        int maxSteps = grid.length * grid[0].length * 4;
        int steps = 0;

        while (steps < maxSteps) {
            if (currentRow == endRow && currentCol == endCol) {
                return path;
            }

            int rightDir = (direction + 1) % 4;
            int rightRow = currentRow + dRow[rightDir];
            int rightCol = currentCol + dCol[rightDir];

            if (isFree(grid, rightRow, rightCol)) {
                direction = rightDir;
                currentRow = rightRow;
                currentCol = rightCol;
                path.add(new Position(currentRow, currentCol));
                steps++;
                continue;
            }

            int forwardRow = currentRow + dRow[direction];
            int forwardCol = currentCol + dCol[direction];
            if (isFree(grid, forwardRow, forwardCol)) {
                currentRow = forwardRow;
                currentCol = forwardCol;
                path.add(new Position(currentRow, currentCol));
                steps++;
                continue;
            }

            int leftDir = (direction + 3) % 4;
            int leftRow = currentRow + dRow[leftDir];
            int leftCol = currentCol + dCol[leftDir];
            if (isFree(grid, leftRow, leftCol)) {
                direction = leftDir;
                currentRow = leftRow;
                currentCol = leftCol;
                path.add(new Position(currentRow, currentCol));
                steps++;
                continue;
            }

            int backDir = (direction + 2) % 4;
            int backRow = currentRow + dRow[backDir];
            int backCol = currentCol + dCol[backDir];
            if (isFree(grid, backRow, backCol)) {
                direction = backDir;
                currentRow = backRow;
                currentCol = backCol;
                path.add(new Position(currentRow, currentCol));
                steps++;
                continue;
            }

            break;
        }
        return Collections.emptyList();
    }

    private static boolean isFree(char[][] grid, int row, int col) {
        return row >= 0 && row < grid.length &&
               col >= 0 && col < grid[0].length &&
               grid[row][col] != '#';
    }
}
