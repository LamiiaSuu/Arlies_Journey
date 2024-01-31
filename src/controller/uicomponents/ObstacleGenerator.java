package controller.uicomponents;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import business.game.elements.ArlieController;
import business.game.elements.Balloon;
import business.game.elements.Tree;
import business.game.elements.FruitTree;
import business.game.elements.Bush;
import business.game.elements.FlowerBush;
import business.game.elements.Zeppelin;
import controller.InGameViewController;

public class ObstacleGenerator {

    private int timer;
    private int interval;
    private int hitBoxCounter;
    private int fps;
    private double groundY;
    private boolean hitBoxVisible;
    private Random random;
    private Scene scene;
    private Pane gamePane;
    private GraphicsContext hitBoxGC;
    private ArlieController arlieController;
    private InGameViewController inGameViewController;
    private List<AnimationTimer> timers;

    public ObstacleGenerator(Pane gamePane, GraphicsContext gc, Scene scene, ArlieController arlieController, InGameViewController inGameViewController) {
        this.inGameViewController = inGameViewController;
        this.gamePane = gamePane;
        this.scene = scene;
        this.arlieController = arlieController;
        this.timer = 0;
        this.interval = 60;
        this.random = new Random();
        this.timers = new ArrayList<>();
        this.hitBoxGC = gc;
        this.hitBoxCounter = 0;
    }
    
    public void setFPS(int fps) {
    	this.fps = fps;
    }

    public void setGround(double groundY) {
        this.groundY = groundY;
    }

//    public void update(InGameViewController inGameViewController) {
//        if (timer == interval) {
//            timer = 0;
//            inGameViewController.generatedObstacle();
//            int obstacleToSpawn = random.nextInt(1000);
//            
//            if(obstacleToSpawn>990) {
//            	generateZeppelin();
//            }else if(obstacleToSpawn>845) {
//            	generateFlowerBush();
//            }else if(obstacleToSpawn>650) {
//            	generateFruitTree();
//            }else if(obstacleToSpawn>500) {
//            	generateBush();
//            }else if(obstacleToSpawn>250) {
//            	generateTree();
//            }else if(obstacleToSpawn>0) {
//            	generateBalloon();
//            }
//        } else {
//            timer++;
//        }
//    }
    
    public void update(InGameViewController inGameViewController) {

            inGameViewController.generatedObstacle();
            int obstacleToSpawn = random.nextInt(1000);
            
            if(obstacleToSpawn>990) {
            	generateZeppelin();
            }else if(obstacleToSpawn>845) {
            	generateFlowerBush();
            }else if(obstacleToSpawn>650) {
            	generateFruitTree();
            }else if(obstacleToSpawn>500) {
            	generateBush();
            }else if(obstacleToSpawn>250) {
            	generateTree();
            }else if(obstacleToSpawn>0) {
            	generateBalloon();
            }

    }

    private void generateTree() {
        int treeType = random.nextInt(3) + 1;
        double fitHeight = random.nextInt(100) + 250;

        Tree obstacle = new Tree(treeType, fitHeight);

        obstacle.setTranslateX(scene.getWidth());
        obstacle.setTranslateY(groundY - obstacle.getFitHeight() + 150);

        gamePane.getChildren().add(obstacle);

        AnimationTimer animTimer = createAnimationTimer(obstacle, "tree");
        animTimer.start();
        timers.add(animTimer);
    }
    

    private void generateBalloon() {
        int balloonType = random.nextInt(6) + 1;
        double fitHeight = random.nextInt(200) + 75;

        Balloon obstacle = new Balloon(balloonType, fitHeight);

        obstacle.setTranslateX(scene.getWidth());
        obstacle.setTranslateY(groundY - (obstacle.getFitHeight() * 0.5) - random.nextInt(150));

        gamePane.getChildren().add(obstacle);

        AnimationTimer animTimer = createAnimationTimer(obstacle, "balloon");
        animTimer.start();
        timers.add(animTimer);
    }
    
    private void generateFruitTree() {
        int treeType = random.nextInt(4) + 1;
        double fitHeight = random.nextInt(100) + 250;

        FruitTree obstacle = new FruitTree(treeType, fitHeight);

        obstacle.setTranslateX(scene.getWidth());
        obstacle.setTranslateY(groundY - obstacle.getFitHeight() + 150);

        gamePane.getChildren().add(obstacle);

        AnimationTimer animTimer = createAnimationTimer(obstacle, "fruit-tree");
        animTimer.start();
        timers.add(animTimer);
    }
    
