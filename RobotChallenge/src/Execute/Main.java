package Execute;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import Logic.Coordinates;
import Logic.Direction;
import Logic.Grid;

public class Main {
    private static final String INVALID_COORDINATES_PARAMETERS = "Coordinates must be integers";
    private static final int DIRECTION_POSITION = 2;
    private static final int X_POSITION = 0;
    private static final int Y_POSITION = 1;

    private final Grid grid;

    public Main() {
        grid = new Grid();
    }

    public void runCommand(String commandAsString, String[] params) {
        switch (commandAsString) {
            case "PLACE":
                callPlace(params);
                break;
            case "MOVE":
                grid.move();
                break;
            case "LEFT":
                grid.left();
                break;
            case "RIGHT":
                grid.right();
                break;
            case "REPORT":
                displayMessage(grid.report());
                break;
            default:
                break;
        }
    }

    private void callPlace(String[] locationParams) {
     
        Coordinates coordinates = getCoordinatesFromParams(locationParams);
        Direction direction = Direction.find(locationParams[DIRECTION_POSITION]);
        grid.place(coordinates, direction);
    }

    private static Coordinates getCoordinatesFromParams(String[] params) {
        Integer x;
        Integer y;
        try {
            x = Integer.valueOf(params[X_POSITION]);
            y = Integer.valueOf(params[Y_POSITION]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_COORDINATES_PARAMETERS);
        }
        return new Coordinates(x, y);
    }


    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("One argument expected: path to the input file.");
            return;
        }

        List<String> inputLines = getLinesFromFiles(args);
        if (inputLines == null) {
            System.out.println("File not found");
            return;
        }

        Main main = new Main();
        for (String line : inputLines) {
            String[] lineAsArray = line.trim().split(" ");

            String command = lineAsArray[0];

            String[] params = getCommandParams(lineAsArray);
            try {
            	main.runCommand(command, params);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static String[] getCommandParams(String[] lineAsArray) {
        if (lineAsArray.length < 2) {
            return null;
        }

        return lineAsArray[1].split(",");
    }

    private static List<String> getLinesFromFiles(String[] args) {
        try {
        	//File path to read the content
        	Path myPath = Paths.get("C:\\Selenium_workspace\\RobotChallenge\\src\\Execute\\test.txt"); //C:\Selenium_workspace\RobotChallenge\src\Execute\test.txt
            return Files.readAllLines(myPath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Trying to find file: " + e);
            e.printStackTrace();
        }
        return null;
    }

    private static void displayMessage(String message) {
        System.out.println(message);
    }
}
