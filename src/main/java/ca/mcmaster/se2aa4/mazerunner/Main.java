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
        Option pathOption = Option.builder("p")
                .hasArg(true)
                .desc("Path instructions")
                .required(false)
                .build();

        Options options = new Options();
        options.addOption(inputFileOption);
        options.addOption(pathOption);

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

        if (cmd.hasOption("p")) {
            String providedPath = cmd.getOptionValue("p");
            boolean valid = MazeSolver.validatePath(mazeData, maze.getEntry(), maze.getExit(), providedPath);
            if(valid){
                System.out.println("That is a valid path");
            }
            else{
                System.out.println("That is NOT a valid path");
            }
        }
        else {
            List<Character> path = MazeSolver.findAnyPath(mazeData, maze.getEntry(), maze.getExit());
            if (path.isEmpty()) {
                System.out.println("No path found.");
            } else {
                String factorizedPath = MazeSolver.factorizePath(path);
                System.out.println(factorizedPath);
            }
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
            int maxLen = 0;
            while ((line = reader.readLine()) != null) {
                char[] lineChars = line.toCharArray();
                if (lineChars.length > maxLen){
                    maxLen = lineChars.length;
                }
                if(lineChars.length == 0){
                    lineChars = new char[maxLen];
                    Arrays.fill(lineChars, ' ');
                }
                rows.add(lineChars);
            }
        } catch (Exception e) {
            System.exit(0);
        }

        return rows.toArray(new char[0][]);
    }
}
