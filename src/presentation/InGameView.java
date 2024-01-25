package presentation;

import business.game.elements.Arlie;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class InGameView extends BaseView {

    public Arlie arlie;
    public Pane arliePane;
    public Pane obstaclePane;
    public Pane backgroundPane;
    public Pane backgroundColorPane;
    public ImageView backgroundColor;

    public InGameView(Scene scene) {
        arlie = new Arlie();
        
        arliePane = new Pane();
        obstaclePane = new Pane();
        backgroundPane = new Pane();
        backgroundColorPane = new Pane();
        
        backgroundPane.setScaleY(0.75);
        
        Image backgroundColorImage = new Image(getClass().getResourceAsStream("/assets/images/background.png"));
        backgroundColor = new ImageView(backgroundColorImage);
        backgroundColorPane.getChildren().add(backgroundColor);
        
        arliePane.getChildren().add(arlie.arlieBody);
 
        getChildren().addAll(backgroundColorPane, backgroundPane, obstaclePane, arliePane);

        setManaged(false);
        
        arlie.arlieBody.setTranslateX(scene.getWidth() * 0.15);
        arlie.arlieBody.setTranslateY(scene.getHeight() * 0.565);
        
        setId("in-game-view");
    }


    
    public Pane getArliePane() {
    	return arliePane;
    }
    
    public Pane getObstaclePane() {
    	return obstaclePane;
    }

    public Pane getBackgroundPane() {
    	return backgroundPane;
    }

}
