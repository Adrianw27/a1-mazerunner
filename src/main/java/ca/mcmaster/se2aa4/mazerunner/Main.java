package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        // Apache CLI setup
        Option inputFileOption = new Option("i");
        inputFileOption.setRequired(true);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

        // Parsing command line
        try {
            cmd = parser.parse(option, args);
        } catch(ParseException e) {
            logger.error("/!\\ An error has occured /!\\");
        }

        // Store file path in a string
        String inputFilePath = cmd.getOptionValue("input");

        char[][] mazeData = parseMaze(inputFilePath);
        Maze maze = new Maze(mazeData);
        
        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }

    public char[][] parseMaze(String inputFilePath){
        List<char[]> rows = new ArrayList<>();

        try {
            System.out.println("**** Reading the maze from file " + inputFilePath);
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
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
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }

        return rows.toArray(new char[0][]);
    }

}
