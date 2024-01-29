package presentation.uicomponents;

import business.game.elements.Arlie.ArlieConditions;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ScoreBar extends HBox{
	
    public Label scoreBoard;
	
	int score;
	
    public ScoreBar(double width, double height) {

            
            
            setSpacing(50);
            
            scoreBoard = new Label(Integer.toString(score));
            
            scoreBoard.setId("score-board");
            
            scoreBoard.setScaleX(6);
            scoreBoard.setScaleY(6);
            
            scoreBoard.setAlignment(Pos.TOP_RIGHT);
            
            scoreBoard.setTranslateX(width-60);
            scoreBoard.setTranslateY(40);
            
            getChildren().add(scoreBoard);


    }
    
    public void initialize() {
    	score = 0;
    }
    
}
