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
        
<<<<<<< Updated upstream
        Image backgroundColorImage = new Image(getClass().getResourceAsStream("/assets/images/background.png"));
=======
        healthPane.getChildren().add(healthBar);
        
        backgroundPane.setScaleY(0.75);
        
        groundPane.setLayoutX(-326);
        groundPane.setLayoutY(435);
        
        
        
        
        
        
        Image groundImage = new Image(getClass().getResourceAsStream("/assets/images/ground-dirt-mirrored.png"));
        ground = new ImageView(groundImage);
        ground.setPreserveRatio(true);
        ground.setFitWidth(1920);
        ground.setScaleX(0.66);
        ground.setScaleY(0.66);
        ground.setY(435); // Müssen so komisch pixel genau gesetzt werden bc the image is not 1920x1080
        ground.setX(-326);
        groundPane.getChildren().add(ground);
        
        Image backgroundColorImage = new Image(getClass().getResourceAsStream("/assets/images/sky-blue.png"));
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
=======
    
    public HealthBar getHealthBar() {
    	return healthBar;
    }
    
    public Pane getGroundPane() {
    	return groundPane;
    }
>>>>>>> Stashed changes

}