    private void generateBush() {
        int bushType = random.nextInt(5) + 1;
        double fitHeight = random.nextInt(100) + 100;

        Bush obstacle = new Bush(bushType, fitHeight);

        obstacle.setTranslateX(scene.getWidth());
        obstacle.setTranslateY(groundY - obstacle.getFitHeight() + 150);

        gamePane.getChildren().add(obstacle);

        AnimationTimer animTimer = createAnimationTimer(obstacle, "bush");
        animTimer.start();
        timers.add(animTimer);
    }
    
    private void generateFlowerBush() {
        int bushType = random.nextInt(4) + 1;
        double fitHeight = random.nextInt(100) + 100;

        FlowerBush obstacle = new FlowerBush(bushType, fitHeight);

        obstacle.setTranslateX(scene.getWidth());
        obstacle.setTranslateY(groundY - obstacle.getFitHeight() + 150);

        gamePane.getChildren().add(obstacle);

        AnimationTimer animTimer = createAnimationTimer(obstacle, "flower-bush");
        animTimer.start();
        timers.add(animTimer);
    }
    
    private void generateZeppelin() {
//        int bushType = random.nextInt(1) + 1; // Change face later?
        double fitHeight = random.nextInt(150) + 200;

        Zeppelin obstacle = new Zeppelin(fitHeight);

        obstacle.setTranslateX(scene.getWidth());
        obstacle.setTranslateY(groundY - (obstacle.getFitHeight()) - random.nextInt(100));

        gamePane.getChildren().add(obstacle);

        AnimationTimer animTimer = createAnimationTimer(obstacle, "zeppelin");
        animTimer.start();
        timers.add(animTimer);
    }

    private AnimationTimer createAnimationTimer(ImageView obstacle, String obstacleType) {
        return new AnimationTimer() {
            @Override
            public void handle(long now) {
            	double speed = 5.0;
	           	if(fps == 60) {
	           		speed = 10;
	        	 } else if(fps == 540) {
	        		 speed = 1.11;
	        	 }
                obstacle.setTranslateX(obstacle.getTranslateX() - speed);

                if (obstacle.getTranslateX() + obstacle.getBoundsInLocal().getWidth() < 0) {
                    gamePane.getChildren().remove(obstacle);
                    stop();
                    timers.remove(this);
                } else {
                    checkCollision(obstacle, obstacleType);
                }
            }
        };
    }

    private void checkCollision(ImageView obstacle, String obstacleType) {
    	
    	
        if (HitBoxManager.checkCollision(arlieController.getArlieBody(), obstacle, obstacleType, hitBoxGC, hitBoxVisible)) {
            inGameViewController.arlieCollided();
        }
//        
//        if(hitBoxVisible) {
//            if(hitBoxCounter > timers.size()+1) {
//            	CollisionChecker.clearCanvas(hitBoxGC);
//            	hitBoxCounter = 0;
//            	
//            	
//            }else {
//            	hitBoxCounter++;
//            }
//        }
    }
    

    public void stopTimer() {
        for (AnimationTimer timer : timers) {
            timer.stop();
        }
    }


    public void startTimer() {
        // Restart all timers
        for (AnimationTimer timer : timers) {
            timer.start();
        }
    }
    
    public void toggleHitBoxVisibility() {
    	if(hitBoxVisible) {
    		hitBoxVisible = false;    
    		HitBoxManager.clearCanvas(hitBoxGC);
    	}else {
    		hitBoxVisible = true;
    	}
    }
    
    public void reset() {
    	while (!timers.isEmpty()) {
    		timers.get(0).stop();
    		timers.remove(timers.get(0));
    	}
    	
    	
    	while (!gamePane.getChildren().isEmpty()) {
    		gamePane.getChildren().removeAll(gamePane.getChildren().get(0));
    	}
    }
}

