package controller.uicomponents;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import business.game.elements.ArlieController;
import business.game.elements.Balloon;
import business.game.elements.Tree;
import controller.InGameViewController;

public class ObstacleGenerator {

    private int timer;
    private int interval;
    private double groundY;
    private Random random;
    private Scene scene;
    private Pane gamePane;
    private ArlieController arlieController;
    private InGameViewController inGameViewController;
    private List<AnimationTimer> timers;

    public ObstacleGenerator(Pane gamePane, Scene scene, ArlieController arlieController, InGameViewController inGameViewController) {
        this.inGameViewController = inGameViewController;
        this.gamePane = gamePane;
        this.scene = scene;
        this.arlieController = arlieController;
        this.timer = 0;
        this.interval = 60;
        this.random = new Random();
        this.timers = new ArrayList<>();
    }

    public void setGround(double groundY) {
        this.groundY = groundY;
    }

    public void update() {
        if (timer == interval) {
            timer = 0;
            if (random.nextInt(2) == 1) {
                generateBalloon();
            } else {
                generateTree();
            }
        } else {
            timer++;
        }
    }

    private void generateTree() {
        int treeType = random.nextInt(3) + 1;
        double fitHeight = random.nextInt(150) + 200;

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

    private AnimationTimer createAnimationTimer(ImageView obstacle, String obstacleType) {
        return new AnimationTimer() {
            @Override
            public void handle(long now) {
                double speed = 5.0;
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
        if (CollisionChecker.checkCollision(arlieController.getArlieBody(), obstacle, obstacleType)) {
            inGameViewController.arlieCollided();
            System.out.println("Arlie Collided!");
        }
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
}
