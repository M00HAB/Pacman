package model;

import javafx.geometry.Point2D;

public class Pacman extends Character {


    private Board board;
    private Board.cellValue[][] grid;
    private Direction direction;
    /*Constructor
     * @param board is the board of the game.
     * @param location is the Pacman location (x,y).
     * @param velocity is the Pacman velocity.
     * @param lastDirection.
     * @param currentDirection.
     */
    public  Pacman(Board board){
        this.board = board;
        grid = new Board.cellValue[board.getRowCount()][board.getColumnCount()];
    }
    @Override
    public void move(){
        Point2D derivativePacmanVelocity = changeVelocity(direction);
        Point2D derivativePacmanLocation = getLocation().add(derivativePacmanVelocity);
        //going off-screen todo

        if(direction.equals(getLastDirection())){
            if(grid[(int) derivativePacmanLocation.getX()][(int) derivativePacmanLocation.getY()] == Board.cellValue.WALL){
                setVelocity(changeVelocity(Direction.NONE));
                setLastDirection(Direction.NONE);
            }else{
                setVelocity(derivativePacmanVelocity);
                setLocation(derivativePacmanLocation);
            }
        }
        else{
            if(grid[(int) derivativePacmanLocation.getX()][(int) derivativePacmanLocation.getY()] == Board.cellValue.WALL){
                derivativePacmanVelocity = changeVelocity(getLastDirection());
                derivativePacmanLocation = getLocation().add(derivativePacmanVelocity);

                if(grid[(int) derivativePacmanLocation.getX()][(int) derivativePacmanLocation.getY()] == Board.cellValue.WALL){
                    setVelocity(changeVelocity(Direction.NONE));
                    setLastDirection(Direction.NONE);
                }else{
                    setVelocity(changeVelocity(getLastDirection()));
                    setLocation(getLocation().add(getVelocity()));
                }
            }else {
                //keep moving
                setVelocity(derivativePacmanVelocity);
                setLocation(derivativePacmanLocation);
                setLastDirection(direction);
            }
        }

    }

    public Board getBoard() {
        return board;
    }

    public Pacman setBoard(Board board) {
        this.board = board;
        return this;
    }

    public Board.cellValue[][] getGrid() {
        return grid;
    }

    public Pacman setGrid(Board.cellValue[][] grid) {
        this.grid = grid;
        return this;
    }

    public Direction getDirection() {
        return direction;
    }

    public Pacman setDirection(Direction direction) {
        this.direction = direction;
        return this;
    }
}
