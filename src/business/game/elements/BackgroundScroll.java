package business.game.elements;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class BackgroundScroll {
    private static final int IMAGE_WIDTH = 1920;
    
    private ImageView[] imageViews = new ImageView[2];
    private double x = 0;
    private Pane root;
    private AnimationTimer timer;

    public BackgroundScroll(Pane root) {
        this.root = root;
        start();
    }

    public void start() {

        Image[] images = new Image[2];
        images[0] = new Image(getClass().getResourceAsStream("/assets/images/mountains-foreground-1.png"));
        images[1] = new Image(getClass().getResourceAsStream("/assets/images/mountain-background-1.png"));
        

        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i] = new ImageView(images[i]);
            imageViews[i].setX(IMAGE_WIDTH * i);
            if(i >= 2) {
            	imageViews[i].setY(imageViews[i].getY()+72);
            }
            root.getChildren().add(imageViews[i]);
        }


        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                x -= 0.5;
                for (int i = 0; i < imageViews.length; i++) {
                    double newX = x + IMAGE_WIDTH * i;

                    while (newX < -IMAGE_WIDTH) {

                        newX += IMAGE_WIDTH * imageViews.length;
                    }
                    imageViews[i].setX(newX);
                }
            }
        };
        timer.start();
    }
    
    public void stopTimer() {
    	timer.stop();
    }
    
    public void startTimer() {
    	timer.start();
    }
    
}