package model;

import javafx.geometry.Point2D;

public class GameEngine {

    private Board board = new Board();
    private int score;
    private int level;
    private int dotCount;
    private boolean gameOver;
    private boolean winGame;
    private Pacman pacman;
    private RedGhost redGhost ;
    private BlueGhost blueGhost ;
    private YellowGhost yellowGhost;

    public GameEngine(){

    }

    /**
     * Determine Locations of Characters.
     */
    public void determineLocations(){
        int rowCount = board.getRowCount();
        int columnCount = board.getColumnCount();
        for(int row = 0;row < rowCount;row++){
            for(int column = 0;column < columnCount;column++){
                switch (board.getCellValue(row,column)){
                    case Board.cellValue.PACMAN:
                        pacman.setLocation(new Point2D(row,columnCount));
                        pacman.setCurrentDirection(Character.Direction.NONE);
                        pacman.setLastDirection(Character.Direction.NONE);
                        break;
                    case Board.cellValue.REDGHOST :
                        redGhost.setLocation(new Point2D(row,column));
                        redGhost.setCurrentDirection(Character.Direction.NONE);
                        redGhost.setLastDirection(Character.Direction.NONE);
                        break;
                    case Board.cellValue.BLUEGHOST:
                        blueGhost.setLocation(new Point2D(row,column));
                        blueGhost.setCurrentDirection(Character.Direction.NONE);
                        blueGhost.setLastDirection(Character.Direction.NONE);
                        break;
                    case Board.cellValue.YELLOWGHOST:
                        yellowGhost.setLocation(new Point2D(row,column));
                        yellowGhost.setCurrentDirection(Character.Direction.NONE);
                        yellowGhost.setLastDirection(Character.Direction.NONE);
                        break;
                    default:
                        //do nothing
                        break;
                }
            }
        }
        pacman.setVelocity(new Point2D(0,0));
        yellowGhost.setVelocity(new Point2D(1,0));
        redGhost.setVelocity(new Point2D(-1,0));
        blueGhost.setVelocity(new Point2D(1,0));

    }

    /**
     * For initialize all Variables at start.
     */
    public void startNewGame(){
        this.gameOver = false;
        this.score = 0;
        this.winGame = false;
        this.pacman = new Pacman(this.board);
        this.redGhost = new RedGhost(pacman.getBoard(),false, pacman.getLocation());
        this.blueGhost = new BlueGhost(pacman.getBoard(), false,pacman.getLocation());
        this.yellowGhost = new YellowGhost(pacman.getBoard(), false,pacman.getLocation());
        this.dotCount = 0;
        //TODO this.board.boardLevel();


    }

    /**
     * initialize Next Level.
     * TODO
     */
    public void nextLevel(){

    }

    /**TODO
     * UpdateModel is function to update the pacman and the ghosts in the change of state.
     * @param direction recently direction for pacman to Move.
     */
    public void updateModel(Character.Direction direction){
        this.pacman.setDirection(direction);
        this.pacman.move();

    }




}
