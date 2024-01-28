package presentation;

import business.game.elements.Arlie;
import business.game.elements.FloorScroller;
import business.game.elements.HealthBar;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class InGameView extends BaseView {
	
	
    public Arlie arlie;
    public HealthBar healthBar;
    
    
    public Pane arliePane;
    public Pane obstaclePane;
    public Pane backgroundPane;
    public Pane backgroundColorPane;
    public Pane groundPane;
    public Pane healthPane;
    public Pane hitBoxPane;
    
    public Canvas canvas;
    public GraphicsContext gc;
    
    public ImageView backgroundColor;
    public ImageView ground;

    public InGameView(Scene scene, int maxHealth) {
        arlie = new Arlie();
        healthBar = new HealthBar(maxHealth);
        
        arliePane = new Pane();
        obstaclePane = new Pane();
        healthPane = new Pane();
        backgroundPane = new Pane();
        backgroundColorPane = new Pane();
        groundPane = new Pane();
        hitBoxPane = new Pane();
        
        canvas = new Canvas(1280, 720);
        gc = canvas.getGraphicsContext2D();
        
        hitBoxPane.getChildren().add(canvas);
        
        healthPane.getChildren().add(healthBar);
        
        backgroundPane.setScaleY(0.75);
        
//        Image groundImage = new Image(getClass().getResourceAsStream("/assets/images/ground-dirt-mirrored.png"));
//        ground = new ImageView(groundImage);
//        ground.setPreserveRatio(true);
//        ground.setFitWidth(1920);
//        ground.setScaleX(0.66);
//        ground.setScaleY(0.66);
//        ground.setY(435); // Müssen so komisch pixel genau gesetzt werden bc the image is not 1920x1080
//        ground.setX(-326);
//        groundPane.getChildren().add(ground);
        
        Image backgroundColorImage = new Image(getClass().getResourceAsStream("/assets/images/sky-blue.png"));
        backgroundColor = new ImageView(backgroundColorImage);
        backgroundColorPane.getChildren().add(backgroundColor);
        
        arliePane.getChildren().add(arlie.arlieBody);
 
        getChildren().addAll(backgroundColorPane, backgroundPane, groundPane, obstaclePane, arliePane, hitBoxPane, healthPane);

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
    
    public HealthBar getHealthBar() {
    	return healthBar;
    }
    
    public Pane getGroundPane() {
    	return groundPane;
    }
    
    public GraphicsContext getHitBoxGraphicsContext() {
    	return gc;
    }


}
