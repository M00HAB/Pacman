package model;

import javafx.geometry.Point2D;

public class RedGhost extends Ghost{


    public RedGhost(Board board, boolean ghostEatingMode, Point2D pacmanLocation) {
        super(board, ghostEatingMode, pacmanLocation);
    }

    /**
     * Reset RedGhost to Home
     */
    public void sendRedGhostToHome(){
        int rowCount = this.getBoard().getRowCount();
        int columnCount = this.getBoard().getColumnCount();
        for(int row = 0;row < rowCount;row++){
            for(int column = 0;column < columnCount;column++){
                if(getBoard().getGrid()[row][column] == Board.cellValue.REDGHOST){
                    this.setLocation(new Point2D(row,column));
                }
            }
        }
        this.setVelocity(new Point2D(-1,0));
    }
}
