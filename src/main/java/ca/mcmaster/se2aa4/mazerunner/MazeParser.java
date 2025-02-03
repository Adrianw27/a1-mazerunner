package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class MazeParser {
    
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
