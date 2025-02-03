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
        char[][] mazeData = MazeParser.parseMaze(inputFilePath);
        Maze maze = new Maze(mazeData);
        MazeSolverAlgorithm solver = new RightHandMazeSolver();

        if (cmd.hasOption("p")) { // If -p flag used, validate provided instructions
            String providedPath = cmd.getOptionValue("p");
            boolean valid = solver.validatePath(maze.getGrid(), maze.getEntry(), maze.getExit(), providedPath);
            if(valid){
                System.out.println("correct path");
            }
            else{
                System.out.println("incorrect path");
            }
        }
        else { // If no -p flag, print factorized maze instructions to user
            List<Character> path = solver.findAnyPath(maze.getGrid(), maze.getEntry(), maze.getExit());
            String factorizedPath = PathFormatter.factorizePath(path);
            System.out.println(factorizedPath);
        }
    }
}
