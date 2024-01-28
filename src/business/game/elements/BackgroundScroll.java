package business.game.elements;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class BackgroundScroll {
    private static final int IMAGE_WIDTH = 1920;
    
    private ImageView[] imageViewsBackground = new ImageView[2];
    private ImageView[] imageViewsForeground = new ImageView[2];
    
    private double x = 0;
    private double y = 0;
    private Pane root;
    private AnimationTimer timer;

    public BackgroundScroll(Pane root) {
        this.root = root;
        start();
    }

    public void start() {
    	
        Image[] imagesBackground = new Image[2];
        imagesBackground[0] = new Image(getClass().getResourceAsStream("/assets/images/mountain-background-1.png"));
        imagesBackground[1] = new Image(getClass().getResourceAsStream("/assets/images/mountain-background-1.png"));

        Image[] imagesForeground = new Image[2];
        imagesForeground[0] = new Image(getClass().getResourceAsStream("/assets/images/mountains-foreground-1.png"));
        imagesForeground[1] = new Image(getClass().getResourceAsStream("/assets/images/mountains-foreground-1.png"));
        
   
        for (int i = 0; i < imageViewsBackground.length; i++) {
        	imageViewsBackground[i] = new ImageView(imagesBackground[i]);
        	imageViewsBackground[i].setX(IMAGE_WIDTH * i);
            if(i >= 2) {
            	imageViewsBackground[i].setY(imageViewsBackground[i].getY()+72);
            }
            root.getChildren().add(imageViewsBackground[i]);
        }

        for (int i = 0; i < imageViewsForeground.length; i++) {
        	imageViewsForeground[i] = new ImageView(imagesForeground[i]);
        	imageViewsForeground[i].setX(IMAGE_WIDTH * i);
            if(i >= 2) {
            	imageViewsForeground[i].setY(imageViewsForeground[i].getY()+72);
            }
            root.getChildren().add(imageViewsForeground[i]);
        }
        
       


        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            	 y -= 0.5;
                 for (int i = 0; i < imageViewsBackground.length; i++) {
                     double newX = y + IMAGE_WIDTH * i;

                     while (newX < -IMAGE_WIDTH) {

                         newX += IMAGE_WIDTH * imageViewsBackground.length;
                     }
                     imageViewsBackground[i].setX(newX);
                 }
            	
                x -= 1;
                for (int i = 0; i < imageViewsForeground.length; i++) {
                    double newX = x + IMAGE_WIDTH * i;

                    while (newX < -IMAGE_WIDTH) {

                        newX += IMAGE_WIDTH * imageViewsForeground.length;
                    }
                    imageViewsForeground[i].setX(newX);
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