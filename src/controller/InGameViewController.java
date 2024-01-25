package controller;

import application.App;
import business.game.elements.ArlieController;
import controller.uicomponents.ObstacleGenerator;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import presentation.InGameView;
import presentation.PrimaryViewNames;

public class InGameViewController extends BaseViewController {
	
	private boolean gamePaused = false;
	private Timeline timeline;
	private double groundY;
	InGameView root;
	ArlieController arlieController;
	ObstacleGenerator obstacleGen;
	App app;
	Scene scene;
	
	
	public InGameViewController(App app, Scene scene) {
		
		root = new InGameView(scene);
		this.app = app;
		this.scene = scene;
		
		arlieController = new ArlieController(app, root.getArliePane(), root.arlie, scene);
		obstacleGen = new ObstacleGenerator(root.getObstaclePane(), scene);
		
		
		initialize();
		
	}

	@Override
	public void initialize() {

		groundY = scene.getHeight() * 0.6;
		
		setGround();
		
		arlieController.initialize();
		
        app.currentViewProperty().addListener(new ChangeListener<PrimaryViewNames>() {
            @Override
            public void changed(ObservableValue<? extends PrimaryViewNames> observable,
                                PrimaryViewNames oldValue, PrimaryViewNames newValue) {
            	if (newValue != PrimaryViewNames.IN_GAME_VIEW) {
                    pauseGame();
                    System.out.println("PAUSE");
                } else if (newValue == PrimaryViewNames.IN_GAME_VIEW){
                    resumeGame();
                    System.out.println("RESuME");
                }
            }
        });
        
        timeline = new Timeline(new KeyFrame(Duration.millis(16), event -> update()));
        timeline.setCycleCount(Animation.INDEFINITE);  
        timeline.play();
        
        
        	scene.setOnKeyPressed(event -> {
        		if(!gamePaused)
                handleKeyPress(event.getCode());
            });
        	
        	
            scene.setOnKeyReleased(event -> {
            	if(!gamePaused)
                handleKeyRelease(event.getCode());
            });
        
            
            scene.heightProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                    groundY = newValue.doubleValue() * 0.6;
                    setGround();
                }
            });
        
	}
	
    private void handleKeyPress(KeyCode code) {
        switch (code) {
            case SPACE:
            	arlieController.jump();
                break;
            case UP:
            	arlieController.jump();
                break;
            case W:
            	arlieController.jump();
                break;
            case DOWN:
            	arlieController.crouch();
                break;
            case CONTROL:
            	arlieController.crouch();
                break;
            case S:
            	arlieController.crouch();
                break;
            case ESCAPE:
            	app.switchView(PrimaryViewNames.MAIN_MENU_VIEW);
            	break;
            
        }
    }
    

    private void handleKeyRelease(KeyCode code) {
        switch (code) {
       		case SPACE:
       			arlieController.jumpRelease();
            	break;
        	case UP:
        		arlieController.jumpRelease();
            	break;
        	case W:
        		arlieController.jumpRelease();
            	break;
            case DOWN:
            	arlieController.crouchRelease();
                break;
            case CONTROL:
            	arlieController.crouchRelease();
                break;
            case S:
            	arlieController.crouchRelease();
                break;
        }
    }
	
    private void pauseGame() {
        if (!gamePaused) {
            timeline.pause();
            gamePaused = true;
        }
    }

    // Resume the game
    private void resumeGame() {
        if (gamePaused) {
            timeline.play();
            gamePaused = false;
        }
    }

	@Override
	public void update() {
		arlieController.update();
		obstacleGen.update();
	}
	
	public void setGround() {
		arlieController.setGround(groundY);
		obstacleGen.setGround(groundY);
	}
	
    public Pane getRoot() {
        return root;
    }
}
