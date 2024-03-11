package Logic;

public class Grid {

    private final static int GRID_HEIGHT = 5;
    private final static int GRID_WIDTH = 5;
    private static final int COORDINATE_MIN_VALUE = 0;
	private static final String ROBOT_WILL_FALL_ERROR_MESSAGE = "The robot will fall";

    private Robot robot;

    public Grid() {
        robot = new Robot();
    }

    public void place(Coordinates coordinates, Direction direction) {
        robot.setCoordinates(coordinates);
        robot.setDirection(direction);
    }

    private static boolean isValidCoordinate(Coordinates coordinates) {
        Integer x = coordinates.getX();
        if (x == null) return false;
        if (x < COORDINATE_MIN_VALUE) return false;
        if (x >= GRID_WIDTH) return false;

        Integer y = coordinates.getY();
        if (y == null) return false;
        if (y < COORDINATE_MIN_VALUE) return false;
        if (y >= GRID_HEIGHT) return false;

        return true;
    }

    public void move() {
    	
         Coordinates nextCoordinates = robot.getDirection().moveTowards(robot.getCoordinates());
        if(!isValidCoordinate(nextCoordinates)){
            throw new IllegalStateException(ROBOT_WILL_FALL_ERROR_MESSAGE);
        }

        robot.setCoordinates(nextCoordinates);
    }

    public void left(){
      
        Direction newDirection = robot.getDirection().left();
        robot.setDirection(newDirection);
    }

    public void right(){
    
        Direction newDirection = robot.getDirection().right();
        robot.setDirection(newDirection);
    }

    public Direction getRobotDirection() {
        return robot.getDirection();
    }

}
