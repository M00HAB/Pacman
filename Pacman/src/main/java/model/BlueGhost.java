package model;

import javafx.geometry.Point2D;

public class BlueGhost extends  Ghost{


    public BlueGhost(Board board, boolean ghostEatingMode, Point2D pacmanLocation) {
        super(board, ghostEatingMode, pacmanLocation);
    }

    /**
     * Reset to Home
     */
    public void sendBlueGhostToHome(){
        int rowCount = this.getBoard().getRowCount();
        int columnCount = this.getBoard().getColumnCount();
        for(int row = 0;row < rowCount;row++){
            for(int column = 0;column < columnCount;column++){
                if(getBoard().getGrid()[row][column] == Board.cellValue.BLUEGHOST){
                    this.setLocation(new Point2D(row,column));
                }
            }
        }
        this.setVelocity(new Point2D(-1,0));
    }
}
