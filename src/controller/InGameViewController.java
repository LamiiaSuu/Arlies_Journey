package controller;

import application.App;
import business.game.elements.ArlieController;
import business.game.elements.BackgroundScroll;
import business.game.elements.HealthBarController;
import business.music.MP3Player;
import controller.uicomponents.ObstacleGenerator;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import presentation.InGameView;
import presentation.PrimaryViewNames;

public class InGameViewController extends BaseViewController {
	
    private static final Duration COLLISION_INTERVAL = Duration.seconds(2);
	private static final int MAX_HEALTH = 3;
	
	private boolean gamePaused = false;
	private boolean gameOver = false;
	private boolean initializationPaused = false;
	private Timeline timeline;
	private double groundY;
    private long lastCollisionTime = 0;
    MP3Player player;
	InGameView root;
	ArlieController arlieController;
	HealthBarController healthBarController;
	ObstacleGenerator obstacleGen;
	BackgroundScroll backgroundScroll;
	App app;
	Scene scene;
	
	
	public InGameViewController(App app, Scene scene, MP3Player player) {
		
		root = new InGameView(scene, MAX_HEALTH);
		this.app = app;
		this.scene = scene;
		this.player = player;
		
		arlieController = new ArlieController(app, root.getArliePane(), root.arlie, scene, player);
		obstacleGen = new ObstacleGenerator(root.getObstaclePane(), scene, arlieController, this);
		backgroundScroll = new BackgroundScroll(root.getBackgroundPane());
		healthBarController = new HealthBarController(root.getHealthBar());
		
		
		initialize();
		
	}

	@Override
	public void initialize() {

		groundY = scene.getHeight() * 0.6;
		
		setGround();
		
		healthBarController.initialize(MAX_HEALTH);
		
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
        		if(!gamePaused && !gameOver)
                handleKeyPress(event.getCode());
            });
        	
        	
            scene.setOnKeyReleased(event -> {
            	if(!gamePaused && !gameOver)
                handleKeyRelease(event.getCode());
            });
        
            
            scene.heightProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                    groundY = newValue.doubleValue() * 0.6;
                    setGround();
                }
            });
            
            healthBarController.getHealthProperty().addListener(new ChangeListener<Number>() {

				@Override
				public void changed(ObservableValue<? extends Number> arg0, Number oldValue, Number newValue) {

					if(oldValue != newValue) {
						healthBarController.setHitPoints((int)newValue);
						if((int) newValue == 0)
							gameOver();
					}
					
					
				}
            });
            
            arlieController.getConfusedLandedProperty().addListener(new ChangeListener<Boolean>() {

                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (newValue == true) {
                    	pauseGame();
                    	arlieController.confusedCircling();
                    }
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
                
            // change to menu pop up
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
            backgroundScroll.stopTimer();
            obstacleGen.stopTimer();
            gamePaused = true;
            if(initializationPaused) {
            	player.pause();
            }
            else {
            	initializationPaused = true;
            }
        }
    }

    // Resume the game
    private void resumeGame() {
        if (gamePaused && !gameOver) {
            timeline.play();
            obstacleGen.startTimer();
            backgroundScroll.startTimer();
            gamePaused = false;
            player.pause();
        }
    }

	@Override
	public void update() {
		arlieController.update();
		obstacleGen.update();
	}
	
	public void gameOver() {
		
		gameOver = true;
		arlieController.gameOver();
		player.playDeathSound();
		
	}
	
	public void arlieCollided() {
		
		long currentTime = System.currentTimeMillis();
		
        if (currentTime - lastCollisionTime >= COLLISION_INTERVAL.toMillis()) {
        	
            healthBarController.damage();
            
            lastCollisionTime = currentTime;
            
            player.playCollidedSound();
        }
        
	}
	
	public void setGround() {
		arlieController.setGround(groundY);
		obstacleGen.setGround(groundY);
	}
	
    public Pane getRoot() {
        return root;
    }
}
