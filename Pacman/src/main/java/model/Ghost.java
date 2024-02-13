package model;

import javafx.geometry.Point2D;

import java.util.Random;

public class Ghost extends Character{

    private Board board;
    private Direction direction;
    private int colorType;
    private boolean ghostEatingMode;

    private Point2D pacmanLocation;
    private Point2D[] dataMoving;

    public Ghost(Board board,boolean ghostEatingMode,Point2D pacmanLocation){
        this.board = board;
        this.ghostEatingMode = ghostEatingMode;
        this.pacmanLocation = pacmanLocation;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getColorType() {
        return colorType;
    }

    public Ghost setColorType(int colorType) {
        this.colorType = colorType;
        return this;
    }

    public boolean isGhostEatingMode() {
        return ghostEatingMode;
    }

    public Ghost setGhostEatingMode(boolean ghostEatingMode) {
        this.ghostEatingMode = ghostEatingMode;
        return this;
    }

    public Point2D getPacmanLocation() {
        return pacmanLocation;
    }

    public Ghost setPacmanLocation(Point2D pacmanLocation) {
        this.pacmanLocation = pacmanLocation;
        return this;
    }

    public Point2D[] getDataMoving() {
        return dataMoving;
    }

    public Ghost setDataMoving(Point2D[] dataMoving) {
        this.dataMoving = dataMoving;
        return this;
    }

    /**
     * Ghost Move randomly until hit wall, then change direction randomly.
     * but if the pacman in same row or column and not in eatingMode they chase until you get wall.
     * then u will change different direction
     * but if ghost in eating mode will move away from pacman
     */
    @Override
    public void move() {
        Random random = new Random();
        //pacmanLocation =getPacmanLocation();
        Point2D derivativeGhostLocation;
        if(ghostEatingMode == false) {
            // pacman in same column;
            if (getLocation().getY() == pacmanLocation.getY()) {
                //try to chase pacman.
                if (getLocation().getX() > pacmanLocation.getX()) {
                    setVelocity(changeVelocity(Direction.UP));
                } else {
                    setVelocity(changeVelocity(Direction.DOWN));
                }

                derivativeGhostLocation = getLocation().add(getVelocity());
                // if ghost going-off screen
                derivativeGhostLocation = setGoingOffScreen(derivativeGhostLocation, board);
                // generate random directions (Not Wall)
                while (board.getGrid()[(int) derivativeGhostLocation.getX()][(int) derivativeGhostLocation.getY()] == Board.cellValue.WALL) {
                    int randomNumber = random.nextInt(4);
                    Direction direction1 = integerToDirection(randomNumber);
                    setVelocity(changeVelocity(direction1));
                    derivativeGhostLocation = getLocation().add(getVelocity());
                }
                setLocation(derivativeGhostLocation);
            }
            // same row
        }else if(getLocation().getX() == pacmanLocation.getX()){
            //chase pacman
            if(getLocation().getY() > pacmanLocation.getY()){
                setVelocity(changeVelocity(Direction.LEFT));
            }else{
                setVelocity(changeVelocity(Direction.RIGHT));
            }
            derivativeGhostLocation = getLocation().add(getVelocity());
            //going off-screen
            derivativeGhostLocation = setGoingOffScreen(derivativeGhostLocation,board);
            //generate random direction (not wall)
            while(board.getGrid()[(int) getLocation().getX()][(int) getLocation().getY()] == Board.cellValue.WALL){
                int randomNumber = random.nextInt(4);
                Direction direction1 = integerToDirection(randomNumber);
                setVelocity(changeVelocity(direction1));
                derivativeGhostLocation = getLocation().add(getVelocity());
            }
            setLocation(derivativeGhostLocation);
            //Move Randomly
        }else{
            derivativeGhostLocation = getLocation().add(getVelocity());
            derivativeGhostLocation = setGoingOffScreen(derivativeGhostLocation,board);
            while(board.getGrid()[(int) derivativeGhostLocation.getX()][(int) derivativeGhostLocation.getY()] == Board.cellValue.WALL){
                int randomNumber = random.nextInt(4);
                Direction direction1 = integerToDirection(randomNumber);
                setVelocity(changeVelocity(direction1));
                derivativeGhostLocation = getLocation().add(getVelocity());
            }
            setLocation(derivativeGhostLocation);
        }

        // if in ghostingMode
        //
        if(ghostEatingMode == true){
            //same column
            if(getLocation().getY() == pacmanLocation.getY()){
                if(getLocation().getX() > pacmanLocation.getX()){
                    setVelocity(changeVelocity(Direction.DOWN));
                }else{
                    setVelocity(changeVelocity(Direction.UP));
                }
                derivativeGhostLocation = getLocation().add(getVelocity());
                derivativeGhostLocation = setGoingOffScreen(derivativeGhostLocation,board);
                while(board.getGrid()[(int) derivativeGhostLocation.getX()][(int) derivativeGhostLocation.getY()] == Board.cellValue.WALL){
                    int randomNumber = random.nextInt(4);
                    Direction direction1 = integerToDirection(randomNumber);
                    setVelocity(changeVelocity(direction1));
                    derivativeGhostLocation = getLocation().add(getVelocity());
                }
                setLocation(derivativeGhostLocation);
            }else if(getLocation().getX() == pacmanLocation.getX()){
                if(getLocation().getY() > pacmanLocation.getY()){
                    setVelocity(changeVelocity(Direction.RIGHT));
                }else{
                    setVelocity(changeVelocity(Direction.LEFT));
                }
                derivativeGhostLocation = getLocation().add(getVelocity());
                derivativeGhostLocation = setGoingOffScreen(derivativeGhostLocation,board);
                while(board.getGrid()[(int) derivativeGhostLocation.getX()][(int) derivativeGhostLocation.getY()] == Board.cellValue.WALL){
                    int randomNumber = random.nextInt(4);
                    Direction direction1 = integerToDirection(randomNumber);
                    setVelocity(changeVelocity(direction1));
                    derivativeGhostLocation = getLocation().add(getVelocity());
                }
                setLocation(derivativeGhostLocation);
            }else{
                derivativeGhostLocation = getLocation().add(getVelocity());
                derivativeGhostLocation = setGoingOffScreen(derivativeGhostLocation,board);
                while(board.getGrid()[(int) derivativeGhostLocation.getX()][(int) derivativeGhostLocation.getY()] == Board.cellValue.WALL){
                    int randomNumber = random.nextInt(4);
                    Direction direction1 = integerToDirection(randomNumber);
                    setVelocity(changeVelocity(direction1));
                    derivativeGhostLocation = getLocation().add(getVelocity());
                }
                setLocation(derivativeGhostLocation);
                dataMoving = new Point2D[]{getVelocity(), getLocation()};
            }
        }


    }

}
