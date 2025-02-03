package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

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
            logger.error("An error has occurred while parsing command line options", e);
        }

        // Get file path
        String inputFilePath = cmd.getOptionValue("i");

        char[][] mazeData = parseMaze(inputFilePath);
        Maze maze = new Maze(mazeData);

        logger.info("**** Computing path");

        List<Character> path = MazeSolver.findAnyPath(mazeData, maze.getEntry(), maze.getExit());
        if (path.isEmpty()) {
            logger.info("No path found using right-hand exploration.");
        } else {
            logger.info("Path using right-hand exploration:");
            for (int i = 0; i < path.size(); i++) {
                if(i > 0 && path.get(i) != path.get(i-1)){
                    logger.info(" ");
                }
                logger.info(path.get(i));
            }
        }

        logger.info("** End of MazeRunner");
    }

    /**
     * Reads the maze file line by line and builds a 2D grid with it
     * Walls are represented by '#' and open passages by ' '
     */
    public static char[][] parseMaze(String inputFilePath) {
        List<char[]> rows = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            logger.info("**** Reading the maze from file {}", inputFilePath);
            String line;
            while ((line = reader.readLine()) != null) {
                char[] lineChars = line.toCharArray();
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        logger.debug("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        logger.debug("PASS ");
                    }
                }
                logger.debug(System.lineSeparator());
                rows.add(lineChars);
            }
        } catch (Exception e) {
            logger.error("An error has occurred while reading the maze file", e);
        }

        return rows.toArray(new char[0][]);
    }
}
