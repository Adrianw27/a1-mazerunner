package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        Option inputFileOption = Option.builder("i")
                .longOpt("input")
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
            logger.error("/!\\ An error has occured /!\\");
        }

        String inputFilePath = cmd.getOptionValue("input");

        char[][] mazeData = parseMaze(inputFilePath);
        Maze maze = new Maze(mazeData);

        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }

    public static char[][] parseMaze(String inputFilePath) {
        List<char[]> rows = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            logger.info("**** Reading the maze from file {}", inputFilePath);
            String line;
            while ((line = reader.readLine()) != null) {
                char[] lineChars = line.toCharArray();
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        logger.info("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        logger.info("PASS ");
                    }
                }
                logger.info(System.lineSeparator());
                rows.add(lineChars);
            }
        } catch (Exception e) {
            logger.error("An error has occurred while reading the maze file", e);
        }

        return rows.toArray(new char[0][]);
    }
}
