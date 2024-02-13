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
    private boolean ghostEatingMode;

    public GameEngine(){
        this.startNewGame();

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
        this.ghostEatingMode = false;
        this.redGhost = new RedGhost(pacman.getBoard(),this.ghostEatingMode, pacman.getLocation());
        this.blueGhost = new BlueGhost(pacman.getBoard(), this.ghostEatingMode,pacman.getLocation());
        this.yellowGhost = new YellowGhost(pacman.getBoard(), this.ghostEatingMode,pacman.getLocation());
        this.dotCount = 0;
        //TODO this.board.boardLevel("get file");
        this.determineLocations();

    }

    /**
     * initialize Next Level.
     * TODO
     */
    public void nextLevel(){
        if(this.isLevelCompelete()){
            this.level++;
            board.setRowCount(0);
            board.setColumnCount(0);
            this.winGame = false;
            this.setGhostEatingMode(false);
            //get levels
        }

    }

    /**TODO
     * UpdateModel is function to update the pacman and the ghosts in the change of state.
     * @param direction recently direction for pacman to Move.
     */
    public void updateModel(Character.Direction direction){
        this.pacman.setDirection(direction);
        this.pacman.move();
        Board.cellValue pacmanLocationValue = board.getCellValue((int) pacman.getLocation().getX(),(int) pacman.getLocation().getY());
        //if pacman is on a big dot, delete it and change ghost-eating mode and initialize counter
        if(pacmanLocationValue == Board.cellValue.BIGDOT){
            board.setCellValue(Board.cellValue.EMPTY,(int) pacman.getLocation().getX(),(int) pacman.getLocation().getY());
            this.score += 50;
            dotCount--;
            this.setGhostEatingMode(true);
            //counter TODO
        }
        //if pacman is on a small dot ,delete it.
        if(pacmanLocationValue == Board.cellValue.SMALLDOT){
            board.setCellValue(Board.cellValue.EMPTY,(int)pacman.getLocation().getX(),(int) pacman.getLocation().getY());
            this.dotCount--;
            this.score += 10;
        }

        //if Ghosts in eatingMode back them to home.
        if(ghostEatingMode){
            if(pacman.getLocation().equals(redGhost.getLocation())){
                redGhost.sendRedGhostToHome();
                this.score += 100;
            }
            if(pacman.getLocation().equals(blueGhost.getLocation())){
                blueGhost.sendBlueGhostToHome();
                this.score += 100;
            }
            if(pacman.getLocation().equals(yellowGhost.getLocation())){
                yellowGhost.sendYellowGhostToHome();
                this.score += 100;
            }
        }else{
            //game over
            if(pacman.getLocation().equals(redGhost.getLocation())){
                this.gameOver = true;
                pacman.setVelocity(new Point2D(0,0));
            }
            if(pacman.getLocation().equals(blueGhost.getLocation())){
                this.gameOver = true;
                pacman.setVelocity(new Point2D(0,0));
            }
            if(pacman.getLocation().equals(yellowGhost.getLocation())){
                this.gameOver = true;
                pacman.setVelocity(new Point2D(0,0));
            }
        }
        //TODO MoveGhosts
        this.moveGhosts();

        if(ghostEatingMode){
            if(pacman.getLocation().equals(redGhost.getLocation())){
                redGhost.sendRedGhostToHome();
                this.score += 100;
            }
            if(pacman.getLocation().equals(blueGhost.getLocation())){
                blueGhost.sendBlueGhostToHome();
                this.score += 100;
            }
            if(pacman.getLocation().equals(yellowGhost.getLocation())){
                yellowGhost.sendYellowGhostToHome();
                this.score += 100;
            }
        }else{
            //game over
            if(pacman.getLocation().equals(redGhost.getLocation())){
                this.gameOver = true;
                pacman.setVelocity(new Point2D(0,0));
            }
            if(pacman.getLocation().equals(blueGhost.getLocation())){
                this.gameOver = true;
                pacman.setVelocity(new Point2D(0,0));
            }
            if(pacman.getLocation().equals(yellowGhost.getLocation())){
                this.gameOver = true;
                pacman.setVelocity(new Point2D(0,0));
            }

            //check if Level is Complete.
            if(this.isLevelCompelete()){
                pacman.setVelocity(new Point2D(0,0));
                this.nextLevel();
            }


        }
    }

    public Board getBoard() {
        return board;
    }

    public GameEngine setBoard(Board board) {
        this.board = board;
        return this;
    }

    public int getScore() {
        return score;
    }

    public GameEngine setScore(int score) {
        this.score = score;
        return this;
    }

    public int getLevel() {
        return level;
    }

    public GameEngine setLevel(int level) {
        this.level = level;
        return this;
    }

    public int getDotCount() {
        return dotCount;
    }

    public GameEngine setDotCount(int dotCount) {
        this.dotCount = dotCount;
        return this;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public GameEngine setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
        return this;
    }

    public boolean isWinGame() {
        return winGame;
    }

    public GameEngine setWinGame(boolean winGame) {
        this.winGame = winGame;
        return this;
    }

    public Pacman getPacman() {
        return pacman;
    }

    public GameEngine setPacman(Pacman pacman) {
        this.pacman = pacman;
        return this;
    }

    public RedGhost getRedGhost() {
        return redGhost;
    }

    public GameEngine setRedGhost(RedGhost redGhost) {
        this.redGhost = redGhost;
        return this;
    }

    public BlueGhost getBlueGhost() {
        return blueGhost;
    }

    public GameEngine setBlueGhost(BlueGhost blueGhost) {
        this.blueGhost = blueGhost;
        return this;
    }

    public YellowGhost getYellowGhost() {
        return yellowGhost;
    }

    public GameEngine setYellowGhost(YellowGhost yellowGhost) {
        this.yellowGhost = yellowGhost;
        return this;
    }

    public boolean isGhostEatingMode() {
        return ghostEatingMode;
    }

    public void setGhostEatingMode(boolean ghostEatingMode) {
        this.ghostEatingMode = ghostEatingMode;
    }

    /**
     * When all dots are eaten.
     * @return boolean
     */
    public boolean isLevelCompelete(){
        return this.dotCount == 0;
    }

    /**
     * update Move method for all ghosts.
     */
    public void moveGhosts(){
        redGhost.move();
        blueGhost.move();
        yellowGhost.move();
        //update data
        redGhost.setVelocity(redGhost.getDataMoving()[0]);
        redGhost.setLocation(redGhost.getDataMoving()[1]);
        blueGhost.setVelocity(blueGhost.getDataMoving()[0]);
        blueGhost.setLocation(blueGhost.getDataMoving()[1]);
        yellowGhost.setVelocity(yellowGhost.getDataMoving()[0]);
        yellowGhost.setLocation(yellowGhost.getDataMoving()[1]);
    }
}
