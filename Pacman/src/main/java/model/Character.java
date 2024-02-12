package model;

import javafx.geometry.Point2D;

abstract public class Character {
    public enum  Direction{
        NONE,
        UP,
        DOWN,
        LEFT,
        RIGHT,
    };

    private Point2D location;
    private Point2D velocity;
    private Direction currentDirection;
    private Direction lastDirection;

    public Point2D getLocation() {
        return location;
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }

    public Point2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }


    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public Direction getLastDirection() {
        return lastDirection;
    }

    public void setLastDirection(Direction lastDirection) {
        this.lastDirection = lastDirection;
    }

    public abstract void move();

    public Point2D changeVelocity(Direction direction){
        if(direction == Direction.UP){
            return new Point2D(-1,0);
        }else if(direction == Direction.DOWN){
            return new Point2D(1,0);
        }else if(direction == Direction.RIGHT){
            return new Point2D(0,1);
        }else if(direction == Direction.LEFT){
            return new Point2D(0,-1);
        }else{
            //Nothing New
            return new Point2D(0,0);
        }
    }
    public Point2D setGoingOffScreen(Point2D elementLocation, Board board) {
        if(elementLocation.getY() >= board.getColumnCount()){
            elementLocation = new Point2D(elementLocation.getX(),0);
        }
        if(elementLocation.getY() < 0){
            elementLocation = new Point2D(elementLocation.getX(), board.getColumnCount() - 1);
        }
        return elementLocation;
    }

    public Direction integerToDirection(int integer){
        switch (integer){
            case 0:
                return Direction.LEFT;
            case 1:
                return  Direction.RIGHT;
            case 2:
                return Direction.UP;
            case 3:
                return Direction.DOWN;
            default:
                return Direction.NONE;
        }

    }

}
