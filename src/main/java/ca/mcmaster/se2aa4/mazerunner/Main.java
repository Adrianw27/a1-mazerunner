package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import org.apache.commons.cli.*;

public class Main {

    public static void main(String[] args) {

        // Setup command-line option: -i
        Option inputFileOption = Option.builder("i")
                .hasArg(true)
                .desc("Path to input file")
                .required(true)
                .build();

        Options options = new Options();
        options.addOption(inputFileOption);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.exit(0);
        }

        // Get file path
        String inputFilePath = cmd.getOptionValue("i");

        char[][] mazeData = parseMaze(inputFilePath);
        Maze maze = new Maze(mazeData);

        List<Character> path = MazeSolver.findAnyPath(mazeData, maze.getEntry(), maze.getExit());
        if (path.isEmpty()) {
            System.out.println("No path found.");
        } else {
            String factorizedPath = MazeSolver.factorizePath(path);
            System.out.println(factorizedPath);
        }

    }

    /**
     * Reads the maze file line by line and builds a 2D grid with it
     * Walls are represented by '#' and open passages by ' '
     */
    public static char[][] parseMaze(String inputFilePath) {
        List<char[]> rows = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                char[] lineChars = line.toCharArray();
                rows.add(lineChars);
            }
        } catch (Exception e) {
            System.exit(0);
        }

        return rows.toArray(new char[0][]);
    }
}
