package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import org.apache.commons.cli.*;

public class Main {

    public static void main(String[] args) {

        // Setup command-line options -i and -p using builder pattern
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

        // Parse command line options with CLI API
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args); 
        } catch (ParseException e) {
            System.exit(0);
        }

        // Get file path
        String inputFilePath = cmd.getOptionValue("i");

        // Parse maze from file
        char[][] mazeData = parseMaze(inputFilePath);
        Maze maze = new Maze(mazeData);

        if (cmd.hasOption("p")) { // If -p flag used, validate provided instructions
            String providedPath = cmd.getOptionValue("p");
            boolean valid = MazeSolver.validatePath(maze.getGrid(), maze.getEntry(), maze.getExit(), providedPath);
            if(valid){
                System.out.println("correct path");
            }
            else{
                System.out.println("incorrect path");
            }
        }
        else { // If no -p flag, print factorized maze instructions to user
            List<Character> path = MazeSolver.findAnyPath(maze.getGrid(), maze.getEntry(), maze.getExit());
            String factorizedPath = MazeSolver.factorizePath(path);
            System.out.println(factorizedPath);
        }
    }

    /**
     * Parses maze file line by line into 2D grid
     * Walls - '#', Spaces - ' '
     */
    public static char[][] parseMaze(String inputFilePath) {
        List<char[]> rows = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            int maxLen = 0;
            while ((line = reader.readLine()) != null) { // Loop through each line
                char[] lineChars = line.toCharArray();
                if (lineChars.length > maxLen){ // Use condition to find max length of the rows (may be needed if an empty row has to be initialized)
                    maxLen = lineChars.length;
                }
                if(lineChars.length == 0){ // If a row has no walls, iniatialize it to an array full of spaces of max length (rather than an empty array)
                    lineChars = new char[maxLen];
                    Arrays.fill(lineChars, ' ');
                }
                rows.add(lineChars);
            }
        } catch (Exception e) {
            System.exit(0); // Exit on errors
        }

        return rows.toArray(new char[0][]); // Convert data from array list to 2D array
    }
}
