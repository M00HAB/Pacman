package model;
import java.io.*;
import java.util.*;
public class Board {

    // Create Enum of Smallest element in the board can be one of ...
    // red -> for ghost1
    //
    public enum cellValue {
        EMPTY, SMALLDOT, BIGDOT, WALL, REDGHOST, BLUEGHOST, YELLOWGHOST, PACMAN
    };

    private int rowCount;
    private  int columnCount;

    private int elementCount;
    private cellValue[][] grid;

    public Board(){
        this.rowCount = 0;
        this.columnCount = 0;
        this.elementCount = 0;
    }

    /**
     * Generate the board based on the txt file and place pacman and ghosts at their start positions.
     * "W" for Wall.
     * "E" for Empty.
     * "1","2" or "3" indicates the ghosts locations.
     * "p" for pacman home.
     *
     * @param filename txt file containing board configuration
     */
public void boardLevel(String fileName){
    File file = new File(fileName);
    Scanner scanner = null;
    try {
        scanner = new Scanner(file);
    }catch (FileNotFoundException e){
        e.printStackTrace();
    }
    while(scanner.hasNextLine())
    {
        String line = scanner.nextLine();
        Scanner lineScan = new Scanner(line);
        while(lineScan.hasNext()){
            lineScan.next();
            elementCount++;
    }
        rowCount++;
}
    columnCount = elementCount / rowCount;
    Scanner scanner1 = null;
    try{
        scanner1 = new Scanner(file);
    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    }
    grid = new cellValue[rowCount][columnCount];
    int row = 0;
    int column = 0;
    int pacManRow = 0;
    int pacManColumn = 0;
    int ghost1Row = 0;
    int ghost1Column = 0;
    int ghost2Row = 0;
    int ghost2Column = 0;
    int ghost3Row = 0;
    int ghost3Column = 0;

    while(scanner1.hasNextLine()){
        String line = scanner1.nextLine();
        Scanner lineScan = new Scanner(line);
        column = 0;
        while(lineScan.hasNext()){
            String data = lineScan.next();
            cellValue thisData;
            if(data.equals("W")){
                thisData = cellValue.WALL;
            }else if(data.equals("E")){
                thisData = cellValue.EMPTY;
            }else if(data.equals("1")){
                thisData = cellValue.REDGHOST;
                ghost1Row = row;
                ghost1Column = column;
            }else if(data.equals("2")){
                thisData = cellValue.BLUEGHOST;
                ghost2Row = row;
                ghost2Column = column;
            }else if(data.equals("3")){
                thisData = cellValue.YELLOWGHOST;
                ghost3Row = row;
                ghost3Column = column;
            }else if(data.equals("P")){
                thisData = cellValue.PACMAN;
                pacManRow = row;
                pacManColumn = column;
            }else if(data.equals("S")){
                thisData = cellValue.SMALLDOT;
            }else if(data.equals("B")){
                thisData = cellValue.BIGDOT;
            }else {
                // otherwise all is empty.
            }
                thisData = cellValue.EMPTY;
            }
        }
    }

    public int getRowCount() {
        return rowCount;
    }

    public Board setRowCount(int rowCount) {
        this.rowCount = rowCount;
        return this;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public Board setColumnCount(int columnCount) {
        this.columnCount = columnCount;
        return this;
    }

    public int getElementCount() {
        return elementCount;
    }

    public Board setElementCount(int elementCount) {
        this.elementCount = elementCount;
        return this;
    }

    public cellValue[][] getGrid() {
        return grid;
    }

    public Board setGrid(cellValue[][] grid) {
        this.grid = grid;
        return this;
    }

    /**
     *
     * @param row
     * @param column
     * @return the cell value of(row,column) but didn't ensure from boundaries.
     */
    public cellValue getCellValue(int row,int column){
        return this.grid[row][column];
    }
}


