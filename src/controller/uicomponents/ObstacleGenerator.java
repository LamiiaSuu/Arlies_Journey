package controller.uicomponents;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.animation.AnimationTimer;
import java.util.Random;

import business.game.elements.*;

public class ObstacleGenerator {

    private int timer;
    public int interval;
    private double groundY;
    Random random;
    Scene scene;
	
	private Pane gamePane;
    

    public ObstacleGenerator(Pane gamePane, Scene scene) {
        this.gamePane = gamePane;
        this.scene = scene;
        timer = 0;
        interval = 75;
        random = new Random();
    }
    
    public void setGround(double groundY) {
    	this.groundY = groundY;
    }

    public void update() {
    	if(timer == interval) {
    		timer = 0;
    		if(random.nextInt(2)==1) {
    			generateBalloon();
    		}else {
    			generateTree();
    		}
    		
    	}else {
    		timer++;
    	}
    	
    }

    private void generateTree() {
        int treeType = random.nextInt(3) + 1; 
        double fitHeight = random.nextInt(150) + 200; 

        Tree obstacle = new Tree(treeType, fitHeight);



        obstacle.setTranslateX(scene.getWidth());
        obstacle.setTranslateY(groundY-obstacle.getFitHeight()+150);



        gamePane.getChildren().add(obstacle);


        new AnimationTimer() {
            @Override
            public void handle(long now) {
                double speed = 5.0; 
                obstacle.setTranslateX(obstacle.getTranslateX() - speed);

                if (obstacle.getTranslateX() + obstacle.getBoundsInLocal().getWidth() < 0) {

                    gamePane.getChildren().remove(obstacle);
                    stop(); 
                }
            }
        }.start();
    }
    
    private void generateBalloon() {
        int balloonType = random.nextInt(3) + 1; 
        double fitHeight = random.nextInt(200) + 100; 

        Balloon obstacle = new Balloon(balloonType, fitHeight);



        obstacle.setTranslateX(scene.getWidth());
        obstacle.setTranslateY(groundY - (obstacle.getFitHeight()*0.8) - random.nextInt(100));



        gamePane.getChildren().add(obstacle);


        new AnimationTimer() {
            @Override
            public void handle(long now) {
                double speed = 5.0; 
                obstacle.setTranslateX(obstacle.getTranslateX() - speed);

                if (obstacle.getTranslateX() + obstacle.getBoundsInLocal().getWidth() < 0) {

                    gamePane.getChildren().remove(obstacle);
                    stop(); 
                }
            }
        }.start();
    }
}