// TIMELINE VERSION - STILL KINDA WHACKY AND WONKY. MARIO BENUTZ DAS FÜRS BALANCING UND HITBOXES, DAS HAT SCHON THE RIGHT SPEED AN SICH
//
//package controller.uicomponents;
//
//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
//import javafx.scene.Scene;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.Pane;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//import business.game.elements.ArlieController;
//import business.game.elements.Balloon;
//import business.game.elements.Tree;
//import controller.InGameViewController;
//
//public class ObstacleGenerator {
//
//    private int timer;
//    private int interval;
//    private int hitBoxCounter;
//    private double groundY;
//    private boolean hitBoxVisible;
//    private Random random;
//    private Scene scene;
//    private Pane gamePane;
//    private GraphicsContext hitBoxGC;
//    private ArlieController arlieController;
//    private InGameViewController inGameViewController;
//    private List<Timeline> timelines;
//
//    public ObstacleGenerator(Pane gamePane, GraphicsContext gc, Scene scene, ArlieController arlieController, InGameViewController inGameViewController) {
//        this.inGameViewController = inGameViewController;
//        this.gamePane = gamePane;
//        this.scene = scene;
//        this.arlieController = arlieController;
//        this.timer = 0;
//        this.interval = 60;
//        this.random = new Random();
//        this.timelines = new ArrayList<>();
//        this.hitBoxGC = gc;
//        this.hitBoxCounter = 0;
//    }
//
//    public void setGround(double groundY) {
//        this.groundY = groundY;
//    }
//
//    public void update() {
//        if (timer == interval) {
//            timer = 0;
//            if (random.nextInt(2) == 1) {
//                generateBalloon();
//            } else {
//                generateTree();
//            }
//        } else {
//            timer++;
//        }
//    }
//
//    private void generateTree() {
//        int treeType = random.nextInt(3) + 1;
//        double fitHeight = random.nextInt(150) + 200;
//
//        Tree obstacle = new Tree(treeType, fitHeight);
//
//        obstacle.setTranslateX(scene.getWidth());
//        obstacle.setTranslateY(groundY - obstacle.getFitHeight() + 150);
//
//        gamePane.getChildren().add(obstacle);
//
//        Timeline timeline = createTimeline(obstacle, "tree");
//        timelines.add(timeline);
//        timeline.play();
//    }
//
//    private void generateBalloon() {
//        int balloonType = random.nextInt(6) + 1;
//        double fitHeight = random.nextInt(200) + 75;
//
//        Balloon obstacle = new Balloon(balloonType, fitHeight);
//
//        obstacle.setTranslateX(scene.getWidth());
//        obstacle.setTranslateY(groundY - (obstacle.getFitHeight() * 0.5) - random.nextInt(150));
//
//        gamePane.getChildren().add(obstacle);
//
//        Timeline timeline = createTimeline(obstacle, "balloon");
//        timelines.add(timeline);
//        timeline.play();
//    }
//
//    private Timeline createTimeline(ImageView obstacle, String obstacleType) {
//        Timeline timeline = new Timeline(
//                new KeyFrame(javafx.util.Duration.millis(8), event -> handleObstacleAnimation(obstacle, obstacleType))
//        );
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        return timeline;
//    }
//
//    private void handleObstacleAnimation(ImageView obstacle, String obstacleType) {
//        double speed = 5.75;
//        obstacle.setTranslateX(obstacle.getTranslateX() - speed);
//
//        if (obstacle.getTranslateX() + obstacle.getBoundsInLocal().getWidth() < 0) {
//            gamePane.getChildren().remove(obstacle);
//            timelines.removeIf(t -> t.getStatus() == Timeline.Status.STOPPED);
//        } else {
//            checkCollision(obstacle, obstacleType);
//        }
//    }
//
//    private void checkCollision(ImageView obstacle, String obstacleType) {
//        if (HitBoxManager.checkCollision(arlieController.getArlieBody(), obstacle, obstacleType, hitBoxGC, hitBoxVisible)) {
//            inGameViewController.arlieCollided();
//            System.out.println("Arlie Collided!");
//        }
//    }
//
//    public void stopTimer() {
//        timelines.forEach(Timeline::stop);
//    }
//
//    public void startTimer() {
//        timelines.forEach(Timeline::play);
//    }
//
//    public void toggleHitBoxVisibility() {
//        if (hitBoxVisible) {
//            hitBoxVisible = false;
//            HitBoxManager.clearCanvas(hitBoxGC);
//        } else {
//            hitBoxVisible = true;
//        }
//    }
//}

