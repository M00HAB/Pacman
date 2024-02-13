package view;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Board;
import model.Character;
import model.GameEngine;
import model.Pacman;

import java.util.Objects;

public class View extends Group {
    public final static double cellWidth = 20;

    @FXML private int rowCount;
    @FXML private int columnCount;
    private ImageView[][] cellView;
    final private Image pacmanRightImage;
    final private Image pacmanUpImage;
    final private Image pacmanLeftImage;
    final private Image pacmanDownImage;
    final private Image redGhostImage;
    final private Image blueGhostImage;
    final private Image yellowGhostImage;
    final private Image ghostInEatingMode;
    final private Image wallImage;
    final private Image smallDotImage;
    final private Image bigDotImage;

    /**
     * initialize all attributes from files.
     * all images which I used in PNG or GIF.
     */
    public View(){
        this.pacmanLeftImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Images/pacmanLeft.gif")));
        this.pacmanRightImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Images/pacmanRight.gif")));
        this.pacmanUpImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Images/pacmanUp.gif")));
        this.pacmanDownImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Images/pacmanDown.gif")));
        this.redGhostImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Images/redGhost.gif")));
        this.yellowGhostImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Images/yellowGhost.gif")));
        this.blueGhostImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Images/blueGhost.gif")));
        this.ghostInEatingMode = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Images/EatingModeGhost.gif")));
        this.smallDotImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Images/smalldot.png")));
        this.bigDotImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Images/whitedot.png")));
        this.wallImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Images/wall.png")));

    }

    /**
     * Construct the empty grid of Images
     */
    public void constructGrid(){
        if(this.rowCount > 0 && this.columnCount > 0){
            this.cellView = new ImageView[this.rowCount][this.columnCount];
            for(int row = 0;row < this.rowCount;row++){
                for(int column = 0;column < this.columnCount;column++){
                    ImageView image = new ImageView();
                    image.setX((double) column * this.cellWidth);
                    image.setY((double) row * this.cellWidth);
                    image.setFitHeight(cellWidth);
                    image.setFitHeight(cellWidth);
                    this.cellView[row][column] = image;
                    this.getChildren().add(image);
                }
            }
        }

    }

    public int getRowCount() {
        return rowCount;
    }

    public View setRowCount(int rowCount) {
        this.rowCount = rowCount;
        return this;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public View setColumnCount(int columnCount) {
        this.columnCount = columnCount;
        return this;
    }

    /**
     * this function is the update function for view to respond of controller request.
     * @param model this is game model class representing all classes.
     */
    public void update(GameEngine model){
        assert model.getBoard().getRowCount() == this.rowCount && model.getBoard().getColumnCount() == this.columnCount
        for(int row = 0;row < this.rowCount;row++){
            for(int column = 0;column < this.columnCount;column++){
                Board.cellValue data = model.getBoard().getCellValue(row,column);
                switch(data){
                    case Board.cellValue.WALL:
                        this.cellView[row][column].setImage(this.wallImage);
                        break;
                    case Board.cellValue.BIGDOT:
                        this.cellView[row][column].setImage(this.bigDotImage);
                        break;
                    case Board.cellValue.SMALLDOT:
                        this.cellView[row][column].setImage(this.smallDotImage);
                        break;
                    default:
                        this.cellView[row][column].setImage(null);
                        break;
                }
                //determine the direction of pacman.
                if(model.getPacman().getLocation().getX() == row && model.getPacman().getLocation().getY() == column && (model.getPacman().getLastDirection() == Character.Direction.RIGHT || model.getPacman().getLastDirection() == Character.Direction.NONE)){
                    this.cellView[row][column].setImage(this.pacmanRightImage);
                }else if(model.getPacman().getLocation().getX() == row && model.getPacman().getLocation().getY() == column && (model.getPacman().getLastDirection() == Character.Direction.LEFT)){
                    this.cellView[row][column].setImage(this.pacmanLeftImage);
                }else if(model.getPacman().getLocation().getX() == row && model.getPacman().getLocation().getY() == column && (model.getPacman().getLastDirection() == Character.Direction.UP)){
                    this.cellView[row][column].setImage(this.pacmanUpImage);
                }else if(model.getPacman().getLocation().getX() == row && model.getPacman().getLocation().getY() == column && (model.getPacman().getLastDirection() == Character.Direction.DOWN)){
                    this.cellView[row][column].setImage(this.pacmanDownImage);
                }

                //make ghosts blinking indicating for ending of eatingMode.
                /*
                 * TODO
                 *  if(model.isGhostEatingMode())
                 */

                //display image of ghosting in eating mode
                if(model.isGhostEatingMode()){
                    if(model.getRedGhost().getLocation().getX() == row && model.getRedGhost().getLocation().getY() == column){
                        this.cellView[row][column].setImage(this.ghostInEatingMode);
                    }
                    if(model.getBlueGhost().getLocation().getX() == row && model.getBlueGhost().getLocation().getY() == column){
                        this.cellView[row][column].setImage(this.ghostInEatingMode);
                    }
                    if(model.getYellowGhost().getLocation().getX() == row && model.getYellowGhost().getLocation().getY() == column){
                        this.cellView[row][column].setImage(this.ghostInEatingMode);
                    }
                }else { // display normal ghost's images.
                    if(model.getRedGhost().getLocation().getX() == row && model.getRedGhost().getLocation().getY() == column){
                        this.cellView[row][column].setImage(this.redGhostImage);
                    }
                    if(model.getBlueGhost().getLocation().getX() == row && model.getBlueGhost().getLocation().getY() == column){
                        this.cellView[row][column].setImage(this.blueGhostImage);
                    }
                    if(model.getYellowGhost().getLocation().getX() == row && model.getYellowGhost().getLocation().getY() == column){
                        this.cellView[row][column].setImage(this.yellowGhostImage);
                    }
                }
            }
        }

    }
}
